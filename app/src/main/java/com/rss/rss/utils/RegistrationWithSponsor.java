package com.rss.rss.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class RegistrationWithSponsor {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public RegistrationWithSponsor(Context context) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
    }

    public void setEmail(String email){
        editor.putString("email",email).apply();
    }
    public String getEmail(){
        return prefs.getString("email","");
    }

    public void setName(String name){
        editor.putString("name",name).apply();
    }
    public String getName(){
        return prefs.getString("name","");
    }

    public void setUserName(String user_name){
        editor.putString("user_name",user_name).apply();
    }
    public String getUserName(){
        return prefs.getString("user_name","");
    }

    public void setPhone(String phone){
        editor.putString("phone",phone).apply();
    }
    public String getPhone(){
        return prefs.getString("phone","");
    }

    public void setBirthday(String birthday){
        editor.putString("birthday",birthday).apply();
    }
    public String getBirthday(){
        return prefs.getString("birthday","");
    }

    /*public void set(String birthday){
        editor.putString("birthday",birthday).apply();
    }
    public String getBirthday(){
        return prefs.getString("birthday","");
    }*/


}
