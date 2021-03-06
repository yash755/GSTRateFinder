package com.gappydevelopers.gstratefinder;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class EnglishHome extends AppCompatActivity implements View.OnClickListener{

    CardView name,code,rate,all;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userLocalStore = new UserLocalStore(this);

        name = (CardView)findViewById(R.id.name);
        code = (CardView)findViewById(R.id.code);
        rate = (CardView)findViewById(R.id.rate);
        all = (CardView)findViewById(R.id.all);

        name.setOnClickListener(this);
        code.setOnClickListener(this);
        rate.setOnClickListener(this);
        all.setOnClickListener(this);

        YoYo.with(Techniques.BounceInUp)
                .duration(3000)
                .repeat(1)
                .playOn(findViewById(R.id.name));

        YoYo.with(Techniques.BounceInUp)
                .duration(3000)
                .repeat(1)
                .playOn(findViewById(R.id.code));

        YoYo.with(Techniques.BounceInUp)
                .duration(3000)
                .repeat(1)
                .playOn(findViewById(R.id.rate));

        YoYo.with(Techniques.BounceInUp)
                .duration(3000)
                .repeat(1)
                .playOn(findViewById(R.id.all));
    }

    @Override
    public void onClick(View view) {

        if (view == name){
            Intent i = new Intent(EnglishHome.this, DescriptionDataDisplay.class);
            startActivity(i);
        }else if (view == code){
            Intent i = new Intent(EnglishHome.this, CodeDataDisplay.class);
            startActivity(i);
        }else if (view == rate){
            Intent i = new Intent(EnglishHome.this, RateDataDisplay.class);
            startActivity(i);
        }else if(view == all){
            Intent i = new Intent(EnglishHome.this, All.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Very effective and great app.\n\n";
                shareBody = shareBody + "https://play.google.com/store/apps/details?id=com.gappydevelopers.gstratefinder \n\n";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "GST Rate Finder");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;
            case R.id.rate:
                Uri uri = Uri.parse("market://details?id=com.gappydevelopers.gstratefinder");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=com.gappydevelopers.gstratefinder")));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!userLocalStore.getuserloggedIn())
            startActivity(new Intent(this,SelectLanguage.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



}
