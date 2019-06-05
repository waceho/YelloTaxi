package com.amanciodrp.yellotaxi.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.customeractivity.CustomerLoginActivity;
import com.amanciodrp.yellotaxi.driveractivity.DriverLoginActivity;
import com.amanciodrp.yellotaxi.model.DefaultUseSettings;

import androidx.lifecycle.MutableLiveData;

public class UtilityKit {

    private static String TAG = UtilityKit.class.getSimpleName();

    private UtilityKit() {
        // for sonar
    }

    public static void saveMode(Context context, Class<DefaultUseSettings> object) {
        SharedPrefsObject.saveObjectToSharedPreference(context, object.getSimpleName(), object.getSimpleName(), object);
    }

    public static MutableLiveData<String> getCountryZipCode(Context context) {
        MutableLiveData<String> countryZipCode = new MutableLiveData<>();
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //getNetworkCountryIso
        String countryId = manager.getSimCountryIso().toUpperCase();
        String[] rl = context.getResources().getStringArray(R.array.DialingCountryCode);
        for (String aRl : rl) {
            String[] g = aRl.split(",");
            countryZipCode.setValue(g[1].trim().equals(countryId.trim()) ? g[0] : "");
        }
        return countryZipCode;
    }

    public static MutableLiveData<String> getPhoneNumber(Context mContext) {
        MutableLiveData<String> phoneNumber = new MutableLiveData<>();
        TelephonyManager tMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        phoneNumber.setValue(tMgr.getLine1Number());
        return phoneNumber;
    }

    public static void launchHome(Activity activity, DefaultUseSettings defaultUseSettings) {
        if (defaultUseSettings.getMode().equals(activity.getString(R.string.passager)))
            activity.startActivity(new Intent(activity, CustomerLoginActivity.class));
        else
            activity.startActivity(new Intent(activity, DriverLoginActivity.class));
    }

    public static void openActivity(Context context, Class activity){
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    public static void showAlert(Context context, String title, String msg){
        try {
            new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert)
                    .setTitle(title)
                    .setIcon(R.drawable.ic_logo)
                    .setMessage(msg)
                    .setPositiveButton("Ok", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .create()
                    .show();
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }


    }


}
