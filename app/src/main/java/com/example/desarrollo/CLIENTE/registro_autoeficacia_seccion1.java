package com.example.desarrollo.CLIENTE;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.desarrollo.R;


public class registro_autoeficacia_seccion1 extends AppCompatActivity {

    SeekBar _seekBar1;
    TextView _txtSeekBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_autoeficacia);

        _seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        _txtSeekBar1 = (TextView) findViewById(R.id.txtSeekBar1);

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
