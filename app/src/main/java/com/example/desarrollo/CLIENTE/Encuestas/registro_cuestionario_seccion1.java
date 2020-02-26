package com.example.desarrollo.CLIENTE.Encuestas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.desarrollo.R;

public class registro_cuestionario_seccion1 extends AppCompatActivity {

    /*
    --------PATALLA 1*-------/
    VARIABLES
     */
    private Button _btnMas, _btnMenos, _btnIgual, _btnNoEstoySeguro, _btnSiguientePregunta1, _btnSiguientePregunta2, _btnSiguientePregunta4, _btnSiguientePregunta5, _btnSiguientePregunta6, _btnSiguientePregunta7, _btnSiguientePregunta8, _btnSiguientePregunta9;
    private TextView _txtIncisoRespuesta;
    private CheckBox _ChBx_1_2_1, _ChBx_2_2_1, _ChBx_3_2_1, _ChBx_4_2_1, _ChBx_5_2_1, _ChBx_1_4_1, _ChBx_2_4_1, _ChBx_3_4_1, _ChBx_4_4_1, _ChBx_5_4_1, _ChBx_1_5_1, _ChBx_2_5_1, _ChBx_3_5_1, _ChBx_4_5_1, _ChBx_1_6_1, _ChBx_2_6_1, _ChBx_3_6_1, _ChBx_4_6_1, _ChBx_5_6_1, _ChBx_1_7_1, _ChBx_2_7_1, _ChBx_3_7_1, _ChBx_4_7_1, _ChBx_1_8_1, _ChBx_2_8_1, _ChBx_3_8_1, _ChBx_4_8_1, _ChBx_5_8_1, _ChBx_1_9_1, _ChBx_2_9_1, _ChBx_3_9_1, _ChBx_4_9_1;
    private LinearLayout _preguntaDos_seccion1, _preguntaTres_seccion1, _preguntaCinco_seccion1, _preguntaCuatro_seccion1, _preguntaSeis_seccion1, _preguntaSeptima_seccion1, _preguntaOctava_seccion1, _preguntaNovena_seccion1;
    private boolean btnActivoPregunta1 = false;
    private boolean btnActivoPregunta2 = false;
    private int cambiarTextoPregunta1 = 1;
    private int cambiarTextoPregunta2 = 1;


    /*
    --------PATALLA 3*-------/
    VARIABLES
     */
    private  Button _btnComerMenos, _btnNoComerMenos, _btnNoEstoySeguro2, _btnSiguientePregunta3;
    private TextView _txtIncisoRespuesta2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuestionario_seccion1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //PANTALLA 1
        _btnMas = (Button) findViewById(R.id.btnMas);
        _btnIgual = (Button) findViewById(R.id.btnIgual);
        _btnMenos = (Button) findViewById(R.id.btnMenos);
        _btnNoEstoySeguro = (Button) findViewById(R.id.btnNoEstoySeguro);
        _btnSiguientePregunta1 = (Button) findViewById(R.id.btnSiguientePregunta1);
        _txtIncisoRespuesta = (TextView) findViewById(R.id.txtIncisoRespuesta);

        //PANTALLA 3
        _txtIncisoRespuesta2 = (TextView) findViewById(R.id.txtIncisoRespuesta2);
        _btnComerMenos = (Button) findViewById(R.id.btnComerMenos);
        _btnNoComerMenos = (Button) findViewById(R.id.btnNoComerMenos);
        _btnNoEstoySeguro2 = (Button) findViewById(R.id.btnNoEstoySeguro2);
        _btnSiguientePregunta3 = (Button) findViewById(R.id.btnSiguientePregunta3);
        /*
        *
        * ---------------------PANTALLA 1-----------------------------
        * METODOS PARA LA SELECCION DE LA RESPUESTA DE LA PRREGUNTA UNO - SECCION 1
        * */

