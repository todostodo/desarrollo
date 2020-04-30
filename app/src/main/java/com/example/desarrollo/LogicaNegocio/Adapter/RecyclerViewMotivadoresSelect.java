package com.example.desarrollo.LogicaNegocio.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Entidades.MotivadoresProceso;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewPreferencias;
import com.example.desarrollo.Precentacion.Motivadores.MotivadoresFragment;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMotivadoresSelect extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MotivadoresSelect> motivadoresList;
    private Dialog dialogMotivadorSelectNino;
    private MotivadoresFragment fragment = new MotivadoresFragment();

    private RecyclerView _myRecyclerView;
    private RecyclerViewMotivadoresSelectNino adapterSelectNino;

    private ArrayList<MotivadoresSelect.MotivadoresNinoDisponible> ninoDisponibleArrayList;
    private MotivadoresDao motivadoresDao;

    private NinoDao ninoDao;
    private int countNino;
    private Toastp toastp;

    private static final String TAG = "RecyclerViewMotivadores";


    public RecyclerViewMotivadoresSelect(Context context, List<MotivadoresSelect> motivadoresList) {
        this.context = context;
        this.motivadoresList = motivadoresList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtTotalValorMotivador;
        TextView txtDescripcionMotivador;
        ConstraintLayout btnMIniciarMotivador;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTotalValorMotivador = (TextView) itemView.findViewById(R.id.txtTotalValorMotivadorSelect);
            txtDescripcionMotivador = (TextView) itemView.findViewById(R.id.txtDescripcionMotivadorSelect);
            btnMIniciarMotivador = (ConstraintLayout) itemView.findViewById(R.id.btnMIniciarMotivador);

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
                        public void onClick(final View v) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                            builder.setCancelable(false);
                            builder.setMessage("Una ves asigne este motivador no podrá cambiarlo por otro, deberá terminarlo para poder hacerlo ¿Quiere asignar este motivador? ");
                            builder.setNegativeButton("Cancelar", null);
                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    boolean diponible = cantidadMotivadoresNino(ninoDisponibleArrayList.get(_myRecyclerView.getChildAdapterPosition(v)).getIdNino());

                                    if (diponible == false) {
                                        boolean existeMotivador = motivadoresRepetidos(ninoDisponibleArrayList.get(_myRecyclerView.getChildAdapterPosition(v)).getIdNino(),
                                                motivadoresList.get(viewHolder.getAdapterPosition()).getIdMotivador());

                                        if (existeMotivador == true) {
                                            String mensaje = "ya tiene asignado este motivador, elige otro";
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
                                                toastp.toastp(parent.getContext(), "Motivador asignado");

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
                            builder.show();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                    builder.setCancelable(false);
                    builder.setMessage("Una ves asigne este motivador no podrá cambiarlo por otro, deberá terminarlo para poder hacerlo ¿Quiere asignar este motivador? ");
                    builder.setNegativeButton("Cancelar", null);
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean diponible = cantidadMotivadoresNino(ninoDisponibleArrayList.get(0).getIdNino());
                            if (diponible == false) {

                                boolean existeMotivador = motivadoresRepetidos(ninoDisponibleArrayList.get(0).getIdNino(), motivadoresList.get(viewHolder.getAdapterPosition()).getIdMotivador());

                                if (existeMotivador == true) {
                                    String mensaje = "ya tiene asignado este motivador, elige otro";
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

                                    } else
                                        Toast.makeText(parent.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                String mensaje = " alcanzo el limite de motivadores debera espera hasta que termine los que tiene en proceso para poder elegir otros";
                                dialogAviso(parent.getContext(), mensaje, ninoDisponibleArrayList.get(0).getNombre());
                            }
                        }
                    });
                    builder.show();
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
        viewHolder.txtTotalValorMotivador.setText(String.valueOf(motivadores.getValor()) + " fichas");

    }

    @Override
    public int getItemCount() {
        return motivadoresList.size();
    }


    private boolean cantidadMotivadoresNino(int idNino) {
        int cantidad = motivadoresDao.countMotivadoresNino(TAG, context, idNino);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", null);
        builder.show();
    }
}
