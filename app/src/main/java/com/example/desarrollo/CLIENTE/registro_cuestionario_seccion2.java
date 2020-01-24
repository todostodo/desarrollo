package com.example.desarrollo.CLIENTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.desarrollo.R;

public class registro_cuestionario_seccion2 extends AppCompatActivity {

    private Button _btnSiguientePregunta1_2, _btnBajoAzucar, _btnNoEstoySeguro1_2, _btnAltoAzucar, _btnAltoSal, _btnBajoSal, _btnNoEstoySeguro2_2;
    private TextView _txtIncisoRespuestaPregunta1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuestionario_seccion2);
        _btnSiguientePregunta1_2 = (Button) findViewById(R.id.btnSiguientePregunta1_2);
        _btnBajoAzucar = (Button) findViewById(R.id.btnBajoAzucar);
        _btnNoEstoySeguro1_2 = (Button) findViewById(R.id.btnNoEstoySeguro1_2);
        _btnAltoAzucar = (Button) findViewById(R.id.btnAltoAzucar);
        _txtIncisoRespuestaPregunta1 = (TextView) findViewById(R.id.txtIncisoRespuestaPregunta1_2);

        /*----------------PANTALLA 1 ------------
        * METODO PARA LA SELECCION DE LA RESPUESTA DE LA PREGINTA 1 - SECCION 2*/

        _btnSiguientePregunta1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        //------------------------END---------------------------------




    }

}
