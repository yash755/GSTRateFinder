package com.gappydevelopers.gstratefinder;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class All extends AppCompatActivity {

    ArrayList<GSTModel> gstModelArrayList;
    ListView listView;
    GSTListAdapter gstListAdapter;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        userLocalStore = new UserLocalStore(this);
        listView=(ListView)findViewById(R.id.gstlist);

        gstModelArrayList = new ArrayList<>();

        try {
            readFile1(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile1(final Context context) throws IOException {
        AssetManager am = context.getAssets();

        if (userLocalStore.getLanguage().equals("english")) {

            InputStream is = am.open("data.txt");
            final ArrayList<String> product_type = new ArrayList<>();
            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] result = line.split("==");

                gstModelArrayList.add(new GSTModel(result[3], result[1], result[2], result[0],"No Image"));
                product_type.add(result[2]);
            }

            br.close();

            gstListAdapter = new GSTListAdapter(gstModelArrayList, context);
            listView.setAdapter(gstListAdapter);
        } else if (userLocalStore.getLanguage().equals("hindi")) {

            InputStream is = am.open("hindi.txt");

            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] result = line.split("=");
                System.out.println(result[0] + result[1]);
                String product = result[1];
                String[] products = product.split("-");
                gstModelArrayList.add(new GSTModel("उत्पाद", products[0], "No Product", result[0], "No Image"));

            }

            br.close();

            gstListAdapter = new GSTListAdapter(gstModelArrayList, context);
            listView.setAdapter(gstListAdapter);
        }

    }
}

