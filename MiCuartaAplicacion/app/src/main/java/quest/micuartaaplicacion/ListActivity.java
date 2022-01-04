package quest.micuartaaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        // Datos a mostrar
        names = new ArrayList<String>();
        names.add("Manuel");
        names.add("Ruiz");
        names.add("González");
        names.add("José");
        names.add("Manuel");
        names.add("Ruiz");
        names.add("González");
        names.add("José");
        names.add("Manuel");
        names.add("Ruiz");
        names.add("González");
        names.add("José");
        names.add("Manuel");
        names.add("Ruiz");
        names.add("González");
        names.add("José");

        // Adaptador, la forma visual en la que mostraremos los datos
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        // Establecemos el adaptador
        // listView.setAdapter(adapter);

        // Evento al clickear los item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this, "Clickeado: " + names.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        /* Enlazamos con el adaptador personalizado */
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names);
        listView.setAdapter(myAdapter);

    }
}

