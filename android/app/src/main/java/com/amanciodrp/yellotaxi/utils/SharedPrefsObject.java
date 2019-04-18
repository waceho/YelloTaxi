package com.amanciodrp.yellotaxi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by francoisbonnin2 on 15/05/2017.
 *
 * Class which allows to easily save objects to the shared preferences
 *
 * Usage: assume SampleClass exists
 *   SampleClass mObject = new SampleObject();
 *
 *   Then to store an object: saveObjectToSharedPreference(context, "mPreference", "mObjectKey", mObject);
 *   And to retrieve it: mObject = getSavedObjectFromPreference(context, "mPreference", "mObjectKey", SampleClass.class);
 *
 */

public class SharedPrefsObject {

    private SharedPrefsObject() {
        // for sonar
    }

    public static void saveObjectToSharedPreference(Context context, String preferenceFileName, String serializedObjectKey, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String preferenceFileName, String preferenceKey, Class<GenericClass> classType) {
        if (null != context){
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
            if (sharedPreferences.contains(preferenceKey)) {
                final Gson gson = new Gson();
                return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
            }
        }

        return null;
    }

}
