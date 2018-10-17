package com.example.diego.parqueosuperuser;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jeffrey on 25/5/2017.
 */





public class ListaArtista extends BaseAdapter {

    private Activity context;
    Context mContext;
    LayoutInflater inflater;
    private List<Encargado> artistaList=null;
    private ArrayList<Encargado> arraylist;

    public ListaArtista(Activity context, List<Encargado> artistaList)
    {

        this.context= context;
        this.artistaList=artistaList;
        inflater = LayoutInflater.from(context);

        this.arraylist = new ArrayList<Encargado>();
        this.arraylist.addAll(artistaList);
    }

    public class ViewHolder {
        TextView name;
        TextView calle;
        TextView sector1;
        TextView telefono1;


    }


    @Override
    public int getCount() {
        return artistaList.size();
    }

    @Override
    public Encargado getItem(int position) {
        return artistaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();





        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.encargado, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) convertView.findViewById(R.id.nombre);
            holder.calle = (TextView) convertView.findViewById(R.id.calle_activa);
            holder.sector1 = (TextView) convertView.findViewById(R.id.sector);
            holder.telefono1 = (TextView) convertView.findViewById(R.id.telefono);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set the results into TextViews
        holder.name.setText("Nombre: "+artistaList.get(position).getNombre());
        holder.calle.setText("Calle asignada: "+artistaList.get(position).getCalle_activa());
        holder.sector1.setText("Soy wapo");
        holder.telefono1.setText("Telefono: "+artistaList.get(position).getTelefono());






        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        artistaList.clear();
        if (charText.length() == 0) {
            artistaList.addAll(arraylist);

        } else {
            for (Encargado wp : arraylist) {
                if (wp.getNombre().toLowerCase(Locale.getDefault()).contains(charText)) {
                    artistaList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}

