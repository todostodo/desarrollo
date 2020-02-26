package com.example.desarrollo.CLIENTE.Encuestas;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.desarrollo.R;


public class registro_autoeficacia extends AppCompatActivity {

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
        _pregunta1_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta1_Autoeficacia);
        _pregunta2_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta2_Autoeficacia);
        _pregunta3_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta3_Autoeficacia);
        _pregunta4_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta4_Autoeficacia);
        _pregunta5_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta5_Autoeficacia);
        _pregunta6_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta6_Autoeficacia);
        _pregunta7_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta7_Autoeficacia);
        _pregunta8_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta8_Autoeficacia);
        _pregunta9_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta9_Autoeficacia);
        _pregunta10_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta10_Autoeficacia);
        _pregunta11_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta11_Autoeficacia);
        _pregunta12_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta12_Autoeficacia);
        _pregunta13_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta13_Autoeficacia);
        _pregunta14_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta14_Autoeficacia);
        _pregunta15_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta15_Autoeficacia);
        _pregunta16_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta16_Autoeficacia);
        _pregunta17_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta17_Autoeficacia);
        _pregunta18_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta18_Autoeficacia);
        _pregunta19_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta19_Autoeficacia);
        _pregunta20_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta20_Autoeficacia);
        _pregunta21_Autoeficacia = (LinearLayout) findViewById(R.id.pregunta21_Autoeficacia);


        /*
        * -------------------------PANTALLA UNO--------------------------------------
        * PREGUNTA 1
        * */
        _btnSiguientePregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (siguientePregunta == 1){

                }
                _pregunta1_Autoeficacia.setVisibility(View.GONE);
                _pregunta2_Autoeficacia.setVisibility(View.VISIBLE);
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

        /*
         * -------------------------PANTALLA DOS--------------------------------------
         * PREGUNTA 2
         * */
        _btnSiguientePregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta2_Autoeficacia.setVisibility(View.GONE);
                _pregunta3_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA TRES--------------------------------------
         * PREGUNTA 3
         * */
        _btnSiguientePregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta3_Autoeficacia.setVisibility(View.GONE);
                _pregunta4_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA CUATRO--------------------------------------
         * PREGUNTA 4
         * */
        _btnSiguientePregunta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta4_Autoeficacia.setVisibility(View.GONE);
                _pregunta5_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA CINCO--------------------------------------
         * PREGUNTA 5
         * */
        _btnSiguientePregunta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta5_Autoeficacia.setVisibility(View.GONE);
                _pregunta6_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA SEIS--------------------------------------
         * PREGUNTA 6
         * */
        _btnSiguientePregunta6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta6_Autoeficacia.setVisibility(View.GONE);
                _pregunta7_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA SIETE--------------------------------------
         * PREGUNTA 7
         * */
        _btnSiguientePregunta7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta7_Autoeficacia.setVisibility(View.GONE);
                _pregunta8_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA OCHO--------------------------------------
         * PREGUNTA 8
         * */
        _btnSiguientePregunta8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta8_Autoeficacia.setVisibility(View.GONE);
                _pregunta9_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA NUEVE--------------------------------------
         * PREGUNTA 9
         * */
        _btnSiguientePregunta9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta9_Autoeficacia.setVisibility(View.GONE);
                _pregunta10_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA DIEZ--------------------------------------
         * PREGUNTA 10
         * */
        _btnSiguientePregunta10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta10_Autoeficacia.setVisibility(View.GONE);
                _pregunta11_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA ONCE--------------------------------------
         * PREGUNTA 11
         * */
        _btnSiguientePregunta11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta11_Autoeficacia.setVisibility(View.GONE);
                _pregunta12_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA DOCE--------------------------------------
         * PREGUNTA 12
         * */
        _btnSiguientePregunta12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta12_Autoeficacia.setVisibility(View.GONE);
                _pregunta13_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA TRESE--------------------------------------
         * PREGUNTA 13
         * */
        _btnSiguientePregunta13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta13_Autoeficacia.setVisibility(View.GONE);
                _pregunta14_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA CATORSE--------------------------------------
         * PREGUNTA 14
         * */
        _btnSiguientePregunta14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta14_Autoeficacia.setVisibility(View.GONE);
                _pregunta15_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA QUINSE--------------------------------------
         * PREGUNTA 15
         * */
        _btnSiguientePregunta15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta15_Autoeficacia.setVisibility(View.GONE);
                _pregunta16_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA DIESISEIS--------------------------------------
         * PREGUNTA 16
         * */
        _btnSiguientePregunta16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta16_Autoeficacia.setVisibility(View.GONE);
                _pregunta17_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA DIESISIETE--------------------------------------
         * PREGUNTA 17
         * */
        _btnSiguientePregunta17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta17_Autoeficacia.setVisibility(View.GONE);
                _pregunta18_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA DIESIOCHO--------------------------------------
         * PREGUNTA 18
         * */
        _btnSiguientePregunta18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta18_Autoeficacia.setVisibility(View.GONE);
                _pregunta19_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA DIESINUEVE--------------------------------------
         * PREGUNTA 19
         * */
        _btnSiguientePregunta19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta19_Autoeficacia.setVisibility(View.GONE);
                _pregunta20_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA VEINTE--------------------------------------
         * PREGUNTA 20
         * */
        _btnSiguientePregunta20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pregunta20_Autoeficacia.setVisibility(View.GONE);
                _pregunta21_Autoeficacia.setVisibility(View.VISIBLE);
            }
        });
        /*
         * -------------------------PANTALLA VEINTIUNO--------------------------------------
         * PREGUNTA 21
         * */
        _btnSiguientePregunta21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
