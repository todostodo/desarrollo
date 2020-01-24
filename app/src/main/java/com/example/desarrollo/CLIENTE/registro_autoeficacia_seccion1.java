package com.example.desarrollo.CLIENTE;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.desarrollo.R;


public class registro_autoeficacia_seccion1 extends AppCompatActivity {

    int siguientePregunta = 1;

    LinearLayout _pregunta1_Autoeficacia, _pregunta2_Autoeficacia, _pregunta3_Autoeficacia, _pregunta4_Autoeficacia, _pregunta5_Autoeficacia, _pregunta6_Autoeficacia,
    _pregunta7_Autoeficacia, _pregunta8_Autoeficacia, _pregunta9_Autoeficacia, _pregunta10_Autoeficacia, _pregunta11_Autoeficacia, _pregunta12_Autoeficacia, _pregunta13_Autoeficacia,
    _pregunta14_Autoeficacia, _pregunta15_Autoeficacia, _pregunta16_Autoeficacia, _pregunta17_Autoeficacia, _pregunta18_Autoeficacia, _pregunta19_Autoeficacia, _pregunta20_Autoeficacia,
    _pregunta21_Autoeficacia;

    SeekBar _seekBar1, _seeckBar2, _seeckBar3, _seeckBar4, _seeckBar5, _seeckBar6, _seeckBar7, _seeckBar8, _seeckBar9, _seeckBar10, _seeckBar11, _seeckBar12, _seeckBar13
    , _seeckBar14, _seeckBar15, _seeckBar16, _seeckBar17, _seeckBar18, _seeckBar19, _seeckBar20, _seeckBar21;

    Button _btnSiguientePregunta1, _btnSiguientePregunta2,_btnSiguientePregunta3,_btnSiguientePregunta4,_btnSiguientePregunta5,_btnSiguientePregunta6,_btnSiguientePregunta7,
            _btnSiguientePregunta8,_btnSiguientePregunta9,_btnSiguientePregunta10,_btnSiguientePregunta11,_btnSiguientePregunta12,_btnSiguientePregunta13,_btnSiguientePregunta14,
            _btnSiguientePregunta15,_btnSiguientePregunta16,_btnSiguientePregunta17,_btnSiguientePregunta18,_btnSiguientePregunta19,_btnSiguientePregunta20,_btnSiguientePregunta21;

    TextView _txtSeekBar1, _autoeficacia_preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_autoeficacia);

        _seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        _txtSeekBar1 = (TextView) findViewById(R.id.txtSeekBar1);
        _autoeficacia_preguntas = (TextView) findViewById(R.id.autoeficacia_preguntas);
        _btnSiguientePregunta1 = (Button) findViewById(R.id.btnSiguientePregunta1);


        /*
        * -------------------------PANTALLA UNO--------------------------------------
        * PREGUNTA 1
        * */
        _btnSiguientePregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (siguientePregunta == 1){

                }
            }
        });

        _seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _txtSeekBar1.setText(String.format("%d",progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
