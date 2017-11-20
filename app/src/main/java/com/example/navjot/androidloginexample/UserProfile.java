package com.example.navjot.androidloginexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Navjot on 11/18/2017.
 */

public class UserProfile extends AppCompatActivity
{
 JSONObject jsonObject ,profilePicData,profilePicUrl;
    ImageView img;
    TextView name,email,birthday;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        SaveFbLogin saveFbLogin = new SaveFbLogin(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        Intent intent =getIntent();
        String jsonData = intent.getStringExtra("userProfile");
        Log.w("jsonData",jsonData);
        name=findViewById(R.id.name);
        email = findViewById(R.id.email);
        img = findViewById(R.id.dp);
        birthday = findViewById(R.id.birthday);
        try
        {
            jsonObject = new JSONObject(jsonData);
            name.setText(jsonObject.get("name").toString());
            email.setText(jsonObject.get("email").toString());
            birthday.setText(jsonObject.get("birthday").toString());
            profilePicData=new JSONObject(jsonObject.get("picture").toString());
            profilePicUrl=new JSONObject(profilePicData.getString("data"));
            String imageUrl = profilePicUrl.getString("url");
            Picasso.with(this).load(profilePicUrl.getString("url")).into(img);
            saveFbLogin.saveFbInfo(jsonObject.get("name").toString(),jsonObject.get("email").toString(),jsonObject.get("birthday").toString(),imageUrl);

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
