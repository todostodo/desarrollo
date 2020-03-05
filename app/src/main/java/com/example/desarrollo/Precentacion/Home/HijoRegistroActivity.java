package com.example.desarrollo.Precentacion.Home;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.ExportJSON.Model.ModelPreferencias;
import com.example.desarrollo.ExportJSON.Reader.ReaderPreferencias;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewPreferencias;
import com.example.desarrollo.R;

import java.util.ArrayList;
import java.util.List;

public class HijoRegistroActivity extends AppCompatActivity implements RecyclerViewPreferencias.ChnageStatusListener, View.OnClickListener {

    private Button _btnAddNino;
    private NestedScrollView _registroNino;
    private LinearLayout _preferenciasNino;
    private CardView _backgroundPreferencias;
    private RelativeLayout _lyt_checked;

    private ArrayList<ReaderPreferencias> frutasItem = new ArrayList<>();
    private RecyclerView myRecyclerView;
    private RecyclerViewPreferencias mAdapter = null;
    private RecyclerView.LayoutManager layoutManager;

    ModelPreferencias modelFrutas = new ModelPreferencias();

    private static final String TAG = "HijoRegistroActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hijo_registro_activity);
        init();

        layoutManager = new GridLayoutManager(this, 3);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setHasFixedSize(true);

        //set data and list adapter
        mAdapter = new RecyclerViewPreferencias(frutasItem, getApplicationContext(), this);
        myRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.btnRegistrarPreferencias).setOnClickListener(HijoRegistroActivity.this);

        addItemsJSON();

        _btnAddNino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _registroNino.setVisibility(View.GONE);
                _preferenciasNino.setVisibility(View.VISIBLE);
            }
        });

    }


    private void init() {
        _btnAddNino = (Button) findViewById(R.id.btnAddNino);
        _registroNino = (NestedScrollView) findViewById(R.id.registroNino);
        _preferenciasNino = (LinearLayout) findViewById(R.id.preferenciasNino);
        _backgroundPreferencias = (CardView) findViewById(R.id.backgroundPreferencias);
        _lyt_checked = (RelativeLayout) findViewById(R.id.lyt_checked);

        myRecyclerView = findViewById(R.id.myRecyclerViewPreferencias);
    }

    private void addItemsJSON() {
        modelFrutas.addItemsFromJSON(frutasItem, TAG, "Frutas", getApplicationContext());
    }

    @Override
    public void onClick(View v) {

        ArrayList<ReaderPreferencias> temp = new ArrayList<>();
        try {
            for (int i = 0; i < frutasItem.size(); i++) {
                if (!frutasItem.get(i).isSelect()) {
                    temp.add(frutasItem.get(i));
                }
            }
        } catch (Exception e) {

        }
        frutasItem = temp;

        if (frutasItem.size() == 0) {
            myRecyclerView.setVisibility(View.GONE);
        }
        mAdapter.setModel(frutasItem);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemChangeListener(int position, ReaderPreferencias model) {
        try {
            frutasItem.set(position, model);
        } catch (Exception e) {

        }
    }
}
