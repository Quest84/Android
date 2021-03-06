package com.quest.minovanaapprefactor.Utils;

import android.content.SharedPreferences;

public class Util {
    /* Se extraen estos dos metodos que se repiten para no escribirlo dos veces
    * por cuestiones de consistencia */
    public static String getUserMailPrefs(SharedPreferences preferences) {
        return preferences.getString("email", "");
    }

    public static String getUserPassPrefs(SharedPreferences preferences){
        return preferences.getString("pass", "");
    }

    public static void removeSharedPreferences(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("pass");
        editor.apply();
    }
}
