package com.example.desarrollo.ExportJSON.RecycrerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.desarrollo.ExportJSON.Filter.FilteredHelperFrutas;
import com.example.desarrollo.Entidades.Frutas;
import com.example.desarrollo.R;
import com.example.desarrollo.Precentacion.Alimentos.AlimentoRegistroactivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterFrutas extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private Context context;
    private List<Frutas> frutas;
    private String tipoAlimento;
    private ArrayList<Frutas> currentList;
    private FilteredHelperFrutas filterHelper;

    public RecyclerViewAdapterFrutas(Context context, ArrayList<Frutas> frutas, String tipoAlimento) {
        this.context = context;
        this.frutas = frutas;
        this.currentList = frutas;
        this.tipoAlimento = tipoAlimento;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView frase;
        TextView ventaja;
        ImageView imgUrl;
        CardView view_container;
        CardView backgroundVentaja;
        RelativeLayout backgroundFrutas;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container = (CardView) itemView.findViewById(R.id.containerFrutas);
            nombre = (TextView) itemView.findViewById(R.id.nombreFruta);
            frase = (TextView) itemView.findViewById(R.id.fraseFruta);
            ventaja = (TextView) itemView.findViewById(R.id.fruta_ventaja);
            imgUrl = (ImageView) itemView.findViewById(R.id.imgUrlFrutas);
            backgroundFrutas = (RelativeLayout) itemView.findViewById(R.id.backgroundFrutas);
            backgroundVentaja = (CardView) itemView.findViewById(R.id.fruta_backgroundVentaja);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.frutas_items, parent, false);

        final ItemViewHolder viewHolder = new ItemViewHolder(layout);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(parent.getContext(), AlimentoRegistroactivity.class);
                i.putExtra("fruta_idAlimentos", frutas.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("fruta_nombre", frutas.get(viewHolder.getAdapterPosition()).getNombre());
                i.putExtra("fruta_equivalencia", frutas.get(viewHolder.getAdapterPosition()).getEquivalencia());
                i.putExtra("fruta_unidadMedida", frutas.get(viewHolder.getAdapterPosition()).getUnidadMedida());
                i.putExtra("fruta_descripcion", frutas.get(viewHolder.getAdapterPosition()).getDescripcion());
                i.putExtra("fruta_recomendacion", frutas.get(viewHolder.getAdapterPosition()).getRecomendacion());
                i.putExtra("fruta_recomendacionDos", frutas.get(viewHolder.getAdapterPosition()).getRecomendacionDos());
                i.putExtra("fruta_frase", frutas.get(viewHolder.getAdapterPosition()).getFrase());
                i.putExtra("fruta_ventaja", frutas.get(viewHolder.getAdapterPosition()).getVentaja());
                i.putExtra("fruta_avisoTitulo", frutas.get(viewHolder.getAdapterPosition()).getAvisoTitulo());
                i.putExtra("fruta_aviso", frutas.get(viewHolder.getAdapterPosition()).getAviso());
                i.putExtra("fruta_imagen", frutas.get(viewHolder.getAdapterPosition()).getImgUrl());
                i.putExtra("fruta_fondo", frutas.get(viewHolder.getAdapterPosition()).getBackground());
                i.putExtra("fruta_tipoAlimento", tipoAlimento);


                parent.getContext().startActivity(i);
            }
        });

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Frutas frutas = (Frutas) this.frutas.get(position);

        itemViewHolder.nombre.setText(frutas.getNombre());
        itemViewHolder.frase.setText(frutas.getFrase());
        itemViewHolder.backgroundFrutas.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + frutas.getBackground())));

        String ventaja = frutas.getVentaja();

        if (ventaja.equals("Alimento para el corazón") || ventaja.equals("Alimento para la sangre") || ventaja.equals("Alimento para las arterias")) {
            itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#F7B3B5"));
            itemViewHolder.ventaja.setTextColor(Color.parseColor("#9A1216"));
        } else {
            if (ventaja.equals("Alimento para el sistema nervioso") || ventaja.equals("Alimento para las infecciones")) {
                itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#C2D5FD"));
                itemViewHolder.ventaja.setTextColor(Color.parseColor("#4981F9"));
            } else {
                if (ventaja.equals("Alimento para el aparato digestivo") || ventaja.equals("Alimento para el aparato urinario") || ventaja.equals("Alimento para el aparato reproductor")) {
                    itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#EBF5EF"));
                    itemViewHolder.ventaja.setTextColor(Color.parseColor("#5C9076"));
                } else {
                    if (ventaja.equals("Alimento para el estómago") || ventaja.equals("Alimento para el intestino")) {
                        itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#F9DEC2"));
                        itemViewHolder.ventaja.setTextColor(Color.parseColor("#EF9D49"));
                    } else {
                        if (ventaja.equals("Alimento para los ojos")) {
                            itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#EFE9FC"));
                            itemViewHolder.ventaja.setTextColor(Color.parseColor("#7639EB"));
                        } else {
                            if (ventaja.equals("Alimento para la piel")) {
                                itemViewHolder.backgroundVentaja.setCardBackgroundColor(Color.parseColor("#FBEDEC"));
                                itemViewHolder.ventaja.setTextColor(Color.parseColor("#ECC4B8"));
                            } else {
                                if (ventaja.equals("")) {
                                    itemViewHolder.backgroundVentaja.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }
            }
        }


        itemViewHolder.ventaja.setText(frutas.getVentaja());
        Glide
                .with(itemViewHolder.imgUrl.getContext())
                .load(getImage(frutas.getImgUrl()))
                .into(itemViewHolder.imgUrl);

    }

    @Override
    public int getItemCount() {
        return frutas.size();
    }


    public void setFrutas(ArrayList<Frutas> filteredSpacecrafts) {
        this.frutas = filteredSpacecrafts;

    }

    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }

    @Override
    public Filter getFilter() {
        if (filterHelper == null) {
            filterHelper = new FilteredHelperFrutas(currentList, this, context);
        }

        return filterHelper;
    }

}
