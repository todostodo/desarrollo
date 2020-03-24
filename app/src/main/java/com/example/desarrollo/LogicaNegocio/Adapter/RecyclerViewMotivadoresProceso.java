package com.example.desarrollo.LogicaNegocio.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Entidades.MotivadoresProceso;
import com.example.desarrollo.Precentacion.Motivadores.MotivadoresFragment;
import com.example.desarrollo.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMotivadoresProceso extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MotivadoresProceso> procesoList = new ArrayList<>();

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
        Button btnCanjearMotivador;
        SeekBar motivadorProgreso;
        RelativeLayout relativeBtnCajearMotivador;
        LinearLayout layoutProcesoMotivador;

        //Done
        RelativeLayout backgroundValor;
        ImageView imgMotivadorTerminado;

        public ItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            txtTotalValor = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorProceso);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcionMotivadorProceso);
            txtSumaValorProgeso = (TextView) itemView.findViewById(R.id.txtSumaValorMotivadorProceso);
            txtTotalValorProgreso = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorProceso2);
            btnCanjearMotivador = (Button) itemView.findViewById(R.id.btnMCanjear);
            motivadorProgreso = (SeekBar) itemView.findViewById(R.id.MotivadorProgreso);
            relativeBtnCajearMotivador = (RelativeLayout) itemView.findViewById(R.id.relativeBtnCajearMotivador);
            layoutProcesoMotivador = (LinearLayout) itemView.findViewById(R.id.layoutProcesoMotivador);

            backgroundValor = (RelativeLayout) itemView.findViewById(R.id.cardViewValorMotivador);
            imgMotivadorTerminado = (ImageView) itemView.findViewById(R.id.imgMotivadorTerminado);

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

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        MotivadoresProceso proceso = this.procesoList.get(position);

        int TotalFichas = proceso.getTotalFicha();
        int ValorMotivador = proceso.getValor();

        viewHolder.txtTotalValor.setText(String.valueOf(ValorMotivador));
        viewHolder.txtDescripcion.setText(proceso.getDescripcion());
        viewHolder.txtTotalValorProgreso.setText(String.valueOf(ValorMotivador));

        if (TotalFichas >= ValorMotivador) {
            viewHolder.relativeBtnCajearMotivador.setVisibility(View.VISIBLE);
            viewHolder.layoutProcesoMotivador.setVisibility(View.GONE);
        } else {
            viewHolder.txtSumaValorProgeso.setText(TotalFichas + " de");
            viewHolder.motivadorProgreso.setMax(ValorMotivador);
            viewHolder.motivadorProgreso.setProgress(TotalFichas);
        }

        if (procesoList.get(position).getActivo() == 2){
            viewHolder.relativeBtnCajearMotivador.setVisibility(View.GONE);
            viewHolder.layoutProcesoMotivador.setVisibility(View.GONE);
            viewHolder.txtTotalValor.setVisibility(View.GONE);
            viewHolder.imgMotivadorTerminado.setVisibility(View.VISIBLE);
            viewHolder.backgroundValor.setBackgroundResource(R.drawable.background_valor_motivadores_terminado);
        }

        viewHolder.motivadorProgreso.setProgress(proceso.getTotalFicha());
        viewHolder.motivadorProgreso.setMax(proceso.getValor());
    }

    @Override
    public int getItemCount() {
        return procesoList.size();
    }


}
