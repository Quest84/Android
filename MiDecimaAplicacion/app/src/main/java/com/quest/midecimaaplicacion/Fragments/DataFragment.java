package com.quest.midecimaaplicacion.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.quest.midecimaaplicacion.R;

public class DataFragment extends Fragment {

    private EditText textData;
    private Button btnSend;
    private DataListener callback;


    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            callback = (DataListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " should implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 1. inflate recibe como primer parametro la referencia del layout
        // 2. Recibe un viewGroup
        // 3. Un booleano
        // Esto es lo primero que hay que hacer en un framento, tambien cambiar el return
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        /* Lógica para capturar los elementos de la UI */
        textData = (EditText) view.findViewById(R.id.editTextData);
        btnSend = (Button) view.findViewById(R.id.buttonSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendText(textData.getText().toString());
                String textToSend = textData.getText().toString();
                callback.sendData(textToSend);
            }
        });





        // Inflate the layout for this fragment
        return view;
    }

    public interface DataListener{
        void sendData(String text);
    }
}
