package quest.micuartaaplicacion;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;

    public MyAdapter(Context context, int layout, List<String> names){
        this.context = context;
        this.layout = layout;
        this.names = names;


    }

    @Override // Numero de veces que debe iterar
    public int getCount() {
        return this.names.size();
    }

    // Casi no se usa
    @Override // Para obtener un item de la coleccion
    public Object getItem(int position) {
        return this.names.get(position);
    }

    // No se usa
    @Override // Para obtener el id del elemento de la coleccion
    public long getItemId(int id) {
        return id;
    }

    // Importante
    @Override // Coje cada item y se dibuja
    public View getView(int position, View convertView, ViewGroup parent) {

        /* ViewHolder Pattern */
        ViewHolder holder;
        if (convertView == null) {
            /* Inflamos la vista que ha llegado del Layout personalizado */
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();
            /* Referenciamos el elemento a modificar y lo rellenamos */
            holder.nameTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        /* Recoge la vista que le ha llegado */
        View v = convertView;

        /* Nos traemos el valor actual dependiendo de la posición */
        String currentName = names.get(position);
        // currentName = getItem(position);

        /* Referenciamos el elemento a modificar y lo rellenamos */
        holder.nameTextView.setText(currentName);

        /* Devolvemos la vista inflada y modificada con nuestros datos */
        return convertView;


        // Es un error dejar el return null;
        //return null;
    }

    static class ViewHolder {
        /* Tanto elementos como los que tenga la lista */
        private TextView nameTextView;


    }
}
