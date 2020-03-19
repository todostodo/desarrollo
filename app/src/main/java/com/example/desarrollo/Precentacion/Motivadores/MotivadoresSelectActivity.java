package com.example.desarrollo.Precentacion.Motivadores;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewPreferencias;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewMotivadoresSelect;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewMotivadoresSelectNino;
import com.example.desarrollo.Precentacion.MainActivity;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class MotivadoresSelectActivity extends AppCompatActivity  {

    private RecyclerView _myRecyclerViewMotivadores;
    private RecyclerViewMotivadoresSelect adapter;
    private ArrayList<MotivadoresSelect> motivadoresList = new ArrayList<>();

    MotivadoresDao consultar;

    private static final String TAG = "MotivadoresSelectActivity";

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
        motivadoresList.clear();
        consultar.cosultarMotivadores(TAG, getApplicationContext(), motivadoresList);
    }

}
