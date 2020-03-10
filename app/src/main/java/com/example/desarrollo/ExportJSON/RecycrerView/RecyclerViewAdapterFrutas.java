package com.example.desarrollo.ExportJSON.RecycrerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.desarrollo.Datos.PreferenciasDao;
import com.example.desarrollo.ExportJSON.Filter.FilterHelperFrutas;
import com.example.desarrollo.ExportJSON.Reader.ReaderFrutas;
import com.example.desarrollo.R;
import com.example.desarrollo.Precentacion.Alimentos.Frutas.frutas_select_activity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterFrutas extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    Context context;
    List<ReaderFrutas> readerFrutas;
    ArrayList meGusta = new ArrayList();
    public ArrayList<ReaderFrutas> currentList;
    FilterHelperFrutas filterHelper;

    private static final String TAG = "RecyclerViewAdapterFrut";

    public RecyclerViewAdapterFrutas(Context context, ArrayList<ReaderFrutas> readerFrutas) {
        this.context = context;
        this.readerFrutas = readerFrutas;
        this.currentList = readerFrutas;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView porcion;
        TextView vitamina;
        TextView descripcion;
        ImageView imgUrl;
        ImageView favoritos;
        CardView view_container;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container = (CardView) itemView.findViewById(R.id.containerFrutas);
            nombre = (TextView) itemView.findViewById(R.id.nombreFruta);
            porcion = (TextView) itemView.findViewById(R.id.porcion);
            vitamina = (TextView) itemView.findViewById(R.id.vitamina);
            imgUrl = (ImageView) itemView.findViewById(R.id.imgUrlFrutas);
            favoritos = (ImageView) itemView.findViewById(R.id.favoritos);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(context).inflate(R.layout.frutas_items, parent,false);

        final ItemViewHolder viewHolder = new ItemViewHolder(layout);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, frutas_select_activity.class);
                i.putExtra("fruta_nombre", readerFrutas.get(viewHolder.getAdapterPosition()).getNombre());
                i.putExtra("fruta_vitamina", readerFrutas.get(viewHolder.getAdapterPosition()).getVitamina());
                i.putExtra("fruta_descripcion", readerFrutas.get(viewHolder.getAdapterPosition()).getDescripcion());
                i.putExtra("fruta_beneficio", readerFrutas.get(viewHolder.getAdapterPosition()).getBeneficio());
                i.putExtra("fruta_imagen", readerFrutas.get(viewHolder.getAdapterPosition()).getImgUrl());


                context.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        ReaderFrutas readerFrutas = (ReaderFrutas) this.readerFrutas.get(position);

        PreferenciasDao preferenciasDao = new PreferenciasDao();

        preferenciasDao.meGusta(TAG, context, meGusta);

        Log.v(TAG, "Size " + meGusta.size());
        /*

        for (int i = 0; i < meGusta.size(); i++){
            if (meGusta.get(i).equals(readerFrutas.getNombre())){
                itemViewHolder.favoritos.setVisibility(View.VISIBLE);
            }
            else {
                itemViewHolder.favoritos.setVisibility(View.GONE);
            }
        }

         */

        itemViewHolder.nombre.setText(readerFrutas.getNombre());
        itemViewHolder.porcion.setText(readerFrutas.getPorcion());
        itemViewHolder.vitamina.setText(readerFrutas.getVitamina());

        Glide
                .with(itemViewHolder.imgUrl.getContext())
                .load(getImage(readerFrutas.getImgUrl()))
                .into(itemViewHolder.imgUrl);


        Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_transition_animation);
        holder.itemView.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return readerFrutas.size();
    }


    public void setReaderFrutas(ArrayList<ReaderFrutas> filteredSpacecrafts)
    {
        this.readerFrutas = filteredSpacecrafts;

    }
    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }

    @Override
    public Filter getFilter() {
        if(filterHelper == null)
        {
            filterHelper = new FilterHelperFrutas(currentList,this, context);
        }

        return filterHelper;
    }

}
