package xyz.mattjashworth.android.canvasapi;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class SplashActivity extends Activity {


    public static final String MY_PREFS_NAME = "UserSessionData";
    String baseUsername;
    String baseFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        //Retrieve session
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        baseUsername = prefs.getString("username", "a");
        baseFullName = prefs.getString("fullname", "b");

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            //Advance to app
            Intent toAppHome = new Intent(getApplicationContext(), FragmentExperience.class);
            startActivity(toAppHome);
        } else {
            //Advance to login
            //Do Nothing
        }



        final Button getStarted = (Button) findViewById(R.id.btn_get_started);
        Button postLogin = (Button) findViewById(R.id.btn_login);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAbout();
            }
        });

        postLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(toLogin);
            }
        });

    }

    public String getBaseUsername() {
        return baseUsername;
    }

    public String getBaseFullName() {
        return baseFullName;
    }


    private void showAbout() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(getString(R.string.about_title));
        builder1.setMessage(getString(R.string.about_message));

        builder1.setPositiveButton(
                "Continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog aboutMessage = builder1.create();
        aboutMessage.show();

    }
}
