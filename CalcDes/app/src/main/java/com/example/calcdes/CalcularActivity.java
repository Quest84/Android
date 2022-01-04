package com.example.calcdes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CalcularActivity extends AppCompatActivity {

    EditText _medida1,_medida2;
    RadioButton _cantidad,_precio;
    Button _btnCalcular;
    String operacion ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);
        _medida1=findViewById(R.id.medida1);
        _medida2=findViewById(R.id.medida2);

        _cantidad=findViewById(R.id.cantidad);
        _precio=findViewById(R.id.precio);

        _btnCalcular = findViewById(R.id.btnCalcular);
        _btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_medida1.getText().toString().length()<1){
                    Toast.makeText(getApplicationContext(),"Ingrese una medida 1",Toast.LENGTH_LONG).show();
                }else if(_medida2.getText().toString().length()<1){
                    Toast.makeText(getApplicationContext(),"Ingrese una medida 2",Toast.LENGTH_LONG).show();
                }else{
                    if(_cantidad.isChecked())
                        operacion="Cantidad";
                    if(_precio.isChecked())
                        operacion="Costos";

                    Intent i = new Intent(getApplicationContext(),ResultadoActivity.class);
                    i.putExtra("medida1",_medida1.getText().toString());
                    i.putExtra("medida2",_medida2.getText().toString());
                    i.putExtra("operacion",operacion);
                    startActivity(i);
                }
            }
        });


    }
}