package com.example.desarrollo.Precentacion.Encuestas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.desarrollo.R;

public class registro_cuestionario_seccion4 extends AppCompatActivity {

    private CheckBox _ChBx_1_1_4, _ChBx_2_1_4, _ChBx_3_1_4, _ChBx_4_1_4;
    private Button _btnSiguientePregunta1_4;
    private LinearLayout _preguntaUno_seccion4;

    private CheckBox _ChBx_1_2_4, _ChBx_2_2_4, _ChBx_3_2_4, _ChBx_4_2_4;
    private Button _btnSiguientePregunta2_4;
    private LinearLayout _preguntaDos_seccion4;

    private CheckBox _ChBx_1_3_4, _ChBx_2_3_4, _ChBx_3_3_4, _ChBx_4_3_4;
    private Button _btnSiguientePregunta3_4;
    private LinearLayout _preguntaTres_seccion4;

    private CheckBox _ChBx_1_4_4, _ChBx_2_4_4, _ChBx_3_4_4, _ChBx_4_4_4, _ChBx_5_4_4;
    private Button _btnSiguientePregunta4_4;
    private LinearLayout _preguntaCuatro_seccion4;

    private CheckBox _ChBx_1_5_4, _ChBx_2_5_4, _ChBx_3_5_4, _ChBx_4_5_4;
    private Button _btnSiguientePregunta5_4;
    private LinearLayout _preguntaCinco_seccion4;

    private CheckBox _ChBx_1_6_4, _ChBx_2_6_4, _ChBx_3_6_4, _ChBx_4_6_4;
    private Button _btnSiguientePregunta6_4;
    private LinearLayout _preguntaSeis_seccion4;

    private CheckBox _ChBx_1_7_4, _ChBx_2_7_4, _ChBx_3_7_4, _ChBx_4_7_4;
    private Button _btnSiguientePregunta7_4;
    private LinearLayout _preguntaSiete_seccion4;

    private CheckBox _ChBx_1_8_4, _ChBx_2_8_4, _ChBx_3_8_4, _ChBx_4_8_4;
    private Button _btnSiguientePregunta8_4;
    private LinearLayout _preguntaOcho_seccion4;

    private CheckBox _ChBx_1_9_4, _ChBx_2_9_4, _ChBx_3_9_4;
    private Button _btnSiguientePregunta9_4;
    private LinearLayout _preguntaNueve_seccion4;

    private CheckBox _ChBx_1_10_4, _ChBx_2_10_4, _ChBx_3_10_4;
    private Button _btnSiguientePregunta10_4;
    private LinearLayout _preguntaDiez_seccion4;

    private CheckBox _ChBx_1_11_4, _ChBx_2_11_4, _ChBx_3_11_4;
    private Button _btnSiguientePregunta11_4;
    private LinearLayout _preguntaOnce_seccion4;

    private CheckBox _ChBx_1_12_4, _ChBx_2_12_4, _ChBx_3_12_4;
    private Button _btnSiguientePregunta12_4;
    private LinearLayout _preguntaDose_seccion4;

    private Button _btnSi, _btnNo, _btnNoEstoySeguro13_4, _btnSiguientePregunta13_4;
    private TextView _txtIncisoRespuestaPregunta13_4;
    private LinearLayout _preguntaTrece_seccion4;

    private CheckBox _ChBx_1_14_4, _ChBx_2_14_4, _ChBx_3_14_4, _ChBx_4_14_4, _ChBx_5_14_4;
    private Button _btnSiguientePregunta14_4;
    private LinearLayout _preguntaCatorse_seccion4;

    private CheckBox _ChBx_1_15_4, _ChBx_2_15_4, _ChBx_3_15_4, _ChBx_4_15_4, _ChBx_5_15_4;
    private Button _btnSiguientePregunta15_4;
    private LinearLayout _preguntaQuinse_seccion4;

