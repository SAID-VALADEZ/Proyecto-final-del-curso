package com.example.projectnotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.projectnotes.R;
import com.example.projectnotes.componentBd.ComponentNotes;

/**
 * Pantalla Splash
 */
public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    public static String nameActivity = "SplashActivity";

    /**
     * Se carga la interfaz del activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        ComponentNotes componentNotes = new ComponentNotes(this);

        //Leemos el usuario de la BDD
        //Si no hay un usuario lanzamos RegistryActivity
        //En caso contrario lanzamos MainActivity
        if (componentNotes.readUsers() == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, RegistryActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }
}
