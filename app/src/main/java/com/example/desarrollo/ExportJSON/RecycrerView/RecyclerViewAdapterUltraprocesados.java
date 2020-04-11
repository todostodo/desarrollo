package com.example.desarrollo.ExportJSON.RecycrerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.ExportJSON.Filter.FilteredHelperUltraprocesados;
import com.example.desarrollo.ExportJSON.Reader.ReaderUltraprocesados;
import com.example.desarrollo.R;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterUltraprocesados extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    Context context;
    List<ReaderUltraprocesados> readerUltraprocesados;
    public ArrayList<ReaderUltraprocesados> currentList;
    FilteredHelperUltraprocesados filterHelper;

    BottomSheetDialog dialogUltraprocesados;

    public RecyclerViewAdapterUltraprocesados(Context context, ArrayList<ReaderUltraprocesados> readerUltraprocesados) {
        this.context = context;
        this.readerUltraprocesados = readerUltraprocesados;
        this.currentList = readerUltraprocesados;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView nombreBebida;
        TextView caloriasBebida;
        CardView view_container;
        TextView nombre;
        //LinearLayout layout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            view_container = (CardView) itemView.findViewById(R.id.containerUltraprocesados);
            nombreBebida = (TextView) itemView.findViewById(R.id.nombreUltraprocesados);
            caloriasBebida = (TextView) itemView.findViewById(R.id.caloriasUltraprocesados);


            nombre = (TextView) itemView.findViewById(R.id.nombreDialog);

            //layout = (LinearLayout) itemView.findViewById(R.id.ultraprocesadosDialog);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view;

        view = LayoutInflater.from(context).inflate(R.layout.ultraprocesados_items, parent, false);

        final ItemViewHolder viewHolder = new ItemViewHolder(view);

        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogUltraprocesados = new BottomSheetDialog(parent.getContext());
                dialogUltraprocesados.setContentView(R.layout.ultraprocesados_dialog);
                dialogUltraprocesados.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView nombreDialog = (TextView) dialogUltraprocesados.findViewById(R.id.nombreDialog);
                Button btnRegistrarAlimento = (Button) dialogUltraprocesados.findViewById(R.id.btnRegistrarUltraprocesado);
                nombreDialog.setText(readerUltraprocesados.get(viewHolder.getAdapterPosition()).getNombre());

                btnRegistrarAlimento.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogUltraprocesados.dismiss();
                    }
                });

                //Toast.makeText(context, "select: " + String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();

                dialogUltraprocesados.show();

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        ReaderUltraprocesados readerUltraprocesados = (ReaderUltraprocesados) this.readerUltraprocesados.get(position);

        itemViewHolder.nombreBebida.setText(readerUltraprocesados.getNombre());
        itemViewHolder.caloriasBebida.setText(readerUltraprocesados.getCalorias());

        //Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_transition_animation);
        //holder.itemView.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return readerUltraprocesados.size();
    }


    public void setReaderUltraprocesados(ArrayList<ReaderUltraprocesados> filteredSpacecrafts)
    {
        this.readerUltraprocesados = filteredSpacecrafts;

    }

    @Override
    public Filter getFilter() {
        if(filterHelper == null)
        {
            filterHelper = new FilteredHelperUltraprocesados(currentList,this, context);
        }

        return filterHelper;
    }
}
