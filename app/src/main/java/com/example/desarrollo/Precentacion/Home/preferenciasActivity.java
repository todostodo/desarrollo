package com.example.desarrollo.Precentacion.Home;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.ExportJSON.Model.ModelFrutas;
import com.example.desarrollo.ExportJSON.Reader.ReaderFrutas;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewAdapterFrutas;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class preferenciasActivity extends AppCompatActivity {

    ArrayList<ReaderFrutas> frutasItem = new ArrayList<>();
    RecyclerView myRecyclerView;
    RecyclerViewAdapterFrutas adapterFrutas;

    private RecyclerView.LayoutManager layoutManager;

    ModelFrutas modelFrutas = new ModelFrutas();

    private static final String TAG = "preferenciasActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.preferencias_nino_activity);

        myRecyclerView = findViewById(R.id.my_recycler_view);
        myRecyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 3);
        myRecyclerView.setLayoutManager(layoutManager);

        adapterFrutas = new RecyclerViewAdapterFrutas(this, frutasItem);
        myRecyclerView.setAdapter(adapterFrutas);

        addItemsJSON();
    }

    private void addItemsJSON() {
        modelFrutas.addItemsFromJSON(frutasItem, TAG, "Frutas",this);
    }
}
