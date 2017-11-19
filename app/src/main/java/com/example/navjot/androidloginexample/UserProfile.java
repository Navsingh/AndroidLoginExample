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
    TextView name,email,id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        Intent intent =getIntent();
        String jsonData = intent.getStringExtra("userProfile");
        Log.w("jsonData",jsonData);
        name=findViewById(R.id.name);
        email = findViewById(R.id.email);
        img = findViewById(R.id.dp);
        try
        {
            jsonObject = new JSONObject(jsonData);
            name.setText(jsonObject.get("name").toString());
            email.setText(jsonObject.get("email").toString());
            profilePicData=new JSONObject(jsonObject.get("picture").toString());
            profilePicUrl=new JSONObject(profilePicData.getString("data"));
            Picasso.with(this).load(profilePicUrl.getString("url")).into(img);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
