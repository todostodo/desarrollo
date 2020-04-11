package com.example.desarrollo.Precentacion.Home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Precentacion.Alimentos.AlimentosActivity;
import com.example.desarrollo.R;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_fragment, container, false);

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
    }

    private void openDialog(){
        new AlertDialog.Builder(getContext())
                .setTitle("Aviso")
                .setMessage("Para poder hacer el registro de consumo debe tener por lo menos a un niño registrado")
                //.setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, null).create().show();
    }
}
