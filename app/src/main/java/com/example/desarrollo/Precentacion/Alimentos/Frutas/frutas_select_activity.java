package com.example.desarrollo.Precentacion.Alimentos.Frutas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.desarrollo.R;

import org.w3c.dom.Text;


public class frutas_select_activity extends AppCompatActivity {

    private TextView
            f_nombre,
            f_descripcion,
            f_recomendacion,
            f_recomendacionDos,
            f_frase,
            f_ventaja,
            f_avisoTitulo,
            f_aviso;

    private ImageView f_imagen;
    private RelativeLayout f_fondo;
    private Button _btnRegistrarFruta;
    private Dialog reaccionHijoDialog;
    private TextView _txtCantidadConsumo;
    private LinearLayout _btnExpandirFrutas;
    private LinearLayout _frutasExpandirSelect;
    private LinearLayout _fruta_linearRecomendacionDos;
    private RelativeLayout _fruta_relativeAviso;
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

                if (_txtCantidadConsumo.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Ingrese la cantidad de consumo", Toast.LENGTH_SHORT).show();
                } else {

                    reaccionHijoDialog = new Dialog(frutas_select_activity.this);
                    reaccionHijoDialog.setCanceledOnTouchOutside(false);
                    reaccionHijoDialog.setContentView(R.layout.reaccion_consumo_dialog);
                    reaccionHijoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    reaccionHijoDialog.show();

                }
            }
        });

        //Expandir información
        _btnExpandirFrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnExpandirFrutas.setVisibility(View.GONE);
                _frutasExpandirSelect.setVisibility(View.VISIBLE);
            }
        });
    }

    public void cargarDatosFruta() {

        String complemento = "Alimento para";
        String recomendacionDos;
        String ventaja;
        String avisoTitulo;

        f_nombre.setText(getIntent().getExtras().getString("fruta_nombre"));
        f_descripcion.setText(getIntent().getExtras().getString("fruta_descripcion"));
        f_recomendacion.setText(getIntent().getExtras().getString("fruta_recomendacion"));
        f_recomendacionDos.setText(getIntent().getExtras().getString("fruta_recomendacionDos"));
        ventaja = getIntent().getExtras().getString("fruta_ventaja");
        avisoTitulo = getIntent().getExtras().getString("fruta_avisoTitulo");
        f_fondo.setBackgroundColor(Color.parseColor(getIntent().getExtras().getString("fruta_fondo")));
        f_frase.setText(getIntent().getExtras().getString("fruta_frase"));
        recomendacionDos = getIntent().getExtras().getString("fruta_recomendacionDos");

        if (ventaja.equals("corazón") || ventaja.equals("intestino") || ventaja.equals("aparato urinario") || ventaja.equals("aparato digestivo") || ventaja.equals("estómago") || ventaja.equals("sistema nervioso") || ventaja.equals("aparato reproductor")) {
            f_ventaja.setText(complemento + " el " + ventaja);
        } else {
            if (ventaja.equals("infecciones") || ventaja.equals("arterias")) {
                f_ventaja.setText(complemento + " las " + ventaja);
            } else {
                if (ventaja.equals("sangre") || ventaja.equals("piel")) {
                    f_ventaja.setText(complemento + " la " + ventaja);
                } else {
                    if (ventaja.equals("ojos")) {
                        f_ventaja.setText(complemento + " los " + ventaja);
                    } else {
                        if (ventaja.equals("")) {
                            f_ventaja.setText("");
                        }
                    }
                }
            }
        }
        if (recomendacionDos.equals("")) {
            _fruta_linearRecomendacionDos.setVisibility(View.GONE);
        }
        if (!avisoTitulo.equals("")){
            f_avisoTitulo.setText(avisoTitulo);
            f_aviso.setText(Html.fromHtml(getIntent().getExtras().getString("fruta_aviso")));
            _fruta_relativeAviso.setVisibility(View.VISIBLE);
        }

        int drawableResourceId = this.getResources().getIdentifier(getIntent().getExtras().getString("fruta_imagen"), "drawable", this.getPackageName());

        Glide.with(this).load(drawableResourceId).into(f_imagen);
    }

    public void init() {
        f_nombre = (TextView) findViewById(R.id.fruta_nombre);
        f_descripcion = (TextView) findViewById(R.id.fruta_descripcion);
        f_recomendacion = (TextView) findViewById(R.id.fruta_recomendacion);
        f_recomendacionDos = (TextView) findViewById(R.id.fruta_recomendacionDos);
        f_frase = (TextView) findViewById(R.id.fruta_frase);
        f_ventaja = (TextView) findViewById(R.id.fruta_ventaja);
        f_avisoTitulo = (TextView) findViewById(R.id.fruta_avisoTitulo);
        f_aviso = (TextView) findViewById(R.id.fruta_aviso);
        f_imagen = (ImageView) findViewById(R.id.fruta_imagen);
        f_fondo = (RelativeLayout) findViewById(R.id.backgroundSelectFruta);
        _btmCerrarSelectFrutas = (ImageButton) findViewById(R.id.btmCerrarSelectFrutas);
        _btnRegistrarFruta = (Button) findViewById(R.id.btnRegistrarFruta);
        _txtCantidadConsumo = (TextView) findViewById(R.id.txtCantidadConsumo);
        _btnExpandirFrutas = (LinearLayout) findViewById(R.id.btnExpandirFrutas);
        _frutasExpandirSelect = (LinearLayout) findViewById(R.id.frutasExpandirSelect);
        _fruta_linearRecomendacionDos = (LinearLayout) findViewById(R.id.fruta_linearRecomendacionDos);
        _fruta_relativeAviso = (RelativeLayout) findViewById(R.id.fruta_relativeAviso);
    }
}
