package quest.ejercicioseccion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNext;
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Te digo la verdad en 20 segundos...", Toast.LENGTH_SHORT).show();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon_round);

        /* Botón para ir al secondActivity*/
        btnNext = (Button) findViewById(R.id.btnNext);
        editTextName = (EditText) findViewById(R.id.editTextName);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextName.getText().toString();
                if (nombre != null && !nombre.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("nombre", nombre);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Escribe tu nombre, mongol de mierda", Toast.LENGTH_LONG).show();
                }
            }
        });

        /* Método para el EditText */


    }
}
