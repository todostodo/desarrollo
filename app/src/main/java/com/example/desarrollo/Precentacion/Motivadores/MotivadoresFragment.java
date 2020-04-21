package com.example.desarrollo.Precentacion.Motivadores;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Entidades.MotivadoresProceso;
import com.example.desarrollo.Entidades.Nino;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewMotivadoresProceso;
import com.example.desarrollo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MotivadoresFragment extends Fragment {

    private View view;
    private RelativeLayout _btnSelectMotivador;
    private LinearLayout _layoutMotivadoresVacio;
    private TextView _txtFichasNino;
    private Spinner _spinnerMotivadoresNino;
    private SwipeRefreshLayout _refreshMotivadoresProceso;
    private String fecha;

    //Open dialog
    private Button _btnEpicMotivadorSalir;
    private ConstraintLayout _epicMotivadorContenido;
    private LinearLayout _epicMotivadorFondoNegro;
    private ImageView _epicMotivadorImg;
    private Animation fromsmall, fromnothing, forloci, togo;
    //-----------------------------[

    private ArrayList<String> listaNino;
    private ArrayList<Nino> ninoList = new ArrayList<>();

    private RecyclerView _myRecyclerViewProceso;
    private RecyclerViewMotivadoresProceso adapter;
    private ArrayList<MotivadoresProceso> procesoList = new ArrayList<>();

    private ArrayAdapter<CharSequence> adapterSpinner;
    private ArrayList totalFichas;
    private MotivadoresDao motivadoresDao;
    private NinoDao ninoDao;

    private static final String TAG = "MotivadoresFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.motivadores_fragment, container, false);

        init();
        consultarListaNino();

        _btnSelectMotivador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MotivadoresSelectActivity.class);
                startActivity(intent);
            }
        });

        _refreshMotivadoresProceso.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                consultarListaNino();
                _refreshMotivadoresProceso.setRefreshing(false);
            }
        });

        _epicMotivadorContenido.setAlpha(0);
        _epicMotivadorFondoNegro.setAlpha(0);
        _epicMotivadorImg.setVisibility(View.GONE);

        return view;
    }

    public void consultarListaNino() {

        ninoList.clear();
        ninoDao.consultarNino(TAG, getContext(), ninoList);

        listaNino = new ArrayList<>();

        for (int i = 0; i < ninoList.size(); i++) {
            listaNino.add(ninoList.get(i).getNombre());
        }

        adapterSpinner = new ArrayAdapter(getContext(), R.layout.spinner_motivadores_item, listaNino);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerMotivadoresNino.setAdapter(adapterSpinner);

        _spinnerMotivadoresNino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                consultarListaMotivadoresProceso(ninoList.get(position).getIdNino());
                consultarFichas(ninoList.get(position).getFichas());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void consultarListaMotivadoresProceso(int idNino) {

        int cantidadMotivadoresProceso = motivadoresDao.countMotivadoresProceso(TAG, getContext(), idNino);

        if (cantidadMotivadoresProceso > 0) {
            _layoutMotivadoresVacio.setVisibility(View.GONE);
            _myRecyclerViewProceso.setVisibility(View.VISIBLE);

            _myRecyclerViewProceso.setLayoutManager(new LinearLayoutManager(getActivity()));
            _myRecyclerViewProceso.setHasFixedSize(true);

            cargarMotivadores(idNino);

        } else {
            _myRecyclerViewProceso.setVisibility(View.GONE);
            _layoutMotivadoresVacio.setVisibility(View.VISIBLE);
        }
    }

    private void canjearFichas(final int posicion) {

        new AlertDialog.Builder(getContext())
                .setTitle("Aviso")
                .setMessage("Al canjear las fichas estas se disminuirán y ya no podrá canjear con otros motivadores ¿Realmente quiere canjear las fichas con este motivador?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        _epicMotivadorImg.setVisibility(View.VISIBLE);
                        _epicMotivadorImg.startAnimation(forloci);

                        _epicMotivadorFondoNegro.setAlpha(1);
                        _epicMotivadorFondoNegro.startAnimation(fromnothing);

                        _epicMotivadorContenido.setAlpha(1);
                        _epicMotivadorContenido.startAnimation(fromsmall);

                        _btnEpicMotivadorSalir.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                _epicMotivadorFondoNegro.startAnimation(togo);
                                _epicMotivadorContenido.startAnimation(togo);
                                _epicMotivadorImg.startAnimation(togo);
                                _epicMotivadorImg.setVisibility(View.GONE);

                                ViewCompat.animate(_epicMotivadorContenido).setStartDelay(1000).alpha(0).start();
                                ViewCompat.animate(_epicMotivadorFondoNegro).setStartDelay(1000).alpha(0).start();

                                setFechayHora();
                                int fichas = (procesoList.get(posicion).getTotalFicha() - procesoList.get(posicion).getValor());

                                motivadoresDao.canjerMotivador(TAG,
                                        getContext(),
                                        procesoList.get(posicion).getIdNino(),
                                        procesoList.get(posicion).getIdMotivador(),
                                        fichas,
                                        fecha);

                                consultarListaNino();

                                int desbloaquearMotivadores = motivadoresDao.desbloquearMotivadores(TAG,
                                        getContext(),
                                        procesoList.get(posicion).getIdNino());

                                if (desbloaquearMotivadores == 2) {
                                    _layoutMotivadoresVacio.setVisibility(View.VISIBLE);
                                } else {
                                    _layoutMotivadoresVacio.setVisibility(View.GONE);
                                }

                                cargarMotivadores(procesoList.get(posicion).getIdNino());
                            }
                        });

                    }
                })
                .setNegativeButton("No", null)
                .create().show();
    }

    private void cargarMotivadores(int idNino) {

        procesoList.clear();
        motivadoresDao.consultarMotivadoresProceso(TAG, getContext(), procesoList, idNino);
        adapter = new RecyclerViewMotivadoresProceso(getContext(), procesoList);


        adapter.setOnItemClickListener(new RecyclerViewMotivadoresProceso.OnItemClickListener() {
            @Override
            public void onCanjerFicharClick(int posicion) {
                canjearFichas(posicion);
            }
        });

        _myRecyclerViewProceso.setAdapter(adapter);

    }

    private void setFechayHora() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        fecha = dateFormat.format(date);
    }


    private void consultarFichas(int cantidad) {
        _txtFichasNino.setText(String.valueOf(cantidad));
    }


    private void init() {
        _btnSelectMotivador = (RelativeLayout) view.findViewById(R.id.btnSelectMotivador);
        _layoutMotivadoresVacio = (LinearLayout) view.findViewById(R.id.layoutMotivadoresVacio);
        _txtFichasNino = (TextView) view.findViewById(R.id.txtFichasNino);
        _spinnerMotivadoresNino = (Spinner) view.findViewById(R.id.spinnerMotivadoresNino);
        _myRecyclerViewProceso = (RecyclerView) view.findViewById(R.id.myRecyclerViewMotivadoresProceso);
        _refreshMotivadoresProceso = (SwipeRefreshLayout) view.findViewById(R.id.refreshMotivadoresProceso);

        //Open Dialog

        _btnEpicMotivadorSalir = (Button) view.findViewById(R.id.btnEpicMotivadorSalir);
        _epicMotivadorContenido = (ConstraintLayout) view.findViewById(R.id.epicMotivadorContenido);
        _epicMotivadorFondoNegro = (LinearLayout) view.findViewById(R.id.epicMotivadorFondoNegro);
        _epicMotivadorImg = (ImageView) view.findViewById(R.id.epicMotivadorImg);

        fromsmall = AnimationUtils.loadAnimation(getContext(), R.anim.fromsmall);
        fromnothing = AnimationUtils.loadAnimation(getContext(), R.anim.fromnothing);
        forloci = AnimationUtils.loadAnimation(getContext(), R.anim.forloci);
        togo = AnimationUtils.loadAnimation(getContext(), R.anim.togo);
    }
}
