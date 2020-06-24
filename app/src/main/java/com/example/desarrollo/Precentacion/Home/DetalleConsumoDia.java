package com.example.desarrollo.Precentacion.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.Calculos;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.TutorDao;
import com.example.desarrollo.Entidades.HistorialConsumo;
import com.example.desarrollo.Entidades.MensajesPersuasivos;
import com.example.desarrollo.Entidades.Nino;
import com.example.desarrollo.Entidades.Tutor;
import com.example.desarrollo.ExportJSON.Model.ModelMsgPersuasivos;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewHistorialConsumo;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewTutor;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import java.text.DecimalFormat;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DetalleConsumoDia extends AppCompatActivity {

    private LinearLayout _btnAddTutor;
    private RelativeLayout _btnAtivityNino; //_backgroundGeneroNino;
    private Spinner _spinnerDetalleConsumo;
    private TextView _txtAvanceFrutas, _txtAvanceVerduras, _txtCaloriasSemanaPasada, _txtCaloriasSemanaActual, _txtCaloriasHoy;
    private ImageView _imgGeneroNino;
    private RelativeLayout _btmCerrarDetalleConsumo;
    private ProgressBar _charFrutas, _chartVerduras;
    private Handler handler = new Handler();
    private int pStatusFrutas = 0, pStatusVerduras = 0;
    private boolean ganoFichaDialogFruta = false, ganoFichaDialogVerdura = false, ganoFichaDialogUltraP = false;
    private double avanceEsfuerzoVerdura, consultarEsfuerzoConsumoVerduras;
    private DecimalFormat reducirDigitosProciones = new DecimalFormat("#.##");

    private RecyclerView _recyclerViewTutor, _recyclerViewHistorialConsumo;
    private RecyclerViewTutor adapterTutor;
    private RecyclerViewHistorialConsumo adapterHistorialConsumo;
    private ArrayList<Tutor> tutorList = new ArrayList<>();
    private ArrayList<Nino> ninoList = new ArrayList<>();

    private ArrayList<HistorialConsumo> consumoList = new ArrayList<>();
    private ArrayList<String> listaNino;
    private ArrayAdapter<CharSequence> adapterSpinner;

    //Open dialog
    private Button _btnEpicFichaSalirDetalle;
    private TextView _txtEpicFichaTituloDetalle, _txtEpicFichaMensajeDetalle, _txtEpicFichaCantidadDetalle;
    private ConstraintLayout _epicFichaContenidoDetalle;
    private LinearLayout _epicFichaFondoNegroDetalle;
    private ImageView _epicFichaImgDetalle;
    private Animation fromsmall, fromnothing, forloci, togo;
    //------------------

    private NinoDao ninoDao;
    private TutorDao tutorDao;
    private Calculos calculos;
    private Toastp toastp;

    private static final String TAG = "DetalleConsumoDia";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_consumo_dia_fragment);
        init();


        _epicFichaContenidoDetalle.setAlpha(0);
        _epicFichaFondoNegroDetalle.setAlpha(0);
        _epicFichaImgDetalle.setVisibility(View.GONE);

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
                finish();
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
        consumoList.clear();
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

        _charFrutas.setProgress(0);
        _chartVerduras.setProgress(0);

        if (genero.equals("hombre")) {
            _imgGeneroNino.setImageResource(R.drawable.icon_genero_hombre);
        }
        if (genero.equals("mujer")) {
            _imgGeneroNino.setImageResource(R.drawable.icon_genero_mujer);
        }


        double avanceEsfuerzoFrutas = calculos.progresoEsfuerzoFruta(TAG, getApplicationContext(), idNino);
        avanceEsfuerzoVerdura = calculos.progresoEsfuerzoVerdura(TAG, getApplicationContext(), idNino);

        double consultarEsfuerzoConsumoFruta = ninoDao.consultarEsfuerzoConsumoFrutas(TAG, getApplicationContext(), idNino);
        consultarEsfuerzoConsumoVerduras = ninoDao.consultarEsfuerzoConsumoVerduras(TAG, getApplicationContext(), idNino);

        final int progresoFrutas = (int) Math.round((avanceEsfuerzoFrutas * 100) / 2);
        final int progresoVerduras = (int) Math.round((avanceEsfuerzoVerdura * 100) / 2);
        int metaPorcionesFruta = (int) Math.round((consultarEsfuerzoConsumoFruta * 100) / 2);
        int metaPorcionesVerduras = (int) Math.round((consultarEsfuerzoConsumoVerduras * 100) / 2);

        _txtAvanceFrutas.setText(String.valueOf(reducirDigitosProciones.format(consultarEsfuerzoConsumoFruta)) + "/" + String.valueOf(reducirDigitosProciones.format(avanceEsfuerzoFrutas)));
        _txtAvanceVerduras.setText(String.valueOf(reducirDigitosProciones.format(consultarEsfuerzoConsumoVerduras)) + "/" + String.valueOf(reducirDigitosProciones.format(avanceEsfuerzoVerdura)));


        if (idNino == 1) {
            if (!lineaBaseGeneradaNinoUno()) {
                if (avanceEsfuerzoFrutas >= consultarEsfuerzoConsumoFruta)
                    avanceEsfuerzoFrutas = consultarEsfuerzoConsumoFruta;
                if (avanceEsfuerzoVerdura >= consultarEsfuerzoConsumoVerduras)
                    avanceEsfuerzoVerdura = consultarEsfuerzoConsumoVerduras;
            }
        } else {
            if (idNino == 2) {
                if (!lineaBaseGeneradaNinoDos()) {
                    if (avanceEsfuerzoFrutas >= consultarEsfuerzoConsumoFruta)
                        avanceEsfuerzoFrutas = consultarEsfuerzoConsumoFruta;
                    if (avanceEsfuerzoVerdura >= consultarEsfuerzoConsumoVerduras)
                        avanceEsfuerzoVerdura = consultarEsfuerzoConsumoVerduras;
                }
            }
        }

        _charFrutas.setMax(metaPorcionesFruta);
        _chartVerduras.setMax(metaPorcionesVerduras);

        fichaFrutas(idNino, avanceEsfuerzoFrutas, consultarEsfuerzoConsumoFruta);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatusFrutas < progresoFrutas) {
                    pStatusFrutas += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            _charFrutas.setProgress(pStatusFrutas);

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
                while (pStatusVerduras < progresoVerduras) {
                    pStatusVerduras += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            _chartVerduras.setProgress((int) pStatusVerduras);

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

        pStatusFrutas = 0;
        pStatusVerduras = 0;


        double caloriasSemanaPasada = calculos.caloriaFija(getApplicationContext(), idNino);
        double caloriasSemanaActual = calculos.caloriaCambio(getApplicationContext(), idNino);
        double caloriasHoy = calculos.caloriaDia(getApplicationContext(), idNino);

        _txtCaloriasSemanaPasada.setText(String.valueOf(caloriasSemanaPasada));
        _txtCaloriasSemanaActual.setText(String.valueOf(caloriasSemanaActual));
        _txtCaloriasHoy.setText(String.valueOf(caloriasHoy));
    }

    private void mensajeFichas(int idNino, String alimento) {

        ArrayList<MensajesPersuasivos> list = new ArrayList<>();
        ModelMsgPersuasivos msgPersuasivos = new ModelMsgPersuasivos();

        int selectMsg = (int) (Math.random() * 5);

        if (alimento.equals("frutas")) {

            msgPersuasivos.addItemsFromJSONMsgPersuasivos(list, TAG, "consumoPlaneadoFruta", getApplicationContext());
            showDialogFicha(1, idNino, list.get(selectMsg).getTitulo(), list.get(selectMsg).getMensaje());
        }
        if (alimento.equals("verduras")) {

            msgPersuasivos.addItemsFromJSONMsgPersuasivos(list, TAG, "consumoPlaneadoVerduras", getApplicationContext());
            showDialogFicha(1, idNino, list.get(selectMsg).getTitulo(), list.get(selectMsg).getMensaje());
        }
        if (alimento.equals("UltraProcesada")) {

            msgPersuasivos.addItemsFromJSONMsgPersuasivos(list, TAG, "consumoPlaneadoUltraP", getApplicationContext());
            showDialogFicha(1, idNino, list.get(selectMsg).getTitulo(), list.get(selectMsg).getMensaje());

        }

        Log.v(TAG, "Size list -----------------------------------------" + list.size() + " Random " + selectMsg);

    }

    private void fichaFrutas(int idNino, double avanceEsfuerzoConsumoFruta, double baseEsfuerzoFrutas) {

        if (avanceEsfuerzoConsumoFruta >= baseEsfuerzoFrutas) {
            SharedPreferences sharedPreferences = getSharedPreferences("Calculo", MODE_PRIVATE);
            if (idNino == 1) {
                if (lineaBaseGeneradaNinoUno()) {
                    if (!sharedPreferences.getBoolean("fichaFruta1", true)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("fichaFruta1", true);
                        editor.commit();
                        ganoFichaDialogFruta = true;
                        ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 1);

                        mensajeFichas(idNino, "frutas");
                    } else {
                        ganoFichaDialogFruta = false;
                        fichaVerdura(idNino);
                    }
                } else {
                    fichaVerdura(idNino);
                }
            }
            if (idNino == 2) {
                if (lineaBaseGeneradaNinoDos()) {
                    if (!sharedPreferences.getBoolean("fichaFruta2", true)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("fichaFruta2", true);
                        editor.commit();
                        ganoFichaDialogFruta = true;
                        ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 1);

                        mensajeFichas(idNino, "frutas");
                    } else {
                        ganoFichaDialogFruta = false;
                        fichaVerdura(idNino);
                    }
                } else {
                    fichaVerdura(idNino);
                }
            }
        } else {
            ganoFichaDialogFruta = false;
            fichaVerdura(idNino);
        }
    }

    private void fichaVerdura(int idNino) {

        if (avanceEsfuerzoVerdura >= consultarEsfuerzoConsumoVerduras) {
            SharedPreferences sharedPreferences = getSharedPreferences("Calculo", MODE_PRIVATE);
            if (idNino == 1) {
                if (lineaBaseGeneradaNinoUno()) {
                    if (!sharedPreferences.getBoolean("fichaVerdura1", true)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("fichaVerdura1", true);
                        editor.commit();
                        ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 1);

                        ganoFichaDialogVerdura = true;
                        mensajeFichas(idNino, "verduras");
                    } else {
                        ganoFichaDialogVerdura = false;
                        fichaUltraP(idNino);
                    }
                }
            }
            if (idNino == 2) {
                if (lineaBaseGeneradaNinoDos()) {
                    if (!sharedPreferences.getBoolean("fichaVerdura2", true)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("fichaVerdura2", true);
                        editor.commit();
                        ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 1);

                        ganoFichaDialogVerdura = true;
                        mensajeFichas(idNino, "verduras");
                    } else {
                        ganoFichaDialogFruta = false;
                        fichaUltraP(idNino);
                    }
                }
            }
        } else {
            ganoFichaDialogVerdura = false;
            fichaUltraP(idNino);
        }
    }

    private void fichaUltraP(int idNino) {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Calculo", MODE_PRIVATE);
        if (idNino == 1) {
            if (sharedPreferences.getBoolean("fichaNino1", true)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("fichaNino1", false);
                editor.commit();
                ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 1);

                mensajeFichas(idNino, "UltraProcesada");
            }
        } else if (idNino == 2) {
            if (sharedPreferences.getBoolean("fichaNino2", true)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("fichaNino2", false);
                editor.commit();
                ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 1);

                mensajeFichas(idNino, "UltraProcesada");
            }
        }
    }

    private boolean lineaBaseGeneradaNinoUno() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Calculo", MODE_PRIVATE);
        Boolean lineaBaseGeneradaNino1 = sharedPreferences.getBoolean("LineaBaseGenerada1", false);
        return lineaBaseGeneradaNino1;
    }

    private boolean lineaBaseGeneradaNinoDos() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Calculo", MODE_PRIVATE);
        Boolean lineaBaseGeneradaNino2 = sharedPreferences.getBoolean("LineaBaseGenerada2", false);
        return lineaBaseGeneradaNino2;
    }

    private void showDialogFicha(final int fichas, final int idNino, String titulo, String mensaje) {

        _epicFichaImgDetalle.setVisibility(View.VISIBLE);
        _epicFichaImgDetalle.startAnimation(forloci);

        _epicFichaFondoNegroDetalle.setAlpha(1);
        _epicFichaFondoNegroDetalle.startAnimation(fromnothing);

        _epicFichaContenidoDetalle.setAlpha(1);
        _epicFichaContenidoDetalle.startAnimation(fromsmall);

        _txtEpicFichaTituloDetalle.setText(titulo);
        _txtEpicFichaMensajeDetalle.setText(mensaje);
        _txtEpicFichaCantidadDetalle.setText("+" + String.valueOf(fichas));

        _btnEpicFichaSalirDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _epicFichaFondoNegroDetalle.startAnimation(togo);
                _epicFichaContenidoDetalle.startAnimation(togo);
                _epicFichaImgDetalle.startAnimation(togo);
                _epicFichaImgDetalle.setVisibility(View.GONE);

                ViewCompat.animate(_epicFichaContenidoDetalle).setStartDelay(1000).alpha(0).start();
                ViewCompat.animate(_epicFichaFondoNegroDetalle).setStartDelay(1000).alpha(0).start();

                if (ganoFichaDialogFruta == true) {

                    ganoFichaDialogFruta = false;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fichaVerdura(idNino);
                        }
                    }, 1000);

                } else if (ganoFichaDialogVerdura == true) {

                    ganoFichaDialogVerdura = false;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fichaUltraP(idNino);
                        }
                    }, 1000);
                }
            }
        });
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
        //_backgroundGeneroNino = (RelativeLayout) findViewById(R.id.backgroundGeneroNino);
        _imgGeneroNino = (ImageView) findViewById(R.id.imgGeneroNino);
        _txtCaloriasSemanaPasada = (TextView) findViewById(R.id.txtDetalleCaloriasFija);
        _txtCaloriasSemanaActual = (TextView) findViewById(R.id.txtDetalleCaloriasCambio);
        _txtCaloriasHoy = (TextView) findViewById(R.id.txtDetalleCaloriasHoy);

        //Open dialog
        _btnEpicFichaSalirDetalle = (Button) findViewById(R.id.btnEpicFichaSalirDetalle);
        _txtEpicFichaTituloDetalle = (TextView) findViewById(R.id.txtEpicFichaTituloDetalle);
        _txtEpicFichaMensajeDetalle = (TextView) findViewById(R.id.txtEpicFichaMensajeDetalle);
        _txtEpicFichaCantidadDetalle = (TextView) findViewById(R.id.txtEpicFichaCantidadDetalle);
        _epicFichaContenidoDetalle = (ConstraintLayout) findViewById(R.id.epicFichaContenidoDetalle);
        _epicFichaFondoNegroDetalle = (LinearLayout) findViewById(R.id.epicFichaFondoNegroDetalle);
        _epicFichaImgDetalle = (ImageView) findViewById(R.id.epicFichaImgDetalle);

        fromsmall = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fromsmall);
        fromnothing = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fromnothing);
        forloci = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.forloci);
        togo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.togo);
    }
}
