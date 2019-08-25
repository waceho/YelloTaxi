package com.amanciodrp.yellotaxi.utils;

import android.content.Context;

import com.amanciodrp.yellotaxi.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.annotation.Nonnull;

public class GoogleServiceSingleton {

    private static final GoogleServiceSingleton ourInstance = new GoogleServiceSingleton();

    public static GoogleServiceSingleton getInstance() {
        return ourInstance;
    }

    private GoogleServiceSingleton() {
    }

    private GoogleSignInOptions getGoogleSignInOptions(Context context){
        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.web_api_key))
                .requestEmail()
                .build();
        // [END configure_signin]

        return gso;
    }

    public GoogleSignInClient getGoogleSigninClient(Context context){
        // [START build_client]
        // Build a GoogleSignInClient with the options specified by gso.
        return GoogleSignIn.getClient(context, getGoogleSignInOptions(context));
        // [END build_client]
    }

    public AuthCredential getAuthCredential(GoogleSignInAccount account){
        return GoogleAuthProvider.getCredential(account.getIdToken(), null);
    }

    public DatabaseReference getDriverDatabase(String driverID){
        return FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(driverID);
    }

    public DatabaseReference getCustomerDatabase(String customerId){
        return FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(customerId);
    }

    public DatabaseReference getDriverAvalaible(){
        return FirebaseDatabase.getInstance().getReference("driversAvailable");
    }

    public DatabaseReference getDriverWorking(){
        return FirebaseDatabase.getInstance().getReference("driversWorking");
    }

    public DatabaseReference getcustomerReQuest(@Nonnull String customerId){
        return FirebaseDatabase.getInstance().getReference().child("customerRequest").child(customerId);
    }
}
