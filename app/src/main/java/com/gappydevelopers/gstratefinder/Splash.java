package com.gappydevelopers.gstratefinder;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    static int SPLASH_TIME_OUT = 2000;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        userLocalStore = new UserLocalStore(this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(userLocalStore.getuserloggedIn()) {
                    if (userLocalStore.getLanguage().equals("hindi")) {
                        Intent i = new Intent(Splash.this, HindiHome.class);
                        startActivity(i);
                    }else if (userLocalStore.getLanguage().equals("english")){
                        Intent i = new Intent(Splash.this, EnglishHome.class);
                        startActivity(i);
                    }
                } else {
                    Intent i = new Intent(Splash.this,SelectLanguage.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