        _btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnMas.setBackgroundResource(R.drawable.seleccionar_button);
                _btnMas.setTextColor(getResources().getColor(R.color.blanco));
                btnActivoPregunta1 = true;

                //Desactivar
                _btnMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnMenos.setTextColor(getResources().getColor(R.color.negro));
                //Desactivar
                _btnIgual.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnIgual.setTextColor(getResources().getColor(R.color.negro));
                //Desactivar
                _btnNoEstoySeguro.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnIgual.setBackgroundResource(R.drawable.seleccionar_button);
                _btnIgual.setTextColor(getResources().getColor(R.color.blanco));
                btnActivoPregunta1 = true;

                //Desactivar
                _btnMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnMenos.setTextColor(getResources().getColor(R.color.negro));
                //Desactivar
                _btnMas.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnMas.setTextColor(getResources().getColor(R.color.negro));
                //Desactivar
                _btnNoEstoySeguro.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnMenos.setBackgroundResource(R.drawable.seleccionar_button);
                _btnMenos.setTextColor(getResources().getColor(R.color.blanco));
                btnActivoPregunta1 = true;

                //Desactivar
                _btnMas.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnMas.setTextColor(getResources().getColor(R.color.negro));

                //Desactivar
                _btnIgual.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnIgual.setTextColor(getResources().getColor(R.color.negro));
                //Desactivar
                _btnNoEstoySeguro.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnNoEstoySeguro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnNoEstoySeguro.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoEstoySeguro.setTextColor(getResources().getColor(R.color.blanco));
                btnActivoPregunta1 = true;

                //Desactivar
                _btnMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnMenos.setTextColor(getResources().getColor(R.color.negro));
                //Desactivar
                _btnIgual.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnIgual.setTextColor(getResources().getColor(R.color.negro));
                //Desactivar
                _btnMas.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnMas.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        //Valicion para poder seleccionar un button a la vez
        _btnSiguientePregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnActivoPregunta1 == true && cambiarTextoPregunta1 == 1){
                    desactivarBotonesPregunta1();
                    _txtIncisoRespuesta.setText("VERDURAS");
                    cambiarTextoPregunta1++;
                    btnActivoPregunta1 = false;
                }
                if (btnActivoPregunta1 == true && cambiarTextoPregunta1 == 2){
                    desactivarBotonesPregunta1();
                    _txtIncisoRespuesta.setText("ALIMENTOS GRASOS");
                    cambiarTextoPregunta1++;
                    btnActivoPregunta1 = false;
                }
                if (btnActivoPregunta1 == true && cambiarTextoPregunta1 == 3){
                    desactivarBotonesPregunta1();
                    _txtIncisoRespuesta.setText("CARNE ROJA PROCESADA");
                    cambiarTextoPregunta1++;
                    btnActivoPregunta1 = false;
                }
                if (btnActivoPregunta1 == true && cambiarTextoPregunta1 == 4){
                    desactivarBotonesPregunta1();
                    _txtIncisoRespuesta.setText("GRANOS INTEGRALES");
                    cambiarTextoPregunta1++;
                    btnActivoPregunta1 = false;
                }
                if (btnActivoPregunta1 == true && cambiarTextoPregunta1 == 5){
                    desactivarBotonesPregunta1();
                    _txtIncisoRespuesta.setText("ALIMENTOS SALADOS");
                    cambiarTextoPregunta1++;
                    btnActivoPregunta1 = false;
                }
                if (btnActivoPregunta1 == true && cambiarTextoPregunta1 == 6){
                    desactivarBotonesPregunta1();
                    _txtIncisoRespuesta.setText("AGUA");
                    cambiarTextoPregunta1++;
                    btnActivoPregunta1 = false;
                    cambiarTextoPregunta1 = 1;
                }
            }
        });
        //------------------FINAL PANTALLA 1---------------------------


        /*------------------------------- PANTALLA 3 -----------------------------
        * METODOS PARA LA SELECCION DE LA RESPUESTA DE LA PRREGUNTA UNO - SECCION 1
        * */

        _btnComerMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnComerMenos.setBackgroundResource(R.drawable.seleccionar_button);
                _btnComerMenos.setTextColor(getResources().getColor(R.color.blanco));
                btnActivoPregunta2 = true;

                //DESACTIVAR
                _btnNoComerMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoComerMenos.setTextColor(getResources().getColor(R.color.negro));
                //DESACTIVAR
                _btnNoEstoySeguro2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro2.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnNoComerMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnNoComerMenos.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoComerMenos.setTextColor(getResources().getColor(R.color.blanco));
                btnActivoPregunta2 = true;

                //DESACTIVAR
                _btnComerMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnComerMenos.setTextColor(getResources().getColor(R.color.negro));
                //DESACTIVAR
                _btnNoEstoySeguro2.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoEstoySeguro2.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnNoEstoySeguro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnNoEstoySeguro2.setBackgroundResource(R.drawable.seleccionar_button);
                _btnNoEstoySeguro2.setTextColor(getResources().getColor(R.color.blanco));
                btnActivoPregunta2 = true;

                //DESACTIVAR
                _btnNoComerMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnNoComerMenos.setTextColor(getResources().getColor(R.color.negro));
                //DESACTIVAR
                _btnComerMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
                _btnComerMenos.setTextColor(getResources().getColor(R.color.negro));
            }
        });

        _btnSiguientePregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnActivoPregunta2 == true && cambiarTextoPregunta2 == 1){
                    _txtIncisoRespuesta2.setText("GRASAS TRANS");
                    desactivarBotonesPregunta3();
                    btnActivoPregunta2 = false;
                    cambiarTextoPregunta2++;
                }
                if (btnActivoPregunta2 == true && cambiarTextoPregunta2 == 2){
                    _txtIncisoRespuesta2.setText("GRASAS SATURADAS");
                    desactivarBotonesPregunta3();
                    btnActivoPregunta2 = false;
                    cambiarTextoPregunta2++;
                }
            }
        });
        /*-------------------------------FINAL PANTALLA 3 -----------------------------*/

        //------------------------END---------------------------------

