package com.gappydevelopers.gstratefinder;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.gappydevelopers.gstratefinder.R.id.image;
import static com.gappydevelopers.gstratefinder.R.id.product_code;
import static com.gappydevelopers.gstratefinder.R.id.product_description;
import static com.gappydevelopers.gstratefinder.R.id.product_rate;
import static com.gappydevelopers.gstratefinder.R.id.product_type;


public class GSTListAdapter extends ArrayAdapter<GSTModel> {
    private ArrayList<GSTModel> dataSet;
    Context mContext;
    public ImageLoader imageLoader;

    // View lookup cache
    private static class ViewHolder {
        TextView prodcut_type,product_code,product_rate,product_description;
        ImageView image;

    }

    public GSTListAdapter(ArrayList<GSTModel> data, Context context) {
        super(context, R.layout.gstlist, data);
        this.dataSet = data;
        this.mContext = context;
        imageLoader = new ImageLoader(context);
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final GSTModel gstModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.gstlist, parent, false);
            viewHolder.prodcut_type = (TextView)convertView.findViewById(product_type);
            viewHolder.product_description = (TextView) convertView.findViewById(product_description);
            viewHolder.product_code = (TextView) convertView.findViewById(product_code);
            viewHolder.product_rate = (TextView) convertView.findViewById(product_rate);
            viewHolder.image = (ImageView)convertView.findViewById(image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        lastPosition = position;

        viewHolder.prodcut_type.setText(gstModel.getProduct_type());
        viewHolder.product_description.setText(gstModel.getProduct_name());
        viewHolder.product_rate.setText(gstModel.getProduct_rate());

        if(gstModel.getProduct_code().equals("No Product")) {
            viewHolder.product_code.setVisibility(View.INVISIBLE);
        }else {
            viewHolder.product_code.setText(gstModel.getProduct_code());
        }


/*        if (!gstModel.getProduct_image().equals("No Image")){*//*
            System.out.println("GSt"+ gstModel.getProduct_image());
            imageLoader.DisplayImage("http://www.hindustantimes.com/interactives/gst-rate-complete-list/img/8508.png", viewHolder.image);*//*
            URL url = null;
            try {
                url = new URL(gstModel.getProduct_image());
                Bitmap bmp = null;
                try {
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    viewHolder.image.setImageBitmap(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }*/





        return convertView;
    }
}
