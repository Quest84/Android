package quest.mitercerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private final String GREETER = "Hello Stranger";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon_round);

        // Activar Icono en la Barra de Acción
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn = (Button)findViewById(R.id.buttonMain);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear/Acceder a un Segundo Activity y mandarle un String
                // A este tipo de intent se le conoce como Explicito
                // Por que se le especifica de que activity inicia y hacia donde
                Intent intent = new Intent(MainActivity.this, secondActivity.class);
                intent.putExtra("greeter", GREETER);
                startActivity(intent);
            }
        });
    }
}
