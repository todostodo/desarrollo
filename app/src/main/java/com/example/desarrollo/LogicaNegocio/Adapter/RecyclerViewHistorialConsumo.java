package com.example.desarrollo.LogicaNegocio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Entidades.HistorialConsumo;
import com.example.desarrollo.R;

import java.util.List;

public class RecyclerViewHistorialConsumo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<HistorialConsumo> consumoList;

    public RecyclerViewHistorialConsumo(Context context, List<HistorialConsumo> consumoList) {
        this.context = context;
        this.consumoList = consumoList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        TextView consumo;
        TextView hora;
        TextView verMas;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtHrNombre);
            consumo = (TextView) itemView.findViewById(R.id.txtHrConsumo);
            hora = (TextView) itemView.findViewById(R.id.txtHRHora);

            verMas = (TextView) itemView.findViewById(R.id.btnHrMasDetalle);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.hostorial_items, parent, false);


        ItemViewHolder viewHolder = new ItemViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        HistorialConsumo historialConsumo = this.consumoList.get(position);

        viewHolder.nombre.setText(historialConsumo.getNombreAlimentos());
        viewHolder.consumo.setText(historialConsumo.getConsumo());
        viewHolder.hora.setText(historialConsumo.getHora());

    }

    @Override
    public int getItemCount() {
        return consumoList.size();
    }
}
