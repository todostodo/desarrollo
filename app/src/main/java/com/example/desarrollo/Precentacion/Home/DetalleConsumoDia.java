package com.example.desarrollo.Precentacion.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.ConexionSQLHelper;
import com.example.desarrollo.Datos.TutorDao;
import com.example.desarrollo.Entidades.Tutor;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewTutor;
import com.example.desarrollo.R;

import java.util.ArrayList;

public class  DetalleConsumoDia extends Fragment {

    View view;
    ImageView _btnAddTutor;
    Button _btnTutorVer, _btnTutorEliminar;

    ProgressBar _charFrutas, _chartVerduras;
    Handler handler = new Handler();
    int pStatus = 0;

    RecyclerView _recyclerViewTutor;
    RecyclerViewTutor adapter;
    ArrayList<Tutor> tutorList = new ArrayList<>();

    TutorDao consultar;

    ConexionSQLHelper conexion;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detalle_consumo_dia_fragment, container, false);

        init();
        startChart();

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
                }
            }
        });

        conexion = new ConexionSQLHelper(getContext(), "basedatos", null, 1);
        _recyclerViewTutor.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        consultarListaTutor();

        adapter = new RecyclerViewTutor(getContext(), tutorList);
        _recyclerViewTutor.setAdapter(adapter);

        return view;
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

        _btnAddTutor = (ImageView) view.findViewById(R.id.btnNuevoTutor);

        _charFrutas = (ProgressBar) view.findViewById(R.id.chartFrutas);
        _chartVerduras = (ProgressBar) view.findViewById(R.id.chartVerduras);

        _recyclerViewTutor = (RecyclerView) view.findViewById(R.id.recyclerViewTutores);
    }
}
