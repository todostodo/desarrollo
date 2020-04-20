package com.example.desarrollo.Precentacion.Home;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.Calculos;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.TutorDao;
import com.example.desarrollo.Entidades.HistorialConsumo;
import com.example.desarrollo.Entidades.Nino;
import com.example.desarrollo.Entidades.Tutor;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewHistorialConsumo;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewTutor;
import com.example.desarrollo.R;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DetalleConsumoDia extends AppCompatActivity {

    private LinearLayout _btnAddTutor;
    private RelativeLayout _btnAtivityNino, _backgroundGeneroNino;
    private Spinner _spinnerDetalleConsumo;
    private TextView _txtAvanceFrutas, _txtAvanceVerduras, _txtCaloriasSemanaPasada, _txtCaloriasSemanaActual, _txtCaloriasHoy;
    private ImageView _imgGeneroNino;
    private RelativeLayout _btmCerrarDetalleConsumo;
    private ProgressBar _charFrutas, _chartVerduras;
    private Handler handler = new Handler();
    private int pStatus = 0;

    private RecyclerView _recyclerViewTutor, _recyclerViewHistorialConsumo;
    private RecyclerViewTutor adapterTutor;
    private RecyclerViewHistorialConsumo adapterHistorialConsumo;
    private ArrayList<Tutor> tutorList = new ArrayList<>();
    private ArrayList<Nino> ninoList = new ArrayList<>();

    private ArrayList<HistorialConsumo> consumoList = new ArrayList<>();
    private ArrayList<String> listaNino;
    private ArrayAdapter<CharSequence> adapterSpinner;

    private NinoDao ninoDao;
    private TutorDao tutorDao;
    private Calculos calculos;

    private static final String TAG = "DetalleConsumoDia";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_consumo_dia_fragment);

        init();
        consultarNinos();
        cargarTutores();

        //Add tutor
        _btnAddTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapterTutor.getItemCount() == 3) {
                    Toast.makeText(getApplicationContext(), "Alcanzo el numero maximo de tutores", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), TutorFragment.class);
                    startActivity(intent);
                    cargarTutores();
                }
            }
        });
        _btnAtivityNino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HijoRegistroActivity.class);
                startActivity(intent);
            }
        });

        //Salir del activity
        _btmCerrarDetalleConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void consultarNinos() {

        int cantidadNino = ninoDao.countNino(TAG, getApplicationContext());

        if (cantidadNino > 1) {
            _btnAtivityNino.setVisibility(View.GONE);
        }

        ninoList.clear();
        ninoDao.consultarNino(TAG, getApplicationContext(), ninoList);

        listaNino = new ArrayList<>();

        for (int i = 0; i < ninoList.size(); i++) {
            listaNino.add(ninoList.get(i).getNombre());
        }

        adapterSpinner = new ArrayAdapter(getApplicationContext(), R.layout.spinner_motivadores_item, listaNino);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerDetalleConsumo.setAdapter(adapterSpinner);

        _spinnerDetalleConsumo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cargarHistorialConsumo(ninoList.get(position).getIdNino());
                cargarDetalleConsumoNino(ninoList.get(position).getIdNino(), ninoList.get(position).getGenero());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void cargarHistorialConsumo(int idNino) {
        _recyclerViewHistorialConsumo.setLayoutManager(new LinearLayoutManager(this));
        ninoDao.consultarItemsHistorialDetalleConsumo(TAG, getApplicationContext(), idNino, consumoList);
        adapterHistorialConsumo = new RecyclerViewHistorialConsumo(getApplicationContext(), consumoList);
        _recyclerViewHistorialConsumo.setAdapter(adapterHistorialConsumo);
    }

    public void cargarTutores() {
        _recyclerViewTutor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        tutorDao.consultaTutor(getApplicationContext(), tutorList);
        adapterTutor = new RecyclerViewTutor(getApplicationContext(), tutorList);
        _recyclerViewTutor.setAdapter(adapterTutor);
    }

    private void cargarDetalleConsumoNino(int idNino, String genero) {

        if (genero.equals("hombre")) {
            _backgroundGeneroNino.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E36B5E")));
            _imgGeneroNino.setBackgroundResource(R.drawable.icon_genero_hombre);
        }
        if (genero.equals("mujer")) {
            _backgroundGeneroNino.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B8C2CD")));
            _imgGeneroNino.setBackgroundResource(R.drawable.icon_genero_mujer);
        }


        double avanceEsfuerzoFrutas = calculos.progresoEsfuerzoFruta(TAG, getApplicationContext(), idNino);
        double avanceEsfuerzoVerdura = calculos.progresoEsfuerzoVerdura(TAG, getApplicationContext(), idNino);
        double constultarEsfuerzoCosumoFruta = ninoDao.consultarEsfuerzoConsumoFrutas(TAG, getApplicationContext(), idNino);
        double consultarEsfuerzoConsumoVerduras = ninoDao.consultarEsfuerzoConsumoVerduras(TAG, getApplicationContext(), idNino);
        final int progresoFrutas = (int) avanceEsfuerzoFrutas * 10;
        final int progresoVerduras = (int) avanceEsfuerzoVerdura * 10;

        _txtAvanceFrutas.setText(String.valueOf(constultarEsfuerzoCosumoFruta) + "/" + String.valueOf(avanceEsfuerzoFrutas));
        _txtAvanceVerduras.setText(String.valueOf(consultarEsfuerzoConsumoVerduras) + "/" + String.valueOf(avanceEsfuerzoVerdura));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus < progresoFrutas) {
                    pStatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            _charFrutas.setProgress(pStatus);

                        }
                    });
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus < progresoVerduras) {
                    pStatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            _chartVerduras.setProgress(pStatus);

                        }
                    });
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //double caloriasSemanaPasada = calculos.KaloriaFija(getApplicationContext(), idNino);
        double caloriasSemanaActual = calculos.KaloriaCambio(getApplicationContext(), idNino);
        double caloriasHoy = calculos.KaloriaDia(getApplicationContext(), idNino);

        //_txtCaloriasSemanaPasada.setText(String.valueOf(caloriasSemanaPasada));
        _txtCaloriasSemanaActual.setText(String.valueOf(caloriasSemanaActual));
        _txtCaloriasHoy.setText(String.valueOf(caloriasHoy));
    }

    private void init() {

        _btnAddTutor = (LinearLayout) findViewById(R.id.btnNuevoTutor);
        _charFrutas = (ProgressBar) findViewById(R.id.chartFrutas);
        _chartVerduras = (ProgressBar) findViewById(R.id.chartVerduras);
        _recyclerViewTutor = (RecyclerView) findViewById(R.id.recyclerViewTutores);
        _btnAtivityNino = (RelativeLayout) findViewById(R.id.btnAtivityNino);
        _spinnerDetalleConsumo = (Spinner) findViewById(R.id.spinnerDetalleConsumo);
        _recyclerViewHistorialConsumo = (RecyclerView) findViewById(R.id.recyclerViewHistorial);
        _txtAvanceFrutas = (TextView) findViewById(R.id.txtAvanceFrutas);
        _txtAvanceVerduras = (TextView) findViewById(R.id.txtAvanceVerduras);
        _btmCerrarDetalleConsumo = (RelativeLayout) findViewById(R.id.btmCerrarDetalleConsumo);
        _backgroundGeneroNino = (RelativeLayout) findViewById(R.id.backgroundGeneroNino);
        _imgGeneroNino = (ImageView) findViewById(R.id.imgGeneroNino);
        _txtCaloriasSemanaPasada = (TextView) findViewById(R.id.txtDetalleCaloriasFija);
        _txtCaloriasSemanaActual = (TextView) findViewById(R.id.txtDetalleCaloriasCambio);
        _txtCaloriasHoy = (TextView) findViewById(R.id.txtDetalleCaloriasHoy);
    }
}
