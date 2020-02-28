package com.example.desarrollo.Precentacion.Alimentos.Frutas;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.Precentacion.EpicMensajes.EpicChildr;

import com.example.desarrollo.R;



public class RegistrarFrutas extends AppCompatActivity {

    Button _btmRegistrasFrutas;

    Context context = this;


    private static final String TAG = "RegistrarFrutas";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frutas_registro_activity);


        _btmRegistrasFrutas = (Button) findViewById(R.id.btmRegistrasFrutas);
        _btmRegistrasFrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EpicChildr.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
