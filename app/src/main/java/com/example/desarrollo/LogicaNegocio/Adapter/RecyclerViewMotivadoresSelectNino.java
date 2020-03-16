package com.example.desarrollo.LogicaNegocio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.R;

import java.util.List;

public class RecyclerViewMotivadoresSelectNino extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MotivadoresSelect.MotivadoresNinoDisponible> ninoDisponibleList;

    public RecyclerViewMotivadoresSelectNino(Context context, List<MotivadoresSelect.MotivadoresNinoDisponible> ninoDisponibleList) {
        this.context = context;
        this.ninoDisponibleList = ninoDisponibleList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreNinoMotivdorSelect;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreNinoMotivdorSelect = (TextView) itemView.findViewById(R.id.txtNombreNinoMotivdorSelect);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.motivadores_select_nino_item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        MotivadoresSelect.MotivadoresNinoDisponible ninoDisponible = this.ninoDisponibleList.get(position);

        viewHolder.txtNombreNinoMotivdorSelect.setText(ninoDisponible.getNombre());
    }

    @Override
    public int getItemCount() {
        return ninoDisponibleList.size();
    }
}
