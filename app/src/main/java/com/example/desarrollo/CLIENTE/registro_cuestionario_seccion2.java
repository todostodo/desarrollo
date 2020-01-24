package com.example.desarrollo.CLIENTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.desarrollo.R;

public class registro_cuestionario_seccion2 extends AppCompatActivity {

    private Button _btnSiguientePregunta1_2, _btnBajoAzucar, _btnNoEstoySeguro1_2, _btnAltoAzucar, _btnAltoSal, _btnBajoSal, _btnNoEstoySeguro2_2;
    private TextView _txtIncisoRespuestaPregunta1_2;
    private boolean verifica = false;
    private int contador = 1;
    private LinearLayout _preguntaUno_seccion2, _preguntaDos_seccion2;
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
         * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGINTA 1 - SECCION 2*/

        _btnSiguientePregunta1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verifica==true ){
                    contador ++;
                    verifica = false;
                    _btnBajoAzucar.setTextColor(getResources().getColor(R.color.blanco));
                    _btnNoEstoySeguro1_2.setTextColor(getResources().getColor(R.color.blanco));
                    _btnAltoAzucar.setTextColor(getResources().getColor(R.color.blanco));

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


        _preguntaDos_seccion2 = (LinearLayout) findViewById(R.id.preguntaDos_seccion2);

    }

}
