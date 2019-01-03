package com.example.lorenzo.alacarta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

public class RestoAdapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private List<Restaurante> restoList;

    public RestoAdapter( Context context, List<Restaurante> restoList ) {
        mContext = context;
        this.restoList = restoList;
    }

    public void setRestoList ( List<Restaurante> restoList1 ) {
        restoList = restoList1;
    }

    @Override
    public int getCount() {
            return restoList.size();
    }

    @Override
    public Restaurante getItem(int i) {
        return restoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) restoList.get(i).getNombre().hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Usamos el LayoutInflater para obtener la vista del item
        view = LayoutInflater.from(mContext).inflate(R.layout.item_list, viewGroup, false);

        //Las vistas asociadas al item
        TextView nombreTv = view.findViewById(R.id.nombreTv);
        TextView direccionTv = view.findViewById(R.id.dirrecionTv);
        TextView especialidadTv = view.findViewById(R.id.especialidadTv);

        //Obtengo el objeto de la lista adecuado a la posicion con la que se llamo el metodo (parametro)
        Restaurante restaurante = restoList.get(i);

        //Le asigno a cada vista asociada, su contenido
        nombreTv.setText(restaurante.getNombre());

        direccionTv.setText(restaurante.getDireccion());
        especialidadTv.setText(restaurante.getEspecialidad());

        //view.setOnClickListener(new PizzaOnClickListener(context, pizza));

        //Devuelvo el item
        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
