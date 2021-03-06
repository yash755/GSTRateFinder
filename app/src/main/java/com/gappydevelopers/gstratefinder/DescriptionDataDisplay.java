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

public class DescriptionDataDisplay extends AppCompatActivity {

    ArrayList<GSTModel> gstModelArrayList;
    ArrayList<GSTModel> temporary;
    ListView listView;
    GSTListAdapter gstListAdapter;
    UserLocalStore userLocalStore;
    ArrayAdapter<String> adapter;
    final ArrayList<String> product_type = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        userLocalStore = new UserLocalStore(this);
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

        if(userLocalStore.getLanguage().equals("english")) {
            InputStream is = am.open("data.txt");

            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] result = line.split("==");

                gstModelArrayList.add(new GSTModel(result[3], result[1], result[2], result[0],"No Image"));
                product_type.add(result[1]);
            }

            br.close();
        }else if (userLocalStore.getLanguage().equals("hindi")) {

            InputStream is = am.open("hindi.txt");

            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] result = line.split("=");
                System.out.println(result[0] + result[1]);
                String product = result[1];
                String[] products = product.split("-");
                gstModelArrayList.add(new GSTModel("उत्पाद", result[1], "No Product", result[0], "No Image"));
                product_type.add(result[1]);

            }

            br.close();
        }

        adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,product_type);

        final AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        actv.setThreshold(3);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);


        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                Toast.makeText(DescriptionDataDisplay.this," selected", Toast.LENGTH_LONG).show();
                System.out.println(parent.getItemAtPosition(pos));
                actv.setText("");

                if (temporary.size() >0)
                    temporary.clear();
                int i = 0;
                while (i < gstModelArrayList.size()) {
                    if (parent.getItemAtPosition(pos).equals(gstModelArrayList.get(i).getProduct_name())) {
                        temporary.add(gstModelArrayList.get(i));
                    }
                    i = i + 1;
                }
                gstListAdapter =new GSTListAdapter(temporary,context);
                listView.setAdapter(gstListAdapter);

            }
        });

    }
}
