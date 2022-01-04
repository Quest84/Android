package quest.miseptimaaplicacion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import quest.miseptimaaplicacion.R;
import quest.miseptimaaplicacion.models.Movie;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;


    // Contexto para el Picasso
    private Context context;


    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener){
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Se infla el View
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        /* Contexto para el Picasso */
        context = parent.getContext();

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Este metodo es inutil si se llegan a agregar más elementos
    // Por lo tanto creamos nuesro metodo bind() dentro de setOnClickListener
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* Elementos UI a rellenar */
        public TextView textViewTitle;
        public ImageView imageViewPoster;
        public ViewHolder(View itemView) {
            /* Recibe la View completa, la pasa al constructor  padre y enlaza referencias UI
            *  con nuestras propiedades ViewHolder declaradas justo arriba */
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movie, final OnItemClickListener listener) {
            /* Procesamos los datos a renderizar
            * Definimos que por cada elemento de nuestro Recycler View, tenemos un click Listener
            * Que se comporta de la siguiente manera... */
            textViewTitle.setText(movie.getName());
            /* Para usar la libreria de picasso hay que ir a ProjectStructure y añadir manualmente
            * el JAR al proyecto actual en app/libs */
            /* usando la libreria picasso para cargar las imagenes */
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);

            //imageViewPoster.setImageResource(movie.getPoster());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie, getAdapterPosition());

                }
            });
        }
    }

    // Nuestro propio OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }
}
