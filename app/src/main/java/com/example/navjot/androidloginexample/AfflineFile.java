package com.example.navjot.androidloginexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Navjot on 11/20/2017.
 */

public class AfflineFile extends AppCompatActivity
{
    ImageView img;
    TextView name,email,birthday;
    SaveFbLogin saveFbLogin = new SaveFbLogin(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofileoffline);
        name=findViewById(R.id.name);
        email = findViewById(R.id.email);
        img = findViewById(R.id.dp);
        birthday = findViewById(R.id.birthday);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        name.setText(sharedPreferences.getString("Name",null));
        email.setText(sharedPreferences.getString("Email",null));
        birthday.setText(sharedPreferences.getString("Birthday",null));
        Picasso.with(this).load(sharedPreferences.getString("ImageUrl",null)).into(img);

    }
}
