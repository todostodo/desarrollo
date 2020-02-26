package com.example.desarrollo.CLIENTE.Alimentos.Verduras;

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

import com.example.desarrollo.ExportJSON.Model.ModelFrutas;
import com.example.desarrollo.ExportJSON.Reader.ReaderFrutas;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewAdapterFrutas;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class VerdurasFragment extends Fragment {

    RecyclerView _myRecyclerViewVerduras;


    ArrayList<ReaderFrutas> verdurasItem = new ArrayList<>();
    RecyclerViewAdapterFrutas adapterFrutas;

    private RecyclerView.LayoutManager layoutManager;

    ModelFrutas modelFrutas = new ModelFrutas();

    private static final String TAG = "VerdurasFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verduras_fragment, container, false);


        _myRecyclerViewVerduras = view.findViewById(R.id.myRecyclerViewVerduras);


        _myRecyclerViewVerduras.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        _myRecyclerViewVerduras.setLayoutManager(layoutManager);

        SearchView mySearchView = view.findViewById(R.id.mySearchViewVerduras);
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterFrutas.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterFrutas.getFilter().filter(newText);
                return false;
            }
        });

        adapterFrutas = new RecyclerViewAdapterFrutas(getContext(), verdurasItem);
        _myRecyclerViewVerduras.setAdapter(adapterFrutas);

        addItemsJSON();

        return view;
    }

    private void addItemsJSON() {
        modelFrutas.addItemsFromJSON(verdurasItem, TAG, "Verduras", getContext());
    }
}
