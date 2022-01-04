package quest.misegundaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Opción 3 Para darle funcinonalidad a un botón con EXTENDS - Tampoco es recomendado si se tienen más de un boton
        btn = (Button)findViewById((R.id.buttonMain));
        //this es la implementación en la clase
        btn.setOnClickListener(this);

        // Opción 2 Para darle funcionalidad a un botón instanciando el mismo botón - Recomendado
        // Se llama al boton desde una referencia -> R
        // Pero primero se tiene que hacer un casting (Button)
        /*btn = (Button)findViewById((R.id.buttonMain));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "(2)->Boton Clickeado desde el código", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "(3)->Boton Clickeado desde el código", Toast.LENGTH_LONG).show();
    }

    // Opción 1 para darle funcionalidad a un botón llamandolo desde la interfzaz - No recomendado por que se mezcal con el XML
    // No es recomendable llamar metodos desde la interfaz activity_main.xml
    // Lo que se debe hacer es hacer referencia al Boton en el Código
    /*public void miMetodo(View v){
        Toast.makeText(MainActivity.this, "(1)->Boton Clickeado desde la interfaz", Toast.LENGTH_LONG).show();
    }*/


}
