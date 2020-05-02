package com.example.desarrollo.ExportJSON.RecycrerView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.desarrollo.Entidades.PreferenciasNino;
import com.example.desarrollo.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPreferencias extends RecyclerView.Adapter<RecyclerViewPreferencias.ViewHolder> {

    private Context context;
    private List<PreferenciasNino> preferencias;
    ChnageStatusListener chnageStatusListener;

    public interface ChnageStatusListener{
        void onItemChangeListener(int position, PreferenciasNino model);
    }

    public void setModel(ArrayList<PreferenciasNino> models){
        this.preferencias = models;
    }

    public RecyclerViewPreferencias(List<PreferenciasNino> preferencias, Context context, ChnageStatusListener chnageStatusListener) {
        this.context = context;
        this.preferencias = preferencias;
        this.chnageStatusListener = chnageStatusListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public ImageView imgUrl;
        public CardView background;
        public RelativeLayout lyt_checket;
        public int position;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombreFrutaPreferencias);
            imgUrl = (ImageView) itemView.findViewById(R.id.imgUrlFrutasPreferencias);
            background  = (CardView) itemView.findViewById(R.id.backgroundPreferencias);
            lyt_checket = (RelativeLayout) itemView.findViewById(R.id.lyt_checked);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.preferencias_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final PreferenciasNino model = (PreferenciasNino) this.preferencias.get(position);

        if (model != null){
            holder.nombre.setText(model.getNombreFruta());
            holder.position = position;
            holder.background.setCardBackgroundColor(Color.parseColor(model.getBackground()));
            Glide
                    .with(holder.imgUrl.getContext())
                    .load(getImage(model.getImgUrlFruta()))
                    .into(holder.imgUrl);

            if (model.isSelect()){
                holder.lyt_checket.setVisibility(View.VISIBLE);
            }else{
                holder.lyt_checket.setVisibility(View.GONE);
            }
        }

        holder.imgUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (model.isSelect()){
                    model.setSelect(false);
                }else{
                    model.setSelect(true);
                }
                preferencias.set(holder.position, model);
                if (chnageStatusListener != null){
                    chnageStatusListener.onItemChangeListener(holder.position, model);
                }

                notifyItemChanged(holder.position);

            }

        });
    }


    @Override
    public int getItemCount() {
        if (preferencias != null)  return preferencias.size();
        return 0;
    }

    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }
}
