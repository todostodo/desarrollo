package com.example.desarrollo.Precentacion.Motivadores;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewMotivadoresSelect;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class MotivadoresSelectActivity extends FragmentActivity {

    private RecyclerView _myRecyclerViewMotivadores;
    private RecyclerViewMotivadoresSelect adapter;
    private ArrayList<MotivadoresSelect> motivadoresList = new ArrayList<>();

    MotivadoresDao consultar;

    private static final String TAG = "MotivadoresSelectActivi";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motivadores_select_activity);

        init();

        _myRecyclerViewMotivadores.setLayoutManager(new LinearLayoutManager(this));
        consultarListaMotivadoresDisponibles();

        adapter = new RecyclerViewMotivadoresSelect(getApplicationContext(), motivadoresList);
        _myRecyclerViewMotivadores.setAdapter(adapter);
    }

    private void init() {
        _myRecyclerViewMotivadores = (RecyclerView) findViewById(R.id.myRecyclerViewMotivadoresDisponibles);
    }

    private void consultarListaMotivadoresDisponibles() {
        consultar.cosultarMotivadores(TAG, getApplicationContext(), motivadoresList);
    }

}
