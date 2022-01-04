package quest.miseptimaaplicacion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import quest.miseptimaaplicacion.adapters.MyAdapter;
import quest.miseptimaaplicacion.R;
import quest.miseptimaaplicacion.models.Movie;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager = new GridLayoutManager(this, 2);
        //mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                // Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
                deleteMovie(position);
            }
        });

        // Mejora el performance si sabemos que no añadiremos más elementos a la vista
        mRecyclerView.setHasFixedSize(true);
        // Añade animaciones por defecto
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                // Se llama al metodo para agregar
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Movie> getAllMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("D O O M - 1", R.drawable.doom1));
            add(new Movie("D O O M - 2", R.drawable.doom2));
            add(new Movie("D O O M - 3", R.drawable.doom3));
            add(new Movie("D O O M - 4", R.drawable.doom4));
        }};
    }

    private void addMovie(int position) {
        movies.add(position, new Movie("New Movie - " + (++counter), R.drawable.newposter));
        /* Norificamos del nuevo item insertado en nuestra colección */
        mAdapter.notifyItemInserted(position);
        /* Hacemos Scroll hacia la posición donde el nuevo elemento se aleja */
        mLayoutManager.scrollToPosition(position);
    }

    private void deleteMovie(int position) {
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }


}
