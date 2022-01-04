package com.quest.midecimaaplicacion.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.quest.midecimaaplicacion.Fragments.DetailsFragment;
import com.quest.midecimaaplicacion.R;

public class DetailsActivity extends AppCompatActivity {

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(getIntent().getExtras() != null) {
            text = getIntent().getStringExtra("Text");
        }

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailsFragment);
        detailsFragment.renderText(text);

    }
}
