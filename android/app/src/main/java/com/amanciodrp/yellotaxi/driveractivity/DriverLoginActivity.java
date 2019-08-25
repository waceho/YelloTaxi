package com.amanciodrp.yellotaxi.driveractivity;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amanciodrp.yellotaxi.OnAppKilled;
import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.customeractivity.CustomerLoginActivity;
import com.amanciodrp.yellotaxi.customeractivity.CustomerMapActivity;
import com.amanciodrp.yellotaxi.databinding.ActivityDriverLoginBinding;
import com.amanciodrp.yellotaxi.databinding.PhoneLoginPopupBinding;
import com.amanciodrp.yellotaxi.model.User;
import com.amanciodrp.yellotaxi.utils.GoogleServiceSingleton;
import com.amanciodrp.yellotaxi.utils.Reachability;
import com.amanciodrp.yellotaxi.utils.SharedPrefsObject;
import com.amanciodrp.yellotaxi.utils.SnackbarUtils;
import com.amanciodrp.yellotaxi.utils.UtilityKit;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.amanciodrp.yellotaxi.utils.AppConstants.ASK_TYPE_CODE;
import static com.amanciodrp.yellotaxi.utils.AppConstants.ASK_TYPE_PHONE;

public class DriverLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private String TAG = DriverLoginActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private ActivityDriverLoginBinding binding;
    private static final int RC_SIGN_IN = 9001;
    private Context mContext;
    private GoogleSignInClient mGoogleSignInClient;
    private AlertDialog builder;
    private DatabaseReference mDriverDatabase;
    private FirebaseUser mUser;
    private String verificationid;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private GoogleServiceSingleton googleServiceSingleton = GoogleServiceSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_driver_login);
        mContext = getBaseContext();
        mAuth = FirebaseAuth.getInstance();

        // [START build_client]
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = googleServiceSingleton.getGoogleSigninClient(mContext);
        // [END build_client]

        firebaseAuthListener = firebaseAuth -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                updateDriverProfile(user, Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
                startService(new Intent(DriverLoginActivity.this, OnAppKilled.class));
                Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        };
    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (null !=  account)
                    firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d(TAG + " success ", String.valueOf(account.getIdToken()));
            // Signed in successfully, show authenticated UI.
            // updateUI(account);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code = " + e.getStatusCode());
            //  updateUI(null);
        }
    }
    // [END handleSignInResult]

    /**
     *
     * @param acct
     */
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle: " + acct.getIdToken());
        Log.d(TAG, "firebaseAuthWithGoogle: " + acct.getDisplayName());

        AuthCredential credential = googleServiceSingleton.getAuthCredential(acct);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        User user = new User(acct.getIdToken());
                        user.setIdToken(acct.getIdToken());
                        SharedPrefsObject.saveObjectToSharedPreference(mContext, User.class.getSimpleName(), User.class.getSimpleName(), user);
                        UtilityKit.openActivity(mContext, DriverMapActivity.class);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_login:
                googleSignIn();
                break;

            case R.id.phone_login:
                if (getCountryCode().equals("+")){
                    UtilityKit.showAlert(this, getString(R.string.app_name), "Votre smartphone semble incompatible avec cette fonctionnalité, veuillez utiliser une autre option d'authentification");
                    return;
                }
                showDialog(ASK_TYPE_PHONE);
                break;
        }
    }

    // [START google account signIn]
    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END google account signIn]

    /**
     *
     * @param type
     */
    private void showDialog(String type) {
        // Get the layout inflater
        try {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.Theme_MaterialComponents_Dialog);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.phone_login_popup, null);
            PhoneLoginPopupBinding loginPopupBinding = DataBindingUtil.bind(view);

            alertDialog
                    .setCancelable(true)
                    .setView(view);
            builder = alertDialog.create();

            if (ASK_TYPE_PHONE.equals(type) && null != loginPopupBinding){
                loginPopupBinding.tvCode.setVisibility(View.VISIBLE);
                loginPopupBinding.tvCode.setText(getCountryCode());
                loginPopupBinding.inputLayoutCode.setHintTextColor(getColorStateList(R.color.red));
                loginPopupBinding.inputLayoutCode.setHint(getString(R.string.ask_auth_phone_hint));
                loginPopupBinding.valider.setText(getString(R.string.valider));

                // set onclick listener
                loginPopupBinding.valider.setOnClickListener(v -> {
                    // launch phone verification and code auth callback
                    if (Reachability.isConnected(mContext)){
                        startPhoneNumberVerification(getInputedPhone(getCountryCode()));
                    }

                    else
                        SnackbarUtils.displayWarning(this, R.string.network_error);
                });

            } else if (ASK_TYPE_CODE.equals(type) && null != loginPopupBinding){
                loginPopupBinding.tvCode.setVisibility(View.GONE);
                loginPopupBinding.tvCode.setText(getCountryCode());
                loginPopupBinding.inputLayoutCode.setHintTextColor(getColorStateList(R.color.red));
                loginPopupBinding.inputLayoutCode.setHint(getString(R.string.ask_auth_code_hint));
                loginPopupBinding.valider.setText(getString(R.string.confirm_code));

                // set onclick listener
                loginPopupBinding.valider.setOnClickListener(v -> {
                    // launch phone verification and code auth callback
                    if (Reachability.isConnected(mContext))
                        verifyPhoneNumberWithCode(verificationid, loginPopupBinding.inputLayoutCode.getText().toString());
                    else
                        SnackbarUtils.displayWarning(this, R.string.network_error);

                });
            }

            // show alert dialog
            alertDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * start phone verification
     * @param phoneNumber
     */
    private void startPhoneNumberVerification(String phoneNumber) {
        Toast.makeText(this, "start login", Toast.LENGTH_SHORT).show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    /**
     * return the current country code
     **/
    private String getCountryCode() {
        return String.format(getString(R.string.countryCodeFormater), UtilityKit.getCountryZipCode(mContext).getValue());
    }

    /**
     * get Phone with Code
     **/
    private String getInputedPhone(String phone) {
        return String.format(getString(R.string.phoneFormater), getCountryCode(), phone);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");

                        FirebaseUser user = Objects.requireNonNull(task.getResult()).getUser();

                        Log.d(TAG, String.valueOf(user));

                        updateDriverProfile(user, Objects.requireNonNull(mAuth.getCurrentUser()).getUid());

                        startActivity(new Intent(getApplicationContext(), DriverMapActivity.class));

                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid

                            //   smsCodeVerificationField.setError("Invalid code.");

                        }

                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
        mAuth.setLanguageCode("fr");

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(mContext, "sucess phone", Toast.LENGTH_LONG).show();
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
                verificationid = s;

                // SHOW CODE DIALOG
                showDialog(ASK_TYPE_CODE);
            }
        };
    }

    /**
     *
     * @param user
     * @param userID
     */
    private void updateDriverProfile(FirebaseUser user, String userID){
        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(userID);

        Map userInfo = new HashMap();
        if (null != user && user.getDisplayName() != null)
            userInfo.put("name", user.getDisplayName());
        if (null != user && null != user.getPhoneNumber())
            userInfo.put("phone", user.getPhoneNumber());
        if (null != user && null != user.getPhotoUrl())
            userInfo.put("profileImageUrl", user.getPhotoUrl().toString());

        Log.d(TAG, "try to update");
        mDriverDatabase.updateChildren(userInfo);
        mDriverDatabase.push();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