//**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA SEGUNDA PANTALLA--------------------------
        _ChBx_1_2_1 = (CheckBox) findViewById(R.id.ChBx_1_2_1);
        _ChBx_2_2_1 = (CheckBox) findViewById(R.id.ChBx_2_2_1);
        _ChBx_3_2_1 = (CheckBox) findViewById(R.id.ChBx_3_2_1);
        _ChBx_4_2_1 = (CheckBox) findViewById(R.id.ChBx_4_2_1);
        _ChBx_5_2_1 = (CheckBox) findViewById(R.id.ChBx_5_2_1);
        _btnSiguientePregunta2 = (Button) findViewById(R.id.btnSiguientePregunta2);
        _preguntaTres_seccion1 = (LinearLayout) findViewById(R.id.preguntaTres_seccion1);
        _preguntaDos_seccion1 = (LinearLayout) findViewById(R.id.preguntaDos_seccion1);
        /*----------------PANTALLA 2 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 2 - SECCION 1*/
        _btnSiguientePregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaDos_seccion1.setVisibility(View.GONE);
                _preguntaTres_seccion1.setVisibility(View.VISIBLE);
            }
        });
//------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_1.setSelected(false);
                _ChBx_3_2_1.setSelected(false);
                _ChBx_4_2_1.setSelected(false);
                _ChBx_5_2_1.setSelected(false);

                _ChBx_2_2_1.setChecked(false);
                _ChBx_3_2_1.setChecked(false);
                _ChBx_4_2_1.setChecked(false);
                _ChBx_5_2_1.setChecked(false);
            }
        });
        _ChBx_2_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_2_1.setSelected(false);
                _ChBx_3_2_1.setSelected(false);
                _ChBx_4_2_1.setSelected(false);
                _ChBx_5_2_1.setSelected(false);

                _ChBx_1_2_1.setChecked(false);
                _ChBx_3_2_1.setChecked(false);
                _ChBx_4_2_1.setChecked(false);
                _ChBx_5_2_1.setChecked(false);
            }
        });
        _ChBx_3_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_1.setSelected(false);
                _ChBx_1_2_1.setSelected(false);
                _ChBx_4_2_1.setSelected(false);
                _ChBx_5_2_1.setSelected(false);

                _ChBx_1_2_1.setChecked(false);
                _ChBx_2_2_1.setChecked(false);
                _ChBx_4_2_1.setChecked(false);
                _ChBx_5_2_1.setChecked(false);
            }
        });
        _ChBx_4_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_1.setSelected(false);
                _ChBx_3_2_1.setSelected(false);
                _ChBx_1_2_1.setSelected(false);
                _ChBx_5_2_1.setSelected(false);

                _ChBx_1_2_1.setChecked(false);
                _ChBx_2_2_1.setChecked(false);
                _ChBx_3_2_1.setChecked(false);
                _ChBx_5_2_1.setChecked(false);
            }
        });
        _ChBx_5_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_2_1.setSelected(false);
                _ChBx_3_2_1.setSelected(false);
                _ChBx_4_2_1.setSelected(false);
                _ChBx_1_2_1.setSelected(false);

                _ChBx_1_2_1.setChecked(false);
                _ChBx_2_2_1.setChecked(false);
                _ChBx_3_2_1.setChecked(false);
                _ChBx_4_2_1.setChecked(false);
            }
        });
