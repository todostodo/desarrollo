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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GalletasPanesillosFragment extends Fragment {

    ArrayList<ReaderUltraprocesados> galletasPenesillosItem = new ArrayList<>();
    RecyclerView recyclerViewGalletasPanesillos;
    RecyclerViewAdapterUltraprocesados adapterUltraprocesados;

    RecyclerView.LayoutManager layoutManager;

    ModelUltraprocesados modelUltraprocesados = new ModelUltraprocesados();

    private static final String TAG = "GalletasPanesillosFragm";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ultraprocesados_fragment_galletas_panesillos, container, false);


        recyclerViewGalletasPanesillos = view.findViewById(R.id.myRecyclerViewGalletasPanesillos);
        recyclerViewGalletasPanesillos.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewGalletasPanesillos.setLayoutManager(layoutManager);

        SearchView mySearchView = view.findViewById(R.id.mySearchViewGalletasPanesillos);
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

        adapterUltraprocesados = new RecyclerViewAdapterUltraprocesados(getContext(), galletasPenesillosItem);
        recyclerViewGalletasPanesillos.setAdapter(adapterUltraprocesados);

        //addItemsFromJSON();
        addItemsJSON();

        return view;
    }

    private void addItemsJSON() {
        modelUltraprocesados.addItemsFromJSON(galletasPenesillosItem, TAG, "Galletas y panesillos", getContext());
    }

    /*

    private void addItemsFromJSON() {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray = object.optJSONArray("Galletas y panesillos");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String nombre = itemObj.getString("nombre");
                String calorias = itemObj.getString("calorias");

                ReaderUltraprocesados readerUltraprocesados = new ReaderUltraprocesados(nombre, calorias);
                galletasPenesillosItem.add(readerUltraprocesados);
            }
        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemFromJSON", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.alimentos_registro);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }

     */
}
