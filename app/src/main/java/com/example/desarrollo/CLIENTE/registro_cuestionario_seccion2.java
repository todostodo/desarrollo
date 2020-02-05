package com.example.desarrollo.CLIENTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.example.desarrollo.R;

public class registro_cuestionario_seccion2 extends AppCompatActivity {

    private Button _btnSiguientePregunta1_2, _btnBajoAzucar, _btnNoEstoySeguro1_2, _btnAltoAzucar, _btnAltoSal, _btnBajoSal, _btnNoEstoySeguro2_2, _btnSiguientePregunta2_2, _btnAltoFibra, _btnBajoFibra, _btnNoEstoySeguro3_2, _btnSiguientePregunta3_2, _btnBuenaFuente, _btnNoBuenaFuente, _btnNoEstoySeguro4_2, _btnSiguientePregunta4_2, _btnConAlmidon, _btnSinAlmidon, _btnNoEstoySeguro5_2, _btnSiguientePregunta5_2, _btnGrasaPolinisaturada, _btnGrasaInsaturada, _btnGrasaSaturada, _btnColesterol, _btnNoEstoySeguro6_2, _btnSiguientePregunta6_2, _btnSiguientePregunta7_2, _btnSiguientePregunta8_2, _btnSiguientePregunta9_2, _btnSiguientePregunta10_2;
    private TextView _txtIncisoRespuestaPregunta1_2, _txtIncisoRespuestaPregunta2_2, _txtIncisoRespuestaPregunta3_2, _txtIncisoRespuestaPregunta4_2, _txtIncisoRespuestaPregunta5_2, _txtIncisoRespuestaPregunta6_2;
    private boolean verifica = false;
    private int contador = 1;
    private LinearLayout _preguntaUno_seccion2, _preguntaDos_seccion2, _preguntaTres_seccion2, _preguntaCuatro_seccion2, _preguntaCinco_seccion2, _preguntaSeis_seccion2, _preguntaSiete_seccion2, _preguntaOcho_seccion2, _preguntaNueve_seccion2, _preguntaDiez_seccion2;
   private CheckBox _ChBx_1_7_2, _ChBx_2_7_2, _ChBx_3_7_2, _ChBx_4_7_2, _ChBx_5_7_2, _ChBx_1_8_2, _ChBx_2_8_2, _ChBx_3_8_2, _ChBx_4_8_2, _ChBx_1_9_2, _ChBx_2_9_2, _ChBx_3_9_2, _ChBx_4_9_2, _ChBx_5_9_2, _ChBx_1_10_2, _ChBx_2_10_2, _ChBx_3_10_2, _ChBx_4_10_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuestionario_seccion2);



        //------------------------COMPONENTES DE LA PRIMERA PANTALLA--------------------------
        _btnSiguientePregunta1_2 = (Button) findViewById(R.id.btnSiguientePregunta1_2);
        _btnBajoAzucar = (Button) findViewById(R.id.btnBajoAzucar);
        _btnNoEstoySeguro1_2 = (Button) findViewById(R.id.btnNoEstoySeguro1_2);
        _btnAltoAzucar = (Button) findViewById(R.id.btnAltoAzucar);
        _txtIncisoRespuestaPregunta1_2 = (TextView) findViewById(R.id.txtIncisoRespuestaPregunta1_2);
        _preguntaUno_seccion2 = (LinearLayout) findViewById(R.id.preguntaUno_seccion2);
        /*----------------PANTALLA 1 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 1 - SECCION 2*/

        _btnSiguientePregunta1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verifica==true ){
                    contador ++;
                    verifica = false;

                    _btnBajoAzucar.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnBajoAzucar.setTextColor(getResources().getColor(R.color.negro));

                    _btnNoEstoySeguro1_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnNoEstoySeguro1_2.setTextColor(getResources().getColor(R.color.negro));

                    _btnAltoAzucar.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnAltoAzucar.setTextColor(getResources().getColor(R.color.negro));

                    if(contador == 2){
                        _txtIncisoRespuestaPregunta1_2.setText("Yogur natural");
                    }
                    else if(contador == 3){
                        _txtIncisoRespuestaPregunta1_2.setText("Helado");
                    }
                    else if(contador == 4){
                        _txtIncisoRespuestaPregunta1_2.setText("Salsa de tomate");
                    }
                    else if(contador == 5){
                        _txtIncisoRespuestaPregunta1_2.setText("MelÃ³n");
                    }

                    if (contador == 6){
                        _preguntaUno_seccion2.setVisibility(View.GONE);
                        _preguntaDos_seccion2.setVisibility(View.VISIBLE);
                        contador = 1;
                    }
                }
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _btnBajoAzucar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnBajoAzucar.setBackgroundResource(R.drawable.seleccionar_button);
                _btnBajoAzucar.setTextColor(getResources().getColor(R.color.blanco));

                _btnNoEstoySeguro1_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro1_2.setTextColor(getResources().getColor(R.color.negro));

                _btnAltoAzucar.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnAltoAzucar.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnNoEstoySeguro1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnNoEstoySeguro1_2.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoEstoySeguro1_2.setTextColor(getResources().getColor(R.color.blanco));

                _btnBajoAzucar.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnBajoAzucar.setTextColor(getResources().getColor(R.color.negro));

                _btnAltoAzucar.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnAltoAzucar.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        _btnAltoAzucar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnAltoAzucar.setBackgroundResource(R.drawable.seleccionar_button);
                _btnAltoAzucar.setTextColor(getResources().getColor(R.color.blanco));

                _btnBajoAzucar.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnBajoAzucar.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro1_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnAltoAzucar.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        //------------------------END---------------------------------



        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************



        //---------------------------COMPONENTES DE LA SEGUNDA PANTALLA--------------------------
        _btnSiguientePregunta2_2 = (Button) findViewById(R.id.btnSiguientePregunta2_2);
        _preguntaDos_seccion2 = (LinearLayout) findViewById(R.id.preguntaDos_seccion2);
        _btnAltoSal = (Button) findViewById(R.id.btnAltoSal);
        _btnNoEstoySeguro2_2 = (Button) findViewById(R.id.btnNoEstoySeguro2_2);
        _btnBajoSal = (Button) findViewById(R.id.btnBajoSal);
        _txtIncisoRespuestaPregunta2_2 = (TextView) findViewById(R.id.txtIncisoRespuestaPregunta2_2);
        _preguntaTres_seccion2 = (LinearLayout) findViewById(R.id.preguntaTres_seccion2);
    /*----------------PANTALLA 2 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 2 - SECCION 2*/
        _btnSiguientePregunta2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifica==true ){
                    contador ++;
                    verifica = false;

                    _btnAltoSal.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnAltoSal.setTextColor(getResources().getColor(R.color.negro));

                    _btnBajoSal.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnBajoSal.setTextColor(getResources().getColor(R.color.negro));

                    _btnNoEstoySeguro2_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnNoEstoySeguro2_2.setTextColor(getResources().getColor(R.color.negro));

                    if(contador == 2){
                        _txtIncisoRespuestaPregunta2_2.setText("Verduras congeladasVerduras congeladas");
                    }
                    else if(contador == 3){
                        _txtIncisoRespuestaPregunta2_2.setText("Pan");
                    }
                    else if(contador == 4){
                        _txtIncisoRespuestaPregunta2_2.setText("Carne roja ");
                    }
                    else if(contador == 5){
                        _txtIncisoRespuestaPregunta2_2.setText("Frijoles horneados ");
                    }
                    else if(contador == 6){
                        _txtIncisoRespuestaPregunta2_2.setText("Sopa enlatada");
                    }

                    if (contador == 7){
                        _preguntaDos_seccion2.setVisibility(View.GONE);
                        _preguntaTres_seccion2.setVisibility(View.VISIBLE);
                        contador = 1;
                    }
                }
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------

        _btnAltoSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnAltoSal.setBackgroundResource(R.drawable.seleccionar_button);
                _btnAltoSal.setTextColor(getResources().getColor(R.color.blanco));

                _btnBajoSal.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnBajoSal.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro2_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro2_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnBajoSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnBajoSal.setBackgroundResource(R.drawable.seleccionar_button);
                _btnBajoSal.setTextColor(getResources().getColor(R.color.blanco));

                _btnAltoSal.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnAltoSal.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro2_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro2_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnNoEstoySeguro2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnNoEstoySeguro2_2.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoEstoySeguro2_2.setTextColor(getResources().getColor(R.color.blanco));

                _btnBajoSal.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnBajoSal.setTextColor(getResources().getColor(R.color.negro));

                _btnAltoSal.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnAltoSal.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        //------------------------END---------------------------------

        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************


        //------------------------COMPONENTES DE LA TERCERA PANTALLA--------------------------
        _btnAltoFibra = (Button) findViewById(R.id.btnAltoFibra);
        _btnBajoFibra = (Button) findViewById(R.id.btnBajoFibra);
        _btnNoEstoySeguro3_2 = (Button) findViewById(R.id.btnNoEstoySeguro3_2);
        _btnSiguientePregunta3_2 = (Button) findViewById(R.id.btnSiguientePregunta3_2);
        _preguntaCuatro_seccion2 = (LinearLayout) findViewById(R.id.preguntaCuatro_seccion2);
        _txtIncisoRespuestaPregunta3_2 = (TextView) findViewById(R.id.txtIncisoRespuestaPregunta3_2);
        /*----------------PANTALLA 3 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 3 - SECCION 2*/
        _btnSiguientePregunta3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (verifica==true ){
                    contador ++;
                    verifica = false;

                    _btnAltoFibra.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnAltoFibra.setTextColor(getResources().getColor(R.color.negro));

                    _btnBajoFibra.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnBajoFibra.setTextColor(getResources().getColor(R.color.negro));

                    _btnNoEstoySeguro3_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnNoEstoySeguro3_2.setTextColor(getResources().getColor(R.color.negro));

                    if(contador == 2){
                        _txtIncisoRespuestaPregunta3_2.setText("Plátanos");
                    }
                    else if(contador == 3){
                        _txtIncisoRespuestaPregunta3_2.setText("Arroz blanco");
                    }
                    else if(contador == 4){
                        _txtIncisoRespuestaPregunta3_2.setText("Huevos");
                    }
                    else if(contador == 5){
                        _txtIncisoRespuestaPregunta3_2.setText("Papas con piel");
                    }
                    else if(contador == 6){
                        _txtIncisoRespuestaPregunta3_2.setText("Pasta");
                    }

                    if (contador == 7){
                        _preguntaTres_seccion2.setVisibility(View.GONE);
                        _preguntaCuatro_seccion2.setVisibility(View.VISIBLE);
                        contador = 1;
                    }
                }

            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _btnAltoFibra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnAltoFibra.setBackgroundResource(R.drawable.seleccionar_button);
                _btnAltoFibra.setTextColor(getResources().getColor(R.color.blanco));

                _btnBajoFibra.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnBajoFibra.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro3_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro3_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        _btnBajoFibra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnBajoFibra.setBackgroundResource(R.drawable.seleccionar_button);
                _btnBajoFibra.setTextColor(getResources().getColor(R.color.blanco));

                _btnAltoFibra.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnAltoFibra.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro3_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro3_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        _btnNoEstoySeguro3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnNoEstoySeguro3_2.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoEstoySeguro3_2.setTextColor(getResources().getColor(R.color.blanco));

                _btnAltoFibra.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnAltoFibra.setTextColor(getResources().getColor(R.color.negro));

                _btnBajoFibra.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnBajoFibra.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        //------------------------END---------------------------------

        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************

        //------------------------COMPONENTES DE LA CUARTA PANTALLA--------------------------
        _btnBuenaFuente = (Button) findViewById(R.id.btnBuenaFuente);
        _btnNoBuenaFuente = (Button) findViewById(R.id.btnNoBuenaFuente);
        _btnNoEstoySeguro4_2 = (Button) findViewById(R.id.btnNoEstoySeguro4_2);
        _btnSiguientePregunta4_2 = (Button) findViewById(R.id.btnSiguientePregunta4_2);
        _txtIncisoRespuestaPregunta4_2 = (TextView) findViewById(R.id.txtIncisoRespuestaPregunta4_2);
        _preguntaCinco_seccion2 = (LinearLayout) findViewById(R.id.preguntaCinco_seccion2);
        /*----------------PANTALLA 4 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 4 - SECCION 2*/
        _btnSiguientePregunta4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verifica==true ){
                    contador ++;
                    verifica = false;

                    _btnBuenaFuente.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnBuenaFuente.setTextColor(getResources().getColor(R.color.negro));

                    _btnNoBuenaFuente.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnNoBuenaFuente.setTextColor(getResources().getColor(R.color.negro));

                    _btnNoEstoySeguro4_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnNoEstoySeguro4_2.setTextColor(getResources().getColor(R.color.negro));

                    if(contador == 2){
                        _txtIncisoRespuestaPregunta4_2.setText("Queso");
                    }
                    else if(contador == 3){
                        _txtIncisoRespuestaPregunta4_2.setText("Fruta");
                    }
                    else if(contador == 4){
                        _txtIncisoRespuestaPregunta4_2.setText("Huevos");
                    }
                    else if(contador == 5){
                        _txtIncisoRespuestaPregunta4_2.setText("Frijoles horneados");
                    }
                    else if(contador == 6){
                        _txtIncisoRespuestaPregunta4_2.setText("Mantequilla");
                    }
                    else if(contador == 7){
                        _txtIncisoRespuestaPregunta4_2.setText("Nueces");
                    }

                    if (contador == 8){
                        _preguntaCuatro_seccion2.setVisibility(View.GONE);
                        _preguntaCinco_seccion2.setVisibility(View.VISIBLE);
                        contador = 1;
                    }
                }

            }
        });

        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _btnBuenaFuente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnBuenaFuente.setBackgroundResource(R.drawable.seleccionar_button);
                _btnBuenaFuente.setTextColor(getResources().getColor(R.color.blanco));

                _btnNoBuenaFuente.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoBuenaFuente.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro4_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro4_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnNoBuenaFuente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnNoBuenaFuente.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoBuenaFuente.setTextColor(getResources().getColor(R.color.blanco));

                _btnBuenaFuente.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnBuenaFuente.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro4_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro4_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnNoEstoySeguro4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnNoEstoySeguro4_2.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoEstoySeguro4_2.setTextColor(getResources().getColor(R.color.blanco));

                _btnBuenaFuente.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnBuenaFuente.setTextColor(getResources().getColor(R.color.negro));

                _btnNoBuenaFuente.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoBuenaFuente.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        //------------------------END---------------------------------


        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************

        //------------------------COMPONENTES DE LA  QUINTA PANTALLA--------------------------
        _btnConAlmidon = (Button) findViewById(R.id.btnConAlmidon);
        _btnSinAlmidon = (Button) findViewById(R.id.btnSinAlmidon);
        _btnNoEstoySeguro5_2 = (Button) findViewById(R.id.btnNoEstoySeguro5_2);
        _btnSiguientePregunta5_2 = (Button) findViewById(R.id.btnSiguientePregunta5_2);
        _txtIncisoRespuestaPregunta5_2 = (TextView) findViewById(R.id.txtIncisoRespuestaPregunta5_2);
        _preguntaSeis_seccion2 = (LinearLayout) findViewById(R.id.preguntaSeis_seccion2);
        /*----------------PANTALLA 5 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 5 - SECCION 2*/

        _btnSiguientePregunta5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifica==true ){
                    contador ++;
                    verifica = false;

                    _btnConAlmidon.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnConAlmidon.setTextColor(getResources().getColor(R.color.negro));

                    _btnSinAlmidon.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnSinAlmidon.setTextColor(getResources().getColor(R.color.negro));

                    _btnNoEstoySeguro5_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnNoEstoySeguro5_2.setTextColor(getResources().getColor(R.color.negro));

                    if(contador == 2){
                        _txtIncisoRespuestaPregunta5_2.setText("Pasta");
                    }
                    else if(contador == 3){
                        _txtIncisoRespuestaPregunta5_2.setText("Papas");
                    }
                    else if(contador == 4){
                        _txtIncisoRespuestaPregunta5_2.setText("Nueces");
                    }
                    else if(contador == 5){
                        _txtIncisoRespuestaPregunta5_2.setText("Plátanos");
                    }

                    if (contador == 6){
                        _preguntaCinco_seccion2.setVisibility(View.GONE);
                        _preguntaSeis_seccion2.setVisibility(View.VISIBLE);
                        contador = 1;
                    }
                }

            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------


        _btnConAlmidon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnConAlmidon.setBackgroundResource(R.drawable.seleccionar_button);
                _btnConAlmidon.setTextColor(getResources().getColor(R.color.blanco));

                _btnSinAlmidon.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnSinAlmidon.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro5_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro5_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnSinAlmidon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnSinAlmidon.setBackgroundResource(R.drawable.seleccionar_button);
                _btnSinAlmidon.setTextColor(getResources().getColor(R.color.blanco));

                _btnConAlmidon.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnConAlmidon.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro5_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro5_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnNoEstoySeguro5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnNoEstoySeguro5_2.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoEstoySeguro5_2.setTextColor(getResources().getColor(R.color.blanco));

                _btnConAlmidon.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnConAlmidon.setTextColor(getResources().getColor(R.color.negro));

                _btnSinAlmidon.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnSinAlmidon.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        //------------------------END---------------------------------


        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************

        //------------------------COMPONENTES DE LA SEXTA PANTALLA--------------------------
        _btnGrasaPolinisaturada = (Button) findViewById(R.id.btnGrasaPolinisaturada);
        _btnGrasaInsaturada = (Button) findViewById(R.id. btnGrasaInsaturada);
        _btnGrasaSaturada = (Button) findViewById(R.id.btnGrasaSaturada);
        _btnColesterol = (Button) findViewById(R.id.btnColesterol);
        _btnNoEstoySeguro6_2 = (Button) findViewById(R.id.btnNoEstoySeguro6_2);
        _btnSiguientePregunta6_2 = (Button) findViewById(R.id.btnSiguientePregunta6_2);
        _txtIncisoRespuestaPregunta6_2 = (TextView) findViewById(R.id.txtIncisoRespuestaPregunta6_2);
        _preguntaSiete_seccion2 = (LinearLayout) findViewById(R.id.preguntaSiete_seccion2);
        /*----------------PANTALLA 6 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 6 - SECCION 2*/
        _btnSiguientePregunta6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifica==true ){
                    contador ++;
                    verifica = false;

                    _btnGrasaPolinisaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnGrasaPolinisaturada.setTextColor(getResources().getColor(R.color.negro));

                    _btnGrasaInsaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnGrasaInsaturada.setTextColor(getResources().getColor(R.color.negro));

                    _btnGrasaSaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnGrasaSaturada.setTextColor(getResources().getColor(R.color.negro));

                    _btnColesterol.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnColesterol.setTextColor(getResources().getColor(R.color.negro));

                    _btnNoEstoySeguro6_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                    _btnNoEstoySeguro6_2.setTextColor(getResources().getColor(R.color.negro));

                    if(contador == 2){
                        _txtIncisoRespuestaPregunta5_2.setText("Aceite de oliva");
                    }
                    else if(contador == 3){
                        _txtIncisoRespuestaPregunta5_2.setText("Mantequilla");
                    }
                    else if(contador == 4){
                        _txtIncisoRespuestaPregunta5_2.setText("Aceite de girasol");
                    }
                    else if(contador == 5){
                        _txtIncisoRespuestaPregunta5_2.setText("Huevos");
                    }

                    if (contador == 6){
                        _preguntaSeis_seccion2.setVisibility(View.GONE);
                        _preguntaSiete_seccion2.setVisibility(View.VISIBLE);
                        contador = 1;
                    }
                }
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _btnGrasaPolinisaturada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnGrasaPolinisaturada.setBackgroundResource(R.drawable.seleccionar_button);
                _btnGrasaPolinisaturada.setTextColor(getResources().getColor(R.color.blanco));

                _btnGrasaInsaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaInsaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnGrasaSaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaSaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnColesterol.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnColesterol.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro6_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro6_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        _btnGrasaInsaturada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnGrasaInsaturada.setBackgroundResource(R.drawable.seleccionar_button);
                _btnGrasaInsaturada.setTextColor(getResources().getColor(R.color.blanco));

                _btnGrasaPolinisaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaPolinisaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnGrasaSaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaSaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnColesterol.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnColesterol.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro6_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro6_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        _btnGrasaSaturada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnGrasaSaturada.setBackgroundResource(R.drawable.seleccionar_button);
                _btnGrasaSaturada.setTextColor(getResources().getColor(R.color.blanco));

                _btnGrasaPolinisaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaPolinisaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnGrasaInsaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaInsaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnColesterol.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnColesterol.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro6_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro6_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        _btnColesterol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnColesterol.setBackgroundResource(R.drawable.seleccionar_button);
                _btnColesterol.setTextColor(getResources().getColor(R.color.blanco));

                _btnGrasaPolinisaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaPolinisaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnGrasaInsaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaInsaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnGrasaSaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaSaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnNoEstoySeguro6_2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro6_2.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        _btnNoEstoySeguro6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica = true;
                _btnNoEstoySeguro6_2.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoEstoySeguro6_2.setTextColor(getResources().getColor(R.color.blanco));

                _btnGrasaPolinisaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaPolinisaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnGrasaInsaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaInsaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnGrasaSaturada.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnGrasaSaturada.setTextColor(getResources().getColor(R.color.negro));

                _btnColesterol.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnColesterol.setTextColor(getResources().getColor(R.color.negro));
            }
        });
        //------------------------END---------------------------------

        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************

        //------------------------COMPONENTES DE LA SEXTIMA PANTALLA--------------------------
        _ChBx_1_7_2 = (CheckBox) findViewById(R.id.ChBx_1_7_2);
        _ChBx_2_7_2 = (CheckBox) findViewById(R.id.ChBx_2_7_2);
        _ChBx_3_7_2 = (CheckBox) findViewById(R.id.ChBx_3_7_2);
        _ChBx_4_7_2 = (CheckBox) findViewById(R.id.ChBx_4_7_2);
        _ChBx_5_7_2 = (CheckBox) findViewById(R.id.ChBx_5_7_2);
        _btnSiguientePregunta7_2 = (Button) findViewById(R.id.btnSiguientePregunta7_2);
        _preguntaOcho_seccion2 = (LinearLayout) findViewById(R.id.preguntaOcho_seccion2);
        /*----------------PANTALLA 7 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 7 - SECCION 2*/
        _btnSiguientePregunta7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaSiete_seccion2.setVisibility(View.GONE);
                _preguntaOcho_seccion2.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_2.setSelected(false);
                _ChBx_3_7_2.setSelected(false);
                _ChBx_4_7_2.setSelected(false);
                _ChBx_5_7_2.setSelected(false);

                _ChBx_2_7_2.setChecked(false);
                _ChBx_3_7_2.setChecked(false);
                _ChBx_4_7_2.setChecked(false);
                _ChBx_5_7_2.setChecked(false);
            }
        });
        _ChBx_2_7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_7_2.setSelected(false);
                _ChBx_3_7_2.setSelected(false);
                _ChBx_4_7_2.setSelected(false);
                _ChBx_5_7_2.setSelected(false);

                _ChBx_1_7_2.setChecked(false);
                _ChBx_3_7_2.setChecked(false);
                _ChBx_4_7_2.setChecked(false);
                _ChBx_5_7_2.setChecked(false);
            }
        });
        _ChBx_3_7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_2.setSelected(false);
                _ChBx_1_7_2.setSelected(false);
                _ChBx_4_7_2.setSelected(false);
                _ChBx_5_7_2.setSelected(false);

                _ChBx_1_7_2.setChecked(false);
                _ChBx_2_7_2.setChecked(false);
                _ChBx_4_7_2.setChecked(false);
                _ChBx_5_7_2.setChecked(false);
            }
        });
        _ChBx_4_7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_2.setSelected(false);
                _ChBx_3_7_2.setSelected(false);
                _ChBx_1_7_2.setSelected(false);
                _ChBx_5_7_2.setSelected(false);

                _ChBx_1_7_2.setChecked(false);
                _ChBx_2_7_2.setChecked(false);
                _ChBx_3_7_2.setChecked(false);
                _ChBx_5_7_2.setChecked(false);
            }
        });
        _ChBx_5_7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_2.setSelected(false);
                _ChBx_3_7_2.setSelected(false);
                _ChBx_4_7_2.setSelected(false);
                _ChBx_1_7_2.setSelected(false);

                _ChBx_1_7_2.setChecked(false);
                _ChBx_2_7_2.setChecked(false);
                _ChBx_3_7_2.setChecked(false);
                _ChBx_4_7_2.setChecked(false);
            }
        });
        //------------------------END---------------------------------

        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************

        //------------------------COMPONENTES DE LA OCTAVA PANTALLA--------------------------
        _ChBx_1_8_2 = (CheckBox) findViewById(R.id.ChBx_1_8_2);
        _ChBx_2_8_2 = (CheckBox) findViewById(R.id.ChBx_2_8_2);
        _ChBx_3_8_2 = (CheckBox) findViewById(R.id.ChBx_3_8_2);
        _ChBx_4_8_2 = (CheckBox) findViewById(R.id.ChBx_4_8_2);
        _btnSiguientePregunta8_2 = (Button) findViewById(R.id.btnSiguientePregunta8_2);
        _preguntaNueve_seccion2 = (LinearLayout) findViewById(R.id.preguntaNueve_seccion2);
        /*----------------PANTALLA 8 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 8 - SECCION 2*/
        _btnSiguientePregunta8_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaOcho_seccion2.setVisibility(View.GONE);
                _preguntaNueve_seccion2.setVisibility(View.VISIBLE);
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_8_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_2.setSelected(false);
                _ChBx_3_8_2.setSelected(false);
                _ChBx_4_8_2.setSelected(false);

                _ChBx_2_8_2.setChecked(false);
                _ChBx_3_8_2.setChecked(false);
                _ChBx_4_8_2.setChecked(false);
            }
        });
        _ChBx_2_8_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_3_8_2.setSelected(false);
                _ChBx_4_8_2.setSelected(false);
                _ChBx_1_8_2.setSelected(false);

                _ChBx_1_8_2.setChecked(false);
                _ChBx_3_8_2.setChecked(false);
                _ChBx_4_8_2.setChecked(false);
            }
        });
        _ChBx_3_8_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_2.setSelected(false);
                _ChBx_4_8_2.setSelected(false);
                _ChBx_1_8_2.setSelected(false);

                _ChBx_1_8_2.setChecked(false);
                _ChBx_2_8_2.setChecked(false);
                _ChBx_4_8_2.setChecked(false);
            }
        });
        _ChBx_4_8_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_2.setSelected(false);
                _ChBx_3_8_2.setSelected(false);
                _ChBx_1_8_2.setSelected(false);

                _ChBx_1_8_2.setChecked(false);
                _ChBx_2_8_2.setChecked(false);
                _ChBx_3_8_2.setChecked(false);
            }
        });
        //------------------------END---------------------------------

        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************

        //------------------------COMPONENTES DE LA NOVENA PANTALLA--------------------------
        _ChBx_1_9_2 = (CheckBox) findViewById(R.id.ChBx_1_9_2);
        _ChBx_2_9_2 = (CheckBox) findViewById(R.id.ChBx_2_9_2);
        _ChBx_3_9_2 = (CheckBox) findViewById(R.id.ChBx_3_9_2);
        _ChBx_4_9_2 = (CheckBox) findViewById(R.id.ChBx_4_9_2);
        _ChBx_5_9_2 = (CheckBox) findViewById(R.id.ChBx_5_9_2);
        _btnSiguientePregunta9_2 = (Button) findViewById(R.id.btnSiguientePregunta9_2);
        _preguntaDiez_seccion2 = (LinearLayout) findViewById(R.id.preguntaDos_seccion2);
        /*----------------PANTALLA 9 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 9 - SECCION 2*/
        _btnSiguientePregunta9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaNueve_seccion2.setVisibility(View.GONE);
                _preguntaDiez_seccion2.setVisibility(View.VISIBLE);
            }
        });

        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_2.setSelected(false);
                _ChBx_3_9_2.setSelected(false);
                _ChBx_4_9_2.setSelected(false);
                _ChBx_5_9_2.setSelected(false);

                _ChBx_2_9_2.setChecked(false);
                _ChBx_3_9_2.setChecked(false);
                _ChBx_4_9_2.setChecked(false);
                _ChBx_5_9_2.setChecked(false);
            }
        });

        _ChBx_2_9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_9_2.setSelected(false);
                _ChBx_3_9_2.setSelected(false);
                _ChBx_4_9_2.setSelected(false);
                _ChBx_5_9_2.setSelected(false);

                _ChBx_1_9_2.setChecked(false);
                _ChBx_3_9_2.setChecked(false);
                _ChBx_4_9_2.setChecked(false);
                _ChBx_5_9_2.setChecked(false);
            }
        });

        _ChBx_3_9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_2.setSelected(false);
                _ChBx_1_9_2.setSelected(false);
                _ChBx_4_9_2.setSelected(false);
                _ChBx_5_9_2.setSelected(false);

                _ChBx_1_9_2.setChecked(false);
                _ChBx_2_9_2.setChecked(false);
                _ChBx_4_9_2.setChecked(false);
                _ChBx_5_9_2.setChecked(false);
            }
        });

        _ChBx_4_9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_2.setSelected(false);
                _ChBx_3_9_2.setSelected(false);
                _ChBx_1_9_2.setSelected(false);
                _ChBx_5_9_2.setSelected(false);

                _ChBx_1_9_2.setChecked(false);
                _ChBx_2_9_2.setChecked(false);
                _ChBx_3_9_2.setChecked(false);
                _ChBx_5_9_2.setChecked(false);
            }
        });

        _ChBx_5_9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_2.setSelected(false);
                _ChBx_3_9_2.setSelected(false);
                _ChBx_4_9_2.setSelected(false);
                _ChBx_1_9_2.setSelected(false);

                _ChBx_1_9_2.setChecked(false);
                _ChBx_2_9_2.setChecked(false);
                _ChBx_3_9_2.setChecked(false);
                _ChBx_4_9_2.setChecked(false);
            }
        });

        //------------------------END---------------------------------
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************

        //------------------------COMPONENTES DE LA DECIMA PANTALLA--------------------------
        _ChBx_1_10_2 = (CheckBox) findViewById(R.id.ChBx_1_10_2);
        _ChBx_2_10_2 = (CheckBox) findViewById(R.id.ChBx_2_10_2);
        _ChBx_3_10_2 = (CheckBox) findViewById(R.id.ChBx_3_10_2);
        _ChBx_4_10_2 = (CheckBox) findViewById(R.id.ChBx_4_10_2);
        _btnSiguientePregunta10_2 = (Button) findViewById(R.id.btnSiguientePregunta10_2);
        /*----------------PANTALLA 10 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 10 - SECCION 2*/
        _btnSiguientePregunta10_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registro_cuestionario_seccion2.this,registro_cuestionario_seccion3.class));
            }
        });
        //------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_10_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_10_2.setSelected(false);
                _ChBx_3_10_2.setSelected(false);
                _ChBx_4_10_2.setSelected(false);

                _ChBx_2_10_2.setChecked(false);
                _ChBx_3_10_2.setChecked(false);
                _ChBx_4_10_2.setChecked(false);

            }
        });
        _ChBx_2_10_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_10_2.setSelected(false);
                _ChBx_3_10_2.setSelected(false);
                _ChBx_4_10_2.setSelected(false);

                _ChBx_1_10_2.setChecked(false);
                _ChBx_3_10_2.setChecked(false);
                _ChBx_4_10_2.setChecked(false);
            }
        });
        _ChBx_3_10_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_10_2.setSelected(false);
                _ChBx_1_10_2.setSelected(false);
                _ChBx_4_10_2.setSelected(false);

                _ChBx_1_10_2.setChecked(false);
                _ChBx_2_10_2.setChecked(false);
                _ChBx_4_10_2.setChecked(false);
            }
        });
        _ChBx_4_10_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_10_2.setSelected(false);
                _ChBx_3_10_2.setSelected(false);
                _ChBx_1_10_2.setSelected(false);

                _ChBx_1_10_2.setChecked(false);
                _ChBx_2_10_2.setChecked(false);
                _ChBx_3_10_2.setChecked(false);
            }
        });
        //------------------------END---------------------------------

    }

}