//------------------------END---------------------------------


//**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA CUARTA PANTALLA--------------------------
        _ChBx_1_4_1 = (CheckBox) findViewById(R.id.ChBx_1_4_1);
        _ChBx_2_4_1 = (CheckBox) findViewById(R.id.ChBx_2_4_1);
        _ChBx_3_4_1 = (CheckBox) findViewById(R.id.ChBx_3_4_1);
        _ChBx_4_4_1 = (CheckBox) findViewById(R.id.ChBx_4_4_1);
        _ChBx_5_4_1 = (CheckBox) findViewById(R.id.ChBx_5_4_1);
        _btnSiguientePregunta4 = (Button) findViewById(R.id.btnSiguientePregunta4);
        _preguntaCinco_seccion1 = (LinearLayout) findViewById(R.id.preguntaCinco_seccion1);
        _preguntaCuatro_seccion1 = (LinearLayout) findViewById(R.id.preguntaCuatro_seccion1);
        /*----------------PANTALLA 4 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 4 - SECCION 1*/
        _btnSiguientePregunta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaCuatro_seccion1.setVisibility(View.GONE);
                _preguntaCinco_seccion1.setVisibility(View.VISIBLE);
            }
        });
//------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_1.setSelected(false);
                _ChBx_3_4_1.setSelected(false);
                _ChBx_4_4_1.setSelected(false);
                _ChBx_5_4_1.setSelected(false);

                _ChBx_2_4_1.setChecked(false);
                _ChBx_3_4_1.setChecked(false);
                _ChBx_4_4_1.setChecked(false);
                _ChBx_5_4_1.setChecked(false);
            }
        });
        _ChBx_2_4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_4_1.setSelected(false);
                _ChBx_3_4_1.setSelected(false);
                _ChBx_4_4_1.setSelected(false);
                _ChBx_5_4_1.setSelected(false);

                _ChBx_1_4_1.setChecked(false);
                _ChBx_3_4_1.setChecked(false);
                _ChBx_4_4_1.setChecked(false);
                _ChBx_5_4_1.setChecked(false);
            }
        });
        _ChBx_3_4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_1.setSelected(false);
                _ChBx_1_4_1.setSelected(false);
                _ChBx_4_4_1.setSelected(false);
                _ChBx_5_4_1.setSelected(false);

                _ChBx_1_4_1.setChecked(false);
                _ChBx_2_4_1.setChecked(false);
                _ChBx_4_4_1.setChecked(false);
                _ChBx_5_4_1.setChecked(false);
            }
        });
        _ChBx_4_4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_1.setSelected(false);
                _ChBx_3_4_1.setSelected(false);
                _ChBx_1_4_1.setSelected(false);
                _ChBx_5_4_1.setSelected(false);

                _ChBx_1_4_1.setChecked(false);
                _ChBx_2_4_1.setChecked(false);
                _ChBx_3_4_1.setChecked(false);
                _ChBx_5_4_1.setChecked(false);
            }
        });
        _ChBx_5_4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_4_1.setSelected(false);
                _ChBx_3_4_1.setSelected(false);
                _ChBx_4_4_1.setSelected(false);
                _ChBx_1_4_1.setSelected(false);

                _ChBx_1_4_1.setChecked(false);
                _ChBx_2_4_1.setChecked(false);
                _ChBx_3_4_1.setChecked(false);
                _ChBx_4_4_1.setChecked(false);
            }
        });
