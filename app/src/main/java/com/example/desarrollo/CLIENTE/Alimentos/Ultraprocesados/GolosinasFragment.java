package com.example.desarrollo.CLIENTE.Alimentos.Ultraprocesados;

import android.os.Bundle;
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

public class GolosinasFragment extends Fragment {

    ArrayList<ReaderUltraprocesados> golosinasItem = new ArrayList<>();
    RecyclerView recyclerViewGolosinas;
    RecyclerViewAdapterUltraprocesados adapterUltraprocesados;

    RecyclerView.LayoutManager layoutManager;

    ModelUltraprocesados modelUltraprocesados = new ModelUltraprocesados();

    private static final String TAG = "GolosinasFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ultraprocesados_fragment_golosinas, container, false);


        recyclerViewGolosinas = view.findViewById(R.id.myRecyclerViewGolosinas);
        recyclerViewGolosinas.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewGolosinas.setLayoutManager(layoutManager);

        SearchView mySearchView = view.findViewById(R.id.mySearchViewGolosinas);
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterUltraprocesados.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterUltraprocesados.getFilter().filter(newText);
                return false;
            }
        });

        adapterUltraprocesados = new RecyclerViewAdapterUltraprocesados(getContext(), golosinasItem);
        recyclerViewGolosinas.setAdapter(adapterUltraprocesados);

        addItemsJSON();

        return view;
    }

    private void addItemsJSON() {
        modelUltraprocesados.addItemsFromJSON(golosinasItem, TAG, "Golosinas", getContext());
    }
}
