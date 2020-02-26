package com.example.desarrollo.CLIENTE.Encuestas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.desarrollo.R;

public class registroHijo extends AppCompatActivity implements View.OnClickListener{

    ImageButton btm1, btm2, btm3;
    Button _btnAceptar_cantidad, _btnRegresar;
    EditText _txtMas;
    LinearLayout _registro, _registroCantidad;

    Dialog epicDialog;
    Button _btnAceptarDialog;
    int seleccionado = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_hijo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Botones para la indicar la cantidad de hijos

        btm1 = (ImageButton) findViewById(R.id.btmUno);
        btm1.setOnClickListener(this);

        btm2 = (ImageButton) findViewById(R.id.btmDos);
        btm2.setOnClickListener(this);

        btm3 = (ImageButton) findViewById(R.id.btmTres);
        btm3.setOnClickListener(this);

        _txtMas = (EditText) findViewById(R.id.txtMas);
        _txtMas.setOnClickListener(this);



        /*
        * METODO PARA REGRESAR ALA PANTALLA DE SELECCION DE LA CANTIDAD
        * DE HIJOS*/
        _btnRegresar = (Button) findViewById(R.id.btmRegresar);
        _btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _registroCantidad.setVisibility(View.VISIBLE);
                _registro.setVisibility(View.GONE);
            }
        });

        //Uso de layout
        _registro = (LinearLayout) findViewById(R.id.registro);
        _registroCantidad = (LinearLayout) findViewById(R.id.registroCantidad);


        /*
        * METODO PARA ACEPTAR LA CANTIDAD DE HIJOS SELECCIONADO
        * */
        _btnAceptar_cantidad = (Button) findViewById(R.id.btmAceptar_cantidad);
        _btnAceptar_cantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    _registroCantidad.setVisibility(View.GONE);
                    _registro.setVisibility(View.VISIBLE);
            }
        });

        /*
        * METODO PARA ABRIR EL LA PANTALLA epic_registro_terminado AVISANDO QUE YA TERMINO
        * DE REGITRAR A SU HIJO*/
        epicDialog = new Dialog(this);
        _btnAceptarDialog = (Button) findViewById(R.id.btmAceptar_registro);
        _btnAceptarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFinalizacionRegistro();
            }
        });

    }

    /*
     * METODO PARA ABRIR EL LA PANTALLA epic_registro_terminado AVISANDO QUE YA TERMINO
     * DE REGITRAR A SU HIJO*/
    public void ShowFinalizacionRegistro(){
        epicDialog.setContentView(R.layout.epic_registro_terminado);
        _btnAceptarDialog = (Button) epicDialog.findViewById(R.id.btnAceptar_finalizacionRegistro);

        _btnAceptarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }


    /*
    * METODO PARA ESTABLECER LA CANTIDAD DE HIJOS DEL USUARIO*/
    @Override
    public void onClick(View v) {
        if (v.getId() == btm1.getId()){
            btm1.setImageResource(R.drawable.unoseleccionado);
            btm2.setImageResource(R.drawable.dos);
            btm3.setImageResource(R.drawable.tres);
            seleccionado = 1;
        }
        if (v.getId() == btm2.getId()){
            btm1.setImageResource(R.drawable.uno);
            btm2.setImageResource(R.drawable.dosseleccionado);
            btm3.setImageResource(R.drawable.tres);
            seleccionado = 2;
        }
        if (v.getId() == btm3.getId()){
            btm1.setImageResource(R.drawable.uno);
            btm2.setImageResource(R.drawable.dos);
            btm3.setImageResource(R.drawable.tresseleccionado);
            seleccionado = 3;
        }
    }
}
