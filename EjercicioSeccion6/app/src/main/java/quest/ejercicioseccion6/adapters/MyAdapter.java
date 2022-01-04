package quest.ejercicioseccion6.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import quest.ejercicioseccion6.R;
import quest.ejercicioseccion6.activities.MainActivity;
import quest.ejercicioseccion6.models.Fruit;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Fruit> fruits;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Activity activity;

    private Context context;

    public MyAdapter(List<Fruit> fruits, int layout, Activity activity, OnItemClickListener listener){
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Se infla el View
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        /* COntexto para picasso */
        context = parent.getContext();

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.bind(fruits.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    /* Nuestra clase ViewHolder */                                      /* Para implementar el Menu Contextual*/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        /* Elementos del UI */
        public TextView textViewName;
        public TextView textViewDescription;
        public TextView textViewStock;
        public ImageView imageViewFruit;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /* Recibe la vista completa, la pasa al constructor padre y enlaza referencias UI
            * con nuestras propiedades ViewHolder declaradas justo arriba */
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            textViewStock = (TextView) itemView.findViewById(R.id.textViewStock);
            imageViewFruit = (ImageView) itemView.findViewById(R.id.imageViewFruit);

            /* ALgo especial */      /* Se podrian implementar los métodos aqui mismo pero es mejor tenerlos separados*/
            itemView.setOnCreateContextMenuListener(this);
        }



        public void bind(final Fruit fruit, final OnItemClickListener listener) {
            /* Procesamos los datos a renderizar
             * Definimos que por cada elemento de nuestro Recycler View, tenemos un Click Listener
             * Que se comporta de la siguiente manera */
            textViewName.setText(fruit.getName());
            textViewDescription.setText(fruit.getDescription());
            textViewStock.setText(fruit.getStock() + "");
            /* Para usar la libreria de picasso hay que ir a ProjectStructure y añadir manualmente
             * el JAR al proyecto actual en app/libs */
            /* usando la libreria picasso para cargar las imagenes */
            Picasso.with(context).load(fruit.getImgBackground()).fit().into(imageViewFruit);
            // imageViewFruit.setImageResource(fruit.getImg());

            // Solo cuando se pincha la imagen es cuando se agrega un nuevo objeto
            this.imageViewFruit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruit, getAdapterPosition());
                }
            });

            // Esto es para toda la vista
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruit, getAdapterPosition());
                }
            });*/

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo contextMenuInfo) {
            /* Recogemos la posicion con el método getAdapterPosition */
            Fruit fruitSelected = fruits.get(this.getAdapterPosition());
            /* Establecemos titulo e icono para cada elemento, mirando en sus propiedades */
            contextMenu.setHeaderTitle(fruitSelected.getName());
            contextMenu.setHeaderIcon(fruitSelected.getImgIcon());
            /* Inflamos el menú */
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu, contextMenu);
            /* Por ultimo, añadimos uno por uno, el listener onMenuItemClick
            * para controlar las acciones en el contextMenu, anteriormente lo manejambamos
            * con el método onContextItemSelected en el activity */
            for (int i = 0; i < contextMenu.size(); i++) {
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
            }

        }


        /* Sobreescribimos onMenuItemClick, dentro del ViewHolder,
        * en vez de hacerlo en el activity bajo el nombre onContextMenuListener  */
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            /* No obtenemos nuestro objeto info
            * porque la posicion la podemos rescatar desde getAdapterPosition */
            switch (menuItem.getItemId()) {
                case R.id.reset_fruit:
                    // Se le pasan un los parametros necesarios junto a la posicion
                    fruits.get(getAdapterPosition()).resetStock();
                    notifyItemChanged(getAdapterPosition());
                    Toast.makeText(activity, "Reseted Element: " + fruits.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.delete_fruit:
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    Toast.makeText(activity, "Deleted Element: " + fruits.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }

    }

    // Nuestro propio OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(Fruit fruit, int position);
    }
}
