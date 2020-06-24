package com.example.desarrollo.Precentacion.Home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.example.desarrollo.ConexionApi.ConexionApi;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.desarrollo.ConexionApi.consultasLocales;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.desarrollo.ConexionApi.ConexionApi;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.PreferenciasDao;
import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.ExportJSON.Model.ModelPreferencias;
import com.example.desarrollo.Entidades.PreferenciasNino;
import com.example.desarrollo.ExportJSON.RecycrerView.RecyclerViewPreferencias;
import com.example.desarrollo.Precentacion.MainActivity;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HijoRegistroActivity extends AppCompatActivity implements RecyclerViewPreferencias.ChnageStatusListener, View.OnClickListener {

    private Button _btnRegistrarPreferenciasVerduras, _btnRegistrarPreferenciasFrutas;
    private String gustos = "siGusta";
    private boolean updateVerdura = false;
    private int gustosAuxiliar = 0, regresar = 1;
    private Button _btnAddNino;
    private TextView _txtAvisoPreferencia, txtToast;
    private TextView _edadSeis, _edadSiete, _edadOcho, _edadNueve, _edadDiez, _edadOnce, _edadDoce;
    private ArrayList<TextView> listTexView = new ArrayList<>();
    private EditText
            _txtHijoNombre,
            _txtHijoApellidoPaterno,
            _txtHijoApellidoMaterno,
            _txtHijoPeso,
            _txtHijoEstatura;
    //_txtHijoMedidaCintura;

    private ConstraintLayout _registroNinoGenero, _registroDatosNino, _registroMedidasNino, _registroNino;
    private Button _btnRegistraGenero, _btnRegistrarDatosNino;
    private ImageView _imgGeneroNinoRegistroDatos;
    private RelativeLayout _btnRegresarGeneroNino, _btnRegresarDatosNino;

    // todo: variable para almacenar todo lo que el usuario ingresa antes de ser almacenado a la base de datos
    private String nombreNino, apellidoPNino, apellidoMNino, generoNino = "", pesoNino, estaturaNino;
    private int auxEdad = 0;
    private ArrayList<String> siGustaFruta = new ArrayList<>();
    private ArrayList<String> noGustaFruta = new ArrayList<>();
    private ArrayList<String> conoscoFruta = new ArrayList<>();
    private ArrayList<String> noSelectFruta = new ArrayList<>();

    private ArrayList<String> siGustaVerdura = new ArrayList<>();
    private ArrayList<String> noGustaVerdura = new ArrayList<>();
    private ArrayList<String> conoscoVerdura = new ArrayList<>();
    private ArrayList<String> noSelectVerdura = new ArrayList<>();
    //--------------------------------------------------
    private NestedScrollView _preferenciasNino;
    private RelativeLayout _checkGeneroHombre, _checkGeneroMujer;
    private LinearLayout _btnGeneroHombre, _btnGeneroMujer;
    private ArrayList<PreferenciasNino> temp;

    private ArrayList<PreferenciasNino> frutasItem;
    private RecyclerView _myRecyclerViewFrutas, _myRecyclerViewVerduras;
    private RecyclerViewPreferencias mAdapter = null;
    private RecyclerView.LayoutManager layoutManager;
    private PreferenciasDao preferenciasDao;
    private NinoDao ninoDao;
    private Toastp toast;
    private UserDao userDao;
    private ConexionApi conexionApi;

    ModelPreferencias modelFrutas = new ModelPreferencias();

    private static final String TAG = "HijoRegistroActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hijo_registro_activity);
        init();

        addItemsJSON("Fruta");
        addPreferecias(_myRecyclerViewFrutas);
        _txtAvisoPreferencia.setText("Seleccione la fruta que le gusta");

        findViewById(R.id.btnRegistrarPreferenciasFrutas).setOnClickListener(HijoRegistroActivity.this);

        _btnRegresarGeneroNino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _registroNinoGenero.setVisibility(View.VISIBLE);
                _registroDatosNino.setVisibility(View.GONE);
                regresar = 1;
            }
        });

        _btnRegresarDatosNino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _registroDatosNino.setVisibility(View.GONE);
                _registroDatosNino.setVisibility(View.VISIBLE);
                regresar = 2;
            }
        });

        validacionEdadNino();
        validacionGeneroNino();
        registrarGenero();
        //addNinoNombreEdad();
    }

    //Validad la primera pantalla que se le precenta al usuario al momento de registra a un niño (Genero, Nombre y Edad)
    /* Validacion primera parte */
    private void validacionGeneroNino() {
        _btnGeneroHombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generoNino = "hombre";
                _checkGeneroHombre.setVisibility(View.VISIBLE);
                _checkGeneroMujer.setVisibility(View.GONE);
            }
        });
        _btnGeneroMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generoNino = "mujer";
                _checkGeneroMujer.setVisibility(View.VISIBLE);
                _checkGeneroHombre.setVisibility(View.GONE);
            }
        });
    }

    private void validacionEdadNino() {

        _edadSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auxEdad = 6;
                validacionEdadNinoActivaEvento(_edadSeis);
                listTexView.add(_edadSeis);
                validacionEdadNinoDesactivarEvento();
            }
        });
        _edadSiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auxEdad = 7;
                validacionEdadNinoActivaEvento(_edadSiete);
                listTexView.add(_edadSiete);
                validacionEdadNinoDesactivarEvento();
            }
        });
        _edadOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auxEdad = 8;
                validacionEdadNinoActivaEvento(_edadOcho);
                listTexView.add(_edadOcho);
                validacionEdadNinoDesactivarEvento();
            }
        });
        _edadNueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auxEdad = 9;
                validacionEdadNinoActivaEvento(_edadNueve);
                listTexView.add(_edadNueve);
                validacionEdadNinoDesactivarEvento();
            }
        });
        _edadDiez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auxEdad = 10;
                validacionEdadNinoActivaEvento(_edadDiez);
                listTexView.add(_edadDiez);
                validacionEdadNinoDesactivarEvento();
            }
        });
        _edadOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auxEdad = 11;
                validacionEdadNinoActivaEvento(_edadOnce);
                listTexView.add(_edadOnce);
                validacionEdadNinoDesactivarEvento();
            }
        });
        _edadDoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auxEdad = 12;
                validacionEdadNinoActivaEvento(_edadDoce);
                listTexView.add(_edadDoce);
                validacionEdadNinoDesactivarEvento();
            }
        });
    }

    private void validacionEdadNinoActivaEvento(TextView textView) {
        textView.setTextSize(60);
        textView.setTextColor(Color.parseColor("#6F0CD2"));
    }

    private void validacionEdadNinoDesactivarEvento() {
        if (listTexView.size() > 1) {
            if (!listTexView.get(0).getText().toString().equals(listTexView.get(1).getText().toString())) {
                listTexView.get(0).setTextColor(Color.parseColor("#DEDFE1"));
                listTexView.get(0).setTextSize(40);
            }
            listTexView.remove(0);
        }
    }

    //Primera parte -- Registrar genereo
    private void registrarGenero() {
        _btnRegistraGenero.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (generoNino.equals("")) {
                    toast.toastp(getApplicationContext(), "Seleccione el genero del niño");
                } else {
                    _registroNinoGenero.setVisibility(View.GONE);
                    _registroDatosNino.setVisibility(View.VISIBLE);

                    if (generoNino.equals("hombre"))
                        _imgGeneroNinoRegistroDatos.setImageResource(R.drawable.icon_genero_hombre);
                    else
                        _imgGeneroNinoRegistroDatos.setImageResource(R.drawable.icon_genero_mujer);

                    regresar = 2;
                    registrarDatosNino();
                }
            }
        });
    }

    //Segunta parte -- registrar datos personales
    private void registrarDatosNino() {

        _btnRegistrarDatosNino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = _txtHijoNombre.getText().toString();
                String apellidoPaterno = _txtHijoApellidoPaterno.getText().toString();
                String apellidoMaterno = _txtHijoApellidoMaterno.getText().toString();

                if (nombre.isEmpty()) {
                    toast.toastp(getApplicationContext(), "Ingrese el nombre del niño");
                } else {
                    nombreNino = nombre;

                    if (apellidoPaterno.isEmpty()) {
                        toast.toastp(getApplicationContext(), "Ingrese su apellido paterno");
                    } else {
                        apellidoPNino = apellidoPaterno;

                        if (apellidoMaterno.isEmpty()) {
                            toast.toastp(getApplicationContext(), "Ingrese su apellido materno");
                        } else {
                            apellidoMNino = apellidoMaterno;

                            if (auxEdad == 0) {
                                toast.toastp(getApplicationContext(), "Seleccione la edad del niño");
                            } else {
                                _registroDatosNino.setVisibility(View.GONE);
                                _registroMedidasNino.setVisibility(View.VISIBLE);
                                regresar = 3;
                                registrarMedidasNino();
                            }
                        }
                    }
                }
            }
        });
    }

    //Tercera parte -- registrar medidas corporales
    private void registrarMedidasNino() {

        _btnAddNino.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String peso = _txtHijoPeso.getText().toString().trim();
                String estatura = _txtHijoEstatura.getText().toString().trim();

                if (peso.isEmpty()) {
                    toast.toastp(getApplicationContext(), "Ingrese su peso");
                } else {
                    pesoNino = peso;

                    if (estatura.isEmpty()) {
                        toast.toastp(getApplicationContext(), "Ingrese su estatura");
                    } else {
                        estaturaNino = estatura;

                        _registroNino.setVisibility(View.GONE);
                        _preferenciasNino.setVisibility(View.VISIBLE);
                        regresar = 4;
                    }
                }
            }

        });
    }

    //Cuarta parte -- preferencias
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
    public void onItemChangeListener(int position, PreferenciasNino model) {
        try {
            frutasItem.set(position, model);
        } catch (Exception e) {
        }
    }

    private void insertGustosFrutas() {

        temp = new ArrayList<>();

        try {
            for (int i = 0; i < frutasItem.size(); i++) {

                if (!frutasItem.get(i).isSelect()) {
                    temp.add(frutasItem.get(i));
                }
                if (gustos.equals("siGusta")) {
                    if (frutasItem.get(i).isSelect()) {
                        siGustaFruta.add(frutasItem.get(i).getNombreFruta());

                    }
                } else if (gustos.equals("noGusta")) {
                    if (frutasItem.get(i).isSelect()) {
                        noGustaFruta.add(frutasItem.get(i).getNombreFruta());

                    }
                } else if (gustos.equals("conosco")) {
                    if (frutasItem.get(i).isSelect()) {
                        conoscoFruta.add(frutasItem.get(i).getNombreFruta());

                    }
                    if (!frutasItem.get(i).isSelect()) {
                        noSelectFruta.add(frutasItem.get(i).getNombreFruta());

                    }

                    updateVerdura = true;
                }
            }

            frutasItem = temp;
            if (gustosAuxiliar == 0) {
                gustos = "noGusta";
                gustosAuxiliar = 1;
                toast.toastp(getApplicationContext(), "Seleccione la fruta que no le gusta");
                _txtAvisoPreferencia.setText("Seleccione la fruta que no le gusta a su hijo");
            } else if (gustosAuxiliar == 1) {
                gustos = "conosco";
                gustosAuxiliar = 2;
                toast.toastp(getApplicationContext(), "Seleccione la fruta que no conoce");
                _txtAvisoPreferencia.setText("Seleccione la fruta que no conoce su hijo");
            }

            if (frutasItem.size() == 0) {
                _myRecyclerViewFrutas.setVisibility(View.GONE);
            }
            mAdapter.setModel(frutasItem);
            mAdapter.notifyDataSetChanged();

            if (updateVerdura == true) {
                updateVerduras();
                gustos = "siGusta";
                updateVerdura = false;
                gustosAuxiliar = 0;
            }

        } catch (Exception e) {
            Log.e(TAG, "Error" + e);
        }

    }

    private void insertGustosVerduras() {

        temp = new ArrayList<>();

        try {
            for (int i = 0; i < frutasItem.size(); i++) {

                if (!frutasItem.get(i).isSelect()) {
                    temp.add(frutasItem.get(i));
                }
                if (gustos.equals("siGusta")) {
                    if (frutasItem.get(i).isSelect()) {
                        siGustaVerdura.add(frutasItem.get(i).getNombreFruta());

                    }
                } else if (gustos.equals("noGusta")) {
                    if (frutasItem.get(i).isSelect()) {
                        noGustaVerdura.add(frutasItem.get(i).getNombreFruta());

                    }
                } else if (gustos.equals("conosco")) {
                    if (frutasItem.get(i).isSelect()) {
                        conoscoVerdura.add(frutasItem.get(i).getNombreFruta());

                    }
                    if (!frutasItem.get(i).isSelect()) {
                        noSelectVerdura.add(frutasItem.get(i).getNombreFruta());

                    }
                    updateVerdura = true;
                }
            }

            frutasItem = temp;
            if (gustosAuxiliar == 0) {
                gustos = "noGusta";
                gustosAuxiliar = 1;
                toast.toastp(getApplicationContext(), "Seleccione la verdura que no le gusta");
                _txtAvisoPreferencia.setText("Seleccione la verdura que no le gusta a su hijo");
            } else if (gustosAuxiliar == 1) {
                gustos = "conosco";
                gustosAuxiliar = 2;
                toast.toastp(getApplicationContext(), "Seleccione la verdura que no conoce");
                _txtAvisoPreferencia.setText("Seleccione la verdura que no conoce su hijo");
            }

            if (frutasItem.size() == 0) {
                _myRecyclerViewFrutas.setVisibility(View.GONE);
            }
            mAdapter.setModel(frutasItem);
            mAdapter.notifyDataSetChanged();

            if (updateVerdura == true) {
                registrarNino();
            }

        } catch (Exception e) {
            Log.e(TAG, "Error" + e);
        }

    }

    private void updateVerduras() {

        addItemsJSON("Verdura");
        addPreferecias(_myRecyclerViewVerduras);
        temp = frutasItem;
        _myRecyclerViewFrutas.setVisibility(View.GONE);
        _myRecyclerViewVerduras.setVisibility(View.VISIBLE);
        _btnRegistrarPreferenciasFrutas.setVisibility(View.GONE);
        _btnRegistrarPreferenciasVerduras.setVisibility(View.VISIBLE);

        toast.toastp(getApplicationContext(), "Seleccione la verdura que mas le gusta");
        _txtAvisoPreferencia.setText("Seleccione la verdura que mas le gusta a su hijo");

        findViewById(R.id.btnRegistrarPreferenciasVerduras).setOnClickListener(HijoRegistroActivity.this);
    }

    private void registrarNino() {
        SharedPreferences preferenc = getApplicationContext().getSharedPreferences("Calculo", getApplicationContext().MODE_PRIVATE);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Usuario", MODE_PRIVATE);
        int inpre = preferenc.getInt("primera", 0);
        if (inpre == 0) {
            SharedPreferences.Editor editor = preferenc.edit();
            editor.putInt("primera", 1);
            editor.putInt("nino", 1);
            editor.commit();
        }
        int idNino = preferenc.getInt("nino", 1);

        ConexionApi.insertarNiñoNuevo(
                getApplicationContext(),
                sharedPreferences.getInt("idGlobal", 0),
                generoNino,
                nombreNino,
                apellidoPNino,
                apellidoMNino,
                auxEdad,
                Double.parseDouble(pesoNino),
                Double.parseDouble(estaturaNino),
                0.0,
                0.0,
                0.0,
                0,
                0.0,
                0.0,
                0.0);

        //Agregar preferencias frutas
        for (int i = 0; i < siGustaFruta.size(); i++) {
            preferenciasDao.addPreferenciasFrutas
                    (
                            TAG,
                            getApplicationContext(),
                            idNino,
                            siGustaFruta.get(i),
                            1,
                            0,
                            0,
                            1
                    );
        }
        for (int i = 0; i < noGustaFruta.size(); i++) {
            preferenciasDao.addPreferenciasFrutas
                    (
                            TAG,
                            getApplicationContext(),
                            idNino,
                            noGustaFruta.get(i),
                            0,
                            1,
                            0,
                            1
                    );
        }
        for (int i = 0; i < conoscoFruta.size(); i++) {
            preferenciasDao.addPreferenciasFrutas
                    (
                            TAG,
                            getApplicationContext(),
                            idNino,
                            conoscoFruta.get(i),
                            0,
                            0,
                            1,
                            1
                    );
        }
        for (int i = 0; i < noSelectFruta.size(); i++) {
            preferenciasDao.addPreferenciasFrutas
                    (
                            TAG,
                            getApplicationContext(),
                            idNino,
                            noSelectFruta.get(i),
                            0,
                            0,
                            0,
                            1
                    );
        }

        //Agregar preferencias verduras

        for (int i = 0; i < siGustaVerdura.size(); i++) {
            preferenciasDao.addPreferenciasVerduras
                    (
                            TAG,
                            getApplicationContext(),
                            idNino,
                            siGustaVerdura.get(i),
                            1,
                            0,
                            0,
                            1
                    );
        }
        for (int i = 0; i < noGustaVerdura.size(); i++) {
            preferenciasDao.addPreferenciasVerduras
                    (
                            TAG,
                            getApplicationContext(),
                            idNino,
                            noGustaVerdura.get(i),
                            0,
                            1,
                            0,
                            1
                    );
        }
        for (int i = 0; i < conoscoVerdura.size(); i++) {
            preferenciasDao.addPreferenciasVerduras
                    (
                            TAG,
                            getApplicationContext(),
                            idNino,
                            conoscoVerdura.get(i),
                            0,
                            0,
                            1,
                            1
                    );
        }
        for (int i = 0; i < noSelectVerdura.size(); i++) {
            preferenciasDao.addPreferenciasVerduras
                    (
                            TAG,
                            getApplicationContext(),
                            idNino,
                            noSelectVerdura.get(i),
                            0,
                            0,
                            0,
                            1
                    );

            int incremento = idNino;
            incremento++;
            SharedPreferences.Editor edito = preferenc.edit();
            edito.remove("nino");
            edito.putInt("nino", incremento);
            edito.commit();
        }

        new registrarHijoProgress().execute("add");

    }

    private void init() {
        _registroNino = (ConstraintLayout) findViewById(R.id.registroNino);
        _preferenciasNino = (NestedScrollView) findViewById(R.id.preferenciasNino);
        _btnRegistrarPreferenciasVerduras = (Button) findViewById(R.id.btnRegistrarPreferenciasVerduras);
        _btnRegistrarPreferenciasFrutas = (Button) findViewById(R.id.btnRegistrarPreferenciasFrutas);
        _txtAvisoPreferencia = (TextView) findViewById(R.id.txtAvisoPreferencia);

        _myRecyclerViewFrutas = findViewById(R.id.myRecyclerViewPreferenciasFrutas);
        _myRecyclerViewVerduras = findViewById(R.id.myRecyclerViewPreferenciasVerduras);

        _txtHijoNombre = (EditText) findViewById(R.id.txtHijoNombre);
        _txtHijoApellidoPaterno = (EditText) findViewById(R.id.txtHijoApellidoPaterno);
        _txtHijoApellidoMaterno = (EditText) findViewById(R.id.txtHijoApellidoMaterno);
        txtToast = (TextView) findViewById(R.id.txtToast);

        _txtHijoPeso = (EditText) findViewById(R.id.txtHijoPeso);
        _txtHijoEstatura = (EditText) findViewById(R.id.txtHijoEstatura);
        //_txtHijoMedidaCintura = (EditText) findViewById(R.id.txtHijoMedidaCintura);

        //Primera pare - Registrar Genero
        _registroNinoGenero = (ConstraintLayout) findViewById(R.id.RegistroNinoGenero);
        _btnRegistraGenero = (Button) findViewById(R.id.btnRegistrarGenero);

        //Segunda parte - Datos del nino
        _registroDatosNino = (ConstraintLayout) findViewById(R.id.RegistroDatosNino);
        _btnRegistrarDatosNino = (Button) findViewById(R.id.btnRegistrarDatosNino);
        _imgGeneroNinoRegistroDatos = (ImageView) findViewById(R.id.imgGeneroNinoRegistroDatos);
        //_fondoGeneroNino = (RelativeLayout) findViewById(R.id.fondoGeneroNino);

        //Tercera parte - medidas corporales
        _registroMedidasNino = (ConstraintLayout) findViewById(R.id.registroNino);

        _edadSeis = (TextView) findViewById(R.id.edadSeis);
        _edadSiete = (TextView) findViewById(R.id.edadSiete);
        _edadOcho = (TextView) findViewById(R.id.edadOcho);
        _edadNueve = (TextView) findViewById(R.id.edadNueve);
        _edadDiez = (TextView) findViewById(R.id.edadDiez);
        _edadOnce = (TextView) findViewById(R.id.edadOnce);
        _edadDoce = (TextView) findViewById(R.id.edadDoce);

        //Seleccionar genero (Hombre, mujer)
        _btnGeneroHombre = (LinearLayout) findViewById(R.id.btmGeneroHombre);
        _btnAddNino = (Button) findViewById(R.id.btnAddNino);
        _btnGeneroMujer = (LinearLayout) findViewById(R.id.btmGeneroMujer);
        _checkGeneroHombre = (RelativeLayout) findViewById(R.id.checkGeneroHombre);
        _checkGeneroMujer = (RelativeLayout) findViewById(R.id.checkGeneroMujer);

        //Regresar
        _btnRegresarGeneroNino = (RelativeLayout) findViewById(R.id.btmRegresarGeneroNino);
        _btnRegresarDatosNino = (RelativeLayout) findViewById(R.id.btmRegresarDatosNino);
    }

    //Regresar

    @Override
    public void onBackPressed() {
        if (regresar == 3) {
            _btnRegresarDatosNino.performClick();
        } else {
            if (regresar == 2) {
                _btnRegresarGeneroNino.performClick();
            } else {
                if (regresar == 1 || regresar == 4) {
                    super.onBackPressed();
                }
            }
        }
    }

    private class registrarHijoProgress extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(HijoRegistroActivity.this,
                    "Porfavor espere", "Registrando al niño", true, false);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                boolean estadoRegistroUsuario = userDao.estadoUsuario(TAG, getApplicationContext());

                if (estadoRegistroUsuario == true) {

                    Intent detalleConsumo = new Intent(getApplicationContext(), DetalleConsumoDia.class);
                    startActivity(detalleConsumo);
                    finish();

                } else {

                    SharedPreferences sharedPreferences = getApplication().getSharedPreferences("Usuario", MODE_PRIVATE);
                    userDao.updateEstadoUsuario(TAG, getApplicationContext());
                    conexionApi.updateEstadoUsuario(getApplicationContext(), sharedPreferences.getInt("idGlobal", 0));

                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }

                return "Success";

            } catch (Exception e) {
                e.printStackTrace();
                return "Fail";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            if (s.equals("Success")) {
                toast.toastp(getApplicationContext(), "Se ha registrado conrrectamente el niño");
            } else {
                toast.toastp(getApplicationContext(), "Algo salio mal");
            }
        }
    }

}
