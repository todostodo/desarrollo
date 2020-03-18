package com.example.desarrollo.LogicaNegocio.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Entidades.MotivadoresProceso;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.Precentacion.Motivadores.MotivadoresFragment;
import com.example.desarrollo.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMotivadoresSelect extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MotivadoresSelect> motivadoresList;
    Dialog dialogMotivadorSelectNino;

    RecyclerView _myRecyclerView;
    RecyclerViewMotivadoresSelectNino adapterSelectNino;

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

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTotalValorMotivador = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorSelect);
            txtDescripcionMotivador = (TextView) itemView.findViewById(R.id.txtDescripcionMotivadorSelect);
            btnMIniciarMotivador = (Button) itemView.findViewById(R.id.btnMIniciarMotivador);

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

        adapterSelectNino = new RecyclerViewMotivadoresSelectNino(parent.getContext(), ninoDisponibleArrayList);
        _myRecyclerView.setAdapter(adapterSelectNino);


        viewHolder.btnMIniciarMotivador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView btnCancelarSelectNinoMotivador = (TextView) dialogMotivadorSelectNino.findViewById(R.id.btnCancelarSelectNinoMotivador);

                if (countNino > 1) {

                    adapterSelectNino.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            boolean diponible = cantidadMotivadoresNino(ninoDisponibleArrayList.get(_myRecyclerView.getChildAdapterPosition(v)).getIdNino());

                            if (diponible == false) {
                                boolean existeMotivador = motivadoresRepetidos(ninoDisponibleArrayList.get(_myRecyclerView.getChildAdapterPosition(v)).getIdNino(),
                                        motivadoresList.get(viewHolder.getAdapterPosition()).getIdMotivador());

                                if (existeMotivador == true) {
                                    String mensaje = " ya tiene asignado ese motivador";
                                    dialogAviso(parent.getContext(), mensaje, ninoDisponibleArrayList.get(_myRecyclerView.getChildAdapterPosition(v)).getNombre());
                                } else {

                                    boolean asignarMotivador = motivadoresDao.insertMotivadoresProceso(
                                            TAG,
                                            parent.getContext(),
                                            ninoDisponibleArrayList.get(_myRecyclerView.getChildAdapterPosition(v)).getIdNino(),
                                            motivadoresList.get(viewHolder.getAdapterPosition()).getIdMotivador(),
                                            1
                                    );
                                    if (asignarMotivador == true) {
                                        Toast.makeText(parent.getContext(), "Insertado", Toast.LENGTH_SHORT).show();
                                        //addItemRecycler(ninoDisponibleArrayList.get(_myRecyclerView.getChildAdapterPosition(v)).getIdNino());
                                        dialogMotivadorSelectNino.dismiss();
                                    } else
                                        Toast.makeText(parent.getContext(), "Error", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                String mensaje = " alcanzo el limite de motivadores debera espera hasta que termine los que tiene en proceso para poder elegir otros";
                                dialogAviso(parent.getContext(), mensaje, ninoDisponibleArrayList.get(_myRecyclerView.getChildAdapterPosition(v)).getNombre());
                            }
                        }

                    });

                    btnCancelarSelectNinoMotivador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogMotivadorSelectNino.dismiss();
                        }
                    });

                    dialogMotivadorSelectNino.show();

                } else {
                    boolean diponible = cantidadMotivadoresNino(ninoDisponibleArrayList.get(0).getIdNino());
                    if (diponible == false) {

                        boolean existeMotivador = motivadoresRepetidos(ninoDisponibleArrayList.get(0).getIdNino(), motivadoresList.get(viewHolder.getAdapterPosition()).getIdMotivador());

                        if (existeMotivador == true) {
                            String mensaje = " ya tiene asignado ese motivador";
                            dialogAviso(parent.getContext(), mensaje, ninoDisponibleArrayList.get(0).getNombre());
                        } else {

                            boolean asignarMotivador = motivadoresDao.insertMotivadoresProceso(
                                    TAG,
                                    parent.getContext(),
                                    ninoDisponibleArrayList.get(0).getIdNino(),
                                    motivadoresList.get(viewHolder.getAdapterPosition()).getIdMotivador(),
                                    1
                            );
                            if (asignarMotivador == true) {
                                Toast.makeText(parent.getContext(), "Insertado", Toast.LENGTH_SHORT).show();

                                //addItemRecycler(ninoDisponibleArrayList.get(0).getIdNino());
                            } else
                                Toast.makeText(parent.getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String mensaje = " alcanzo el limite de motivadores debera espera hasta que termine los que tiene en proceso para poder elegir otros";
                        dialogAviso(parent.getContext(), mensaje, ninoDisponibleArrayList.get(0).getNombre());
                    }
                }

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final ItemViewHolder viewHolder = (ItemViewHolder) holder;
        final MotivadoresSelect motivadores = this.motivadoresList.get(position);

        viewHolder.txtDescripcionMotivador.setText(motivadores.getDescripcion());
        viewHolder.txtTotalValorMotivador.setText(String.valueOf(motivadores.getValor()));

    }

    @Override
    public int getItemCount() {
        return motivadoresList.size();
    }

    private void addItemRecycler(int idNino){
        MotivadoresFragment motivadoresFragment = new MotivadoresFragment();
        motivadoresFragment.cargarMotivadores(idNino);
    }

    private boolean cantidadMotivadoresNino(int idNino) {
        int cantidad = motivadoresDao.countMotivadoresNino(TAG, context, idNino);
        Toast.makeText(context, "cantidad " + cantidad, Toast.LENGTH_SHORT).show();
        if (cantidad > 3)
            return true;
        else
            return false;
    }

    private boolean motivadoresRepetidos(int idNino, int idMotivador) {
        int existe = motivadoresDao.existeMotivadorNino(TAG, context, idNino, idMotivador);
        if (existe == 1) {
            return true;
        } else {
            return false;
        }
    }

    private void dialogAviso(Context context, String mensaje, String nombre) {
        new AlertDialog.Builder(context)
                .setTitle("Aviso")
                .setMessage(nombre + " " + mensaje)
                .setPositiveButton(android.R.string.yes, null).create().show();
    }
}
