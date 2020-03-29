package com.example.desarrollo.Precentacion.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.math.BigDecimal;
import java.util.ArrayList;

public class DetalleConsumoDia extends Fragment {

    private View view;
    private LinearLayout _btnAddTutor;
    private RelativeLayout _btnAtivityNino;
    private Spinner _spinnerDetalleConsumo;
    private TextView _txtAvanceFrutas, _txtAvanceVerduras;

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
    private RelativeLayout mostrarAddNino, mostrarNino;

    private NinoDao ninoDao;
    private TutorDao tutorDao;
    private Calculos calculos;

    private static final String TAG = "DetalleConsumoDia";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detalle_consumo_dia_fragment, container, false);

        init();
        consultarNinos();
        cargarTutores();

        //Add tutor
        _btnAddTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapterTutor.getItemCount() == 3) {
                    Toast.makeText(getContext(), "Alcanzo el numero maximo de tutores", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), TutorFragment.class);
                    startActivity(intent);
                    cargarTutores();
                }
            }
        });
        _btnAtivityNino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HijoRegistroActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    public void consultarNinos() {

        int cantidadNino = ninoDao.countNino(TAG, getContext());

        if (cantidadNino > 1) {
            mostrarAddNino.setVisibility(View.GONE);
            mostrarNino.setVisibility(View.VISIBLE);
        }

        ninoList.clear();
        ninoDao.consultarNino(TAG, getContext(), ninoList);

        listaNino = new ArrayList<>();

        for (int i = 0; i < ninoList.size(); i++) {
            listaNino.add(ninoList.get(i).getNombre());
        }

        adapterSpinner = new ArrayAdapter(getContext(), R.layout.spinner_motivadores_item, listaNino);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerDetalleConsumo.setAdapter(adapterSpinner);

        _spinnerDetalleConsumo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cargarHistorialConsumo(ninoList.get(position).getIdNino());
                startChart(ninoList.get(position).getIdNino());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void cargarHistorialConsumo(int idNino) {
        _recyclerViewHistorialConsumo.setLayoutManager(new LinearLayoutManager(getActivity()));
        ninoDao.consultarItemsHistorialDetalleConsumo(TAG, getContext(), idNino, consumoList);
        adapterHistorialConsumo = new RecyclerViewHistorialConsumo(getContext(), consumoList);
        _recyclerViewHistorialConsumo.setAdapter(adapterHistorialConsumo);
    }

    public void cargarTutores() {
        _recyclerViewTutor.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        tutorDao.consultaTutor(getContext(), tutorList);
        adapterTutor = new RecyclerViewTutor(getContext(), tutorList);
        _recyclerViewTutor.setAdapter(adapterTutor);
    }

    private void startChart(int idNino) {

        double avanceEsfuerzoFrutas = calculos.progresoEsfuerzoFruta(TAG, getContext(), idNino);
        double avanceEsfuerzoVerdura = calculos.progresoEsfuerzoVerdura(TAG, getContext(), idNino);
        final int progresoFrutas = (int) avanceEsfuerzoFrutas * 10;
        final int progresoVerduras = (int) avanceEsfuerzoVerdura * 10;

        _txtAvanceFrutas.setText(String.valueOf(avanceEsfuerzoFrutas));
        _txtAvanceVerduras.setText(String.valueOf(avanceEsfuerzoVerdura));


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
    }

    private void init() {

        _btnAddTutor = (LinearLayout) view.findViewById(R.id.btnNuevoTutor);
        _charFrutas = (ProgressBar) view.findViewById(R.id.chartFrutas);
        _chartVerduras = (ProgressBar) view.findViewById(R.id.chartVerduras);
        _recyclerViewTutor = (RecyclerView) view.findViewById(R.id.recyclerViewTutores);
        _btnAtivityNino = (RelativeLayout) view.findViewById(R.id.btnAtivityNino);
        _spinnerDetalleConsumo = (Spinner) view.findViewById(R.id.spinnerDetalleConsumo);
        mostrarAddNino = (RelativeLayout) view.findViewById(R.id.btnAtivityNino);
        mostrarNino = (RelativeLayout) view.findViewById(R.id.relativeNinosAgregados);
        _recyclerViewHistorialConsumo = (RecyclerView) view.findViewById(R.id.recyclerViewHistorial);
        _txtAvanceFrutas = (TextView) view.findViewById(R.id.txtAvanceFrutas);
        _txtAvanceVerduras = (TextView) view.findViewById(R.id.txtAvanceVerduras);
    }
}
