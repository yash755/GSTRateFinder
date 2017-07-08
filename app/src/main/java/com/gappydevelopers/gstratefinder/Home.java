package com.gappydevelopers.gstratefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener{

    CardView name,code,rate,all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = (CardView)findViewById(R.id.name);
        code = (CardView)findViewById(R.id.code);
        rate = (CardView)findViewById(R.id.rate);
        all = (CardView)findViewById(R.id.all);

        name.setOnClickListener(this);
        code.setOnClickListener(this);
        rate.setOnClickListener(this);
        all.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == name){
            Intent i = new Intent(Home.this, DescriptionDataDisplay.class);
            startActivity(i);
        }else if (view == code){
            Intent i = new Intent(Home.this, CodeDataDisplay.class);
            startActivity(i);
        }else if (view == rate){
            Intent i = new Intent(Home.this, RateDataDisplay.class);
            startActivity(i);
        }else if(view == all){
            Intent i = new Intent(Home.this, All.class);
            startActivity(i);
        }
    }



}
