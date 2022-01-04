package quest.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        // Datos a mostrar
        List<String> names = new ArrayList<String>();
        names.add("Manuel");
        names.add("Ruiz");
        names.add("González");
        names.add("José");

        // Adaptador, la forma visual en la que mostraremos los datos
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        // Establecemos el adaptador
        listView.setAdapter(adapter);


    }
}