    private boolean verifica = false;
    private int contador = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuestionario_seccion4);


        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA PRIMERA PANTALLA--------------------------
        _ChBx_1_1_4 = (CheckBox) findViewById(R.id.ChBx_1_1_4);
        _ChBx_2_1_4 = (CheckBox) findViewById(R.id.ChBx_2_1_4);
        _ChBx_3_1_4 = (CheckBox) findViewById(R.id.ChBx_3_1_4);
        _ChBx_4_1_4 = (CheckBox) findViewById(R.id.ChBx_4_1_4);
        _btnSiguientePregunta1_4 = (Button) findViewById(R.id.btnSiguientePregunta1_4);
        _preguntaUno_seccion4 = (LinearLayout) findViewById(R.id.preguntaUno_seccion4);
        //                /*----------------PANTALLA 1 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 1 - SECCION 4*/
        _btnSiguientePregunta1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaUno_seccion4.setVisibility(View.GONE);
                _preguntaDos_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_1_4.setSelected(false);
                _ChBx_3_1_4.setSelected(false);
                _ChBx_4_1_4.setSelected(false);

                _ChBx_2_1_4.setChecked(false);
                _ChBx_3_1_4.setChecked(false);
                _ChBx_4_1_4.setChecked(false);
            }
        });
        _ChBx_2_1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_1_4.setSelected(false);
                _ChBx_3_1_4.setSelected(false);
                _ChBx_4_1_4.setSelected(false);

                _ChBx_1_1_4.setChecked(false);
                _ChBx_3_1_4.setChecked(false);
                _ChBx_4_1_4.setChecked(false);
            }
        });
        _ChBx_3_1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_1_4.setSelected(false);
                _ChBx_1_1_4.setSelected(false);
                _ChBx_4_1_4.setSelected(false);

                _ChBx_1_1_4.setChecked(false);
                _ChBx_2_1_4.setChecked(false);
                _ChBx_4_1_4.setChecked(false);
            }
        });
        _ChBx_4_1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_1_4.setSelected(false);
                _ChBx_3_1_4.setSelected(false);
                _ChBx_1_1_4.setSelected(false);

                _ChBx_1_1_4.setChecked(false);
                _ChBx_2_1_4.setChecked(false);
                _ChBx_3_1_4.setChecked(false);
            }
        });



        //        //------------------------END---------------------------------

        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA SEGUNDA PANTALLA--------------------------
        _ChBx_1_2_4 = (CheckBox) findViewById(R.id.ChBx_1_2_4);
        _ChBx_2_2_4 = (CheckBox) findViewById(R.id.ChBx_2_2_4);
        _ChBx_3_2_4 = (CheckBox) findViewById(R.id.ChBx_3_2_4);
        _ChBx_4_2_4 = (CheckBox) findViewById(R.id.ChBx_4_2_4);
        _btnSiguientePregunta2_4 = (Button) findViewById(R.id.btnSiguientePregunta2_4);
        _preguntaDos_seccion4 = (LinearLayout) findViewById(R.id.preguntaDos_seccion4);
        //                /*----------------PANTALLA 2 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 2 - SECCION 4*/
        _btnSiguientePregunta2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaDos_seccion4.setVisibility(View.GONE);
                _preguntaTres_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_4.setSelected(false);
                _ChBx_3_2_4.setSelected(false);
                _ChBx_4_2_4.setSelected(false);

                _ChBx_2_2_4.setChecked(false);
                _ChBx_3_2_4.setChecked(false);
                _ChBx_4_2_4.setChecked(false);
            }
        });
        _ChBx_2_2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_2_4.setSelected(false);
                _ChBx_3_2_4.setSelected(false);
                _ChBx_4_2_4.setSelected(false);

                _ChBx_1_2_4.setChecked(false);
                _ChBx_3_2_4.setChecked(false);
                _ChBx_4_2_4.setChecked(false);
            }
        });
        _ChBx_3_2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_4.setSelected(false);
                _ChBx_1_2_4.setSelected(false);
                _ChBx_4_2_4.setSelected(false);

                _ChBx_1_2_4.setChecked(false);
                _ChBx_2_2_4.setChecked(false);
                _ChBx_4_2_4.setChecked(false);
            }
        });
        _ChBx_4_2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_4.setSelected(false);
                _ChBx_3_2_4.setSelected(false);
                _ChBx_1_2_4.setSelected(false);

                _ChBx_1_2_4.setChecked(false);
                _ChBx_2_2_4.setChecked(false);
                _ChBx_3_2_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA TERCERA PANTALLA--------------------------
        _ChBx_1_3_4 = (CheckBox) findViewById(R.id.ChBx_1_3_4);
        _ChBx_2_3_4 = (CheckBox) findViewById(R.id.ChBx_2_3_4);
        _ChBx_3_3_4 = (CheckBox) findViewById(R.id.ChBx_3_3_4);
        _ChBx_4_3_4 = (CheckBox) findViewById(R.id.ChBx_4_3_4);
        _btnSiguientePregunta3_4 = (Button) findViewById(R.id.btnSiguientePregunta3_4);
        _preguntaTres_seccion4 = (LinearLayout) findViewById(R.id.preguntaTres_seccion4);
        //                /*----------------PANTALLA 3 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 3 - SECCION 4*/
        _btnSiguientePregunta3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaTres_seccion4.setVisibility(View.GONE);
                _preguntaCuatro_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_3_4.setSelected(false);
                _ChBx_3_3_4.setSelected(false);
                _ChBx_4_3_4.setSelected(false);

                _ChBx_2_3_4.setChecked(false);
                _ChBx_3_3_4.setChecked(false);
                _ChBx_4_3_4.setChecked(false);
            }
        });
        _ChBx_2_3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_3_4.setSelected(false);
                _ChBx_3_3_4.setSelected(false);
                _ChBx_4_3_4.setSelected(false);

                _ChBx_1_3_4.setChecked(false);
                _ChBx_3_3_4.setChecked(false);
                _ChBx_4_3_4.setChecked(false);
            }
        });
        _ChBx_3_3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_3_4.setSelected(false);
                _ChBx_1_3_4.setSelected(false);
                _ChBx_4_3_4.setSelected(false);

                _ChBx_1_3_4.setChecked(false);
                _ChBx_2_3_4.setChecked(false);
                _ChBx_4_3_4.setChecked(false);
            }
        });
        _ChBx_4_3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_3_4.setSelected(false);
                _ChBx_3_3_4.setSelected(false);
                _ChBx_1_3_4.setSelected(false);

                _ChBx_1_3_4.setChecked(false);
                _ChBx_2_3_4.setChecked(false);
                _ChBx_3_3_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA CUARTA PANTALLA--------------------------
        _ChBx_1_4_4 = (CheckBox) findViewById(R.id.ChBx_1_4_4);
        _ChBx_2_4_4 = (CheckBox) findViewById(R.id.ChBx_2_4_4);
        _ChBx_3_4_4 = (CheckBox) findViewById(R.id.ChBx_3_4_4);
        _ChBx_4_4_4 = (CheckBox) findViewById(R.id.ChBx_4_4_4);
        _ChBx_5_4_4 = (CheckBox) findViewById(R.id.ChBx_5_4_4);
        _btnSiguientePregunta4_4 = (Button) findViewById(R.id.btnSiguientePregunta4_4);
        _preguntaCuatro_seccion4 = (LinearLayout) findViewById(R.id.preguntaCuatro_seccion4);
        //                /*----------------PANTALLA 4 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 4 - SECCION 4*/
        _btnSiguientePregunta4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaCuatro_seccion4.setVisibility(View.GONE);
                _preguntaCinco_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_4.setSelected(false);
                _ChBx_3_4_4.setSelected(false);
                _ChBx_4_4_4.setSelected(false);
                _ChBx_5_4_4.setSelected(false);

                _ChBx_2_4_4.setChecked(false);
                _ChBx_3_4_4.setChecked(false);
                _ChBx_4_4_4.setChecked(false);
                _ChBx_5_4_4.setChecked(false);
            }
        });
        _ChBx_2_4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_4_4.setSelected(false);
                _ChBx_3_4_4.setSelected(false);
                _ChBx_4_4_4.setSelected(false);
                _ChBx_5_4_4.setSelected(false);

                _ChBx_1_4_4.setChecked(false);
                _ChBx_3_4_4.setChecked(false);
                _ChBx_4_4_4.setChecked(false);
                _ChBx_5_4_4.setChecked(false);
            }
        });
        _ChBx_3_4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_4.setSelected(false);
                _ChBx_1_4_4.setSelected(false);
                _ChBx_4_4_4.setSelected(false);
                _ChBx_5_4_4.setSelected(false);

                _ChBx_1_4_4.setChecked(false);
                _ChBx_2_4_4.setChecked(false);
                _ChBx_4_4_4.setChecked(false);
                _ChBx_5_4_4.setChecked(false);
            }
        });
        _ChBx_4_4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_4.setSelected(false);
                _ChBx_3_4_4.setSelected(false);
                _ChBx_1_4_4.setSelected(false);
                _ChBx_5_4_4.setSelected(false);

                _ChBx_1_4_4.setChecked(false);
                _ChBx_2_4_4.setChecked(false);
                _ChBx_3_4_4.setChecked(false);
                _ChBx_5_4_4.setChecked(false);
            }
        });
        _ChBx_5_4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_4.setSelected(false);
                _ChBx_3_4_4.setSelected(false);
                _ChBx_4_4_4.setSelected(false);
                _ChBx_1_4_4.setSelected(false);

                _ChBx_1_4_4.setChecked(false);
                _ChBx_2_4_4.setChecked(false);
                _ChBx_3_4_4.setChecked(false);
                _ChBx_4_4_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA QUINTA PANTALLA--------------------------
        _ChBx_1_5_4 = (CheckBox) findViewById(R.id.ChBx_1_5_4);
        _ChBx_2_5_4 = (CheckBox) findViewById(R.id.ChBx_2_5_4);
        _ChBx_3_5_4 = (CheckBox) findViewById(R.id.ChBx_3_5_4);
        _ChBx_4_5_4 = (CheckBox) findViewById(R.id.ChBx_4_5_4);
        _btnSiguientePregunta5_4 = (Button) findViewById(R.id.btnSiguientePregunta5_4);
        _preguntaCinco_seccion4 = (LinearLayout) findViewById(R.id.preguntaQuinta_seccion4);
        //                /*----------------PANTALLA 5 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 5 - SECCION 4*/
        _btnSiguientePregunta5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaCinco_seccion4.setVisibility(View.GONE);
                _preguntaSeis_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_5_4.setSelected(false);
                _ChBx_3_5_4.setSelected(false);
                _ChBx_4_5_4.setSelected(false);

                _ChBx_2_5_4.setChecked(false);
                _ChBx_3_5_4.setChecked(false);
                _ChBx_4_5_4.setChecked(false);
            }
        });
        _ChBx_2_5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_5_4.setSelected(false);
                _ChBx_3_5_4.setSelected(false);
                _ChBx_4_5_4.setSelected(false);

                _ChBx_1_5_4.setChecked(false);
                _ChBx_3_5_4.setChecked(false);
                _ChBx_4_5_4.setChecked(false);
            }
        });
        _ChBx_3_5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_5_4.setSelected(false);
                _ChBx_1_5_4.setSelected(false);
                _ChBx_4_5_4.setSelected(false);

                _ChBx_1_5_4.setChecked(false);
                _ChBx_2_5_4.setChecked(false);
                _ChBx_4_5_4.setChecked(false);
            }
        });
        _ChBx_4_5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_5_4.setSelected(false);
                _ChBx_3_5_4.setSelected(false);
                _ChBx_1_5_4.setSelected(false);

                _ChBx_1_5_4.setChecked(false);
                _ChBx_2_5_4.setChecked(false);
                _ChBx_3_5_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA SEXTA PANTALLA--------------------------
        _ChBx_1_6_4 = (CheckBox) findViewById(R.id.ChBx_1_6_4);
        _ChBx_2_6_4 = (CheckBox) findViewById(R.id.ChBx_2_6_4);
        _ChBx_3_6_4 = (CheckBox) findViewById(R.id.ChBx_3_6_4);
        _ChBx_4_6_4 = (CheckBox) findViewById(R.id.ChBx_4_6_4);
        _btnSiguientePregunta6_4 = (Button) findViewById(R.id.btnSiguientePregunta6_4);
        _preguntaSeis_seccion4 = (LinearLayout) findViewById(R.id.preguntaSeis_seccion4);
        //                /*----------------PANTALLA 6 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 6 - SECCION 4*/
        _btnSiguientePregunta6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaSeis_seccion4.setVisibility(View.GONE);
                _preguntaSiete_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_4.setSelected(false);
                _ChBx_3_6_4.setSelected(false);
                _ChBx_4_6_4.setSelected(false);

                _ChBx_2_6_4.setChecked(false);
                _ChBx_3_6_4.setChecked(false);
                _ChBx_4_6_4.setChecked(false);
            }
        });
        _ChBx_2_6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_6_4.setSelected(false);
                _ChBx_3_6_4.setSelected(false);
                _ChBx_4_6_4.setSelected(false);

                _ChBx_1_6_4.setChecked(false);
                _ChBx_3_6_4.setChecked(false);
                _ChBx_4_6_4.setChecked(false);
            }
        });
        _ChBx_3_6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_4.setSelected(false);
                _ChBx_1_6_4.setSelected(false);
                _ChBx_4_6_4.setSelected(false);

                _ChBx_1_6_4.setChecked(false);
                _ChBx_2_6_4.setChecked(false);
                _ChBx_4_6_4.setChecked(false);
            }
        });
        _ChBx_4_6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_4.setSelected(false);
                _ChBx_3_6_4.setSelected(false);
                _ChBx_1_6_4.setSelected(false);

                _ChBx_1_6_4.setChecked(false);
                _ChBx_2_6_4.setChecked(false);
                _ChBx_3_6_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA SEPTIMA PANTALLA--------------------------
        _ChBx_1_7_4 = (CheckBox) findViewById(R.id.ChBx_1_7_4);
        _ChBx_2_7_4 = (CheckBox) findViewById(R.id.ChBx_2_7_4);
        _ChBx_3_7_4 = (CheckBox) findViewById(R.id.ChBx_3_7_4);
        _ChBx_4_7_4 = (CheckBox) findViewById(R.id.ChBx_4_7_4);
        _btnSiguientePregunta7_4 = (Button) findViewById(R.id.btnSiguientePregunta7_4);
        _preguntaSiete_seccion4 = (LinearLayout) findViewById(R.id.preguntaSiete_seccion4);
        //                /*----------------PANTALLA 7 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 7 - SECCION 4*/
        _btnSiguientePregunta7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaSiete_seccion4.setVisibility(View.GONE);
                _preguntaOcho_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_4.setSelected(false);
                _ChBx_3_7_4.setSelected(false);
                _ChBx_4_7_4.setSelected(false);

                _ChBx_2_7_4.setChecked(false);
                _ChBx_3_7_4.setChecked(false);
                _ChBx_4_7_4.setChecked(false);
            }
        });
        _ChBx_2_7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_7_4.setSelected(false);
                _ChBx_3_7_4.setSelected(false);
                _ChBx_4_7_4.setSelected(false);

                _ChBx_1_7_4.setChecked(false);
                _ChBx_3_7_4.setChecked(false);
                _ChBx_4_7_4.setChecked(false);
            }
        });
        _ChBx_3_7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_4.setSelected(false);
                _ChBx_1_7_4.setSelected(false);
                _ChBx_4_7_4.setSelected(false);

                _ChBx_1_7_4.setChecked(false);
                _ChBx_2_7_4.setChecked(false);
                _ChBx_4_7_4.setChecked(false);
            }
        });
        _ChBx_4_7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_4.setSelected(false);
                _ChBx_3_7_4.setSelected(false);
                _ChBx_1_7_4.setSelected(false);

                _ChBx_1_7_4.setChecked(false);
                _ChBx_2_7_4.setChecked(false);
                _ChBx_3_7_4.setChecked(false);
            }
        });


        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA OCTAVA PANTALLA--------------------------
        _ChBx_1_8_4 = (CheckBox) findViewById(R.id.ChBx_1_8_4);
        _ChBx_2_8_4 = (CheckBox) findViewById(R.id.ChBx_2_8_4);
        _ChBx_3_8_4 = (CheckBox) findViewById(R.id.ChBx_3_8_4);
        _ChBx_4_8_4 = (CheckBox) findViewById(R.id.ChBx_4_8_4);
        _btnSiguientePregunta8_4 = (Button) findViewById(R.id.btnSiguientePregunta8_4);
        _preguntaOcho_seccion4 = (LinearLayout) findViewById(R.id.preguntaOctava_seccion4);
        //                /*----------------PANTALLA 8 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 8 - SECCION 4*/
        _btnSiguientePregunta8_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaOcho_seccion4.setVisibility(View.GONE);
                _preguntaNueve_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_8_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_4.setSelected(false);
                _ChBx_3_8_4.setSelected(false);
                _ChBx_4_8_4.setSelected(false);

                _ChBx_2_8_4.setChecked(false);
                _ChBx_3_8_4.setChecked(false);
                _ChBx_4_8_4.setChecked(false);
            }
        });
        _ChBx_2_8_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_8_4.setSelected(false);
                _ChBx_3_8_4.setSelected(false);
                _ChBx_4_8_4.setSelected(false);

                _ChBx_1_8_4.setChecked(false);
                _ChBx_3_8_4.setChecked(false);
                _ChBx_4_8_4.setChecked(false);
            }
        });
        _ChBx_3_8_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_4.setSelected(false);
                _ChBx_1_8_4.setSelected(false);
                _ChBx_4_8_4.setSelected(false);

                _ChBx_1_8_4.setChecked(false);
                _ChBx_2_8_4.setChecked(false);
                _ChBx_4_8_4.setChecked(false);
            }
        });
        _ChBx_4_8_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_4.setSelected(false);
                _ChBx_3_8_4.setSelected(false);
                _ChBx_1_8_4.setSelected(false);

                _ChBx_1_8_4.setChecked(false);
                _ChBx_2_8_4.setChecked(false);
                _ChBx_3_8_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA NOVENA PANTALLA--------------------------
        _ChBx_1_9_4 = (CheckBox) findViewById(R.id.ChBx_1_9_4);
        _ChBx_2_9_4 = (CheckBox) findViewById(R.id.ChBx_2_9_4);
        _ChBx_3_9_4 = (CheckBox) findViewById(R.id.ChBx_3_9_4);
        _btnSiguientePregunta9_4 = (Button) findViewById(R.id.btnSiguientePregunta9_4);
        _preguntaNueve_seccion4 = (LinearLayout) findViewById(R.id.preguntaNueve_seccion4);
        //                /*----------------PANTALLA 9 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 9 - SECCION 4*/
        _btnSiguientePregunta9_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaNueve_seccion4.setVisibility(View.GONE);
                _preguntaDiez_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_9_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_4.setSelected(false);
                _ChBx_3_9_4.setSelected(false);

                _ChBx_2_9_4.setChecked(false);
                _ChBx_3_9_4.setChecked(false);
            }
        });
        _ChBx_2_9_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_9_4.setSelected(false);
                _ChBx_3_9_4.setSelected(false);

                _ChBx_1_9_4.setChecked(false);
                _ChBx_3_9_4.setChecked(false);
            }
        });
        _ChBx_3_9_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_4.setSelected(false);
                _ChBx_1_9_4.setSelected(false);

                _ChBx_1_9_4.setChecked(false);
                _ChBx_2_9_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA DECIMA PANTALLA--------------------------
        _ChBx_1_10_4 = (CheckBox) findViewById(R.id.ChBx_1_10_4);
        _ChBx_2_10_4 = (CheckBox) findViewById(R.id.ChBx_2_10_4);
        _ChBx_3_10_4 = (CheckBox) findViewById(R.id.ChBx_3_10_4);
        _btnSiguientePregunta10_4 = (Button) findViewById(R.id.btnSiguientePregunta10_4);
        _preguntaDiez_seccion4 = (LinearLayout) findViewById(R.id.preguntaDiez_seccion4);
        //                /*----------------PANTALLA 10 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 10 - SECCION 4*/
        _btnSiguientePregunta10_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaDiez_seccion4.setVisibility(View.GONE);
                _preguntaOnce_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_10_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_10_4.setSelected(false);
                _ChBx_3_10_4.setSelected(false);

                _ChBx_2_10_4.setChecked(false);
                _ChBx_3_10_4.setChecked(false);
            }
        });
        _ChBx_2_10_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_10_4.setSelected(false);
                _ChBx_3_10_4.setSelected(false);

                _ChBx_1_10_4.setChecked(false);
                _ChBx_3_10_4.setChecked(false);
            }
        });
        _ChBx_3_10_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_10_4.setSelected(false);
                _ChBx_1_10_4.setSelected(false);

                _ChBx_1_10_4.setChecked(false);
                _ChBx_2_10_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA ONCEAVA PANTALLA--------------------------
        _ChBx_1_11_4 = (CheckBox) findViewById(R.id.ChBx_1_11_4);
        _ChBx_2_11_4 = (CheckBox) findViewById(R.id.ChBx_2_11_4);
        _ChBx_3_11_4 = (CheckBox) findViewById(R.id.ChBx_3_11_4);
        _btnSiguientePregunta11_4 = (Button) findViewById(R.id.btnSiguientePregunta11_4);
        _preguntaOnce_seccion4 = (LinearLayout) findViewById(R.id.preguntaOnce_seccion4);
        //                /*----------------PANTALLA 11 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 11 - SECCION 4*/
        _btnSiguientePregunta11_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaOnce_seccion4.setVisibility(View.GONE);
                _preguntaDose_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_11_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_11_4.setSelected(false);
                _ChBx_3_11_4.setSelected(false);

                _ChBx_2_11_4.setChecked(false);
                _ChBx_3_11_4.setChecked(false);
            }
        });
        _ChBx_2_11_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_11_4.setSelected(false);
                _ChBx_3_11_4.setSelected(false);

                _ChBx_1_11_4.setChecked(false);
                _ChBx_3_11_4.setChecked(false);
            }
        });
        _ChBx_3_11_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_11_4.setSelected(false);
                _ChBx_1_11_4.setSelected(false);

                _ChBx_1_11_4.setChecked(false);
                _ChBx_2_11_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA DOCEAVA PANTALLA--------------------------
        _ChBx_1_12_4 = (CheckBox) findViewById(R.id.ChBx_1_12_4);
        _ChBx_2_12_4 = (CheckBox) findViewById(R.id.ChBx_2_12_4);
        _ChBx_3_12_4 = (CheckBox) findViewById(R.id.ChBx_3_12_4);
        _btnSiguientePregunta12_4 = (Button) findViewById(R.id.btnSiguientePregunta12_4);
        _preguntaDose_seccion4 = (LinearLayout) findViewById(R.id.preguntaDose_seccion4);
        //                /*----------------PANTALLA 12 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 12 - SECCION 4*/
        _btnSiguientePregunta12_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaDose_seccion4.setVisibility(View.GONE);
                _preguntaTrece_seccion4.setVisibility(View.VISIBLE);

            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_12_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_12_4.setSelected(false);
                _ChBx_3_12_4.setSelected(false);

                _ChBx_2_12_4.setChecked(false);
                _ChBx_3_12_4.setChecked(false);
            }
        });
        _ChBx_2_12_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_12_4.setSelected(false);
                _ChBx_3_12_4.setSelected(false);

                _ChBx_1_12_4.setChecked(false);
                _ChBx_3_12_4.setChecked(false);
            }
        });
        _ChBx_3_12_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_12_4.setSelected(false);
                _ChBx_1_12_4.setSelected(false);

                _ChBx_1_12_4.setChecked(false);
                _ChBx_2_12_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA TRECEAVA PANTALLA--------------------------
        _btnSi = (Button) findViewById(R.id.btnSi);
        _btnNo = (Button) findViewById(R.id.btnNo);
        _btnNoEstoySeguro13_4 = (Button) findViewById(R.id.btnNoEstoySeguro13_4);
        _btnSiguientePregunta13_4 = (Button) findViewById(R.id.btnSiguientePregunta13_4);
        _txtIncisoRespuestaPregunta13_4 = (TextView) findViewById(R.id.txtIncisoRespuestaPregunta13_4);
        _preguntaTrece_seccion4 = (LinearLayout) findViewById(R.id.preguntaTrece_seccion4);
        //                /*----------------PANTALLA 13 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 13 - SECCION 4*/
        _btnSiguientePregunta13_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifica==true ){
                    contador ++;
                    verifica = false;

                    _btnSi.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnSi.setTextColor(getResources().getColor(R.color.negro));

                    _btnNo.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnNo.setTextColor(getResources().getColor(R.color.negro));

                    _btnNoEstoySeguro13_4.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnNoEstoySeguro13_4.setTextColor(getResources().getColor(R.color.negro));

                    if(contador == 2){
                        _txtIncisoRespuestaPregunta13_4.setText("Leer las etiquetas de los alimentos");
                    }
                    else if(contador == 3){
                        _txtIncisoRespuestaPregunta13_4.setText("Tomar suplementos nutricionales");
                    }
                    else if(contador == 4){
                        _txtIncisoRespuestaPregunta13_4.setText("Monitorear su alimentación");
                    }
                    else if(contador == 5){
                        _txtIncisoRespuestaPregunta13_4.setText("Monitorear su peso");
                    }

                    else if(contador == 6){
                        _txtIncisoRespuestaPregunta13_4.setText("Rasguñar durante el día");
                    }

                    if (contador == 7){
                        _preguntaTrece_seccion4.setVisibility(View.GONE);
                        _preguntaCatorse_seccion4.setVisibility(View.VISIBLE);
                        contador = 1;
                    }
                }
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnSi.setBackgroundResource(R.drawable.seleccionar_button);
                _btnSi.setTextColor(getResources().getColor(R.color.blanco));

                _btnNo.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNo.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro13_4.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro13_4.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        _btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnNo.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNo.setTextColor(getResources().getColor(R.color.blanco));

                _btnSi.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnSi.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro13_4.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro13_4.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        _btnNoEstoySeguro13_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnNoEstoySeguro13_4.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoEstoySeguro13_4.setTextColor(getResources().getColor(R.color.blanco));

                _btnSi.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnSi.setTextColor(getResources().getColor(R.color.negro));

                _btnNo.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNo.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA CATORCEAVA PANTALLA--------------------------
        _ChBx_1_14_4 = (CheckBox) findViewById(R.id.ChBx_1_14_4);
        _ChBx_2_14_4 = (CheckBox) findViewById(R.id.ChBx_2_14_4);
        _ChBx_3_14_4 = (CheckBox) findViewById(R.id.ChBx_3_14_4);
        _ChBx_4_14_4 = (CheckBox) findViewById(R.id.ChBx_4_14_4);
        _ChBx_5_14_4 = (CheckBox) findViewById(R.id.ChBx_5_14_4);
        _btnSiguientePregunta14_4 = (Button) findViewById(R.id.btnSiguientePregunta14_4);
        _preguntaCatorse_seccion4 = (LinearLayout) findViewById(R.id.preguntaCatorse_seccion4);
        //                /*----------------PANTALLA 14 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 14 - SECCION 4*/
        _btnSiguientePregunta14_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaCatorse_seccion4.setVisibility(View.GONE);
                _preguntaQuinse_seccion4.setVisibility(View.VISIBLE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_14_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_14_4.setSelected(false);
                _ChBx_3_14_4.setSelected(false);
                _ChBx_4_14_4.setSelected(false);
                _ChBx_5_14_4.setSelected(false);

                _ChBx_2_14_4.setChecked(false);
                _ChBx_3_14_4.setChecked(false);
                _ChBx_4_14_4.setChecked(false);
                _ChBx_5_14_4.setChecked(false);
            }
        });
        _ChBx_2_14_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_14_4.setSelected(false);
                _ChBx_3_14_4.setSelected(false);
                _ChBx_4_14_4.setSelected(false);
                _ChBx_5_14_4.setSelected(false);

                _ChBx_1_14_4.setChecked(false);
                _ChBx_3_14_4.setChecked(false);
                _ChBx_4_14_4.setChecked(false);
                _ChBx_5_14_4.setChecked(false);
            }
        });
        _ChBx_3_14_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_14_4.setSelected(false);
                _ChBx_1_14_4.setSelected(false);
                _ChBx_4_14_4.setSelected(false);
                _ChBx_5_14_4.setSelected(false);

                _ChBx_1_14_4.setChecked(false);
                _ChBx_2_14_4.setChecked(false);
                _ChBx_4_14_4.setChecked(false);
                _ChBx_5_14_4.setChecked(false);
            }
        });
        _ChBx_4_14_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_14_4.setSelected(false);
                _ChBx_3_14_4.setSelected(false);
                _ChBx_1_14_4.setSelected(false);
                _ChBx_5_14_4.setSelected(false);

                _ChBx_1_14_4.setChecked(false);
                _ChBx_2_14_4.setChecked(false);
                _ChBx_3_14_4.setChecked(false);
                _ChBx_5_14_4.setChecked(false);
            }
        });
        _ChBx_5_14_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_14_4.setSelected(false);
                _ChBx_3_14_4.setSelected(false);
                _ChBx_4_14_4.setSelected(false);
                _ChBx_1_14_4.setSelected(false);

                _ChBx_1_14_4.setChecked(false);
                _ChBx_2_14_4.setChecked(false);
                _ChBx_3_14_4.setChecked(false);
                _ChBx_4_14_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA QUINSEAVA PANTALLA--------------------------
        _ChBx_1_15_4 = (CheckBox) findViewById(R.id.ChBx_1_15_4);
        _ChBx_2_15_4 = (CheckBox) findViewById(R.id.ChBx_2_15_4);
        _ChBx_3_15_4 = (CheckBox) findViewById(R.id.ChBx_3_15_4);
        _ChBx_4_15_4 = (CheckBox) findViewById(R.id.ChBx_4_15_4);
        _ChBx_5_15_4 = (CheckBox) findViewById(R.id.ChBx_5_15_4);
        _btnSiguientePregunta15_4 = (Button) findViewById(R.id.btnSiguientePregunta15_4);
        _preguntaQuinse_seccion4 = (LinearLayout) findViewById(R.id.preguntaQuinse_seccion4);
        //                /*----------------PANTALLA 15 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 15 - SECCION 4*/
        _btnSiguientePregunta15_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaQuinse_seccion4.setVisibility(View.GONE);
            }
        });
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_15_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_15_4.setSelected(false);
                _ChBx_3_15_4.setSelected(false);
                _ChBx_4_15_4.setSelected(false);
                _ChBx_5_15_4.setSelected(false);

                _ChBx_2_15_4.setChecked(false);
                _ChBx_3_15_4.setChecked(false);
                _ChBx_4_15_4.setChecked(false);
                _ChBx_5_15_4.setChecked(false);
            }
        });
        _ChBx_2_15_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_15_4.setSelected(false);
                _ChBx_3_15_4.setSelected(false);
                _ChBx_4_15_4.setSelected(false);
                _ChBx_5_15_4.setSelected(false);

                _ChBx_1_15_4.setChecked(false);
                _ChBx_3_15_4.setChecked(false);
                _ChBx_4_15_4.setChecked(false);
                _ChBx_5_15_4.setChecked(false);
            }
        });
        _ChBx_3_15_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_15_4.setSelected(false);
                _ChBx_1_15_4.setSelected(false);
                _ChBx_4_15_4.setSelected(false);
                _ChBx_5_15_4.setSelected(false);

                _ChBx_1_15_4.setChecked(false);
                _ChBx_2_15_4.setChecked(false);
                _ChBx_4_15_4.setChecked(false);
                _ChBx_5_15_4.setChecked(false);
            }
        });
        _ChBx_4_15_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_15_4.setSelected(false);
                _ChBx_3_15_4.setSelected(false);
                _ChBx_1_15_4.setSelected(false);
                _ChBx_5_15_4.setSelected(false);

                _ChBx_1_15_4.setChecked(false);
                _ChBx_2_15_4.setChecked(false);
                _ChBx_3_15_4.setChecked(false);
                _ChBx_5_15_4.setChecked(false);
            }
        });
        _ChBx_5_15_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_15_4.setSelected(false);
                _ChBx_3_15_4.setSelected(false);
                _ChBx_4_15_4.setSelected(false);
                _ChBx_1_15_4.setSelected(false);

                _ChBx_1_15_4.setChecked(false);
                _ChBx_2_15_4.setChecked(false);
                _ChBx_3_15_4.setChecked(false);
                _ChBx_4_15_4.setChecked(false);
            }
        });
        //        //------------------------END---------------------------------
        //**************************************************************************************
        ////**************************************************************************************
        ////**************************************************************************************
        //
        //
        //
        ////------------------------COMPONENTES DE LA DIESISEISAVA PANTALLA--------------------------
        //                /*----------------PANTALLA 16 ------------
        //         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 16 - SECCION 4*/
        //        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        //        //------------------------END---------------------------------
    }
}
