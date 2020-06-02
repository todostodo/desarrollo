package com.example.desarrollo.Precentacion.Foro;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Entidades.Foro;
import com.example.desarrollo.ExportJSON.Model.ModelForo;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewForoRecetas;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class ForoActivity extends AppCompatActivity {

    private RecyclerView myRecyclerViewForoRecetas;
    private RecyclerViewForoRecetas adapterRecetas;
    private ArrayList<Foro> listReceta = new ArrayList<>();
    private SearchView mySearchViewRecetas;
    private RelativeLayout btmCerrarForo;

    private ModelForo modelForo = new ModelForo();


    private static final String TAG = "ForoActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro_activity);
        init();

        cargarRecetas();

        btmCerrarForo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void cargarRecetas(){
        myRecyclerViewForoRecetas.setLayoutManager(new GridLayoutManager(this, 2));
        mySearchViewRecetas.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterRecetas.getFilter().filter(newText);
                return false;
            }
        });

        adapterRecetas = new RecyclerViewForoRecetas(listReceta, getApplicationContext());
        myRecyclerViewForoRecetas.setAdapter(adapterRecetas);

        modelForo.addItemsFromJSONForo(listReceta, TAG, getApplicationContext());
    }

    private void init(){
        myRecyclerViewForoRecetas = findViewById(R.id.myRecyclerViewForoRecetas);
        mySearchViewRecetas = findViewById(R.id.mySearchViewRecetas);
        btmCerrarForo = (RelativeLayout) findViewById(R.id.btmCerrarForo);
    }
}
