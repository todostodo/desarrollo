package com.example.desarrollo.Precentacion.Alimentos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.ExportJSON.Model.ModelFrutas;
import com.example.desarrollo.ExportJSON.Model.ModelUltraprocesados;
import com.example.desarrollo.ExportJSON.Reader.ReaderFrutas;
import com.example.desarrollo.ExportJSON.Reader.ReaderUltraprocesados;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewAdapterFrutas;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewAdapterUltraprocesados;
import com.example.desarrollo.R;


import java.util.ArrayList;


public class AlimentosActivity extends AppCompatActivity {

    private LinearLayout _tituloAlimentos, _btnSubUltraProcesados;
    private RelativeLayout _btmCerrarAlimentos;
    private Button _btnFrutas, _btnVerduras, _btnUltraProcesados;
    private Button _btnBebidas, _btnFrituras, _btnGolosinas, _btnGalletasPanesillos, _btnOtros;
    private ArrayList<Button> listButton = new ArrayList<>();
    private ArrayList<Button> listButtonUltra = new ArrayList<>();
    private boolean activoSearchFruta = false, activoSearchVerduras = false, activoSearchUltralProcesados = false;

    //todo: Variables para mostrar las frutas
    private ArrayList<ReaderFrutas> frutasItem = new ArrayList<>();
    private RecyclerView myRecyclerViewFrutas;
    private RecyclerViewAdapterFrutas adapterFrutas;
    private SearchView mySearchViewFrutas;
    private ModelFrutas modelFrutas = new ModelFrutas();

    //todo: Variables para mostrar la verduras
    private RecyclerView _myRecyclerViewVerduras;
    private SearchView mySearchViewVerduras;
    private ArrayList<ReaderFrutas> verdurasItem = new ArrayList<>();
    private RecyclerViewAdapterFrutas adapterVerduras;
    private RecyclerView.LayoutManager layoutManagerVerduras;

    //todo: Variables para mostrar los alimentos ultra procesados
    private RecyclerView _myRecyclerViewUltraProcesados;
    private ArrayList<ReaderUltraprocesados> ultraprocesadosItem = new ArrayList<>();
    private SearchView mySearchViewUltraProcesados;
    RecyclerViewAdapterUltraprocesados adapterUltraprocesados;
    ModelUltraprocesados modelUltraprocesados = new ModelUltraprocesados();

