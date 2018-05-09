package xyz.mattjashworth.android.canvasapi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Created by mattjashworth on 07/05/2018.
 * For University of Hull Tour Guide Manager
 * Originally CanvasAPI.
 */

public class LoginActivity extends AppCompatActivity  {


    Context context;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Background Animation
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.login_bg);
        AnimationDrawable animationDrawable;
        animationDrawable =(AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        context = getApplicationContext();

        final EditText user = (EditText) findViewById(R.id.et_username);
        final EditText pass = (EditText) findViewById(R.id.et_password);
        final Button login = (Button) findViewById(R.id.btn_login);

        setupProgressDialog();

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Show progress dialog
                progressDialog.show();
                //Do something
                final String strUsername = user.getText().toString();
                final String strPassword = pass.getText().toString();

                final String data = "username=" + strUsername + "&" + "password=" + strPassword;
                final String loginURI = "https://identify.hull.ac.uk/idp/fAIXr_zKeKX/resumeSAML20/idp/startSSO.ping";

                //Parse auth
                ParseUser.logInInBackground(strUsername, strPassword, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            progressDialog.dismiss();
                            Boolean type = parseUser.getBoolean("organiser");
                            Toast.makeText(getApplicationContext(), "Welcome " + strUsername, Toast.LENGTH_LONG).show();
                            if (ParseUser.getCurrentUser().getBoolean("organiser") == true) {
                                Intent postOrganiser = new Intent(getApplicationContext(), FragmentExperienceOrganiser.class);
                                startActivity(postOrganiser);
                            } if (ParseUser.getCurrentUser().getBoolean("organiser") == false) {
                                Intent postTourGuide = new Intent(getApplicationContext(), FragmentExperience.class);
                                startActivity(postTourGuide);
                            }

                        } else {
                            ParseUser.logOut();
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }

    private void setupProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait....");
        progressDialog.setMessage("Logging in, please wait...");
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(true);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_BACK:

                Intent toSplash = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(toSplash);

                return true;
        }
        return false;
    }



}