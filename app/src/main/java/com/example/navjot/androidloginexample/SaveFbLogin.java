package com.example.navjot.androidloginexample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.share.widget.ShareDialog;

import java.util.Map;
import java.util.Set;

/**
 * Created by Navjot on 11/20/2017.
 */

public class SaveFbLogin
{
    private Activity activity ;
    public SaveFbLogin(Activity activity)
    {
        this.activity = activity;
    }
   //SharedPreferences sharedPreferences ;
    public  void saveAcesToken(String token)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token",token);
        editor.apply();
    }
    public String getToken()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return sharedPreferences.getString("Token",null);
    }

    public void clearTokeb()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public  void saveFbInfo(String name,String email,String birthday,String imageUrl)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name",name);
        editor.putString("Email",email);
        editor.putString("Birthday",birthday);
        editor.putString("ImageUrl",imageUrl);
        editor.apply();
        Log.d("LoginInfo",sharedPreferences.getString("Name",null)+"\nEmail : "+sharedPreferences.getString("Email",null));
    }
    public String getAll()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return sharedPreferences.getString("Name",null)+"\nEmail :"+sharedPreferences.getString("Email",null);
    }

}
