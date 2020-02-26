package com.example.desarrollo.CLIENTE.Encuestas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.desarrollo.R;

public class registro_cuestionario_seccion3 extends AppCompatActivity {

    private CheckBox _ChBx_1_1_3, _ChBx_2_1_3, _ChBx_3_1_3, _ChBx_4_1_3;
    private Button _btnSiguientePregunta1_3;
    private LinearLayout _preguntaUno_seccion3;

    private CheckBox _ChBx_1_2_3, _ChBx_2_2_3, _ChBx_3_2_3, _ChBx_4_2_3;
    private Button _btnSiguientePregunta2_3;
    private LinearLayout _preguntaDos_seccion3;

    private CheckBox _ChBx_1_3_3, _ChBx_2_3_3, _ChBx_3_3_3;
    private Button _btnSiguientePregunta3_3;
    private LinearLayout _preguntaTres_seccion3;

    private CheckBox _ChBx_1_4_3, _ChBx_2_4_3, _ChBx_3_4_3, _ChBx_4_4_3;
    private Button _btnSiguientePregunta4_3;
    private LinearLayout _preguntaCuatro_seccion3;

    private CheckBox _ChBx_1_5_3, _ChBx_2_5_3, _ChBx_3_5_3, _ChBx_4_5_3, _ChBx_5_5_3;
    private Button _btnSiguientePregunta5_3;
    private LinearLayout _preguntaCinco_seccion3;

    private CheckBox _ChBx_1_6_3, _ChBx_2_6_3, _ChBx_3_6_3, _ChBx_4_6_3;
    private Button _btnSiguientePregunta6_3;
    private LinearLayout _preguntaSeis_seccion3;

    private CheckBox _ChBx_1_7_3, _ChBx_2_7_3, _ChBx_3_7_3, _ChBx_4_7_3;
    private Button _btnSiguientePregunta7_3;
    private LinearLayout _preguntaSiete_seccion3;

    private CheckBox _ChBx_1_8_3, _ChBx_2_8_3, _ChBx_3_8_3, _ChBx_4_8_3;
    private Button _btnSiguientePregunta8_3;
    private LinearLayout _preguntaOcho_seccion3;

    private CheckBox _ChBx_1_9_3, _ChBx_2_9_3, _ChBx_3_9_3, _ChBx_4_9_3, _ChBx_5_9_3;
    private Button _btnSiguientePregunta9_3;
    private LinearLayout _preguntaNueve_seccion3;

    private CheckBox _ChBx_1_10_3, _ChBx_2_10_3, _ChBx_3_10_3, _ChBx_4_10_3;
    private Button _btnSiguientePregunta10_3;
    private LinearLayout _preguntaDiez_seccion3;

