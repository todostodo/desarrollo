package com.example.desarrollo.LogicaNegocio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Entidades.Tutor;
import com.example.desarrollo.R;

import java.util.List;

public class RecyclerViewTutor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Context context;
    List<Tutor> tutorList;

    public RecyclerViewTutor(List<Tutor> tutorList) {
        //this.context = context;
        this.tutorList = tutorList;
    }

    public  class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        TextView parentesco;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtTutorItemNombre);
            parentesco = (TextView) itemView.findViewById(R.id.txtTutorItemParentesco);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutor_items, parent, false);

        ItemViewHolder viewHolder = new ItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        Tutor tutor = this.tutorList.get(position);

        viewHolder.nombre.setText(tutor.getNombreTutor());
        viewHolder.parentesco.setText(tutor.getParentesto());

    }

    @Override
    public int getItemCount() {
        return tutorList.size();
    }
}
