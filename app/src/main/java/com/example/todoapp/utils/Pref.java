package com.example.todoapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Pref {
    private SharedPreferences preferences;
    public static Pref pref;

    public Pref(Context context) {
        pref = this;
        preferences = context.getSharedPreferences("Top", Context.MODE_PRIVATE);
    }
    public void saveState() {
        preferences.edit().putBoolean("show", true).apply();
    }

    public boolean isBoardShow() {
        return preferences.getBoolean("show", false);
    }

    public static Pref getPrefs() {
        return pref;
    }
    public void saveFirstName(String name) {
        preferences.edit().putString("first", name).apply();
    }

    public String firstName() {
        return preferences.getString("first", "First name");

    }

    public void saveLastName(String last) {
        preferences.edit().putString("last", last).apply();
    }

    public String lastName() {
        return preferences.getString("last", "Last name");

    }

    public void saveImage(Uri uri) {
        preferences.edit().putString("image", uri.toString()).apply();
    }

    public String getImage() {
        return preferences.getString("image", "");
    }
}
