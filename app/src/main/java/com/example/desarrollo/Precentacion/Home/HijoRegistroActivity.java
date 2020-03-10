package com.example.desarrollo.Precentacion.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.BoolRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.PreferenciasDao;
import com.example.desarrollo.ExportJSON.Model.ModelPreferencias;
import com.example.desarrollo.ExportJSON.Reader.ReaderPreferencias;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewPreferencias;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class HijoRegistroActivity extends AppCompatActivity implements RecyclerViewPreferencias.ChnageStatusListener, View.OnClickListener {

    public static int idNino;
    private Button _btnRegistrarPreferenciasVerduras, _btnRegistrarPreferenciasFrutas;
    private String gustos = "siGusta";
    private boolean gustosAuxiliar = false, updateVerdura = false;
    private Button _btnAddNino;
    private EditText
            _txtHijoNombre,
            _txtHijoApellidoPaterno,
            _txtHijoApellidoMaterno,
            _txtHijoEdad,
            _txtHijoPeso,
            _txtHijoEstatura,
            _txtHijoMedidaCintura;
    private NestedScrollView _registroNino, _preferenciasNino;
    private CardView _backgroundPreferencias;
    private RelativeLayout _lyt_checked;
    private ArrayList<ReaderPreferencias> temp;

    private ArrayList<ReaderPreferencias> frutasItem;
    private RecyclerView _myRecyclerViewFrutas, _myRecyclerViewVerduras;
    private ReaderPreferencias getItem;
    private RecyclerViewPreferencias mAdapter = null;
    private RecyclerView.LayoutManager layoutManager;
    PreferenciasDao preferenciasDao;
    NinoDao ninoDao;

    ModelPreferencias modelFrutas = new ModelPreferencias();

    private static final String TAG = "HijoRegistroActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hijo_registro_activity);
        init();

        addItemsJSON("Frutas");
        addPreferecias(_myRecyclerViewFrutas);

        findViewById(R.id.btnRegistrarPreferenciasFrutas).setOnClickListener(HijoRegistroActivity.this);

        _btnAddNino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNino();
            }
        });

    }

    private void addNino() {

        String nombre = _txtHijoNombre.getText().toString();
        String apellidoP = _txtHijoApellidoPaterno.getText().toString();
        String apellidoM = _txtHijoApellidoMaterno.getText().toString();
        String edad = _txtHijoEdad.getText().toString();
        String peso = _txtHijoPeso.getText().toString();
        String estatura = _txtHijoEstatura.getText().toString();
        String medidaCintura = _txtHijoMedidaCintura.getText().toString();
        int idUsuario = 1;

        if (nombre.isEmpty()) {
            _txtHijoNombre.setBackgroundResource(R.drawable.rectangulo_border_rojo);
            Toast.makeText(this, "Ingrese el nombre del tutor", Toast.LENGTH_SHORT).show();
        } else {
            _txtHijoNombre.setBackgroundResource(R.drawable.rectangulo_gris);

            if (apellidoP.isEmpty()) {
                _txtHijoApellidoPaterno.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                Toast.makeText(this, "Ingrese el apellido paterno", Toast.LENGTH_SHORT).show();
            } else {
                _txtHijoApellidoPaterno.setBackgroundResource(R.drawable.rectangulo_gris);

                if (apellidoM.isEmpty()) {
                    _txtHijoApellidoMaterno.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                    Toast.makeText(this, "Ingrese el apellido materno", Toast.LENGTH_SHORT).show();
                } else {
                    _txtHijoApellidoMaterno.setBackgroundResource(R.drawable.rectangulo_gris);

                    if (edad.isEmpty()) {
                        _txtHijoEdad.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                        Toast.makeText(this, "Ingrese la edad", Toast.LENGTH_SHORT).show();
                    } else {
                        _txtHijoEdad.setBackgroundResource(R.drawable.rectangulo_gris);

                        if (peso.isEmpty()) {
                            _txtHijoPeso.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                            Toast.makeText(this, "Ingrese el peso", Toast.LENGTH_SHORT).show();
                        } else {
                            _txtHijoPeso.setBackgroundResource(R.drawable.rectangulo_gris);

                            if (estatura.isEmpty()) {
                                _txtHijoEstatura.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                                Toast.makeText(this, "Ingrese la estatura", Toast.LENGTH_SHORT).show();
                            } else {
                                _txtHijoEstatura.setBackgroundResource(R.drawable.rectangulo_gris);
                                if (medidaCintura.isEmpty()) {
                                    _txtHijoMedidaCintura.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                                    Toast.makeText(this, "Ingrese la medida de cintura", Toast.LENGTH_SHORT).show();
                                } else {
                                    _txtHijoMedidaCintura.setBackgroundResource(R.drawable.rectangulo_gris);
                                    //addPreferecias(_myRecyclerViewFrutas);
                                    _registroNino.setVisibility(View.GONE);
                                    _preferenciasNino.setVisibility(View.VISIBLE);

                                    boolean insert = ninoDao.addNino
                                            (
                                                    TAG,
                                                    getApplicationContext(),
                                                    1,
                                                    nombre,
                                                    apellidoP,
                                                    apellidoM,
                                                    Integer.parseInt(edad),
                                                    Double.parseDouble(peso),
                                                    Double.parseDouble(estatura),
                                                    Double.parseDouble(medidaCintura),
                                                    0.0,
                                                    0.0,
                                                    0.0,
                                                    0,
                                                    0.25,
                                                    0.25,
                                                    0.25
                                            );

                                    if (insert == true) {
                                        Toast.makeText(getApplicationContext(), "Nino agregado", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void addPreferecias(RecyclerView recyclerView) {

        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerViewPreferencias(frutasItem, getApplicationContext(), this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void addItemsJSON(String alimento) {
        frutasItem = null;
        frutasItem = new ArrayList<>();
        modelFrutas.addItemsFromJSON(frutasItem, TAG, alimento, getApplicationContext());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegistrarPreferenciasFrutas:
                insertGustosFrutas();
                break;

            case R.id.btnRegistrarPreferenciasVerduras:
                insertGustosVerduras();
                break;

        }

    }

    @Override
    public void onItemChangeListener(int position, ReaderPreferencias model) {
        try {
            frutasItem.set(position, model);
        } catch (Exception e) {

        }
    }

    public void insertGustosFrutas(){

        temp = new ArrayList<>();

        try {
            for (int i = 0; i < frutasItem.size(); i++) {

                if (!frutasItem.get(i).isSelect()) {
                    temp.add(frutasItem.get(i));
                }
                if (gustos.equals("siGusta")) {
                    if (frutasItem.get(i).isSelect()) {
                        preferenciasDao.addPreferenciasFrutas
                                (
                                        TAG,
                                        getApplicationContext(),
                                        idNino,
                                        frutasItem.get(i).getNombreFruta(),
                                        1,
                                        0,
                                        0
                                );
                        Log.v(TAG, "si me gusta registra ID = " + idNino);
                    }
                } else if (gustos.equals("noGusta")) {
                    if (frutasItem.get(i).isSelect()) {
                        preferenciasDao.addPreferenciasFrutas
                                (
                                        TAG,
                                        getApplicationContext(),
                                        idNino,
                                        frutasItem.get(i).getNombreFruta(),
                                        0,
                                        1,
                                        0
                                );
                        Log.v(TAG, "No me gusta registra ID = " + idNino);
                    }
                } else if (gustos.equals("conosco")) {
                    if (frutasItem.get(i).isSelect()) {
                        preferenciasDao.addPreferenciasFrutas
                                (
                                        TAG,
                                        getApplicationContext(),
                                        idNino,
                                        frutasItem.get(i).getNombreFruta(),
                                        0,
                                        0,
                                        1
                                );
                        Log.v(TAG, "Concosco registra ID = " + idNino);
                    }
                    if (!frutasItem.get(i).isSelect()) {
                        preferenciasDao.addPreferenciasFrutas
                                (
                                        TAG,
                                        getApplicationContext(),
                                        idNino,
                                        frutasItem.get(i).getNombreFruta(),
                                        0,
                                        0,
                                        0
                                );
                    }
                   updateVerdura = true;
                }
            }

            frutasItem = temp;
            if (gustosAuxiliar == false) {
                gustos = "noGusta";
                gustosAuxiliar = true;
            } else {
                gustos = "conosco";
                gustosAuxiliar = false;
            }

            if (frutasItem.size() == 0) {
                _myRecyclerViewFrutas.setVisibility(View.GONE);
            }
            mAdapter.setModel(frutasItem);
            mAdapter.notifyDataSetChanged();

            if (updateVerdura == true){
                UpdateVerduras();
                gustos = "siGusta";
                updateVerdura = false;
                gustosAuxiliar = false;
            }

        } catch (Exception e) {
            Log.e(TAG,"Error" + e );
        }

    }

    public void insertGustosVerduras(){

        temp = new ArrayList<>();

        try {
            for (int i = 0; i < frutasItem.size(); i++) {

                if (!frutasItem.get(i).isSelect()) {
                    temp.add(frutasItem.get(i));
                }
                if (gustos.equals("siGusta")) {
                    if (frutasItem.get(i).isSelect()) {
                        preferenciasDao.addPreferenciasVerduras
                                (
                                        TAG,
                                        getApplicationContext(),
                                        idNino,
                                        frutasItem.get(i).getNombreFruta(),
                                        1,
                                        0,
                                        0
                                );
                        Log.v(TAG, "si me gusta registra ID = " + idNino);
                    }
                } else if (gustos.equals("noGusta")) {
                    if (frutasItem.get(i).isSelect()) {
                        preferenciasDao.addPreferenciasVerduras
                                (
                                        TAG,
                                        getApplicationContext(),
                                        idNino,
                                        frutasItem.get(i).getNombreFruta(),
                                        0,
                                        1,
                                        0
                                );
                        Log.v(TAG, "No me gusta registra ID = " + idNino);
                    }
                } else if (gustos.equals("conosco")) {
                    if (frutasItem.get(i).isSelect()) {
                        preferenciasDao.addPreferenciasVerduras
                                (
                                        TAG,
                                        getApplicationContext(),
                                        idNino,
                                        frutasItem.get(i).getNombreFruta(),
                                        0,
                                        0,
                                        1
                                );
                        Log.v(TAG, "Concosco registra ID = " + idNino);
                    }
                    if (!frutasItem.get(i).isSelect()) {
                        preferenciasDao.addPreferenciasVerduras
                                (
                                        TAG,
                                        getApplicationContext(),
                                        idNino,
                                        frutasItem.get(i).getNombreFruta(),
                                        0,
                                        0,
                                        0
                                );
                    }
                    updateVerdura = true;
                }
            }

            frutasItem = temp;
            if (gustosAuxiliar == false) {
                gustos = "noGusta";
                gustosAuxiliar = true;
            } else {
                gustos = "conosco";
                gustosAuxiliar = false;
            }

            if (frutasItem.size() == 0) {
                _myRecyclerViewFrutas.setVisibility(View.GONE);
            }
            mAdapter.setModel(frutasItem);
            mAdapter.notifyDataSetChanged();

            if (updateVerdura == true){
                finish();
            }

        } catch (Exception e) {
            Log.e(TAG,"Error" + e );
        }

    }

    private void UpdateVerduras() {
        addItemsJSON("Verduras");
        addPreferecias(_myRecyclerViewVerduras);
        temp = frutasItem;
        _myRecyclerViewFrutas.setVisibility(View.GONE);
        _myRecyclerViewVerduras.setVisibility(View.VISIBLE);
        _btnRegistrarPreferenciasFrutas.setVisibility(View.GONE);
        _btnRegistrarPreferenciasVerduras.setVisibility(View.VISIBLE);

        findViewById(R.id.btnRegistrarPreferenciasVerduras).setOnClickListener(HijoRegistroActivity.this);
    }

    private void init() {
        _btnAddNino = (Button) findViewById(R.id.btnAddNino);
        _registroNino = (NestedScrollView) findViewById(R.id.registroNino);
        _preferenciasNino = (NestedScrollView) findViewById(R.id.preferenciasNino);
        _backgroundPreferencias = (CardView) findViewById(R.id.backgroundPreferencias);
        _lyt_checked = (RelativeLayout) findViewById(R.id.lyt_checked);
        _btnRegistrarPreferenciasVerduras = (Button) findViewById(R.id.btnRegistrarPreferenciasVerduras);
        _btnRegistrarPreferenciasFrutas = (Button) findViewById(R.id.btnRegistrarPreferenciasFrutas);

        _myRecyclerViewFrutas = findViewById(R.id.myRecyclerViewPreferenciasFrutas);
        _myRecyclerViewVerduras = findViewById(R.id.myRecyclerViewPreferenciasVerduras);

        _txtHijoNombre = (EditText) findViewById(R.id.txtHijoNombre);
        _txtHijoApellidoPaterno = (EditText) findViewById(R.id.txtHijoApellidoPaterno);
        _txtHijoApellidoMaterno = (EditText) findViewById(R.id.txtHijoApellidoMaterno);
        _txtHijoEdad = (EditText) findViewById(R.id.txtHijoEdad);
        _txtHijoPeso = (EditText) findViewById(R.id.txtHijoPeso);
        _txtHijoEstatura = (EditText) findViewById(R.id.txtHijoEstatura);
        _txtHijoMedidaCintura = (EditText) findViewById(R.id.txtHijoMedidaCintura);

        _btnAddNino = (Button) findViewById(R.id.btnAddNino);
    }
}
