package quest.ejercicioseccion6.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import quest.ejercicioseccion6.R;
import quest.ejercicioseccion6.adapters.MyAdapter;
import quest.ejercicioseccion6.models.Fruit;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MenuItem genericFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = this.getAllFruits();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new MyAdapter(fruits, R.layout.recycler_view_item, this, new MyAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Fruit fruit, int position) {
                /* Agrega una nueva fruta del mismo tipo */
                /*String name = fruit.getName();
                addSpecificFruit(position, name);*/
                fruit.addStock();
                mAdapter.notifyItemChanged(position);
                Toast.makeText(MainActivity.this, "Elemento Agregado: " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        /*Mejora el performance si sabemos que no añadiremos más elementos a la vista */
        mRecyclerView.setHasFixedSize(true);

        // Añade animaciones por defecto
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        genericFruit = menu.findItem(R.id.add_fruit);
        return true;
    }

    /* Boton para agregar frutas genericas y desactivar el botón */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fruit:
                // Se llama al método para agregar
                this.addGenericFruit(0);
                this.genericFruit.setVisible(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        *//* Inflamos el contextMenu con nuestro Layout *//*
        MenuInflater inflater = getMenuInflater();
        *//* Antes de inflar, le añadimos el header dependiendo del objetos que se haya presionado *//*
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        *//* Inflams *//*
        inflater.inflate(R.menu.context_menu, menu);
    }*/

    /*@Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        *//* Obtener info en el context menu del objeto que se pinche *//*
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.reset_fruit:
                // Se le pasan un los parametros necesarios junto a la posicion
                this.resetFruit(info.position ,this.fruits.get(info.position).getName(), this.fruits.get(info.position).getDescription(), this.fruits.get(info.position).getImg());
                return true;
            case R.id.delete_fruit:
                this.deleteFruit(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }*/

    private List<Fruit> getAllFruits() {
        return new ArrayList<Fruit>(){{
            add(new Fruit("Apple", "Mmh yum", 0, R.drawable.apple_bg, R.mipmap.ic_apple));
            add(new Fruit("Plum", "2-2", 0, R.drawable.plum_bg, R.mipmap.ic_plum));
            add(new Fruit("Banana", "Para el licuado", 0, R.drawable.banana_bg, R.mipmap.ic_banana));
            add(new Fruit("Cherry", "Yum but not too much", 0, R.drawable.cherry_bg, R.mipmap.ic_cherry));
            add(new Fruit("Pear", "Mmh yum yum", 0, R.drawable.pear_bg, R.mipmap.ic_pear));
            add(new Fruit("Raspberry", "Fruit of the goddess", 0, R.drawable.raspberry_bg, R.mipmap.ic_rasberry));
        }};
    }

    private void addGenericFruit(int position) {
        fruits.add(position, new Fruit("Nueva Fruta", "Está rica... supongo", 0, R.drawable.orange_bg, R.mipmap.ic_orange));
        /* Y se le notifica al adaptador que se ha insertado un nuevo item */
        mAdapter.notifyItemInserted(position);
        /* Hacemos scroll hasta el nuevo item */
        mLayoutManager.scrollToPosition(position);
    }
}
