package com.example.desarrollo.LogicaNegocio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.desarrollo.Entidades.MotivadoresProceso;
import com.example.desarrollo.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMotivadoresProceso extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MotivadoresProceso> procesoList = new ArrayList<>();

    public RecyclerViewMotivadoresProceso(Context context, List<MotivadoresProceso> procesoList) {
        this.context = context;
        this.procesoList = procesoList;
    }

    public RecyclerViewMotivadoresProceso(){}

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtTotalValor;
        TextView txtDescripcion;
        TextView txtSumaValorProgeso;
        TextView txtTotalValorProgreso;
        SeekBar motivadorProgreso;
        RelativeLayout relativeBtnCajearMotivador;
        LinearLayout layoutProcesoMotivador;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTotalValor = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorProceso);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcionMotivadorProceso);
            txtSumaValorProgeso = (TextView) itemView.findViewById(R.id.txtSumaValorMotivadorProceso);
            txtTotalValorProgreso = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorProceso2);
            motivadorProgreso = (SeekBar) itemView.findViewById(R.id.MotivadorProgreso);
            relativeBtnCajearMotivador = (RelativeLayout) itemView.findViewById(R.id.relativeBtnCajearMotivador);
            layoutProcesoMotivador = (LinearLayout) itemView.findViewById(R.id.layoutProcesoMotivador);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.motivadores_proceso_item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        MotivadoresProceso proceso = this.procesoList.get(position);

        viewHolder.txtTotalValor.setText(String.valueOf(proceso.getValor()));
        viewHolder.txtDescripcion.setText(proceso.getDescripcion());
        viewHolder.txtTotalValorProgreso.setText(String.valueOf(proceso.getValor()));

        if (proceso.getTotalFicha() > proceso.getValor()){
            viewHolder.txtSumaValorProgeso.setText(String.valueOf(proceso.getValor()) + " de");
            viewHolder.relativeBtnCajearMotivador.setVisibility(View.VISIBLE);
            viewHolder.layoutProcesoMotivador.setVisibility(View.GONE);
        }
        else{
            viewHolder.txtSumaValorProgeso.setText(String.valueOf(proceso.getValor()) + " de");
        }

        viewHolder.motivadorProgreso.setProgress(proceso.getTotalFicha());
        viewHolder.motivadorProgreso.setMax(proceso.getValor());
    }

    @Override
    public int getItemCount() {
        return procesoList.size();
    }

    public void addItem(){
        notifyDataSetChanged();
    }
}