//------------------------END---------------------------------

//**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA QUINTA PANTALLA--------------------------
        _ChBx_1_5_1 = (CheckBox) findViewById(R.id.ChBx_1_5_1);
        _ChBx_2_5_1 = (CheckBox) findViewById(R.id.ChBx_2_5_1);
        _ChBx_3_5_1 = (CheckBox) findViewById(R.id.ChBx_3_5_1);
        _ChBx_4_5_1 = (CheckBox) findViewById(R.id.ChBx_4_5_1);
        _btnSiguientePregunta5 = (Button) findViewById(R.id.btnSiguientePregunta5);
        _preguntaSeis_seccion1 = (LinearLayout) findViewById(R.id.preguntaSeis_seccion1);
        /*----------------PANTALLA 5 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 5 - SECCION 1*/
        _btnSiguientePregunta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaCinco_seccion1.setVisibility(View.GONE);
                _preguntaSeis_seccion1.setVisibility(View.VISIBLE);
            }
        });
//------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_5_1.setSelected(false);
                _ChBx_3_5_1.setSelected(false);
                _ChBx_4_5_1.setSelected(false);

                _ChBx_2_5_1.setChecked(false);
                _ChBx_3_5_1.setChecked(false);
                _ChBx_4_5_1.setChecked(false);
            }
        });
        _ChBx_2_5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_5_1.setSelected(false);
                _ChBx_3_5_1.setSelected(false);
                _ChBx_4_5_1.setSelected(false);

                _ChBx_1_5_1.setChecked(false);
                _ChBx_3_5_1.setChecked(false);
                _ChBx_4_5_1.setChecked(false);
            }
        });
        _ChBx_3_5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_5_1.setSelected(false);
                _ChBx_1_5_1.setSelected(false);
                _ChBx_4_5_1.setSelected(false);

                _ChBx_1_5_1.setChecked(false);
                _ChBx_2_5_1.setChecked(false);
                _ChBx_4_5_1.setChecked(false);
            }
        });
        _ChBx_4_5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//------------------------END---------------------------------

//**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA SEXTA PANTALLA--------------------------
        _ChBx_1_6_1 = (CheckBox) findViewById(R.id.ChBx_1_6_1);
        _ChBx_2_6_1 = (CheckBox) findViewById(R.id.ChBx_2_6_1);
        _ChBx_3_6_1 = (CheckBox) findViewById(R.id.ChBx_3_6_1);
        _ChBx_4_6_1 = (CheckBox) findViewById(R.id.ChBx_4_6_1);
        _ChBx_5_6_1 = (CheckBox) findViewById(R.id.ChBx_5_6_1);
        _btnSiguientePregunta6 = (Button) findViewById(R.id.btnSiguientePregunta6);
        /*----------------PANTALLA 6 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 6 - SECCION 1*/
        _btnSiguientePregunta6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaSeis_seccion1.setVisibility(View.GONE);
                _preguntaSeptima_seccion1.setVisibility(View.VISIBLE);
            }
        });
