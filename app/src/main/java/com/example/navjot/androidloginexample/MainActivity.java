package com.example.navjot.androidloginexample;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity
{
    private LoginButton loginButton;
    CallbackManager callBackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callBackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","username");
        loginButton.registerCallback(callBackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
               Toast.makeText(getApplicationContext(),loginResult.getAccessToken().toString(),Toast.LENGTH_LONG).show();
               getUserDetails(loginResult);
            }

            @Override
            public void onCancel()
            {

            }

            @Override
            public void onError(FacebookException error)
            {
                Toast.makeText(getApplicationContext(),"Error to get Result try again later",Toast.LENGTH_LONG).show();
            }
        });

      try {
            printHashKey();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    protected  void getUserDetails(LoginResult loginResult)
    {
        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
              Intent intent=new Intent(MainActivity.this,UserProfile.class);
              intent.putExtra("userProfile",object.toString());
              startActivity(intent);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields","id,name,email,picture.width(120).height(120)");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        callBackManager.onActivityResult(requestCode,resultCode,data);
    }
    public void printHashKey() throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        PackageInfo info = getPackageManager().getPackageInfo("com.example.navjot.androidloginexample", PackageManager.GET_SIGNATURES);
        for(Signature signature:info.signatures)
        {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            Log.d("keyhash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
        }
    }
}
