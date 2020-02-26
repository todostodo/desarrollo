package com.example.desarrollo.CLIENTE.Alimentos.Frutas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.desarrollo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


public class frutas_select_activity extends AppCompatActivity {

    Context context = this;
    Button
            _btm1_4,
            _btm1_2,
            _btm3_4,
            _btmGuardarFruta;

    ImageButton _btmCerrarSelectFrutas;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frutas_select_activity);



        final String name = getIntent().getExtras().getString("fruta_nombre");
        String vitamina = getIntent().getExtras().getString("fruta_vitamina");
        String descripcion = getIntent().getExtras().getString("fruta_descripcion");
        String beneficio = getIntent().getExtras().getString("fruta_beneficio");
        String imagen = getIntent().getExtras().getString("fruta_imagen");
        String backgroud = getIntent().getExtras().getString("fruta_background");

        final TextView f_nombre = (TextView) findViewById(R.id.fruta_nombre);
        TextView f_vitamina = (TextView) findViewById(R.id.fruta_vitamina);
        TextView f_descripcion = (TextView) findViewById(R.id.fruta_descripcion);
        TextView f_beneficio = (TextView) findViewById(R.id.fruta_beneficio);
        ImageView f_imagen = (ImageView) findViewById(R.id.fruta_imagen);

        f_nombre.setText(name);
        f_vitamina.setText(vitamina);
        f_descripcion.setText(descripcion);
        f_beneficio.setText(beneficio);

        int drawableResourceId = this.getResources().getIdentifier(imagen, "drawable", this.getPackageName());

        Glide.with(this).load(drawableResourceId).into(f_imagen);

        //Guardar Fruta en archivo JSON


        /*
        VALIDACIONES
         */
        _btm1_4 = (Button) findViewById(R.id.btm1_4);
        _btm1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btm1_4.setBackgroundResource(R.drawable.custom_buttom_cantidad_select);

                //DISABLE
                _btm1_2.setBackgroundResource(R.drawable.custom_bottom_background_gris);
                _btm3_4.setBackgroundResource(R.drawable.custom_bottom_background_gris);
            }
        });
        _btm1_2 = (Button) findViewById(R.id.btm1_2);
        _btm1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btm1_2.setBackgroundResource(R.drawable.custom_buttom_cantidad_select);

                //DISABBLE
                _btm1_4.setBackgroundResource(R.drawable.custom_bottom_background_gris);
                _btm3_4.setBackgroundResource(R.drawable.custom_bottom_background_gris);

            }
        });
        _btm3_4 = (Button) findViewById(R.id.btm3_4);
        _btm3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btm3_4.setBackgroundResource(R.drawable.custom_buttom_cantidad_select);

                //DISABBLE
                _btm1_4.setBackgroundResource(R.drawable.custom_bottom_background_gris);
                _btm1_2.setBackgroundResource(R.drawable.custom_bottom_background_gris);
            }
        });

        //Salir del activity
        _btmCerrarSelectFrutas = (ImageButton) findViewById(R.id.btmCerrarSelectFrutas);
        _btmCerrarSelectFrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