    private  CheckBox _ChBx_1_11_3, _ChBx_2_11_3, _ChBx_3_11_3;
    private Button _btnSiguientePregunta11_3;
    private LinearLayout _preguntaOnce_seccion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuestionario_seccion3);

        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA PRIMERA PANTALLA--------------------------
        _ChBx_1_1_3 = (CheckBox) findViewById(R.id.ChBx_1_1_3);
        _ChBx_2_1_3 = (CheckBox) findViewById(R.id.ChBx_2_1_3);
        _ChBx_3_1_3 = (CheckBox) findViewById(R.id.ChBx_3_1_3);
        _ChBx_4_1_3 = (CheckBox) findViewById(R.id.ChBx_4_1_3);
        _btnSiguientePregunta1_3 = (Button) findViewById(R.id.btnSiguientePregunta1_3);
        _preguntaUno_seccion3 = (LinearLayout) findViewById(R.id.preguntaUno_seccion3);
        /*----------------PANTALLA 1 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 1 - SECCION 3*/
        _btnSiguientePregunta1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaUno_seccion3.setVisibility(View.GONE);
                _preguntaDos_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_1_3.setSelected(false);
                _ChBx_3_1_3.setSelected(false);
                _ChBx_4_1_3.setSelected(false);

                _ChBx_2_1_3.setChecked(false);
                _ChBx_3_1_3.setChecked(false);
                _ChBx_4_1_3.setChecked(false);
            }
        });
        _ChBx_2_1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_1_3.setSelected(false);
                _ChBx_3_1_3.setSelected(false);
                _ChBx_4_1_3.setSelected(false);

                _ChBx_1_1_3.setChecked(false);
                _ChBx_3_1_3.setChecked(false);
                _ChBx_4_1_3.setChecked(false);
            }
        });
        _ChBx_3_1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_1_3.setSelected(false);
                _ChBx_1_1_3.setSelected(false);
                _ChBx_4_1_3.setSelected(false);

                _ChBx_1_1_3.setChecked(false);
                _ChBx_2_1_3.setChecked(false);
                _ChBx_4_1_3.setChecked(false);
            }
        });
        _ChBx_4_1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_1_3.setSelected(false);
                _ChBx_3_1_3.setSelected(false);
                _ChBx_1_1_3.setSelected(false);

                _ChBx_1_1_3.setChecked(false);
                _ChBx_2_1_3.setChecked(false);
                _ChBx_3_1_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------

        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA SEGUNDA PANTALLA--------------------------
        _ChBx_1_2_3 = (CheckBox) findViewById(R.id.ChBx_1_2_3);
        _ChBx_2_2_3 = (CheckBox) findViewById(R.id.ChBx_2_2_3);
        _ChBx_3_2_3 = (CheckBox) findViewById(R.id.ChBx_3_2_3);
        _ChBx_4_2_3 = (CheckBox) findViewById(R.id.ChBx_4_2_3);
        _btnSiguientePregunta2_3 = (Button) findViewById(R.id.btnSiguientePregunta2_3);
        _preguntaDos_seccion3 = (LinearLayout) findViewById(R.id.preguntaDos_seccion3);
        /*----------------PANTALLA 2 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 2 - SECCION 3*/
        _btnSiguientePregunta2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaDos_seccion3.setVisibility(View.GONE);
                _preguntaTres_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_3.setSelected(false);
                _ChBx_3_2_3.setSelected(false);
                _ChBx_4_2_3.setSelected(false);

                _ChBx_2_2_3.setChecked(false);
                _ChBx_3_2_3.setChecked(false);
                _ChBx_4_2_3.setChecked(false);
            }
        });
        _ChBx_2_2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_2_3.setSelected(false);
                _ChBx_3_2_3.setSelected(false);
                _ChBx_4_2_3.setSelected(false);

                _ChBx_1_2_3.setChecked(false);
                _ChBx_3_2_3.setChecked(false);
                _ChBx_4_2_3.setChecked(false);
            }
        });
        _ChBx_3_2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_3.setSelected(false);
                _ChBx_1_2_3.setSelected(false);
                _ChBx_4_2_3.setSelected(false);

                _ChBx_1_2_3.setChecked(false);
                _ChBx_2_2_3.setChecked(false);
                _ChBx_4_2_3.setChecked(false);
            }
        });
        _ChBx_4_2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_3.setSelected(false);
                _ChBx_3_2_3.setSelected(false);
                _ChBx_1_2_3.setSelected(false);

                _ChBx_1_2_3.setChecked(false);
                _ChBx_2_2_3.setChecked(false);
                _ChBx_3_2_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------
        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA TERCERA PANTALLA--------------------------
        _ChBx_1_3_3 = (CheckBox) findViewById(R.id.ChBx_1_3_3);
        _ChBx_2_3_3 = (CheckBox) findViewById(R.id.ChBx_2_3_3);
        _ChBx_3_3_3 = (CheckBox) findViewById(R.id.ChBx_3_3_3);
        _btnSiguientePregunta3_3 = (Button) findViewById(R.id.btnSiguientePregunta3_3);
        _preguntaTres_seccion3 = (LinearLayout) findViewById(R.id.preguntaTres_seccion3);
        /*----------------PANTALLA 3 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 3 - SECCION 3*/
        _btnSiguientePregunta3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaTres_seccion3.setVisibility(View.GONE);
                _preguntaCuatro_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_3_3.setSelected(false);
                _ChBx_3_3_3.setSelected(false);

                _ChBx_2_3_3.setChecked(false);
                _ChBx_3_3_3.setChecked(false);
            }
        });
        _ChBx_2_3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_3_3.setSelected(false);
                _ChBx_3_3_3.setSelected(false);

                _ChBx_1_3_3.setChecked(false);
                _ChBx_3_3_3.setChecked(false);
            }
        });
        _ChBx_3_3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_3_3.setSelected(false);
                _ChBx_1_3_3.setSelected(false);

                _ChBx_1_3_3.setChecked(false);
                _ChBx_2_3_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------

        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA CUARTA PANTALLA--------------------------
        _ChBx_1_4_3 = (CheckBox) findViewById(R.id.ChBx_1_4_3);
        _ChBx_2_4_3 = (CheckBox) findViewById(R.id.ChBx_2_4_3);
        _ChBx_3_4_3 = (CheckBox) findViewById(R.id.ChBx_3_4_3);
        _ChBx_4_4_3 = (CheckBox) findViewById(R.id.ChBx_4_4_3);
        _btnSiguientePregunta4_3 = (Button) findViewById(R.id.btnSiguientePregunta4_3);
        _preguntaCuatro_seccion3 = (LinearLayout) findViewById(R.id.preguntaCuatro_seccion3);
        /*----------------PANTALLA 4 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 4 - SECCION 3*/
        _btnSiguientePregunta4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaCuatro_seccion3.setVisibility(View.GONE);
                _preguntaCinco_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_3.setSelected(false);
                _ChBx_3_4_3.setSelected(false);
                _ChBx_4_4_3.setSelected(false);

                _ChBx_2_4_3.setChecked(false);
                _ChBx_3_4_3.setChecked(false);
                _ChBx_4_4_3.setChecked(false);
            }
        });
        _ChBx_2_4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_4_3.setSelected(false);
                _ChBx_3_4_3.setSelected(false);
                _ChBx_4_4_3.setSelected(false);

                _ChBx_1_4_3.setChecked(false);
                _ChBx_3_4_3.setChecked(false);
                _ChBx_4_4_3.setChecked(false);
            }
        });
        _ChBx_3_4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_3.setSelected(false);
                _ChBx_1_4_3.setSelected(false);
                _ChBx_4_4_3.setSelected(false);

                _ChBx_1_4_3.setChecked(false);
                _ChBx_2_4_3.setChecked(false);
                _ChBx_4_4_3.setChecked(false);
            }
        });
        _ChBx_4_4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_3.setSelected(false);
                _ChBx_3_4_3.setSelected(false);
                _ChBx_1_4_3.setSelected(false);

                _ChBx_1_4_3.setChecked(false);
                _ChBx_2_4_3.setChecked(false);
                _ChBx_3_4_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------
        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA QUINTA PANTALLA--------------------------
        _ChBx_1_5_3 = (CheckBox) findViewById(R.id.ChBx_1_5_3);
        _ChBx_2_5_3 = (CheckBox) findViewById(R.id.ChBx_2_5_3);
        _ChBx_3_5_3 = (CheckBox) findViewById(R.id.ChBx_3_5_3);
        _ChBx_4_5_3 = (CheckBox) findViewById(R.id.ChBx_4_5_3);
        _ChBx_5_5_3 = (CheckBox) findViewById(R.id.ChBx_5_5_3);
        _btnSiguientePregunta5_3 = (Button) findViewById(R.id.btnSiguientePregunta5_3);
        _preguntaCinco_seccion3 = (LinearLayout) findViewById(R.id.preguntaCinco_seccion3);
        /*----------------PANTALLA 5 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 5 - SECCION 3*/
        _btnSiguientePregunta5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaCinco_seccion3.setVisibility(View.GONE);
                _preguntaSeis_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_5_3.setSelected(false);
                _ChBx_3_5_3.setSelected(false);
                _ChBx_4_5_3.setSelected(false);
                _ChBx_5_5_3.setSelected(false);

                _ChBx_2_5_3.setChecked(false);
                _ChBx_3_5_3.setChecked(false);
                _ChBx_4_5_3.setChecked(false);
                _ChBx_5_5_3.setChecked(false);
            }
        });
        _ChBx_2_5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_5_3.setSelected(false);
                _ChBx_3_5_3.setSelected(false);
                _ChBx_4_5_3.setSelected(false);
                _ChBx_5_5_3.setSelected(false);

                _ChBx_1_5_3.setChecked(false);
                _ChBx_3_5_3.setChecked(false);
                _ChBx_4_5_3.setChecked(false);
                _ChBx_5_5_3.setChecked(false);
            }
        });
        _ChBx_3_5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_5_3.setSelected(false);
                _ChBx_1_5_3.setSelected(false);
                _ChBx_4_5_3.setSelected(false);
                _ChBx_5_5_3.setSelected(false);

                _ChBx_1_5_3.setChecked(false);
                _ChBx_2_5_3.setChecked(false);
                _ChBx_4_5_3.setChecked(false);
                _ChBx_5_5_3.setChecked(false);
            }
        });
        _ChBx_4_5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_5_3.setSelected(false);
                _ChBx_3_5_3.setSelected(false);
                _ChBx_1_5_3.setSelected(false);
                _ChBx_5_5_3.setSelected(false);

                _ChBx_1_5_3.setChecked(false);
                _ChBx_2_5_3.setChecked(false);
                _ChBx_3_5_3.setChecked(false);
                _ChBx_5_5_3.setChecked(false);
            }
        });
        _ChBx_5_5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_5_3.setSelected(false);
                _ChBx_3_5_3.setSelected(false);
                _ChBx_4_5_3.setSelected(false);
                _ChBx_1_5_3.setSelected(false);

                _ChBx_1_5_3.setChecked(false);
                _ChBx_2_5_3.setChecked(false);
                _ChBx_3_5_3.setChecked(false);
                _ChBx_4_5_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------


        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA SEXTA PANTALLA--------------------------
        _ChBx_1_6_3 = (CheckBox) findViewById(R.id.ChBx_1_6_3);
        _ChBx_2_6_3 = (CheckBox) findViewById(R.id.ChBx_2_6_3);
        _ChBx_3_6_3 = (CheckBox) findViewById(R.id.ChBx_3_6_3);
        _ChBx_4_6_3 = (CheckBox) findViewById(R.id.ChBx_4_6_3);
        _btnSiguientePregunta6_3 = (Button) findViewById(R.id.btnSiguientePregunta6_3);
        _preguntaSeis_seccion3 = (LinearLayout) findViewById(R.id.preguntaSeis_seccion3);
        /*----------------PANTALLA 6 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 6 - SECCION 3*/
        _btnSiguientePregunta6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaSeis_seccion3.setVisibility(View.GONE);
                _preguntaSiete_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_3.setSelected(false);
                _ChBx_3_6_3.setSelected(false);
                _ChBx_4_6_3.setSelected(false);

                _ChBx_2_6_3.setChecked(false);
                _ChBx_3_6_3.setChecked(false);
                _ChBx_4_6_3.setChecked(false);
            }
        });
        _ChBx_2_6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_6_3.setSelected(false);
                _ChBx_3_6_3.setSelected(false);
                _ChBx_4_6_3.setSelected(false);

                _ChBx_1_6_3.setChecked(false);
                _ChBx_3_6_3.setChecked(false);
                _ChBx_4_6_3.setChecked(false);
            }
        });
        _ChBx_3_6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_3.setSelected(false);
                _ChBx_1_6_3.setSelected(false);
                _ChBx_4_6_3.setSelected(false);

                _ChBx_1_6_3.setChecked(false);
                _ChBx_2_6_3.setChecked(false);
                _ChBx_4_6_3.setChecked(false);
            }
        });
        _ChBx_4_6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_3.setSelected(false);
                _ChBx_3_6_3.setSelected(false);
                _ChBx_1_6_3.setSelected(false);

                _ChBx_1_6_3.setChecked(false);
                _ChBx_2_6_3.setChecked(false);
                _ChBx_3_6_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------

        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA SEPTIMA PANTALLA--------------------------
        _ChBx_1_7_3 = (CheckBox) findViewById(R.id.ChBx_1_7_3);
        _ChBx_2_7_3 = (CheckBox) findViewById(R.id.ChBx_2_7_3);
        _ChBx_3_7_3 = (CheckBox) findViewById(R.id.ChBx_3_7_3);
        _ChBx_4_7_3 = (CheckBox) findViewById(R.id.ChBx_4_7_3);
        _btnSiguientePregunta7_3 = (Button) findViewById(R.id.btnSiguientePregunta7_3);
        _preguntaSiete_seccion3 = (LinearLayout) findViewById(R.id.preguntaSiete_seccion3);
        /*----------------PANTALLA 7 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 7 - SECCION 3*/
        _btnSiguientePregunta7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaSiete_seccion3.setVisibility(View.GONE);
                _preguntaOcho_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_3.setSelected(false);
                _ChBx_3_7_3.setSelected(false);
                _ChBx_4_7_3.setSelected(false);

                _ChBx_2_7_3.setChecked(false);
                _ChBx_3_7_3.setChecked(false);
                _ChBx_4_7_3.setChecked(false);
            }
        });
        _ChBx_2_7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_7_3.setSelected(false);
                _ChBx_3_7_3.setSelected(false);
                _ChBx_4_7_3.setSelected(false);

                _ChBx_1_7_3.setChecked(false);
                _ChBx_3_7_3.setChecked(false);
                _ChBx_4_7_3.setChecked(false);
            }
        });
        _ChBx_3_7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_3.setSelected(false);
                _ChBx_1_7_3.setSelected(false);
                _ChBx_4_7_3.setSelected(false);

                _ChBx_1_7_3.setChecked(false);
                _ChBx_2_7_3.setChecked(false);
                _ChBx_4_7_3.setChecked(false);
            }
        });
        _ChBx_4_7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_3.setSelected(false);
                _ChBx_3_7_3.setSelected(false);
                _ChBx_1_7_3.setSelected(false);

                _ChBx_1_7_3.setChecked(false);
                _ChBx_2_7_3.setChecked(false);
                _ChBx_3_7_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------

        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA OCTAVA PANTALLA--------------------------
        _ChBx_1_8_3 = (CheckBox) findViewById(R.id.ChBx_1_8_3);
        _ChBx_2_8_3 = (CheckBox) findViewById(R.id.ChBx_2_8_3);
        _ChBx_3_8_3 = (CheckBox) findViewById(R.id.ChBx_3_8_3);
        _ChBx_4_8_3 = (CheckBox) findViewById(R.id.ChBx_4_8_3);
        _btnSiguientePregunta8_3 = (Button) findViewById(R.id.btnSiguientePregunta8_3);
        _preguntaOcho_seccion3 = (LinearLayout) findViewById(R.id.preguntaOcho_seccion3);
        /*----------------PANTALLA 8 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 8 - SECCION 3*/
        _btnSiguientePregunta8_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaOcho_seccion3.setVisibility(View.GONE);
                _preguntaNueve_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_8_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_3.setSelected(false);
                _ChBx_3_8_3.setSelected(false);
                _ChBx_4_8_3.setSelected(false);

                _ChBx_2_8_3.setChecked(false);
                _ChBx_3_8_3.setChecked(false);
                _ChBx_4_8_3.setChecked(false);
            }
        });
        _ChBx_2_8_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_8_3.setSelected(false);
                _ChBx_3_8_3.setSelected(false);
                _ChBx_4_8_3.setSelected(false);

                _ChBx_1_8_3.setChecked(false);
                _ChBx_3_8_3.setChecked(false);
                _ChBx_4_8_3.setChecked(false);
            }
        });
        _ChBx_3_8_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_3.setSelected(false);
                _ChBx_1_8_3.setSelected(false);
                _ChBx_4_8_3.setSelected(false);

                _ChBx_1_8_3.setChecked(false);
                _ChBx_2_8_3.setChecked(false);
                _ChBx_4_8_3.setChecked(false);
            }
        });
        _ChBx_4_8_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_3.setSelected(false);
                _ChBx_3_8_3.setSelected(false);
                _ChBx_1_8_3.setSelected(false);

                _ChBx_1_8_3.setChecked(false);
                _ChBx_2_8_3.setChecked(false);
                _ChBx_3_8_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------
        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA NOVENA PANTALLA--------------------------
        _ChBx_1_9_3 = (CheckBox) findViewById(R.id.ChBx_1_9_3);
        _ChBx_2_9_3 = (CheckBox) findViewById(R.id.ChBx_2_9_3);
        _ChBx_3_9_3 = (CheckBox) findViewById(R.id.ChBx_3_9_3);
        _ChBx_4_9_3 = (CheckBox) findViewById(R.id.ChBx_4_9_3);
        _ChBx_5_9_3 = (CheckBox) findViewById(R.id.ChBx_5_9_3);
        _btnSiguientePregunta9_3 = (Button) findViewById(R.id.btnSiguientePregunta9_3);
        _preguntaNueve_seccion3 = (LinearLayout) findViewById(R.id.preguntaNueve_seccion3);
        /*----------------PANTALLA 9 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 9 - SECCION 3*/
        _btnSiguientePregunta9_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaNueve_seccion3.setVisibility(View.GONE);
                _preguntaDiez_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_9_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_3.setSelected(false);
                _ChBx_3_9_3.setSelected(false);
                _ChBx_4_9_3.setSelected(false);
                _ChBx_5_9_3.setSelected(false);

                _ChBx_2_9_3.setChecked(false);
                _ChBx_3_9_3.setChecked(false);
                _ChBx_4_9_3.setChecked(false);
                _ChBx_5_9_3.setChecked(false);
            }
        });
        _ChBx_2_9_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_9_3.setSelected(false);
                _ChBx_3_9_3.setSelected(false);
                _ChBx_4_9_3.setSelected(false);
                _ChBx_5_9_3.setSelected(false);

                _ChBx_1_9_3.setChecked(false);
                _ChBx_3_9_3.setChecked(false);
                _ChBx_4_9_3.setChecked(false);
                _ChBx_5_9_3.setChecked(false);
            }
        });
        _ChBx_3_9_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_3.setSelected(false);
                _ChBx_1_9_3.setSelected(false);
                _ChBx_4_9_3.setSelected(false);
                _ChBx_5_9_3.setSelected(false);

                _ChBx_1_9_3.setChecked(false);
                _ChBx_2_9_3.setChecked(false);
                _ChBx_4_9_3.setChecked(false);
                _ChBx_5_9_3.setChecked(false);
            }
        });
        _ChBx_4_9_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_3.setSelected(false);
                _ChBx_3_9_3.setSelected(false);
                _ChBx_1_9_3.setSelected(false);
                _ChBx_5_9_3.setSelected(false);

                _ChBx_1_9_3.setChecked(false);
                _ChBx_2_9_3.setChecked(false);
                _ChBx_3_9_3.setChecked(false);
                _ChBx_5_9_3.setChecked(false);
            }
        });
        _ChBx_5_9_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_3.setSelected(false);
                _ChBx_3_9_3.setSelected(false);
                _ChBx_4_9_3.setSelected(false);
                _ChBx_1_9_3.setSelected(false);

                _ChBx_1_9_3.setChecked(false);
                _ChBx_2_9_3.setChecked(false);
                _ChBx_3_9_3.setChecked(false);
                _ChBx_4_9_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------
        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA DECIMA PANTALLA--------------------------
        _ChBx_1_10_3 = (CheckBox) findViewById(R.id.ChBx_1_10_3);
        _ChBx_2_10_3 = (CheckBox) findViewById(R.id.ChBx_2_10_3);
        _ChBx_3_10_3 = (CheckBox) findViewById(R.id.ChBx_3_10_3);
        _ChBx_4_10_3 = (CheckBox) findViewById(R.id.ChBx_4_10_3);
        _btnSiguientePregunta10_3 = (Button) findViewById(R.id.btnSiguientePregunta10_3);
        _preguntaDiez_seccion3 = (LinearLayout) findViewById(R.id.preguntaDiez_seccion3);
        /*----------------PANTALLA 10 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 10 - SECCION 3*/
        _btnSiguientePregunta10_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaDiez_seccion3.setVisibility(View.GONE);
                _preguntaOnce_seccion3.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_10_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_10_3.setSelected(false);
                _ChBx_3_10_3.setSelected(false);
                _ChBx_4_10_3.setSelected(false);

                _ChBx_2_10_3.setChecked(false);
                _ChBx_3_10_3.setChecked(false);
                _ChBx_4_10_3.setChecked(false);
            }
        });
        _ChBx_2_10_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_10_3.setSelected(false);
                _ChBx_3_10_3.setSelected(false);
                _ChBx_4_10_3.setSelected(false);

                _ChBx_1_10_3.setChecked(false);
                _ChBx_3_10_3.setChecked(false);
                _ChBx_4_10_3.setChecked(false);
            }
        });
        _ChBx_3_10_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_10_3.setSelected(false);
                _ChBx_1_10_3.setSelected(false);
                _ChBx_4_10_3.setSelected(false);

                _ChBx_1_10_3.setChecked(false);
                _ChBx_2_10_3.setChecked(false);
                _ChBx_4_10_3.setChecked(false);
            }
        });
        _ChBx_4_10_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_10_3.setSelected(false);
                _ChBx_3_10_3.setSelected(false);
                _ChBx_1_10_3.setSelected(false);

                _ChBx_1_10_3.setChecked(false);
                _ChBx_2_10_3.setChecked(false);
                _ChBx_3_10_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------
        //**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA ONCEAVA PANTALLA--------------------------
        _ChBx_1_11_3 = (CheckBox) findViewById(R.id.ChBx_1_11_3);
        _ChBx_2_11_3 = (CheckBox) findViewById(R.id.ChBx_2_11_3);
        _ChBx_3_11_3 = (CheckBox) findViewById(R.id.ChBx_3_11_3);
        _btnSiguientePregunta11_3 = (Button) findViewById(R.id.btnSiguientePregunta11_3);
        _preguntaOnce_seccion3 = (LinearLayout) findViewById(R.id.preguntaOnce_seccion3);
        /*----------------PANTALLA 11 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 11 - SECCION 3*/
        _btnSiguientePregunta11_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registro_cuestionario_seccion3.this,registro_cuestionario_seccion4.class));
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_11_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_11_3.setSelected(false);
                _ChBx_3_11_3.setSelected(false);

                _ChBx_2_11_3.setChecked(false);
                _ChBx_3_11_3.setChecked(false);
            }
        });
        _ChBx_2_11_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_11_3.setSelected(false);
                _ChBx_3_11_3.setSelected(false);

                _ChBx_1_11_3.setChecked(false);
                _ChBx_3_11_3.setChecked(false);
            }
        });
        _ChBx_3_11_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_11_3.setSelected(false);
                _ChBx_1_11_3.setSelected(false);

                _ChBx_1_11_3.setChecked(false);
                _ChBx_2_11_3.setChecked(false);
            }
        });
        //------------------------END---------------------------------

    }
}
