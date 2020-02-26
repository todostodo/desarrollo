package com.example.desarrollo.CLIENTE.Alimentos.Ultraprocesados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.desarrollo.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UltraprocesadosDialogFragment extends BottomSheetDialogFragment {

    String nombre;
    TextView _nombreDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ultraprocesados_dialog, container, false);

        //setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme);

        _nombreDialog = (TextView) view.findViewById(R.id.nombreDialog);

        _nombreDialog.setText(this.getArguments().getString("nombre"));


        return view;
    }
}
