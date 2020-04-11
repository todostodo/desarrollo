package com.example.desarrollo.LogicaNegocio.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Entidades.MotivadoresProceso;
import com.example.desarrollo.Precentacion.Motivadores.MotivadoresFragment;
import com.example.desarrollo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewMotivadoresProceso extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MotivadoresProceso> procesoList = new ArrayList<>();
    int i = 0;

    private OnItemClickListener mListener;

    public RecyclerViewMotivadoresProceso(Context context, ArrayList<MotivadoresProceso> procesoList) {
        this.context = context;
        this.procesoList = procesoList;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onCanjerFicharClick(int posicion);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtTotalValor;
        TextView txtDescripcion;
        TextView txtSumaValorProgeso;
        TextView txtTotalValorProgreso;
        TextView txtPorcentajeMotivadoresProceso;
        Button btnCanjearMotivador;
        SeekBar motivadorProgreso;
        LinearLayout layoutProcesoMotivador;
        ConstraintLayout backgroundMotivadoresProgreso;
        ArrayList<String> listColor = new ArrayList<>();


        public ItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            txtTotalValor = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorProceso);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcionMotivadorProceso);
            txtSumaValorProgeso = (TextView) itemView.findViewById(R.id.txtSumaValorMotivadorProceso);
            txtTotalValorProgreso = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorProceso2);
            txtPorcentajeMotivadoresProceso = (TextView) itemView.findViewById(R.id.txtPorcentajeMotivadoresProceso);
            btnCanjearMotivador = (Button) itemView.findViewById(R.id.btnMCanjear);
            motivadorProgreso = (SeekBar) itemView.findViewById(R.id.MotivadorProgreso);
            layoutProcesoMotivador = (LinearLayout) itemView.findViewById(R.id.layoutProcesoMotivador);
            backgroundMotivadoresProgreso = (ConstraintLayout) itemView.findViewById(R.id.backgroundMotivadoresProgreso);

            listColor.add("#D589F9");
            listColor.add("#8A87FF");
            listColor.add("#95C3C7");
            listColor.add("#ADD6A7");

            btnCanjearMotivador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int posicion = getAdapterPosition();
                        if (posicion != RecyclerView.NO_POSITION) {
                            listener.onCanjerFicharClick(posicion);
                        }
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.motivadores_proceso_item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view, mListener);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        MotivadoresProceso proceso = this.procesoList.get(position);

        boolean terminoMotivador = false;
        viewHolder.backgroundMotivadoresProgreso.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(viewHolder.listColor.get(i))));
        i++;
        if (i == 4)
            i = 0;

        int totalFichas = proceso.getTotalFicha();
        int valorMotivador = proceso.getValor();


        viewHolder.txtTotalValor.setText(String.valueOf(valorMotivador));
        viewHolder.txtDescripcion.setText(proceso.getDescripcion());
        viewHolder.txtTotalValorProgreso.setText(String.valueOf(valorMotivador));

        if (totalFichas >= valorMotivador) {
            viewHolder.btnCanjearMotivador.setVisibility(View.VISIBLE);
            viewHolder.layoutProcesoMotivador.setVisibility(View.GONE);
            terminoMotivador = true;
        } else {
            viewHolder.txtSumaValorProgeso.setText(totalFichas + " de");
            viewHolder.motivadorProgreso.setMax(valorMotivador);
            viewHolder.motivadorProgreso.setProgress(totalFichas);
            terminoMotivador = false;
        }

        if (procesoList.get(position).getActivo() == 2) {
            viewHolder.txtPorcentajeMotivadoresProceso.setText("100");
            viewHolder.btnCanjearMotivador.setVisibility(View.GONE);
            viewHolder.layoutProcesoMotivador.setVisibility(View.GONE);
            viewHolder.txtTotalValor.setVisibility(View.GONE);
        } else {
            viewHolder.txtPorcentajeMotivadoresProceso.setText(String.valueOf(porcentajeMotivador(valorMotivador, totalFichas)));
        }
        if (terminoMotivador == true){
            viewHolder.txtPorcentajeMotivadoresProceso.setText("100");
        }

        viewHolder.motivadorProgreso.setProgress(proceso.getTotalFicha());
        viewHolder.motivadorProgreso.setMax(proceso.getValor());
    }

    @Override
    public int getItemCount() {
        return procesoList.size();
    }

    private double porcentajeMotivador(int valorMotivador, int totalFichar) {

        double x1 = (100 * totalFichar);
        double x2 = (x1 / valorMotivador);

        long factor = (long) Math.pow(10, 2);
        x2 = x2 * factor;
        long round = Math.round(x2);

        return (double) round / factor;
    }
}
