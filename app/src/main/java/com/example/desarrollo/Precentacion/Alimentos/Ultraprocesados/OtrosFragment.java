package com.example.desarrollo.Precentacion.Alimentos.Ultraprocesados;

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

public class OtrosFragment extends Fragment {

    ArrayList<ReaderUltraprocesados> otrosItem = new ArrayList<>();
    RecyclerView recyclerViewOtros;
    RecyclerViewAdapterUltraprocesados adapterUltraprocesados;

    RecyclerView.LayoutManager layoutManager;

    ModelUltraprocesados modelUltraprocesados = new ModelUltraprocesados();

    private static final String TAG = "OtrosFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ultraprocesados_fragment_otros, container, false);

        recyclerViewOtros = view.findViewById(R.id.myRecyclerViewOtros);
        recyclerViewOtros.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewOtros.setLayoutManager(layoutManager);

        SearchView mySearchView = view.findViewById(R.id.mySearchViewOtros);
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

        adapterUltraprocesados = new RecyclerViewAdapterUltraprocesados(getContext(), otrosItem);
        recyclerViewOtros.setAdapter(adapterUltraprocesados);

        addItemsJSON();

        return view;
    }

    private void addItemsJSON() {
        modelUltraprocesados.addItemsFromJSON(otrosItem, TAG, "Otros", getContext());
    }
}
