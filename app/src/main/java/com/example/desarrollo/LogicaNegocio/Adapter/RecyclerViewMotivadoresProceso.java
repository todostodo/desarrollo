package com.example.desarrollo.LogicaNegocio.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Entidades.MotivadoresProceso;
import com.example.desarrollo.R;

import java.util.ArrayList;
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

        // TextView txtTotalValor;
        TextView txtDescripcion;
        TextView txtSumaValorProgeso;
        TextView txtTotalValorProgreso;
        TextView txtPorcentajeMotivadoresProceso;
        Button btnCanjearMotivador, btnMEnProgreso;
        SeekBar motivadorProgreso;
        ConstraintLayout constProcesoMotivador;
        ImageView imgTerminoMotivador, imgRegaloMotivador;

        public ItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            //txtTotalValor = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorProceso);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcionMotivadorProceso);
            txtSumaValorProgeso = (TextView) itemView.findViewById(R.id.txtSumaValorMotivadorProceso);
            txtTotalValorProgreso = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorProceso);
            txtPorcentajeMotivadoresProceso = (TextView) itemView.findViewById(R.id.txtPorcentajeMotivadoresProceso);
            btnCanjearMotivador = (Button) itemView.findViewById(R.id.btnMCanjear);
            btnMEnProgreso = (Button) itemView.findViewById(R.id.btnMEnProgreso);
            motivadorProgreso = (SeekBar) itemView.findViewById(R.id.MotivadorProgreso);
            constProcesoMotivador = (ConstraintLayout) itemView.findViewById(R.id.constProcesoMotivador);
            imgTerminoMotivador = (ImageView) itemView.findViewById(R.id.imgTerminoMotivador);
            imgRegaloMotivador = (ImageView) itemView.findViewById(R.id.imgRegaloMotivador);

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

        int totalFichas = proceso.getTotalFicha();
        int valorMotivador = proceso.getValor();

        viewHolder.txtDescripcion.setText(proceso.getDescripcion());
        viewHolder.txtTotalValorProgreso.setText(String.valueOf(valorMotivador));

        viewHolder.motivadorProgreso.setMax(valorMotivador);
        viewHolder.motivadorProgreso.setProgress(totalFichas);

        if (totalFichas >= valorMotivador) {
            viewHolder.btnMEnProgreso.setVisibility(View.GONE);
            viewHolder.btnCanjearMotivador.setVisibility(View.VISIBLE);
            viewHolder.txtSumaValorProgeso.setText(String.valueOf(valorMotivador) + "/");
            viewHolder.txtSumaValorProgeso.setTextColor(Color.parseColor("#4B9D4C"));
            viewHolder.txtTotalValorProgreso.setTextColor(Color.parseColor("#4B9D4C"));

            terminoMotivador = true;

        } else {
            viewHolder.txtSumaValorProgeso.setText(totalFichas + "/");
            terminoMotivador = false;
        }

        if (procesoList.get(position).getActivo() == 2) {
            viewHolder.imgTerminoMotivador.setVisibility(View.VISIBLE);
            viewHolder.txtPorcentajeMotivadoresProceso.setText("100%");
            viewHolder.btnCanjearMotivador.setVisibility(View.GONE);
            viewHolder.constProcesoMotivador.setVisibility(View.GONE);
            viewHolder.motivadorProgreso.setMax(1);
            viewHolder.motivadorProgreso.setProgress(1);
            viewHolder.imgRegaloMotivador.setImageResource(R.drawable.icon_regalo_abierto);

        } else {
            viewHolder.txtPorcentajeMotivadoresProceso.setText(String.valueOf(porcentajeMotivador(valorMotivador, totalFichas)));
        }
        if (terminoMotivador == true) {
            viewHolder.txtPorcentajeMotivadoresProceso.setText("100%");
        }
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
