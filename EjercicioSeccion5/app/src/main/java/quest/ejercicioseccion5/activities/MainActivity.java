package quest.ejercicioseccion5.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import quest.ejercicioseccion5.adapters.FruitAdapter;
import quest.ejercicioseccion5.R;
import quest.ejercicioseccion5.models.Fruit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /* Declaración de ListView, GridView y Adapters*/
    private ListView listView;
    private GridView gridView;
    private FruitAdapter adapterListView;
    private FruitAdapter adapterGridView;

    /* Lista de nuestro modelo, Fruta */
    private List<Fruit> fruits;

    /* Items en el option Menu */
    private MenuItem itemListView;
    private MenuItem itemGridView;

    /* Variables */
    private int counter = 0;
    private final int SWITCH_TO_LIST_VIEW = 0;
    private final int SWITCH_TO_GRID_VIEW = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Mostrar icono */
        this.enforceIconBar();

        this.fruits = getAllFruits();

        this.listView = (ListView) findViewById(R.id.listView);
        this.gridView = (GridView) findViewById(R.id.gridView);

        /* Adjuntando el mismo método click para ambas vistas */
        this.listView.setOnItemClickListener(this);
        this.gridView.setOnItemClickListener(this);

        this.adapterListView = new FruitAdapter(this, R.layout.list_view_item_fruit, fruits);
        this.adapterGridView = new FruitAdapter(this, R.layout.grid_view_item_fruit, fruits);

        this.listView.setAdapter(adapterListView);
        this.gridView.setAdapter(adapterGridView);

        /* Registrar el context menu para ambos */
        registerForContextMenu(this.listView);
        registerForContextMenu(this.gridView);
    }

    private void enforceIconBar() {
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.clickFruit(fruits.get(position));
    }

    private void clickFruit(Fruit fruit) {
        /* Diferenciamos entre las furtas conocidas y desconocidas */
        if (fruit.getOrigin().equals("Unknown"))
            Toast.makeText(this, "Sorry, we don't have many info about " + fruit.getName(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "The best fruit from " + fruit.getOrigin() + " is " + fruit.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflamos el option menu con nuestro layout */
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        /* Despues de inflar, recogemos las referencias a los botones que nos interesan */
        this.itemListView = menu.findItem(R.id.list_view);
        this.itemGridView = menu.findItem(R.id.grid_view);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /* Eventos para lso click en los botones del option menu */
        switch(item.getItemId()) {
            case R.id.add_fruit:
                this.addFruit(new Fruit("Added nº: " + (++counter) + " ", R.mipmap.ic_plum, "Unknown"));
                return true;
            case R.id.list_view:
                this.switchListGridView(this.SWITCH_TO_LIST_VIEW);
                return true;
            case R.id.grid_view:
                this.switchListGridView(this.SWITCH_TO_GRID_VIEW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        /* Inflamos el context menu con nuestro layout */
        MenuInflater inflater = getMenuInflater();
        /* Antes de inflar, le añadimos el header dependiendo del objeto qeu se toque */
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        /* Inflamos */
        inflater.inflate(R.menu.context_menu_fruits, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        /* Obtener info en el context menu del objeto que se pinche */
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()) {
            case R.id.delete_fruit:
                this.deleteFruit(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void switchListGridView(int option) {
        /* Método para caambiar entre Grid/List View */
        if (option == SWITCH_TO_LIST_VIEW) {
            /* Si queremos cambiar a list view, y el list view está en modo invisible...*/
            if (this.listView.getVisibility() == View.INVISIBLE) {
                /* ... Escondemosel gridView y enseñamos su boton en el menu de opciones*/
                this.gridView.setVisibility(View.INVISIBLE);
                this.itemGridView.setVisible(true);
                this.listView.setVisibility(View.VISIBLE);
                this.itemListView.setVisible(false);
                Toast.makeText(this, "(1)", Toast.LENGTH_SHORT).show();
            }
        } else if (option == SWITCH_TO_GRID_VIEW){
            /* Si queremos cambiar a grid view, y el grid view esá en modo invisible */
            if (this.gridView.getVisibility() == View.INVISIBLE) {
                /* ... Escondemos el listView y enseñamos su boton en el menu de opciones*/
                this.listView.setVisibility(View.INVISIBLE);
                this.itemListView.setVisible(true);
                /* no olvidemos enseñar el grid view y esconder su boton en el menu de opciones */
                this.gridView.setVisibility(View.VISIBLE);;
                this.itemGridView.setVisible(false);
                Toast.makeText(this, "(2)", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /* CRUD Actions (Create, Update & Delete - Get, Add, Delete */
    private List<Fruit> getAllFruits() {
        List<Fruit> list = new ArrayList<Fruit>() {{
            add(new Fruit("Banana", R.mipmap.ic_banana, "Queretaro"));
            add(new Fruit("Strawberry", R.mipmap.ic_strawberry, "Chiapas"));
            add(new Fruit("Orange", R.mipmap.ic_orange, "Oaxaca"));
            add(new Fruit("Apple", R.mipmap.ic_apple, "Yucatan"));
            add(new Fruit("Cherry", R.mipmap.ic_cherry, "Guerrero"));
            add(new Fruit("Pear", R.mipmap.ic_pear, "Guadalajara"));
            add(new Fruit("Raspberry", R.mipmap.ic_raspberry, "California"));
            add(new Fruit("Plum", R.mipmap.ic_plum, "Tabasco"));
        }};
        return list;
    }

    private void addFruit(Fruit fruit) {
        this.fruits.add(fruit);
        /* Avisa del cambio en ambos adapters */
        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
        Toast.makeText(this, "Elemento añadido: " + fruit.getName(), Toast.LENGTH_SHORT).show();
    }

    private void deleteFruit(int position) {
        this.fruits.remove(position);
        /* Avisa del cmabio en ambos adapters */
        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
        Toast.makeText(this, "Elemento eliminado: " + fruits.get(position).getName(), Toast.LENGTH_SHORT).show();
    }





}


