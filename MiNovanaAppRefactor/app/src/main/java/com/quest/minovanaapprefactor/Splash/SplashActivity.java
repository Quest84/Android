package com.quest.minovanaapprefactor.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.quest.minovanaapprefactor.Activities.LoginActivity;
import com.quest.minovanaapprefactor.Activities.MainActivity;
import com.quest.minovanaapprefactor.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);

        if (!TextUtils.isEmpty(Util.getUserMailPrefs(prefs)) &&
                !TextUtils.isEmpty(Util.getUserPassPrefs(prefs))){
            startActivity(intentMain);
        } else {
            startActivity(intentLogin);
        }
        // Para matar la instancia del activity
        finish();
    }
}
