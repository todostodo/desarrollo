package com.example.desarrollo.Precentacion.ReporteConsumo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.Calculos;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.ReporteConsumoDao;
import com.example.desarrollo.Entidades.Nino;
import com.example.desarrollo.Entidades.ReporteConsumo;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewReporteConsumo;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReporteConsumoActivity extends AppCompatActivity {

    private Spinner spinnerReporteConsumo, spinnerNinoReporteConsumo;
    private ArrayAdapter<CharSequence> adapterApinnerNino;
    private TextView _txtSumPorcionesReporteConsumo;
    private Calendar calendar;
    private List<String> spinnerArrayFecha = new ArrayList<>();
    private String[] getDaysFormat = new String[7];
    private ImageView btnUltraprocesadosReporte;
    private TextView textSelectUltraprocesador, txtFechaInicioReporteConsumo, txtFechaFinalReporteConsumo, txtSumKcaloriasReporteConsumo;
    private boolean activarUltraprocesados = false, desplazarAlimento = true, desplazarUltraP = false;
    private ConstraintLayout btnOcultarReporteAlimentos, btnOcultarReporteUltraP;
    private ImageView imgDesplazarAlimentosReporte, imgDesplazarUltraPReporte;
    private LinearLayout layoutMostrarAlimentosReporteConsumo, layoutMostrarUltraPReporteConsumo;
    private RelativeLayout _btnCerrarReporteConsumo;
    private LinearLayout layoutDezplazarUltraPReporteConsumo;

    private RecyclerView _myRecyclerViewReporteConsumo, _myRecyclerViewUltraPReporteConsumo;
    private RecyclerViewReporteConsumo adapterReporteConsumo;
    private ArrayList<ReporteConsumo> reporteConsumoListFV = new ArrayList<>();
    private ArrayList<ReporteConsumo> reporteConsumoListUltraP = new ArrayList<>();
    private ArrayList<Nino> ninoList = new ArrayList<>();
    private ArrayList<String> listNino;

    private ReporteConsumoDao reporteConsumoDao = new ReporteConsumoDao();
    private Calculos calculos;
    private Toastp toastp;
    private NinoDao ninoDao;

    private static final String TAG = "ReporteConsumoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte_consumo_activity);
        init();

        btnUltraprocesadosReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activarUltraprocesados == true) {
                    textSelectUltraprocesador.setVisibility(View.INVISIBLE);
                    layoutMostrarUltraPReporteConsumo.setVisibility(View.GONE);
                    activarUltraprocesados = false;
                    layoutMostrarAlimentosReporteConsumo.setVisibility(View.VISIBLE);
                    imgDesplazarAlimentosReporte.setImageResource(R.drawable.icon_desplazar_abajo);
                    desplazarAlimento = true;
                } else {
                    textSelectUltraprocesador.setVisibility(View.VISIBLE);
                    activarUltraprocesados = true;
                    layoutMostrarUltraPReporteConsumo.setVisibility(View.VISIBLE);
                    layoutMostrarAlimentosReporteConsumo.setVisibility(View.GONE);
                    imgDesplazarAlimentosReporte.setImageResource(R.drawable.icon_desplazar_arriba);
                    desplazarAlimento = false;
                }
            }
        });

        btnOcultarReporteAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (desplazarAlimento == true) {
                    layoutMostrarAlimentosReporteConsumo.setVisibility(View.GONE);
                    imgDesplazarAlimentosReporte.setImageResource(R.drawable.icon_desplazar_arriba);
                    desplazarAlimento = false;
                } else {
                    layoutMostrarAlimentosReporteConsumo.setVisibility(View.VISIBLE);
                    imgDesplazarAlimentosReporte.setImageResource(R.drawable.icon_desplazar_abajo);
                    desplazarAlimento = true;
                }
            }
        });

        btnOcultarReporteUltraP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (desplazarUltraP == true) {
                    imgDesplazarUltraPReporte.setImageResource(R.drawable.icon_desplazar_abajo);
                    layoutDezplazarUltraPReporteConsumo.setVisibility(View.VISIBLE);
                    desplazarUltraP = false;
                } else {
                    imgDesplazarUltraPReporte.setImageResource(R.drawable.icon_desplazar_arriba);
                    layoutDezplazarUltraPReporteConsumo.setVisibility(View.GONE);
                    desplazarUltraP = true;
                }
            }
        });

        _btnCerrarReporteConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        selectNino();

    }

    private void selectNino() {

        ninoList.clear();
        ninoDao.consultarNino(TAG, getApplicationContext(), ninoList);
        listNino = new ArrayList<>();

        for (int i = 0; i < ninoList.size(); i++){
            listNino.add(ninoList.get(i).getNombre());
        }

        adapterApinnerNino = new ArrayAdapter(getApplicationContext(), R.layout.spinner_reporte_consumo, listNino);
        adapterApinnerNino.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNinoReporteConsumo.setAdapter(adapterApinnerNino);

        spinnerNinoReporteConsumo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectFecha(ninoList.get(position).getIdNino());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void selectFecha(final int idNino){

        spinnerArrayFecha.add("Semana actual");
        spinnerArrayFecha.add("Semana anterior");
        spinnerArrayFecha.add("2 semanas atras");
        spinnerArrayFecha.add("3 semanas atras");
        spinnerArrayFecha.add("4 semanas atras");
        spinnerArrayFecha.add("5 semanas atras");
        spinnerArrayFecha.add("6 semanas atras");
        spinnerArrayFecha.add("7 semanas atras");
        spinnerArrayFecha.add("8 semanas atras");

        ArrayAdapter<String> adapterApinnerFecha = new ArrayAdapter<String>(this, R.layout.spinner_reporte_consumo, spinnerArrayFecha);
        adapterApinnerFecha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReporteConsumo.setAdapter(adapterApinnerFecha);

        spinnerReporteConsumo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                mostrarReporteConsumo(item.toString(), idNino);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void mostrarReporteConsumo(String semana, int idNino) {

        String[] fechas = new String[7];
        boolean mostrarReporte = false;

        if (semana.equals("Semana actual")) {
            fechas = semanaActual();
            mostrarReporte = true;
        } else if (semana.equals("Semana anterior")) {
            fechas = calendarReporteConsumo(7);
            mostrarReporte = true;
        } else if (semana.equals("2 semanas atras")) {
            fechas = calendarReporteConsumo(14);
            mostrarReporte = true;
        } else if (semana.equals("3 semanas atras")) {
            fechas = calendarReporteConsumo(21);
            mostrarReporte = true;
        } else if (semana.equals("4 semanas atras")) {
            fechas = calendarReporteConsumo(28);
            mostrarReporte = true;
        } else if (semana.equals("5 semanas atras")) {
            fechas = calendarReporteConsumo(35);
            mostrarReporte = true;
        } else if (semana.equals("6 semanas atras")) {
            fechas = calendarReporteConsumo(42);
            mostrarReporte = true;
        } else if (semana.equals("7 semanas atras")) {
            fechas = calendarReporteConsumo(49);
            mostrarReporte = true;
        } else if (semana.equals("8 semanas atras")) {
            fechas = calendarReporteConsumo(56);
            mostrarReporte = true;
        }


        txtFechaInicioReporteConsumo.setText(fechas[0] + " al ");
        txtFechaFinalReporteConsumo.setText(fechas[6]);

        if (mostrarReporte == true) {

            //Codigo para mostrar Reporte Frutas y verduras

            reporteConsumoListFV.clear();
            double sumProciones = 0;
            _myRecyclerViewReporteConsumo.setLayoutManager(new LinearLayoutManager(this));
            reporteConsumoDao.consultarReporteConsumo(TAG, getApplicationContext(), reporteConsumoListFV, getDaysFormat[0], getDaysFormat[6], idNino);
            adapterReporteConsumo = new RecyclerViewReporteConsumo(reporteConsumoListFV, "FrutasVerduras");
            _myRecyclerViewReporteConsumo.setAdapter(adapterReporteConsumo);

            for (int i = 0; i < reporteConsumoListFV.size(); i++) {
                sumProciones += reporteConsumoListFV.get(i).getEquivalencia();
            }
            _txtSumPorcionesReporteConsumo.setText(String.valueOf(calculos.rendondearValor(sumProciones)) + " prc");


            //Codigo para mostrar Reporte Ultra procesados

            reporteConsumoListUltraP.clear();
            double sumKcalorias = 0;
            _myRecyclerViewUltraPReporteConsumo.setLayoutManager(new LinearLayoutManager(this));
            reporteConsumoDao.consultarReporteConsumoUltraProcesados(TAG, getApplicationContext(), reporteConsumoListUltraP, getDaysFormat[0], getDaysFormat[6], idNino);
            adapterReporteConsumo = new RecyclerViewReporteConsumo(reporteConsumoListUltraP, "UltraProcesados");
            _myRecyclerViewUltraPReporteConsumo.setAdapter((adapterReporteConsumo));


            for (int i = 0; i < reporteConsumoListUltraP.size(); i++) {
                sumKcalorias += reporteConsumoListUltraP.get(i).getEquivalencia();
            }

            txtSumKcaloriasReporteConsumo.setText(String.valueOf(calculos.rendondearValor(sumKcalorias)) + " kcal");

        }

    }

    private String[] semanaActual() {
        this.calendar = Calendar.getInstance();
        this.calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        this.calendar.set(Calendar.DAY_OF_WEEK, calendar.MONDAY);
        return getNextWeek();
    }

    private String[] calendarReporteConsumo(int dias) {
        this.calendar = Calendar.getInstance();
        this.calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        this.calendar.set(Calendar.DAY_OF_WEEK, calendar.MONDAY);
        this.calendar.add(Calendar.DATE, -dias);
        return getNextWeek();
    }

    private String[] getNextWeek() {
        DateFormat format = new SimpleDateFormat("dd 'de' MMMM");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] days = new String[7];
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(this.calendar.getTime());
            this.getDaysFormat[i] = dateFormat.format(this.calendar.getTime());
            this.calendar.add(Calendar.DATE, 1);
        }
        return days;
    }

    private void init() {
        spinnerReporteConsumo = (Spinner) findViewById(R.id.spinnerReporteConsumo);
        spinnerNinoReporteConsumo = (Spinner) findViewById(R.id.spinnerNinoReporteConsumo);
        _myRecyclerViewReporteConsumo = (RecyclerView) findViewById(R.id.myRecyclerViewAlimentosReporteConsumo);
        _myRecyclerViewUltraPReporteConsumo = (RecyclerView) findViewById(R.id.myRecyclerViewUltraPReporteConsumo);
        _txtSumPorcionesReporteConsumo = (TextView) findViewById(R.id.txtSumPorcionesReporteConsumo);
        btnUltraprocesadosReporte = (ImageView) findViewById(R.id.btnUltraProcesadosReporteConsumo);
        textSelectUltraprocesador = (TextView) findViewById(R.id.txtSelectUltraprocesadosReporte);
        txtFechaInicioReporteConsumo = (TextView) findViewById(R.id.txtFechaInicioReporteConsumo);
        txtFechaFinalReporteConsumo = (TextView) findViewById(R.id.txtFechaFinalReporteConsumo);
        btnOcultarReporteAlimentos = (ConstraintLayout) findViewById(R.id.btnOcultarReporteAlimentos);
        btnOcultarReporteUltraP = (ConstraintLayout) findViewById(R.id.btnOcultarReporteUltraP);
        imgDesplazarAlimentosReporte = (ImageView) findViewById(R.id.imgDesplazarAlimentosReporte);
        layoutMostrarAlimentosReporteConsumo = (LinearLayout) findViewById(R.id.layoutMostrarAlimentosReporteConsumo);
        imgDesplazarUltraPReporte = (ImageView) findViewById(R.id.imgDesplazarUltraPReporte);
        layoutMostrarUltraPReporteConsumo = (LinearLayout) findViewById(R.id.layoutMostrarUltraPReporteConsumo);
        txtSumKcaloriasReporteConsumo = (TextView) findViewById(R.id.txtSumKcaloriasReporteConsumo);
        _btnCerrarReporteConsumo = (RelativeLayout) findViewById(R.id.btnCerrarReporteConsumo);
        layoutDezplazarUltraPReporteConsumo = (LinearLayout) findViewById(R.id.layoutDezplazarUltraPReporteConsumo);
    }
}
