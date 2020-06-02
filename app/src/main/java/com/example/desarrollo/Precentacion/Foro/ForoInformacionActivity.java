package com.example.desarrollo.Precentacion.Foro;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.desarrollo.R;

public class ForoInformacionActivity extends AppCompatActivity {

    private ImageView imgFondoRecetasForo;
    private RelativeLayout btmCerrarInformacionReceta;
    private TextView txtTituloReceta, txtTiempoPreparacionReceta, txtIngredientesReceta, txtPreparacionRecera;
    private LinearLayout layoutTimeReceta;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foro_recetas_informacion);
        init();
        cargarDatosInformacionRecetas();

        btmCerrarInformacionReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void cargarDatosInformacionRecetas() {

        String timeReceta = getIntent().getExtras().getString("receta_tiempo");
        txtTituloReceta.setText(getIntent().getExtras().getString("receta_titulo"));
        txtIngredientesReceta.setText(getIntent().getExtras().getString("receta_ingredientes"));
        txtPreparacionRecera.setText(getIntent().getExtras().getString("receta_preparacion"));

        if (!timeReceta.equals(""))
            txtTiempoPreparacionReceta.setText(timeReceta);
        else
            layoutTimeReceta.setVisibility(View.GONE);

        int drawableResourceId = this.getResources().getIdentifier(getIntent().getExtras().getString("receta_background"), "drawable", this.getPackageName());

        Glide.with(this).load(drawableResourceId).into(imgFondoRecetasForo);

    }

    private void init() {
        imgFondoRecetasForo = (ImageView) findViewById(R.id.imgFondoRecetasForo);
        btmCerrarInformacionReceta = (RelativeLayout) findViewById(R.id.btmCerrarInformacionReceta);
        txtTituloReceta = (TextView) findViewById(R.id.txtTituloReceta);
        txtTiempoPreparacionReceta = (TextView) findViewById(R.id.txtTiempoPreparacionReceta);
        txtIngredientesReceta = (TextView) findViewById(R.id.txtIngredientesReceta);
        txtPreparacionRecera = (TextView) findViewById(R.id.txtPreparacionRecera);
        layoutTimeReceta = (LinearLayout) findViewById(R.id.layoutTimeReceta);
    }
}
