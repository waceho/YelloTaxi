package com.amanciodrp.yellotaxi.driveractivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.utils.GoogleServiceSingleton;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DriverSettingsActivity extends AppCompatActivity {

    private final String TAG = DriverSettingsActivity.class.getSimpleName();
    private TextInputEditText mNameField, mPhoneField, mCarField;

    private ImageView mProfileImage;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mDriverDatabase;
    private GoogleServiceSingleton googleServiceSingleton = GoogleServiceSingleton.getInstance();

    private String userID;
    private String mName;
    private String mPhone;
    private String mCar;
    private String mService;
    private String mProfileImageUrl;

    private Uri resultUri;

    private RadioGroup mRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_settings);

        mNameField = findViewById(R.id.name);
        mPhoneField = findViewById(R.id.phone);
        mCarField = findViewById(R.id.car);

        mProfileImage = findViewById(R.id.profileImage);

        mRadioGroup = findViewById(R.id.radioGroup);

        ImageView mBack = findViewById(R.id.back);
        AppCompatButton mConfirm = findViewById(R.id.confirm);

        mAuth = FirebaseAuth.getInstance();

        userID = mAuth.getCurrentUser().getUid();

        mDriverDatabase = googleServiceSingleton.getDriverDatabase(userID);

        getUserInfo();

        mProfileImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });

        mConfirm.setOnClickListener(v -> saveUserInformation());

        mBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void getUserInfo() {

        mDriverDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    try {
                        if (map.get("name") != null) {
                            mName = map.get("name").toString();
                            mNameField.setText(mName);
                        }

                        if (map.get("phone") != null) {
                            mPhone = map.get("phone").toString();
                            mPhoneField.setText(mPhone);
                        }

                        if (map.get("car") != null) {
                            mCar = map.get("car").toString();
                            mCarField.setText(mCar);
                        }
                        if (map.get("service") != null) {
                            mService = map.get("service").toString();
                            switch (mService) {
                                case "Taxi":
                                    mRadioGroup.check(R.id.UberX);
                                    break;
                                case "Particulier":
                                    mRadioGroup.check(R.id.UberBlack);
                                    break;
                            }
                        }
                        if (map.get("profileImageUrl") != null) {
                            mProfileImageUrl = map.get("profileImageUrl").toString();
                            Glide.with(getApplication()).load(mProfileImageUrl).into(mProfileImage);
                            Log.d("DriverSettings url", mProfileImageUrl);
                        }
                    } catch (Exception e){
                        Log.d(TAG, e.getMessage());
                    }

                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {
                Log.e(TAG, databaseError.getDetails());
            }
        });
    }

    /**
     * save user information in firebase database
     */
    private void saveUserInformation() {
        mName = mNameField.getText().toString();
        mPhone = mPhoneField.getText().toString();
        mCar = mCarField.getText().toString();

        int selectId = mRadioGroup.getCheckedRadioButtonId();

        final RadioButton radioButton = findViewById(selectId);

        if (radioButton.getText() == null) {
            return;
        }

        mService = radioButton.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("name", mName);
        userInfo.put("phone", mPhone);
        userInfo.put("car", mCar);
        userInfo.put("service", mService);
        Log.d(TAG, mPhone);
        mDriverDatabase.updateChildren(userInfo);

        if (resultUri != null) {

            StorageReference filePath = FirebaseStorage.getInstance().getReference().child("profile_images").child(userID);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filePath.putBytes(data);

            uploadTask.addOnFailureListener(e -> {
                finish();
            });
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                Map newImage = new HashMap();
                newImage.put("profileImageUrl", downloadUrl.toString());
                mDriverDatabase.updateChildren(newImage);
                finish();
            });
        } else {
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            mProfileImage.setImageURI(resultUri);
        }
    }
}
