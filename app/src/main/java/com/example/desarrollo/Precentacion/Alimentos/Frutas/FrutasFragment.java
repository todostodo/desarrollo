package com.example.desarrollo.Precentacion.Alimentos.Frutas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class FrutasFragment extends Fragment {

    private View view;

    private ArrayList<ReaderFrutas> frutasItem = new ArrayList<>();
    private RecyclerView myRecyclerView;
    private RecyclerViewAdapterFrutas adapterFrutas;
    private SearchView mySearchView;
    private TextView _tituloFrutas;

    private RecyclerView.LayoutManager layoutManager;

    private ModelFrutas modelFrutas = new ModelFrutas();

    private static final String TAG = "FrutasFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frutas_fragment, container, false);
        init();
        cargarFrutas();
        addItemsJSON();
        return view;

    }

    private void cargarFrutas(){

        myRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(layoutManager);

        mySearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _tituloFrutas.setVisibility(View.GONE);
                ViewGroup.LayoutParams lp = mySearchView.getLayoutParams();
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
                mySearchView.setLayoutParams(lp);
            }
        });
        mySearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                _tituloFrutas.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams lp = mySearchView.getLayoutParams();
                lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                mySearchView.setLayoutParams(lp);
                return false;
            }
        });
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


        adapterFrutas = new RecyclerViewAdapterFrutas(getContext(), frutasItem, "Fruta");
        myRecyclerView.setAdapter(adapterFrutas);
    }
    private void addItemsJSON() {
        modelFrutas.addItemsFromJSON(frutasItem, TAG, "Fruta", getContext());
    }

    private void init(){
        myRecyclerView = view.findViewById(R.id.my_recycler_view);
        mySearchView = (SearchView) view.findViewById(R.id.mySearchView);
        _tituloFrutas = (TextView) view.findViewById(R.id.tituloFrutas);
    }

}
