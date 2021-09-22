package com.rss.rss.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

public class StoreAdListPrefs {


    public static final String STORE_FILE_NAME = "daily_ad_list";
    public Context context;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public StoreAdListPrefs(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(STORE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public <T> void setList(String key, List<Integer> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }

    public static void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }
}
