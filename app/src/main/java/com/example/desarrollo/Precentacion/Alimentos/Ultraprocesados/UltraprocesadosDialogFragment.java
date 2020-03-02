package com.example.desarrollo.Precentacion.Alimentos.Ultraprocesados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.desarrollo.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UltraprocesadosDialogFragment extends BottomSheetDialogFragment {

    View view;

    //Texview
    TextView _nombreDialog;

    //Botones
    Button _btnRegistrarUltraprocesado;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ultraprocesados_dialog, container, false);

        init();

        _nombreDialog = (TextView) view.findViewById(R.id.nombreDialog);

        _nombreDialog.setText(this.getArguments().getString("nombre"));

        _btnRegistrarUltraprocesado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    private void init() {
        _btnRegistrarUltraprocesado = (Button) view.findViewById(R.id.btnRegistrarUltraprocesado);
    }
}
