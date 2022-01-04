package com.example.calcdes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {
    TextView _recibemedida1,_recibemedida2,_texto,_resultado;
    Button _btnVolver,_btnDiseño;
    int n1,n2,r;
    String operacion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        _recibemedida1=findViewById(R.id.recibimedida1);
        _recibemedida2=findViewById(R.id.recibimedida2);
        _texto=findViewById(R.id.texto);
        _resultado=findViewById(R.id.resultado);
        _btnVolver=findViewById(R.id.volver);

        n1=Integer.valueOf(getIntent().getExtras().getString("medida1"));
        n2=Integer.valueOf(getIntent().getExtras().getString("medida2"));

        operacion=getIntent().getExtras().getString("operacion");
        switch(operacion){
            case "Calcular material":
                r=(n1*n2)*5;
                break;
            case "Calcular costo":
                r=(n1*n2)*18;
                break;
        }
        _texto.setText("El "+operacion+" aproximado es:");
        _resultado.setText(String.valueOf(r));

        _btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _btnDiseño.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}