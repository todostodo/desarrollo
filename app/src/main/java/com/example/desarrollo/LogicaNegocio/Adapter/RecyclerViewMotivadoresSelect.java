package com.example.desarrollo.LogicaNegocio.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMotivadoresSelect extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MotivadoresSelect> motivadoresList;
    Dialog dialogMotivadorSelectNino;

    RecyclerView _myRecyclerView;
    RecyclerViewMotivadoresSelectNino adapter;

    ArrayList<MotivadoresSelect.MotivadoresNinoDisponible> ninoDisponibleArrayList;
    MotivadoresDao motivadoresDao;

    NinoDao ninoDao;
    int countNino;

    private static final String TAG = "RecyclerViewMotivadores";

    public RecyclerViewMotivadoresSelect(Context context, List<MotivadoresSelect> motivadoresList) {
        this.context = context;
        this.motivadoresList = motivadoresList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtTotalValorMotivador;
        TextView txtDescripcionMotivador;
        Button btnMIniciarMotivador;
        RelativeLayout _btnAsignarMotivadorNino;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTotalValorMotivador = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorSelect);
            txtDescripcionMotivador = (TextView) itemView.findViewById(R.id.txtDescripcionMotivadorSelect);
            btnMIniciarMotivador = (Button) itemView.findViewById(R.id.btnMIniciarMotivador);
            _btnAsignarMotivadorNino = (RelativeLayout) itemView.findViewById(R.id.)
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.motivadores_select_item, parent, false);
        final ItemViewHolder viewHolder = new ItemViewHolder(view);


        dialogMotivadorSelectNino = new Dialog(parent.getContext());
        dialogMotivadorSelectNino.setContentView(R.layout.motivadores_select_nino);
        dialogMotivadorSelectNino.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        _myRecyclerView = (RecyclerView) dialogMotivadorSelectNino.findViewById(R.id.myRecyclerViewMotivadoresSelectNino);
        ninoDisponibleArrayList = new ArrayList<>();
        _myRecyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));

        motivadoresDao.consultarNino(TAG, parent.getContext(), ninoDisponibleArrayList);
        countNino = ninoDao.countNino(TAG, parent.getContext());

        adapter = new RecyclerViewMotivadoresSelectNino(parent.getContext(), ninoDisponibleArrayList);
        _myRecyclerView.setAdapter(adapter);

        viewHolder.btnMIniciarMotivador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView btnCancelarSelectNinoMotivador = (TextView) dialogMotivadorSelectNino.findViewById(R.id.btnCancelarSelectNinoMotivador);


                if (countNino > 1) {

                    btnAsignarMotivador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean asignarMotivador = motivadoresDao.insertMotivadoresProceso
                                    (
                                            TAG,
                                            parent.getContext(),
                                            ninoDisponibleArrayList.get(viewHolder.getAdapterPosition()).getIdNino(),
                                            motivadoresList.get(viewHolder.getAdapterPosition()).getIdMotivador(),
                                            1
                                    );
                            if (asignarMotivador == true){
                                Toast.makeText(parent.getContext(), "Insertado" + viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                                removeItem(viewHolder.getAdapterPosition());
                            }
                            else
                                Toast.makeText(parent.getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                    /*
                    btnCancelarSelectNinoMotivador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogMotivadorSelectNino.dismiss();
                        }
                    });

                     */

                    dialogMotivadorSelectNino.show();
                } else {
                    boolean asignarMotivador = motivadoresDao.insertMotivadoresProceso(
                            TAG,
                            parent.getContext(),
                            ninoDisponibleArrayList.get(0).getIdNino(),
                            motivadoresList.get(viewHolder.getAdapterPosition()).getIdMotivador(),
                            1
                    );
                    if (asignarMotivador == true){
                        Toast.makeText(parent.getContext(), "Insertado", Toast.LENGTH_SHORT).show();
                        removeItem(viewHolder.getAdapterPosition());
                    }
                    else
                        Toast.makeText(parent.getContext(), "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        MotivadoresSelect motivadores = this.motivadoresList.get(position);

        viewHolder.txtDescripcionMotivador.setText(motivadores.getDescripcion());
        viewHolder.txtTotalValorMotivador.setText(String.valueOf(motivadores.getValor()));

    }

    @Override
    public int getItemCount() {
        return motivadoresList.size();
    }

    public void removeItem(int item){
        motivadoresList.remove(item);
        notifyItemRemoved(item);
    }

}
