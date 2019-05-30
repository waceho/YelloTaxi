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
import com.amanciodrp.yellotaxi.utils.Reachability;
import com.amanciodrp.yellotaxi.utils.SnackbarUtils;
import com.amanciodrp.yellotaxi.utils.UtilityKit;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private String verificationid;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_driver_login);
        mContext = getBaseContext();
        mAuth = FirebaseAuth.getInstance();

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END build_client]

        firebaseAuthListener = firebaseAuth -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                startService(new Intent(DriverLoginActivity.this, OnAppKilled.class));
                Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
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
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d(TAG + "success", String.valueOf(account));
            // Signed in successfully, show authenticated UI.
            // updateUI(account);
            UtilityKit.openActivity(mContext, DriverMapActivity.class);
            finish();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //  updateUI(null);
        }
    }
    // [END handleSignInResult]

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_login:
                googleSignIn();
                break;

            case R.id.phone_login:
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
                loginPopupBinding.inputLayoutCode.setDefaultHintTextColor(getColorStateList(R.color.red));
                loginPopupBinding.inputLayoutCode.setHint(getString(R.string.ask_auth_phone_hint));
                loginPopupBinding.valider.setText(getString(R.string.valider));

                // set onclick listener
                loginPopupBinding.valider.setOnClickListener(v -> {
                    // launch phone verification and code auth callback
                    if (Reachability.isConnected(mContext))
                        startPhoneNumberVerification(getInputedPhone(getCountryCode()));
                    else
                        SnackbarUtils.displayWarning(this, R.string.network_error);
                });

            } else if (ASK_TYPE_CODE.equals(type) && null != loginPopupBinding){
                loginPopupBinding.tvCode.setVisibility(View.GONE);
                loginPopupBinding.tvCode.setText(getCountryCode());
                loginPopupBinding.inputLayoutCode.setDefaultHintTextColor(getColorStateList(R.color.red));
                loginPopupBinding.inputLayoutCode.setHint(getString(R.string.ask_auth_code_hint));
                loginPopupBinding.valider.setText(getString(R.string.confirm_code));

                // set onclick listener
                loginPopupBinding.valider.setOnClickListener(v -> {
                    // launch phone verification and code auth callback
                    if (Reachability.isConnected(mContext))
                        verifyPhoneNumberWithCode(verificationid, loginPopupBinding.edCode.getText().toString());
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

                        FirebaseUser user = task.getResult().getUser();

                        startActivity(new Intent(getApplicationContext(), CustomerMapActivity.class));

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

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
