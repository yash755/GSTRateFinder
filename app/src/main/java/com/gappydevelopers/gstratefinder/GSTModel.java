package com.gappydevelopers.gstratefinder;

/**
 * Created by yash on 8/7/17.
 */

public class GSTModel {

    String product_type,product_name,product_code,product_rate,product_image;

    public GSTModel (String product_type,String product_name,String product_code,String product_rate,String product_image){
        this.product_type = product_type;
        this.product_name = product_name;
        this.product_code = product_code;
        this.product_rate = product_rate;
        this.product_image= product_image;
    }

    public String getProduct_type(){
        return product_type;
    }

    public String getProduct_name(){
        return product_name;
    }

    public String getProduct_code(){
        return product_code;
    }

    public String getProduct_rate(){
        return product_rate;
    }

    public String getProduct_image(){
        return product_image;
    }
}
