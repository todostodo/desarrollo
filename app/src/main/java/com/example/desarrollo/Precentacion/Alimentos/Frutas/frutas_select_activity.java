package com.example.desarrollo.Precentacion.Alimentos.Frutas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.desarrollo.R;


public class frutas_select_activity extends AppCompatActivity {

    private TextView f_nombre;
    private TextView f_descripcion;
    private TextView f_recomendacion;
    private ImageView f_imagen;
    private RelativeLayout f_fondo;
    private Button _btnRegistrarFruta;
    private Dialog reaccionHijoDialog;
    private TextView _txtCantidadConsumo;

    private ImageButton _btmCerrarSelectFrutas;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frutas_select_activity);

        init();
        cargarDatosFruta();

        //Salir del activity
        _btmCerrarSelectFrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Registrar Frutas o verduras
        _btnRegistrarFruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (_txtCantidadConsumo.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Ingrese la cantidad de consumo", Toast.LENGTH_SHORT).show();
                }else{

                    reaccionHijoDialog = new Dialog(frutas_select_activity.this);
                    reaccionHijoDialog.setCanceledOnTouchOutside(false);
                    reaccionHijoDialog.setContentView(R.layout.reaccion_consumo_dialog);
                    reaccionHijoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    reaccionHijoDialog.show();

                }
            }
        });
    }

    public void cargarDatosFruta() {

        f_nombre.setText(getIntent().getExtras().getString("fruta_nombre"));
        f_descripcion.setText(getIntent().getExtras().getString("fruta_descripcion"));
        f_recomendacion.setText(getIntent().getExtras().getString("fruta_recomendacion"));
        f_fondo.setBackgroundColor(Color.parseColor(getIntent().getExtras().getString("fruta_fondo")));

        int drawableResourceId = this.getResources().getIdentifier(getIntent().getExtras().getString("fruta_imagen"), "drawable", this.getPackageName());

        Glide.with(this).load(drawableResourceId).into(f_imagen);
    }

    public void init() {
        f_nombre = (TextView) findViewById(R.id.fruta_nombre);
        f_descripcion = (TextView) findViewById(R.id.fruta_descripcion);
        f_recomendacion = (TextView) findViewById(R.id.fruta_recomendacion);
        f_imagen = (ImageView) findViewById(R.id.fruta_imagen);
        f_fondo = (RelativeLayout) findViewById(R.id.backgroundSelectFruta);
        _btmCerrarSelectFrutas = (ImageButton) findViewById(R.id.btmCerrarSelectFrutas);
        _btnRegistrarFruta = (Button) findViewById(R.id.btnRegistrarFruta);
        _txtCantidadConsumo = (TextView) findViewById(R.id.txtCantidadConsumo);
    }
}