//------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_1.setSelected(false);
                _ChBx_3_6_1.setSelected(false);
                _ChBx_4_6_1.setSelected(false);
                _ChBx_5_6_1.setSelected(false);

                _ChBx_2_6_1.setChecked(false);
                _ChBx_3_6_1.setChecked(false);
                _ChBx_4_6_1.setChecked(false);
                _ChBx_5_6_1.setChecked(false);
            }
        });
        _ChBx_2_6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_6_1.setSelected(false);
                _ChBx_3_6_1.setSelected(false);
                _ChBx_4_6_1.setSelected(false);
                _ChBx_5_6_1.setSelected(false);

                _ChBx_1_6_1.setChecked(false);
                _ChBx_3_6_1.setChecked(false);
                _ChBx_4_6_1.setChecked(false);
                _ChBx_5_6_1.setChecked(false);
            }
        });
        _ChBx_3_6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_1.setSelected(false);
                _ChBx_1_6_1.setSelected(false);
                _ChBx_4_6_1.setSelected(false);
                _ChBx_5_6_1.setSelected(false);

                _ChBx_1_6_1.setChecked(false);
                _ChBx_2_6_1.setChecked(false);
                _ChBx_4_6_1.setChecked(false);
                _ChBx_5_6_1.setChecked(false);
            }
        });
        _ChBx_4_6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_1.setSelected(false);
                _ChBx_3_6_1.setSelected(false);
                _ChBx_1_6_1.setSelected(false);
                _ChBx_5_6_1.setSelected(false);

                _ChBx_1_6_1.setChecked(false);
                _ChBx_2_6_1.setChecked(false);
                _ChBx_3_6_1.setChecked(false);
                _ChBx_5_6_1.setChecked(false);
            }
        });
        _ChBx_5_6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_6_1.setSelected(false);
                _ChBx_3_6_1.setSelected(false);
                _ChBx_4_6_1.setSelected(false);
                _ChBx_1_6_1.setSelected(false);

                _ChBx_1_6_1.setChecked(false);
                _ChBx_2_6_1.setChecked(false);
                _ChBx_3_6_1.setChecked(false);
                _ChBx_4_6_1.setChecked(false);
            }
        });
//------------------------END---------------------------------

//**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA SEPTIMA PANTALLA--------------------------
        _ChBx_1_7_1 = (CheckBox) findViewById(R.id.ChBx_1_7_1);
        _ChBx_2_7_1 = (CheckBox) findViewById(R.id.ChBx_2_7_1);
        _ChBx_3_7_1 = (CheckBox) findViewById(R.id.ChBx_3_7_1);
        _ChBx_4_7_1 = (CheckBox) findViewById(R.id.ChBx_4_7_1);
        _btnSiguientePregunta7 = (Button) findViewById(R.id.btnSiguientePregunta7);
        _preguntaSeptima_seccion1 = (LinearLayout) findViewById(R.id.preguntaSeptima_seccion1);
        /*----------------PANTALLA 7 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 7 - SECCION 1*/
        _btnSiguientePregunta7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaSeptima_seccion1.setVisibility(View.GONE);
                _preguntaOctava_seccion1.setVisibility(View.VISIBLE);
            }
        });
