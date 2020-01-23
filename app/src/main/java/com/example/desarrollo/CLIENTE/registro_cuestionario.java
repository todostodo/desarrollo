package com.example.desarrollo.CLIENTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.desarrollo.R;

public class registro_cuestionario extends AppCompatActivity {

    /*
    --------PATALLA 1*-------/
    VARIABLES
     */
    private Button _btnMas, _btnMenos, _btnIgual, _btnNoEstoySeguro, _btnSiguientePregunta1;
    private TextView _txtIncisoRespuesta;
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
