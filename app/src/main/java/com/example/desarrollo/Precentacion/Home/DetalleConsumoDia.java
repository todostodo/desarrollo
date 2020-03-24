package com.example.desarrollo.Precentacion.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.ConexionSQLHelper;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.TutorDao;
import com.example.desarrollo.Entidades.Nino;
import com.example.desarrollo.Entidades.Tutor;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewTutor;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class  DetalleConsumoDia extends Fragment {

    private View view;
    private LinearLayout _btnAddTutor;
    private RelativeLayout _btnAtivityNino;
    private Spinner _spinnerDetalleConsumo;

    private ProgressBar _charFrutas, _chartVerduras;
    private Handler handler = new Handler();
    private int pStatus = 0;

    private RecyclerView _recyclerViewTutor;
    private RecyclerViewTutor adapter;
    private ArrayList<Tutor> tutorList = new ArrayList<>();
    private ArrayList<Nino> ninoList = new ArrayList<>();
    private ArrayList<String> listaNino;
    private ArrayAdapter<CharSequence> adapterSpinner;
    private RelativeLayout mostrarAddNino, mostrarNino;

    private NinoDao ninoDao;
    private TutorDao consultar;

    private static final String TAG = "DetalleConsumoDia";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detalle_consumo_dia_fragment, container, false);

        init();
        startChart();
        consultarNinos();
        cargarTutores();

        //Add tutor
        _btnAddTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getItemCount() == 3){
                    Toast.makeText(getContext(), "Alcanzo el numero maximo de tutores", Toast.LENGTH_SHORT).show();
                }
                else{
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

    public void consultarNinos(){
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
                cargarDetalleConsumoNino(ninoList.get(position).getIdNino());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void cargarDetalleConsumoNino(int idNino){
        int cantidadNino = ninoDao.countNino(TAG, getContext());

        if (cantidadNino > 1){
            mostrarAddNino.setVisibility(View.GONE);
            mostrarNino.setVisibility(View.VISIBLE);
        }

    }

    public void cargarTutores(){
        _recyclerViewTutor.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        consultarListaTutor();
        adapter = new RecyclerViewTutor(getContext(), tutorList);
        _recyclerViewTutor.setAdapter(adapter);
    }

    private void consultarListaTutor() {
        consultar.consultaTutor(getContext(), tutorList);
    }

    private void startChart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus < 36){
                    pStatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            _charFrutas.setProgress(pStatus);

                        }
                    });
                    try {
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus < 20){
                    pStatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            _chartVerduras.setProgress(pStatus);

                        }
                    });
                    try {
                        Thread.sleep(20);
                    }catch (InterruptedException e){
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
    }
}