//------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_7_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_1.setSelected(false);
                _ChBx_3_7_1.setSelected(false);
                _ChBx_4_7_1.setSelected(false);

                _ChBx_2_7_1.setChecked(false);
                _ChBx_3_7_1.setChecked(false);
                _ChBx_4_7_1.setChecked(false);
            }
        });
        _ChBx_2_7_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_7_1.setSelected(false);
                _ChBx_3_7_1.setSelected(false);
                _ChBx_4_7_1.setSelected(false);

                _ChBx_1_7_1.setChecked(false);
                _ChBx_3_7_1.setChecked(false);
                _ChBx_4_7_1.setChecked(false);
            }
        });
        _ChBx_3_7_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_1.setSelected(false);
                _ChBx_1_7_1.setSelected(false);
                _ChBx_4_7_1.setSelected(false);

                _ChBx_1_7_1.setChecked(false);
                _ChBx_2_7_1.setChecked(false);
                _ChBx_4_7_1.setChecked(false);
            }
        });
        _ChBx_4_7_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_7_1.setSelected(false);
                _ChBx_3_7_1.setSelected(false);
                _ChBx_1_7_1.setSelected(false);

                _ChBx_1_7_1.setChecked(false);
                _ChBx_2_7_1.setChecked(false);
                _ChBx_3_7_1.setChecked(false);
            }
        });
//------------------------END---------------------------------

//**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA OCTAVA PANTALLA--------------------------
        _ChBx_1_8_1 = (CheckBox) findViewById(R.id.ChBx_1_8_1);
        _ChBx_2_8_1 = (CheckBox) findViewById(R.id.ChBx_2_8_1);
        _ChBx_3_8_1 = (CheckBox) findViewById(R.id.ChBx_3_8_1);
        _ChBx_4_8_1 = (CheckBox) findViewById(R.id.ChBx_4_8_1);
        _ChBx_5_8_1 = (CheckBox) findViewById(R.id.ChBx_5_8_1);
        _btnSiguientePregunta8 = (Button) findViewById(R.id.btnSiguientePregunta8);
        _preguntaOctava_seccion1 = (LinearLayout) findViewById(R.id.preguntaOctava_seccion1);
        /*----------------PANTALLA 8 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 8 - SECCION 1*/
        _btnSiguientePregunta8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _preguntaOctava_seccion1.setVisibility(View.GONE);
                _preguntaNovena_seccion1.setVisibility(View.VISIBLE);
            }
        });
//------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_1.setSelected(false);
                _ChBx_3_8_1.setSelected(false);
                _ChBx_4_8_1.setSelected(false);
                _ChBx_5_8_1.setSelected(false);

                _ChBx_2_8_1.setChecked(false);
                _ChBx_3_8_1.setChecked(false);
                _ChBx_4_8_1.setChecked(false);
                _ChBx_5_8_1.setChecked(false);
            }
        });
        _ChBx_2_8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_8_1.setSelected(false);
                _ChBx_3_8_1.setSelected(false);
                _ChBx_4_8_1.setSelected(false);
                _ChBx_5_8_1.setSelected(false);

                _ChBx_1_8_1.setChecked(false);
                _ChBx_3_8_1.setChecked(false);
                _ChBx_4_8_1.setChecked(false);
                _ChBx_5_8_1.setChecked(false);
            }
        });
        _ChBx_3_8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_1.setSelected(false);
                _ChBx_1_8_1.setSelected(false);
                _ChBx_4_8_1.setSelected(false);
                _ChBx_5_8_1.setSelected(false);

                _ChBx_1_8_1.setChecked(false);
                _ChBx_2_8_1.setChecked(false);
                _ChBx_4_8_1.setChecked(false);
                _ChBx_5_8_1.setChecked(false);
            }
        });
        _ChBx_4_8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_1.setSelected(false);
                _ChBx_3_8_1.setSelected(false);
                _ChBx_1_8_1.setSelected(false);
                _ChBx_5_8_1.setSelected(false);

                _ChBx_1_8_1.setChecked(false);
                _ChBx_2_8_1.setChecked(false);
                _ChBx_3_8_1.setChecked(false);
                _ChBx_5_8_1.setChecked(false);
            }
        });
        _ChBx_5_8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_8_1.setSelected(false);
                _ChBx_3_8_1.setSelected(false);
                _ChBx_4_8_1.setSelected(false);
                _ChBx_1_8_1.setSelected(false);

                _ChBx_1_8_1.setChecked(false);
                _ChBx_2_8_1.setChecked(false);
                _ChBx_3_8_1.setChecked(false);
                _ChBx_4_8_1.setChecked(false);
            }
        });
