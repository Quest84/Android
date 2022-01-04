package quest.mitercerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class secondActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon_round);

        // Activar Flecha ir Atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = (TextView)findViewById(R.id.textViewMain);
        btnNext = (Button)findViewById(R.id.buttonSecondary);


        // Tomar los datos del Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString("greeter") != null){
            String greeter = bundle.getString("greeter");
            Toast.makeText(secondActivity.this, greeter, Toast.LENGTH_LONG).show();
            textView.setText(greeter);
        }
        else{
            Toast.makeText(secondActivity.this, "It's Empty", Toast.LENGTH_LONG).show();
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(secondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
