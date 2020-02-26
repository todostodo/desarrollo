package com.example.desarrollo.CLIENTE.Alimentos.Ultraprocesados;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.ExportJSON.Model.ModelUltraprocesados;
import com.example.desarrollo.ExportJSON.Reader.ReaderUltraprocesados;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewAdapterUltraprocesados;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class BebidasFragment extends Fragment {

    ArrayList<ReaderUltraprocesados> bebidasItem = new ArrayList<>();
    RecyclerView recyclerViewBebidas;
    RecyclerViewAdapterUltraprocesados adapterBebidas;

    RecyclerView.LayoutManager layoutManager;

    ModelUltraprocesados modelUltraprocesados = new ModelUltraprocesados();

    private static String TAG = "BebidasFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ultraprocesados_fragment_bebidas, container, false);


        recyclerViewBebidas = view.findViewById(R.id.myRecyclerViewBebidas);
        recyclerViewBebidas.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBebidas.setLayoutManager(layoutManager);

        SearchView mySearchView = view.findViewById(R.id.mySearchViewBebidas);
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterBebidas.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterBebidas.getFilter().filter(newText);
                return false;
            }
        });

        adapterBebidas = new RecyclerViewAdapterUltraprocesados(getContext(), bebidasItem);
        recyclerViewBebidas.setAdapter(adapterBebidas);

        addItemJSON();

        return view;
    }

    private void addItemJSON() {
        modelUltraprocesados.addItemsFromJSON(bebidasItem, TAG, "Bebidas", getContext());
    }
}
