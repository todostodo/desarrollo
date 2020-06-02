package com.example.desarrollo.ExportJSON.RecycrerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.desarrollo.Entidades.Foro;
import com.example.desarrollo.ExportJSON.Filter.FilteredHelperForoRecetas;
import com.example.desarrollo.Precentacion.Foro.ForoInformacionActivity;
import com.example.desarrollo.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewForoRecetas extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<Foro> listForo;
    private ArrayList<Foro> currentList;
    private Context context;
    private FilteredHelperForoRecetas filterHelper;

    public RecyclerViewForoRecetas(ArrayList<Foro> listForo, Context context) {
        this.listForo = listForo;
        this.context = context;
        this.currentList = listForo;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTituloRecetaForo;
        private ImageView imgUrlRecetas;
        private RelativeLayout btnInformacionRecetaForo;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTituloRecetaForo = (TextView) itemView.findViewById(R.id.txtTituloRecetaForo);
            imgUrlRecetas = (ImageView) itemView.findViewById(R.id.imgUrlRecetas);
            btnInformacionRecetaForo = (RelativeLayout) itemView.findViewById(R.id.btnInformacionRecetaForo);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foro_activity_item, parent, false);
        final ItemViewHolder viewHolder = new ItemViewHolder(view);

        viewHolder.btnInformacionRecetaForo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewInforamcion = new Intent(parent.getContext(), ForoInformacionActivity.class);
                viewInforamcion.putExtra("receta_titulo", listForo.get(viewHolder.getAdapterPosition()).getTituloReceta());
                viewInforamcion.putExtra("receta_tiempo", listForo.get(viewHolder.getAdapterPosition()).getTiempoPreparacion());
                viewInforamcion.putExtra("receta_ingredientes", listForo.get(viewHolder.getAdapterPosition()).getIngredientesReceta());
                viewInforamcion.putExtra("receta_preparacion", listForo.get(viewHolder.getAdapterPosition()).getPreparacionReceta());
                viewInforamcion.putExtra("receta_background", listForo.get(viewHolder.getAdapterPosition()).getFondoReceta());

                parent.getContext().startActivity(viewInforamcion);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Foro foro = (Foro) this.listForo.get(position);

        itemViewHolder.txtTituloRecetaForo.setText(foro.getTituloReceta());

        Glide
                .with(itemViewHolder.imgUrlRecetas.getContext())
                .load(getImage(foro.getFondoReceta()))
                .into(itemViewHolder.imgUrlRecetas);

    }

    private int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }

    @Override
    public int getItemCount() {
        return listForo.size();
    }

    public void setRecetasForo(ArrayList<Foro> filteredSpacecraft){
        this.listForo = filteredSpacecraft;
    }

    public Filter getFilter() {
        if (filterHelper == null) {
            filterHelper = new FilteredHelperForoRecetas(currentList, this, context);
        }
        return filterHelper;
    }
}
