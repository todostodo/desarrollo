package com.example.desarrollo.Precentacion.Motivadores;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewMotivadoresSelect;
import com.example.desarrollo.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class MotivadoresSelectActivity extends AppCompatActivity {

    private RecyclerView _myRecyclerViewMotivadores;
    private RecyclerViewMotivadoresSelect adapter;
    private ArrayList<MotivadoresSelect> motivadoresList = new ArrayList<>();
    private Dialog addMotivadorDialog;
    private RelativeLayout _btnAddMotivador;
    private RelativeLayout _btnCerrarMotivadoresSelect;

    MotivadoresDao consultar;

    private static final String TAG = "MotivadoresSelectActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motivadores_select_activity);

        init();

        _myRecyclerViewMotivadores.setLayoutManager(new LinearLayoutManager(this));
        _myRecyclerViewMotivadores.scrollToPosition(1);
        consultarListaMotivadoresDisponibles();


        _btnAddMotivador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMotivador();
            }
        });

        _btnCerrarMotivadoresSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void consultarListaMotivadoresDisponibles() {
        motivadoresList.clear();
        consultar.cosultarMotivadores(TAG, getApplicationContext(), motivadoresList);
        adapter = new RecyclerViewMotivadoresSelect(getApplicationContext(), motivadoresList);
        _myRecyclerViewMotivadores.setAdapter(adapter);
    }

    private void insertMotivador() {

        addMotivadorDialog = new BottomSheetDialog(MotivadoresSelectActivity.this);
        addMotivadorDialog.setContentView(R.layout.motivadores_add_dialog);
        addMotivadorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final RelativeLayout backgroundDescripcion = (RelativeLayout) addMotivadorDialog.findViewById(R.id.relBackgroudDescripcionMotivador);
        final RelativeLayout backgroundValor = (RelativeLayout) addMotivadorDialog.findViewById(R.id.relBackgroudValorMotivador);
        final TextView txtDescripcion = (TextView) addMotivadorDialog.findViewById(R.id.txtDescripcionNuevoMotivador);
        final TextView txtValor = (TextView) addMotivadorDialog.findViewById(R.id.txtValorNuevoMotivador);
        Button btnAddMotivador = (Button) addMotivadorDialog.findViewById(R.id.btnAddNuevoMotivador);

        btnAddMotivador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descripcion = txtDescripcion.getText().toString();
                String valor = txtValor.getText().toString().trim();

                if (descripcion.equals("")) {
                    backgroundDescripcion.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                    Toast.makeText(getApplicationContext(), "Ingrese la descripción del motivador", Toast.LENGTH_SHORT).show();
                } else {
                    backgroundDescripcion.setBackgroundResource(R.drawable.rectangulo_gris);
                    if (valor.equals("")) {
                        backgroundValor.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                        Toast.makeText(getApplicationContext(), "Ingrese el valor del motivador", Toast.LENGTH_SHORT).show();
                    }else{
                        backgroundValor.setBackgroundResource(R.drawable.rectangulo_gris);

                        boolean insert = consultar.insertMotivador(TAG, getApplicationContext(), descripcion, Integer.valueOf(valor));

                        if (insert == true){
                            Toast.makeText(getApplicationContext(), "El motivador fue agregado con exito", Toast.LENGTH_SHORT).show();
                            addMotivadorDialog.dismiss();
                            consultarListaMotivadoresDisponibles();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error al agregar el motivador", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        addMotivadorDialog.show();
    }

    private void init() {
        _myRecyclerViewMotivadores = (RecyclerView) findViewById(R.id.myRecyclerViewMotivadoresDisponibles);
        _btnAddMotivador = (RelativeLayout) findViewById(R.id.btnAddMotivador);
        _btnCerrarMotivadoresSelect = (RelativeLayout) findViewById(R.id.btnCerrarLogin);
    }
}
