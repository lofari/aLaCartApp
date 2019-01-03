package com.example.lorenzo.alacarta;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SPManager {
    public static final String TAG = "tu_vieja";
    public static final String ALACARTE_SHARED_PREFERENCES = "bla";
    private SharedPreferences sharedPref;

    public SPManager (Context context) {
        sharedPref = context.getSharedPreferences(ALACARTE_SHARED_PREFERENCES,context.MODE_PRIVATE);
        Log.d(TAG, "onCreate: "+ sharedPref.getAll().toString());
    }

    public void saveSharedPref(String username){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", username );
        editor.apply();

    }

    public Boolean validateCredentials (String username, String password){
        if(username.toLowerCase().equals("loco") && password.equals("123")){
            saveSharedPref(username);
            return true;
        }
        return false;
    }

    public boolean sessionExists(){
        Log.d(TAG, "sessionExists:"+ sharedPref.getAll().toString());
        return sharedPref.contains("username");
    }

    public void deleteSharedPref(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        Log.d(TAG, "deleteSharedPref: " + sharedPref.getAll().toString());
    }
}
