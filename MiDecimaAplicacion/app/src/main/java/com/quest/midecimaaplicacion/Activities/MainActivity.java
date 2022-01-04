package com.quest.midecimaaplicacion.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.quest.midecimaaplicacion.Fragments.DataFragment;
import com.quest.midecimaaplicacion.Fragments.DetailsFragment;
import com.quest.midecimaaplicacion.R;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    private boolean isMultiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }


    @Override
    public void sendData(String text) {
        // Llamar al método renderizar texto de el DetailsFragment
        // pasando el texto que recibimos por parámetro en este mismo método

        if (isMultiPanel){
            DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.detailsFragment);
            detailsFragment.renderText(text);
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("text", text);
            startActivity(intent);
        }
    }

    private void setMultiPanel(){
        isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.detailsFragment) != null);
    }



}
