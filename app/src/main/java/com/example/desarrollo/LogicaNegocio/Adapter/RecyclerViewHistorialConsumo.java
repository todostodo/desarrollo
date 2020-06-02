package com.example.desarrollo.LogicaNegocio.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.print.PrinterId;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.desarrollo.Entidades.HistorialConsumo;
import com.example.desarrollo.R;

import java.util.List;

public class RecyclerViewHistorialConsumo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HistorialConsumo> consumoList;

    public RecyclerViewHistorialConsumo(Context context, List<HistorialConsumo> consumoList) {
        this.context = context;
        this.consumoList = consumoList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView consumo;
        TextView hora;
        TextView porciones;
        TextView backgroundAlimento;
        ImageView imgUrlAlimento;
        CardView cardBackgroudHistoryItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtHrNombre);
            consumo = (TextView) itemView.findViewById(R.id.txtHrConsumo);
            hora = (TextView) itemView.findViewById(R.id.txtHRHora);
            porciones = (TextView) itemView.findViewById(R.id.txtHrPorciones);
            backgroundAlimento = (TextView) itemView.findViewById(R.id.backgroundHrFrutas);
            imgUrlAlimento = (ImageView) itemView.findViewById(R.id.imgUrlHrFrutas);
            cardBackgroudHistoryItem = (CardView) itemView.findViewById(R.id.cardBackgroudHistoryItem);

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        HistorialConsumo historialConsumo = this.consumoList.get(position);

        viewHolder.nombre.setText(historialConsumo.getNombreAlimentos());
        viewHolder.consumo.setText(String.valueOf(historialConsumo.getCantidadAlimento()));
        viewHolder.porciones.setText(String.valueOf(historialConsumo.getUnidadMedida()));
        viewHolder.hora.setText(historialConsumo.getHora());
        viewHolder.cardBackgroudHistoryItem.setCardBackgroundColor(Color.parseColor("#33" + historialConsumo.getBackgroundAlimento()));

        viewHolder.backgroundAlimento.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + historialConsumo.getBackgroundAlimento())));
        Glide
                .with(viewHolder.imgUrlAlimento.getContext())
                .load(getImage(historialConsumo.getImgUrl()))
                .into(viewHolder.imgUrlAlimento);

    }

    @Override
    public int getItemCount() {
        return consumoList.size();
    }

    private int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }
}
