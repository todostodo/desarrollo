package com.example.desarrollo.Precentacion.Motivadores;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.desarrollo.R;

public class MotivadoresFragment extends Fragment{

    private View view;
    private CardView _btnSelectMotivador;


    private static final String TAG = "MotivadoresFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.motivadores_fragment, container, false);

        init();

        _btnSelectMotivador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //seleccionarMotivador();
                Intent intent = new Intent(getActivity(), MotivadoresSelectActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void init() {
        _btnSelectMotivador = (CardView) view.findViewById(R.id.btnSelectMotivador);
    }
}
