package com.rss.rss.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public Session(Context context) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
    }

    public void setAuthToken(String token){
        editor.putString("token",token).apply();
    }
    public String getAuthToken(){
        return prefs.getString("token","");
    }

    public void setLoginStatus(boolean loginStatus){
        editor.putBoolean("loginStatus",loginStatus).apply();
    }
    public Boolean getLoginStatus() {
        return prefs.getBoolean("loginStatus",false);
    }
    public void logout(){
        editor.remove("loginStatus");
        editor.apply();
    }

    public void setRememberMe(boolean rememberMe){
        editor.putBoolean("rememberMe",rememberMe).apply();
    }
    public Boolean getRememberMe() {
        return prefs.getBoolean("rememberMe",false);
    }

    public void setUserID(int userID){
        editor.putInt("userID",userID).apply();
    }
    public int getUserID(){
        return prefs.getInt("userID",0);
    }

    public void setSetName(String userName) {
        editor.putString("username", userName).apply();
    }
    public String getUserName() {
        return prefs.getString("username","");
    }

    public void setUserEmail(String userEmail) {
        editor.putString("useremail", userEmail).apply();
    }
    public String getUserEmail() {
        return prefs.getString("useremail","");
    }
}
