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
        TextView frase;
        TextView ventaja;
        ImageView imgUrl;
        CardView view_container;
        CardView backgroundVentaja;
        LinearLayout backgroundFrutas;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container = (CardView) itemView.findViewById(R.id.containerFrutas);
            nombre = (TextView) itemView.findViewById(R.id.nombreFruta);
            frase = (TextView) itemView.findViewById(R.id.fraseFruta);
            ventaja = (TextView) itemView.findViewById(R.id.fruta_ventaja);
            imgUrl = (ImageView) itemView.findViewById(R.id.imgUrlFrutas);
            backgroundFrutas = (LinearLayout) itemView.findViewById(R.id.backgroundFrutas);
            backgroundVentaja = (CardView) itemView.findViewById(R.id.fruta_backgroundVentaja);
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
                i.putExtra("fruta_idAlimentos", readerFrutas.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("fruta_nombre", readerFrutas.get(viewHolder.getAdapterPosition()).getNombre());
                i.putExtra("fruta_equivalencia", readerFrutas.get(viewHolder.getAdapterPosition()).getEquivalencia());
                i.putExtra("fruta_descripcion", readerFrutas.get(viewHolder.getAdapterPosition()).getDescripcion());
                i.putExtra("fruta_recomendacion", readerFrutas.get(viewHolder.getAdapterPosition()).getRecomendacion());
                i.putExtra("fruta_recomendacionDos", readerFrutas.get(viewHolder.getAdapterPosition()).getRecomendacionDos());
                i.putExtra("fruta_frase", readerFrutas.get(viewHolder.getAdapterPosition()).getFrase());
                i.putExtra("fruta_ventaja", readerFrutas.get(viewHolder.getAdapterPosition()).getVentaja());
                i.putExtra("fruta_avisoTitulo", readerFrutas.get(viewHolder.getAdapterPosition()).getAvisoTitulo());
                i.putExtra("fruta_aviso", readerFrutas.get(viewHolder.getAdapterPosition()).getAviso());
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
        itemViewHolder.frase.setText(readerFrutas.getFrase());
        itemViewHolder.backgroundFrutas.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(readerFrutas.getBackground())));

        String ventaja = readerFrutas.getVentaja();

        if (ventaja.equals("corazón") || ventaja.equals("sangre") || ventaja.equals("arterias")) {
            itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#F7B3B5"));
            itemViewHolder.ventaja.setTextColor(Color.parseColor("#9A1216"));
        } else {
            if (ventaja.equals("sistema nervioso") || ventaja.equals("infecciones")) {
                itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#C2D5FD"));
                itemViewHolder.ventaja.setTextColor(Color.parseColor("#4981F9"));
            } else {
                if (ventaja.equals("aparato digestivo") || ventaja.equals("aparato urinario") || ventaja.equals("aparato reproductor")) {
                    itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#EBF5EF"));
                    itemViewHolder.ventaja.setTextColor(Color.parseColor("#5C9076"));
                } else {
                    if (ventaja.equals("estómago") || ventaja.equals("intestino")){
                        itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#F9DEC2"));
                        itemViewHolder.ventaja.setTextColor(Color.parseColor("#EF9D49"));
                    }else {
                        if (ventaja.equals("ojos")){
                            itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#EFE9FC"));
                            itemViewHolder.ventaja.setTextColor(Color.parseColor("#7639EB"));
                        }else{
                            if (ventaja.equals("piel")){
                                itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#FBEDEC"));
                                itemViewHolder.ventaja.setTextColor(Color.parseColor("#ECC4B8"));
                            }else{
                                if (ventaja.equals("")){
                                    itemViewHolder.backgroundVentaja.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }
            }
        }


        itemViewHolder.ventaja.setText(readerFrutas.getVentaja());
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
