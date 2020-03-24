package com.example.desarrollo.ExportJSON.RecycrerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        TextView descripcion;
        ImageView imgUrl;
        CardView view_container;
        LinearLayout backgroundFrutas;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container = (CardView) itemView.findViewById(R.id.containerFrutas);
            nombre = (TextView) itemView.findViewById(R.id.nombreFruta);
            descripcion = (TextView) itemView.findViewById(R.id.descripcionFruta);
            imgUrl = (ImageView) itemView.findViewById(R.id.imgUrlFrutas);
            backgroundFrutas = (LinearLayout) itemView.findViewById(R.id.backgroundFrutas);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(context).inflate(R.layout.frutas_items, parent, false);

        final ItemViewHolder viewHolder = new ItemViewHolder(layout);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, frutas_select_activity.class);
                i.putExtra("fruta_nombre", readerFrutas.get(viewHolder.getAdapterPosition()).getNombre());
                i.putExtra("fruta_descripcion", readerFrutas.get(viewHolder.getAdapterPosition()).getDescripcion());
                i.putExtra("fruta_recomendacion", readerFrutas.get(viewHolder.getAdapterPosition()).getRecomendacion());
                //i.putExtra("fruta_beneficio", readerFrutas.get(viewHolder.getAdapterPosition()).getBeneficio());
                i.putExtra("fruta_imagen", readerFrutas.get(viewHolder.getAdapterPosition()).getImgUrl());
                i.putExtra("fruta_fondo", readerFrutas.get(viewHolder.getAdapterPosition()).getBackground());


                context.startActivity(i);
            }
        });

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        ReaderFrutas readerFrutas = (ReaderFrutas) this.readerFrutas.get(position);

        itemViewHolder.nombre.setText(readerFrutas.getNombre());
        itemViewHolder.descripcion.setText(readerFrutas.getDescripcion());
        itemViewHolder.backgroundFrutas.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(readerFrutas.getBackground())));

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


    public void setReaderFrutas(ArrayList<ReaderFrutas> filteredSpacecrafts) {
        this.readerFrutas = filteredSpacecrafts;

    }

    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }

    @Override
    public Filter getFilter() {
        if (filterHelper == null) {
            filterHelper = new FilterHelperFrutas(currentList, this, context);
        }

        return filterHelper;
    }

}