    private static final String TAG = "FrutasFragment";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alimentos_activity);
        init();
        cargarAlimentos();

        selectButton(_btnFrutas);
        mostrarFrutas();

        //Salir
        _btmCerrarAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Cargar Frutas
        _btnFrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(_btnFrutas);
                undeselectButton(_btnFrutas);
                mostrarFrutas();
                undeselectButtonUltra(_btnOtros);
                myRecyclerViewFrutas.setVisibility(View.VISIBLE);
                _myRecyclerViewVerduras.setVisibility(View.GONE);
                mySearchViewFrutas.setVisibility(View.VISIBLE);
                mySearchViewVerduras.setVisibility(View.GONE);
                mySearchViewUltraProcesados.setVisibility(View.GONE);
                _btnSubUltraProcesados.setVisibility(View.GONE);
                _myRecyclerViewUltraProcesados.setVisibility(View.GONE);
            }
        });
        //Cargar Verduras
        _btnVerduras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(_btnVerduras);
                undeselectButton(_btnVerduras);
                mostrarVerduras();
                undeselectButtonUltra(_btnOtros);
                myRecyclerViewFrutas.setVisibility(View.GONE);
                _myRecyclerViewVerduras.setVisibility(View.VISIBLE);
                mySearchViewFrutas.setVisibility(View.GONE);
                mySearchViewVerduras.setVisibility(View.VISIBLE);
                mySearchViewUltraProcesados.setVisibility(View.GONE);
                _btnSubUltraProcesados.setVisibility(View.GONE);
                _myRecyclerViewUltraProcesados.setVisibility(View.GONE);


            }
        });
        //Cargar categorias de alimentos ultra procesados
        _btnUltraProcesados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(_btnUltraProcesados);
                undeselectButton(_btnUltraProcesados);
                myRecyclerViewFrutas.setVisibility(View.GONE);
                _myRecyclerViewVerduras.setVisibility(View.GONE);
                _btnSubUltraProcesados.setVisibility(View.VISIBLE);
                _myRecyclerViewUltraProcesados.setVisibility(View.VISIBLE);
                mySearchViewUltraProcesados.setVisibility(View.VISIBLE);
                mySearchViewFrutas.setVisibility(View.GONE);
                mySearchViewVerduras.setVisibility(View.GONE);

                selectButtonUltra(_btnBebidas);
                undeselectButtonUltra(_btnBebidas);
                cargarUltraProcesados("Bebidas");

            }
        });

        _btnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButtonUltra(_btnBebidas);
                undeselectButtonUltra(_btnBebidas);
                cargarUltraProcesados("Bebidas");
            }
        });
        _btnFrituras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButtonUltra(_btnFrituras);
                undeselectButtonUltra(_btnFrituras);
                cargarUltraProcesados("Frituras");

            }
        });
        _btnGolosinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButtonUltra(_btnGolosinas);
                undeselectButtonUltra(_btnGolosinas);
                cargarUltraProcesados("Golosinas");
            }
        });
        _btnGalletasPanesillos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButtonUltra(_btnGalletasPanesillos);
                undeselectButtonUltra(_btnGalletasPanesillos);
                cargarUltraProcesados("Galletas y panesillos");
            }
        });
        _btnOtros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButtonUltra(_btnOtros);
                undeselectButtonUltra(_btnOtros);
                cargarUltraProcesados("Otros");

            }
        });
    }

    private void selectButton(Button button) {
        button.setBackgroundResource(R.drawable.custom_button_verde);
        button.setTextColor(getResources().getColor(R.color.blanco));
        listButton.add(button);
    }

    private void selectButtonUltra(Button button) {
        button.setBackgroundResource(R.drawable.custom_button_rosa);
        button.setTextColor(getResources().getColor(R.color.blanco));
        listButtonUltra.add(button);
    }

    private void undeselectButton(Button button) {
        if (listButton.size() > 1) {
            if (!listButton.get(0).getText().toString().equals(listButton.get(1).getText().toString())) {
                listButton.get(0).setBackgroundResource(R.drawable.custom_button_gris);
                listButton.get(0).setTextColor(Color.parseColor("#B8C2CD"));
            }
            listButton.remove(0);
        }
    }

    private void undeselectButtonUltra(Button button) {
        if (listButtonUltra.size() > 1) {
            if (!listButtonUltra.get(0).getText().toString().equals(listButtonUltra.get(1).getText().toString())) {
                listButtonUltra.get(0).setBackgroundResource(R.drawable.custom_button_gris);
                listButtonUltra.get(0).setTextColor(Color.parseColor("#B8C2CD"));
            }
            listButtonUltra.remove(0);
        }
    }

    private void mostrarFrutas() {

        if (activoSearchFruta == true)
            activarSearch(mySearchViewFrutas);
        else
            desactivarSearch(mySearchViewFrutas);

        mySearchViewFrutas.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activoSearchFruta = true;
                activarSearch(mySearchViewFrutas);
            }
        });
        mySearchViewFrutas.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                activoSearchFruta = false;
                desactivarSearch(mySearchViewFrutas);
                return false;
            }
        });

        mySearchViewFrutas.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    }

    private void mostrarVerduras() {

        if (activoSearchVerduras == true)
            activarSearch(mySearchViewVerduras);
        else
            desactivarSearch(mySearchViewVerduras);

        mySearchViewVerduras.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activoSearchVerduras = true;
                activarSearch(mySearchViewVerduras);
            }
        });
        mySearchViewVerduras.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                activoSearchVerduras = false;
                desactivarSearch(mySearchViewVerduras);
                return false;
            }
        });

        mySearchViewVerduras.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterVerduras.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterVerduras.getFilter().filter(newText);
                return false;
            }
        });


    }

    private void activarSearch(SearchView searchView) {
        _tituloAlimentos.setVisibility(View.GONE);
        ViewGroup.LayoutParams lp = searchView.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        searchView.setLayoutParams(lp);
    }

    private void desactivarSearch(SearchView searchView) {
        _tituloAlimentos.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams lp = searchView.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        searchView.setLayoutParams(lp);
    }

    private void cargarAlimentos() {

        myRecyclerViewFrutas.setHasFixedSize(true);
        myRecyclerViewFrutas.setLayoutManager(new LinearLayoutManager(this));

        adapterFrutas = new RecyclerViewAdapterFrutas(getApplicationContext(), frutasItem, "Fruta");
        myRecyclerViewFrutas.setAdapter(adapterFrutas);

        modelFrutas.addItemsFromJSON(frutasItem, TAG, "Fruta", getApplicationContext());


        layoutManagerVerduras = new LinearLayoutManager(this);
        _myRecyclerViewVerduras.setLayoutManager(layoutManagerVerduras);
        _myRecyclerViewVerduras.setHasFixedSize(true);

        adapterVerduras = new RecyclerViewAdapterFrutas(getApplicationContext(), verdurasItem, "Verdura");
        _myRecyclerViewVerduras.setAdapter(adapterVerduras);

        modelFrutas.addItemsFromJSON(verdurasItem, TAG, "Verdura", getApplicationContext());

        _myRecyclerViewUltraProcesados.setHasFixedSize(true);

    }

    private void cargarUltraProcesados(String alimento){
        ultraprocesadosItem.clear();

        if (activoSearchUltralProcesados == true)
            activarSearch(mySearchViewUltraProcesados);
        else
            desactivarSearch(mySearchViewUltraProcesados);

        mySearchViewUltraProcesados.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activoSearchUltralProcesados = true;
                activarSearch(mySearchViewUltraProcesados);
            }
        });
        mySearchViewUltraProcesados.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                activoSearchUltralProcesados = false;
                desactivarSearch(mySearchViewUltraProcesados);
                return false;
            }
        });

        _myRecyclerViewUltraProcesados.setLayoutManager(new LinearLayoutManager(this));
        mySearchViewUltraProcesados.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        adapterUltraprocesados = new RecyclerViewAdapterUltraprocesados(getApplicationContext(), ultraprocesadosItem);
        _myRecyclerViewUltraProcesados.setAdapter(adapterUltraprocesados);

        modelUltraprocesados.addItemsFromJSON(ultraprocesadosItem, TAG, alimento, getApplicationContext());

    }

    private void init() {
        _btmCerrarAlimentos = (RelativeLayout) findViewById(R.id.btmCerrarAlimentos);
        _tituloAlimentos = (LinearLayout) findViewById(R.id.tituloAlimentos);

        //Frutas
        _btnFrutas = (Button) findViewById(R.id.btnFrutas);
        myRecyclerViewFrutas = findViewById(R.id.myRecyclerViewFrutas);
        mySearchViewFrutas = (SearchView) findViewById(R.id.mySearchViewFrutas);

        //Verduras
        _btnVerduras = (Button) findViewById(R.id.btnVerduras);
        _myRecyclerViewVerduras = findViewById(R.id.myRecyclerViewVerduras2);
        mySearchViewVerduras = findViewById(R.id.mySearchViewVerduras2);

        //Ultra procesados
        _myRecyclerViewUltraProcesados = (RecyclerView) findViewById(R.id.myRecyclerViewUltraProcesados);
        _btnUltraProcesados = (Button) findViewById(R.id.btnUltraProcesados);
        _btnSubUltraProcesados = (LinearLayout) findViewById(R.id.btnSubUltraProcesados);
        _btnBebidas = (Button) findViewById(R.id.btnBebidas2);
        _btnFrituras = (Button) findViewById(R.id.btnFrituras2);
        _btnGolosinas = (Button) findViewById(R.id.btnGolosinas2);
        _btnGalletasPanesillos = (Button) findViewById(R.id.btnGalletasPanesillos2);
        _btnOtros = (Button) findViewById(R.id.btnOtros2);
        mySearchViewUltraProcesados = findViewById(R.id.mySearchViewUltraProcesados);
    }
}