//------------------------END---------------------------------

//**************************************************************************************
//**************************************************************************************
//**************************************************************************************



//------------------------COMPONENTES DE LA NOVENA PANTALLA--------------------------
        _ChBx_1_9_1 = (CheckBox) findViewById(R.id.ChBx_1_9_1);
        _ChBx_2_9_1 = (CheckBox) findViewById(R.id.ChBx_2_9_1);
        _ChBx_3_9_1 = (CheckBox) findViewById(R.id.ChBx_3_9_1);
        _ChBx_4_9_1 = (CheckBox) findViewById(R.id.ChBx_4_9_1);
        _btnSiguientePregunta9 = (Button) findViewById(R.id.btnSiguientePregunta9);
        _preguntaNovena_seccion1 = (LinearLayout) findViewById(R.id.preguntaNovena_seccion1);
        /*----------------PANTALLA 9 ------------
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGUNTA 9 - SECCION 1*/
        _btnSiguientePregunta9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registro_cuestionario_seccion1.this,registro_cuestionario_seccion2.class));
            }
        });
//------------------------METODOS DE LOS BOTONES DE SELECCION------------
        _ChBx_1_9_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_1.setSelected(false);
                _ChBx_3_9_1.setSelected(false);
                _ChBx_4_9_1.setSelected(false);

                _ChBx_2_9_1.setChecked(false);
                _ChBx_3_9_1.setChecked(false);
                _ChBx_4_9_1.setChecked(false);
            }
        });
        _ChBx_2_9_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_1_9_1.setSelected(false);
                _ChBx_3_9_1.setSelected(false);
                _ChBx_4_9_1.setSelected(false);

                _ChBx_1_9_1.setChecked(false);
                _ChBx_3_9_1.setChecked(false);
                _ChBx_4_9_1.setChecked(false);
            }
        });
        _ChBx_3_9_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_1.setSelected(false);
                _ChBx_1_9_1.setSelected(false);
                _ChBx_4_9_1.setSelected(false);

                _ChBx_1_9_1.setChecked(false);
                _ChBx_2_9_1.setChecked(false);
                _ChBx_4_9_1.setChecked(false);
            }
        });
        _ChBx_4_9_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ChBx_2_9_1.setSelected(false);
                _ChBx_3_9_1.setSelected(false);
                _ChBx_1_9_1.setSelected(false);

                _ChBx_1_9_1.setChecked(false);
                _ChBx_2_9_1.setChecked(false);
                _ChBx_3_9_1.setChecked(false);
            }
        });
//------------------------END---------------------------------

    }

    public void desactivarBotonesPregunta3(){
        //DESACTIVAR
        _btnComerMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
        _btnComerMenos.setTextColor(getResources().getColor(R.color.negro));
        //DESACTIVAR
        _btnNoComerMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
        _btnNoComerMenos.setTextColor(getResources().getColor(R.color.negro));
        //DESACTIVAR
        _btnComerMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
        _btnComerMenos.setTextColor(getResources().getColor(R.color.negro));
    }
    public void desactivarBotonesPregunta1(){
        //Desactivar
        _btnMenos.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
        _btnMenos.setTextColor(getResources().getColor(R.color.negro));
        //Desactivar
        _btnIgual.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
        _btnIgual.setTextColor(getResources().getColor(R.color.negro));
        //Desactivar
        _btnMas.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
        _btnMas.setTextColor(getResources().getColor(R.color.negro));
        //Desactivar
        _btnNoEstoySeguro.setBackgroundResource(R.drawable.custom_button_cuestionario_respuesta);
        _btnNoEstoySeguro.setTextColor(getResources().getColor(R.color.negro));
    }

}

