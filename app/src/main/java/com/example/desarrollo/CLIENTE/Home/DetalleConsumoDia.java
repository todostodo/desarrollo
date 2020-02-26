package com.example.desarrollo.CLIENTE.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.desarrollo.R;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;


public class DetalleConsumoDia extends Fragment {

    View view;
    ImageButton _btnAddTutor;

    ProgressBar _charFrutas, _chartVerduras;

    Handler handler = new Handler();
    int pStatus = 0;

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
                Intent intent = new Intent(getContext(), HijoRegistroActivity.class);
                startActivity(intent);

            }
        });

        return view;
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

        _btnAddTutor = (ImageButton) view.findViewById(R.id.btnAddTutor);

        _charFrutas = (ProgressBar) view.findViewById(R.id.chartFrutas);
        _chartVerduras = (ProgressBar) view.findViewById(R.id.chartVerduras);
    }
}
