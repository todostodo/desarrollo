package com.example.desarrollo.Precentacion.Alimentos.Frutas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.ExportJSON.Model.ModelFrutas;
import com.example.desarrollo.ExportJSON.Reader.ReaderFrutas;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewAdapterFrutas;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class FrutasFragment extends Fragment{

    View rootView;



    ArrayList<ReaderFrutas> frutasItem = new ArrayList<>();
    RecyclerView myRecyclerView;
    RecyclerViewAdapterFrutas adapterFrutas;

    private RecyclerView.LayoutManager layoutManager;

    ModelFrutas modelFrutas = new ModelFrutas();

    private static final String TAG = "FrutasFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frutas_fragment, container, false);

        ImageButton _btmAbrirDetalleConsumo = (ImageButton) rootView.findViewById(R.id.btmActivityRegistroFrutas);
        _btmAbrirDetalleConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegistrarFrutas.class);
                startActivity(intent);
            }
        });

        myRecyclerView = rootView.findViewById(R.id.my_recycler_view);
        myRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(layoutManager);

        SearchView mySearchView = rootView.findViewById(R.id.mySearchView);
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

        adapterFrutas = new RecyclerViewAdapterFrutas(getContext(), frutasItem);
        myRecyclerView.setAdapter(adapterFrutas);

        addItemsJSON();

        return rootView;
    }

    private void addItemsJSON() {
        modelFrutas.addItemsFromJSON(frutasItem, TAG, "Frutas", getContext());
    }
}
