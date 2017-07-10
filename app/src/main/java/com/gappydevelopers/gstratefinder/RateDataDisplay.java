package com.gappydevelopers.gstratefinder;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RateDataDisplay extends AppCompatActivity {

    String[] tx ={"No GST","0.25%","3%","5%","12%","18%","28%"};
    ArrayList<GSTModel> gstModelArrayList;
    ArrayList<GSTModel> temporary;
    ListView listView;
    GSTListAdapter gstListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_data_display);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.gstlist);

        gstModelArrayList = new ArrayList<>();
        temporary = new ArrayList<>();
        try {
            readFile1(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile1(final Context context) throws IOException {
        AssetManager am = context.getAssets();
        InputStream is = am.open("data.txt");

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] result = line.split("==");

            gstModelArrayList.add(new GSTModel(result[3],result[1],result[2],result[0],result[4]));

        }

        br.close();

        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_text, tx);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(RateDataDisplay.this," selected", Toast.LENGTH_LONG).show();

                if (temporary.size() >0)
                    temporary.clear();
                int i = 0;
                while (i < gstModelArrayList.size()) {
                    if (parentView.getItemAtPosition(position).equals(gstModelArrayList.get(i).getProduct_rate())) {
                        temporary.add(gstModelArrayList.get(i));
                    }
                    i = i + 1;
                }
                gstListAdapter =new GSTListAdapter(temporary,context);
                listView.setAdapter(gstListAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }


}

