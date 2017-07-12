package com.gappydevelopers.gstratefinder;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserLocalStore {

    public static final String SP_Name = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context)
    {
        userLocalDatabase = context.getSharedPreferences(SP_Name, 0);
    }

    public void userData(String language)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putString("language",language);

        speditor.commit();
    }

    public String getLanguage(){

        String language = userLocalDatabase.getString("language", "");
        return language;
    }

    public void updatedata()
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putString("slidingpanel","1");
        speditor.apply();
    }

    public void updateadd(String id)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        System.out.println("Addupdate" + id);
        speditor.putString("addid",id);
        speditor.apply();
    }

    public String getaddress(){

        String name = userLocalDatabase.getString("addid", "");
        System.out.println("Addget" + name);
        return name;

    }

    public void updatepres(String id)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putString("presid",id);
        speditor.apply();
    }

    public void updatearraypres(ArrayList<String> string){

        Set<String> set = new HashSet<String>();
        set.addAll(string);

        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putStringSet("presarrid",set);
        speditor.apply();
    }

    public Set<String> getarraypres(){

        Set<String> set = userLocalDatabase.getStringSet("presarrid",null);
        //System.out.println("Add"+ name);
        return set;
    }

    public void updateurl(String posturl)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putString("posturl",posturl);
        speditor.apply();
    }

    public String geturl(){

        String name = userLocalDatabase.getString("posturl", "");
        System.out.println("Add"+ name);
        return name;
    }

    public String getpres(){

        String name = userLocalDatabase.getString("presid", "");
        System.out.println("Add"+ name);
        return name;
    }

    public boolean getflag(){

        String name = userLocalDatabase.getString("slidingpanel", "");
        if(name.equals("1"))
            return true;
        else
            return false;

    }

    public String gettoken(){

        String name = userLocalDatabase.getString("token", "");
        System.out.println("Token" + name);
        return name;

    }





    public void setUserloggedIn(boolean loggedIn){
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putBoolean("loggedIn",loggedIn);
        speditor.commit();

    }



    public boolean getuserloggedIn(){

        if(userLocalDatabase.getBoolean("loggedIn",false) == true)
            return true;
        else
            return false;
    }



    public void clearUserdata(){

        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.clear();
        speditor.commit();

    }
}
