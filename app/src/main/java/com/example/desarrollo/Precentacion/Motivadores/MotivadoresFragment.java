package com.example.desarrollo.Precentacion.Motivadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
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

import java.util.ArrayList;
import java.util.List;

public class MotivadoresFragment extends Fragment {

    private View view;
    private CardView _btnSelectMotivador;
    private LinearLayout _layoutMotivadoresVacio;
    private TextView _fichasNino1Motivador;
    private TextView _nombreNino1Motivador;
    private TextView _fichasNino2Motivador;
    private TextView _nombreNino2Motivador;
    private ImageView _imgCirculoMotivadores;
    private LinearLayout _layourMotivador2Nino;
    private LinearLayout _linearTotalFicha;
    private RelativeLayout _relativeSpinner;
    private Spinner _spinnerMotivadoresNino;
    private SwipeRefreshLayout _refreshMotivadoresProceso;

    private ArrayList<String> listaNino;
    private ArrayList<Nino> ninoList = new ArrayList<>();

    private RecyclerView _myRecyclerViewProceso;
    private RecyclerViewMotivadoresProceso adapter;
    private ArrayList<MotivadoresProceso> procesoList = new ArrayList<>();

    private ArrayAdapter<CharSequence> adapterSpinner;
    private ArrayList totalFichas;
    private MotivadoresDao consultar;
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



        return view;
    }

    public void consultarListaNino() {

        ninoList.clear();
        ninoDao.consultarNino(TAG, getContext(), ninoList);

        listaNino = new ArrayList<>();

        for (int i = 0; i < ninoList.size(); i++) {
            listaNino.add(ninoList.get(i).getNombre());
        }

        adapterSpinner = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listaNino);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerMotivadoresNino.setAdapter(adapterSpinner);

        _spinnerMotivadoresNino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                consultarListaMotivadoresProceso(ninoList.get(position).getIdNino());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void consultarListaMotivadoresProceso(int idNino) {

        int cantidadNino = ninoDao.countNino(TAG, getContext());

        if (cantidadNino < 1) {
            _linearTotalFicha.setVisibility(View.GONE);
        } else {
            int cantidadMotivadoresProceso = consultar.countMotivadoresProceso(TAG, getContext());
            totalFichas = ninoDao.countFichasNino(TAG, getContext());

            _nombreNino1Motivador.setText(String.valueOf(totalFichas.get(1)));
            _fichasNino1Motivador.setText(String.valueOf(totalFichas.get(0)));

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

            if (cantidadNino > 1) {
                _imgCirculoMotivadores.setVisibility(View.VISIBLE);
                _layourMotivador2Nino.setVisibility(View.VISIBLE);
                _relativeSpinner.setVisibility(View.VISIBLE);
                _nombreNino2Motivador.setText(String.valueOf(totalFichas.get(3)));
                _fichasNino2Motivador.setText(String.valueOf(totalFichas.get(2)));

            }
        }
    }

    public void cargarMotivadores(int idNino) {

        procesoList.clear();
        consultar.consultarMotivadoresProceso(TAG, getContext(), procesoList, idNino);
        adapter = new RecyclerViewMotivadoresProceso(getContext(), procesoList);
        _myRecyclerViewProceso.setAdapter(adapter);
        //Toast.makeText(getContext(), "Id " + idNino, Toast.LENGTH_SHORT).show();
    }

    private void init() {
        _btnSelectMotivador = (CardView) view.findViewById(R.id.btnSelectMotivador);
        _layoutMotivadoresVacio = (LinearLayout) view.findViewById(R.id.layoutMotivadoresVacio);
        _fichasNino1Motivador = (TextView) view.findViewById(R.id.fichasNino1Motivador);
        _nombreNino1Motivador = (TextView) view.findViewById(R.id.nombreNino1Motivador);
        _fichasNino2Motivador = (TextView) view.findViewById(R.id.fichasNino2Motivador);
        _nombreNino2Motivador = (TextView) view.findViewById(R.id.nombreNino2Motivador);
        _spinnerMotivadoresNino = (Spinner) view.findViewById(R.id.spinnerMotivadoresNino);
        _relativeSpinner = (RelativeLayout) view.findViewById(R.id.relativeSpinner);
        _myRecyclerViewProceso = (RecyclerView) view.findViewById(R.id.myRecyclerViewMotivadoresProceso);
        _imgCirculoMotivadores = (ImageView) view.findViewById(R.id.imgCirculoMotivadores);
        _layourMotivador2Nino = (LinearLayout) view.findViewById(R.id.layourMotivador2Nino);
        _linearTotalFicha = (LinearLayout) view.findViewById(R.id.linearTotalFicha);
        _refreshMotivadoresProceso = (SwipeRefreshLayout) view.findViewById(R.id.refreshMotivadoresProceso);
    }
}
