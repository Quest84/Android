package quest.ejercicioseccion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    /* Elementos de la Interfaz de Usuario */
    private Button btnNext;
    private SeekBar seekBar;
    private TextView textViewEdad;
    private RadioButton btnSaludo;
    private RadioButton btnDespedida;

    /* Otras Variables */
    String nombre = "";
    int edad = 18;
    private final int MAX_AGE = 60;
    private final int MIN_AGE = 16;

    /* Para Compartir */
    public static final int OPCION_SALUDO = 1;
    public static final int OPCION_DESPEDIDA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnNext = (Button) findViewById(R.id.btnNext);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textViewEdad = (TextView) findViewById(R.id.textViewEdad);
        btnSaludo = (RadioButton) findViewById(R.id.btnSaludo);
        btnDespedida = (RadioButton) findViewById(R.id.btnDespedida);

        /* Recuperar el String del Nombre */
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nombre = bundle.getString("nombre");
        }

        /* Evento change para el SeekBar */
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int edadActual, boolean fromUser) {
                edad = edadActual;
                textViewEdad.setText(edad + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                /* Aunque no lo sobreescrivaos con alguna funcionalidad, OnSeekBarChangeListener es una interfaz
                 * y como interfaz que es, necesitamos sobreescrbir todos sus métodos, aunque lo dejemos vacio*/
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                /* Declaramos nuestras reestricciones de edad en el evento en el que el usuario suelta/deja el seekbar */
                edad = seekBar.getProgress();
                textViewEdad.setText(edad + "");

                if (edad > MAX_AGE) {
                    btnNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "La edad maxima permitida es: " + MAX_AGE, Toast.LENGTH_LONG).show();
                } else if (edad < MIN_AGE) {
                    btnNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "La edad minima permitida es: " + MIN_AGE, Toast.LENGTH_LONG).show();
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                }
            }
        });

        /* Boton Para el siguiente Activity */
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                int opcion = (btnSaludo.isChecked()) ? OPCION_SALUDO : OPCION_DESPEDIDA;
                intent.putExtra("opcion", opcion);
                startActivity(intent);
            }
        });
    }
}
