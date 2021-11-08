package com.example.courseraweek4;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    public static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    public static final String SEARCH_KEY = "SEARCH_KEY";
    private SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean addSearching(String Browser) {
        mSharedPreferences.edit().putString(SEARCH_KEY, Browser).apply();
        return true;
    }

    public String getSearching() {
        return mSharedPreferences.getString(SEARCH_KEY, null);
    }
}
