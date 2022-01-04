package quest.micuartaaplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    List<String> names;

    private MyAdapter myAdapter;

    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gridView = (GridView) findViewById(R.id.gridView);

        // Datos a mostrar
        names = new ArrayList<String>();
        names.add("Manuel");
        names.add("Ruiz");
        names.add("González");
        names.add("José");


        // Evento al clickear los item
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridActivity.this, "Clickeado: " + names.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        /* Enlazamos con el adaptador personalizado */
        myAdapter = new MyAdapter(this, R.layout.grid_item, names);
        gridView.setAdapter(myAdapter);

        registerForContextMenu(gridView);
    }


    // Método para crear el menu - Inflamos el layout del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    // Para los eventos en las opciones - Manejamos eventos clic en el menu de opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Puede cambiar en el futuro si se agregan mas elementos al menu
        switch(item.getItemId()) {
            /* Añadimos nuevos nombres */
            case R.id.add_item:
                this.names.add("Added nº: " + (++counter));
                /* Notificamos al adaptador de los cambios producidos */
                this.myAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    // Inflamos el layout del context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.names.get(info.position));

        inflater.inflate(R.menu.context_menu, menu);
    }

    // Manejamos eventos clic en el context menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.delete_item:
                // Borrando el item clickeado
                this.names.remove(info.position);
                /* Notificamos al adaptador del cambio producido*/
                this.myAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
