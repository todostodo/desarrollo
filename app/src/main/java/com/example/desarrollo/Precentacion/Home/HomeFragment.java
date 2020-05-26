package com.example.desarrollo.Precentacion.Home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.Precentacion.Alimentos.AlimentosActivity;
import com.example.desarrollo.Precentacion.Login.IntroduccionActivity;
import com.example.desarrollo.Precentacion.ReporteConsumo.ReporteConsumoActivity;
import com.example.desarrollo.R;

import java.util.Calendar;


public class HomeFragment extends Fragment {

    private TextView _txtSaludo;
    private UserDao userDao;
    private View view;

    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        boolean estadoUsuario = userDao.estadoUsuario(TAG, getContext());

        if (estadoUsuario != true) {

            Intent introduccion = new Intent(getActivity(), IntroduccionActivity.class);
            startActivity(introduccion);
        }

        view = inflater.inflate(R.layout.home_fragment, container, false);
        init();
        saludoUsuario();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getView().findViewById(R.id.btmFrutas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NinoDao ninoDao = new NinoDao();
                int count = ninoDao.countNino(TAG, getActivity());

                if (count > 0) {
                    Intent intent = new Intent(getActivity(), AlimentosActivity.class);
                    startActivity(intent);
                } else {
                    openDialog();
                }
            }
        });

        getView().findViewById(R.id.btnDetalleConsumoDia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetalleConsumoDia.class);
                startActivity(intent);
            }
        });

        getView().findViewById(R.id.btnReporteConsumo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reporteConsumo = new Intent(getActivity(), ReporteConsumoActivity.class);
                startActivity(reporteConsumo);
            }
        });
    }

    private void init() {
        _txtSaludo = (TextView) view.findViewById(R.id.txtSaludo);
    }

    private void saludoUsuario() {
        Calendar calendar = Calendar.getInstance();
        String saludo = null;
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 12) {
            saludo = "Buenos días.";
        } else if (timeOfDay >= 12 && timeOfDay < 18) {
            saludo = "Buenas tardes.";
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            saludo = "Buenas noches.";
        }
        _txtSaludo.setText(saludo);
    }

    private void openDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Aviso")
                .setMessage("Para poder hacer el registro de consumo debe tener por lo menos a un niño registrado")
                //.setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, null).create().show();
    }
}
