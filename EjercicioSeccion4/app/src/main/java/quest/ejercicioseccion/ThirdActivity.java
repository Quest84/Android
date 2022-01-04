package quest.ejercicioseccion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private Button btnFinal;
    private Button btnCompartir;
    private Button btnScaryButton;
    private int ScaryCode = 50;
    String nombre;
    int edad;
    int opcion;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnFinal = (Button) findViewById(R.id.btnFinal);
        btnCompartir = (Button) findViewById(R.id.btnCompartir);
        btnScaryButton = (Button) findViewById(R.id.btnScaryButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nombre = bundle.getString("nombre");
            edad = bundle.getInt("edad");
            opcion = bundle.getInt("opcion");
        }

        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opcion == 1) {
                    Toast.makeText(ThirdActivity.this, "Hola " + nombre + ", ¿como llevas esos "
                            + edad + " años?", Toast.LENGTH_LONG).show();
                    Toast.makeText(ThirdActivity.this, "Seguro que a tu edad los años" +
                            " empizan a pesarte, no es así?", Toast.LENGTH_LONG).show();
                    Toast.makeText(ThirdActivity.this, "¿Sientes que desperdicias el tiempo?",
                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(ThirdActivity.this, "No te preocupes, todos lo hacemos",
                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(ThirdActivity.this, "Al final todas tus acciones" +
                            " cumplen con el unico proposito de distraerte del hecho " +
                            "de saber que vas a morir", Toast.LENGTH_LONG).show();
                    Toast.makeText(ThirdActivity.this, "Asi que... ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ThirdActivity.this, "¿Cómo llevas esos años?",
                            Toast.LENGTH_SHORT).show();
                    flag = 1;
                    btnScaryButton.setVisibility(View.VISIBLE);
                } else {
                    int edadRelativa = 60 - edad;
                    Toast.makeText(ThirdActivity.this, "Vuelve pronto, " +
                            "igual aun te quedan " + edadRelativa + " años en este lugar.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ThirdActivity.this, ":)", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnScaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    // Si ya lo saludó, habilita el botón
                    btnScaryButton.setVisibility(View.VISIBLE);
                    Intent intentScary = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intentScary, ScaryCode);
                } else {
                    // Sino lo desabilita
                    btnScaryButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_TEXT, createMessage(opcion, nombre, edad));
                startActivity(intentShare);
            }
        });

    }

    private String createMessage(int opcion, String nombre, int edad) {
        if (opcion == 1) {
            return "Hola " + nombre + ", ¿como llevas esos "
                    + edad + " años? " + "\n\nSeguro que a tu edad los años"
                    + " empiezan a pesarte, no es así?" + "\n¿Sientes que desperdicias el tiempo?"
                    + "\nNo te preocupes, todos lo hacemos" + "\n\nAl final todas tus acciones"
                    + " cumplen con el unico proposito de distraerte del hecho"
                    + " de saber que vas a morir" + "\n\nAsi que... " + "\n¿Cómo llevas esos años?"
                    + "\n#ScaryButtonIsYou";
        } else {
            int edadRelativa = 60 - edad;
            return "Vuelve pronto, " + "igual aun te quedan " + edadRelativa + " años en este lugar.";
        }
    }

}

