package com.example.desarrollo.Precentacion.Alimentos.Verduras;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.ExportJSON.Model.ModelFrutas;
import com.example.desarrollo.ExportJSON.Reader.ReaderFrutas;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewAdapterFrutas;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class VerdurasFragment extends Fragment {

    private View view;
    private RecyclerView _myRecyclerViewVerduras;
    private SearchView mySearchView;
    private TextView _tituloVerduras;

    private ArrayList<ReaderFrutas> verdurasItem = new ArrayList<>();
    private RecyclerViewAdapterFrutas adapterFrutas;

    private RecyclerView.LayoutManager layoutManager;

    private ModelFrutas modelFrutas = new ModelFrutas();

    private static final String TAG = "VerdurasFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.verduras_fragment, container, false);

        init();
        cargarVerduras();
        addItemsJSON();

        return view;
    }

    private void cargarVerduras(){

        layoutManager = new LinearLayoutManager(getActivity());
        _myRecyclerViewVerduras.setLayoutManager(layoutManager);
        _myRecyclerViewVerduras.setHasFixedSize(true);

        mySearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _tituloVerduras.setVisibility(View.GONE);
                ViewGroup.LayoutParams lp = mySearchView.getLayoutParams();
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
                mySearchView.setLayoutParams(lp);
            }
        });
        mySearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                _tituloVerduras.setVisibility(View.VISIBLE);
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

        adapterFrutas = new RecyclerViewAdapterFrutas(getContext(), verdurasItem);
        _myRecyclerViewVerduras.setAdapter(adapterFrutas);
    }

    private void addItemsJSON() {
        modelFrutas.addItemsFromJSON(verdurasItem, TAG, "Verduras", getContext());
    }

    private void init(){
        _myRecyclerViewVerduras = view.findViewById(R.id.myRecyclerViewVerduras);
        mySearchView = view.findViewById(R.id.mySearchViewVerduras);
        _tituloVerduras = (TextView) view.findViewById(R.id.tituloVerduras);

    }
}
