package com.gappydevelopers.gstratefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLanguage extends AppCompatActivity implements View.OnClickListener{

    Button hindi,english;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        userLocalStore = new UserLocalStore(this);

        hindi = (Button)findViewById(R.id.hindi);
        english = (Button)findViewById(R.id.english);

        hindi.setOnClickListener(this);
        english.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == hindi){
            userLocalStore.userData("hindi");
            userLocalStore.setUserloggedIn(true);
            Intent i = new Intent(SelectLanguage.this,HindiHome.class);
            startActivity(i);
        }else if (view == english){
            userLocalStore.userData("english");
            userLocalStore.setUserloggedIn(true);
            Intent i = new Intent(SelectLanguage.this,EnglishHome.class);
            startActivity(i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (userLocalStore.getuserloggedIn())
            startActivity(new Intent(this,HindiHome.class));
    }
}
