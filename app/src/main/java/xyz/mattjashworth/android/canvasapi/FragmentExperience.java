package xyz.mattjashworth.android.canvasapi;

/**
 * Created by mattjashworth on 07/05/2018.
 * For canvasAPI.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.ParseUser;

import java.io.File;


public class FragmentExperience extends AppCompatActivity {

    private TextView mTextMessage;

    public static String PACKAGE_NAME;
    public final static String EXTRA_ORIENTATION = "EXTRA_ORIENTATION";
    public final static String EXTRA_WITH_LINE_PADDING = "EXTRA_WITH_LINE_PADDING";
    public static int EXTRA_TIMELINE = 1;


    static Context mContext;


    //Auth
    String username;
    Boolean accountType;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.navigation_schedule:
                        fragmentTransaction.replace(R.id.content, new ScheduleFragment()).commit();
                        return true;
                    case R.id.navigation_maps:
                        fragmentTransaction.replace(R.id.content, new MapsFragment()).commit();
                        return true;
                    case R.id.navigation_updates:
                        fragmentTransaction.replace(R.id.content, new UpdatesFragment()).commit();
                        return true;
                    case R.id.navigation_logout:
                        //Logout
                        ParseUser.logOut();
                        Intent logoutIntent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(logoutIntent);
                        //Close network connections
                        //Disband auth
                        return true;
                }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fexperience);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Background animation
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.container_bg);
        AnimationDrawable animationDrawable;
        animationDrawable =(AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        PACKAGE_NAME = getApplicationContext().getPackageName();

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        accountType = intent.getBooleanExtra("type", false);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (accountType == false) {
            fragmentTransaction.replace(R.id.content, new ScheduleFragment()).commit();
        }
        if (accountType == true) {
            //Do Something
        }


    }

    @Override
    public void onResume() {
        super.onResume();

        //moveTaskToBack(true);
    }

  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }*/


    public static Context getContext() {
        //  return instance.getApplicationContext();
        return mContext;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_BACK:

                return true;
        }
        return false;
    }


    public String getPackageNameString() {
        return PACKAGE_NAME;
    }



}
