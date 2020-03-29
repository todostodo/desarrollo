package com.example.desarrollo.Precentacion.Alimentos.Frutas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.desarrollo.Datos.Calculos;
import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewMotivadoresSelectNino;
import com.example.desarrollo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


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
    private String
            f_idAlimento,
            f_equivalencia,
            hora,
            fecha,
            tipoAlimento;
    private int idNino;
    private ImageView f_imagen;
    private RelativeLayout f_fondo;
    private Button _btnRegistrarFruta;
    private Dialog reaccionHijoDialog, ninosDialog;
    private TextView _txtCantidadConsumo;
    private LinearLayout
            _btnExpandirFrutas,
            _frutasExpandirSelect,
            _fruta_linearRecomendacionDos,
            _btnTerrible,
            _btnEstaBien,
            _btnGenia;
    private RelativeLayout _fruta_relativeAviso;
    private ImageButton _btmCerrarSelectFrutas;

    private Calculos calculos;
    private NinoDao ninoDao;
    private MotivadoresDao motivadoresDao;
    private ArrayList<MotivadoresSelect.MotivadoresNinoDisponible> ninoDisponibleArrayList;
    private RecyclerView _myRecyclerViewNino;
    private RecyclerViewMotivadoresSelectNino adapterSelectNino;

    private static final String TAG = "frutas_select_activity";

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
                registrarFrutaNino();
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

    private void registrarFrutaNino() {

        ninoDisponibleArrayList = new ArrayList<>();
        motivadoresDao.consultarNino(TAG, this, ninoDisponibleArrayList);

        if (_txtCantidadConsumo.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Ingrese la cantidad de consumo", Toast.LENGTH_SHORT).show();
        } else {
            int countNino = ninoDao.countNino(TAG, getApplicationContext());
            Toast.makeText(getApplicationContext(), "Cantidad = " + countNino, Toast.LENGTH_SHORT).show();
            if (countNino > 1) {
                ninosDialog = new Dialog(frutas_select_activity.this);
                ninosDialog.setContentView(R.layout.motivadores_select_nino);
                ninosDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ninosDialog.setCanceledOnTouchOutside(false);

                _myRecyclerViewNino = (RecyclerView) ninosDialog.findViewById(R.id.myRecyclerViewMotivadoresSelectNino);

                _myRecyclerViewNino.setLayoutManager(new LinearLayoutManager(this));
                adapterSelectNino = new RecyclerViewMotivadoresSelectNino(this, ninoDisponibleArrayList);
                _myRecyclerViewNino.setAdapter(adapterSelectNino);

                final TextView cancelar = (TextView) ninosDialog.findViewById(R.id.btnCancelarSelectNinoMotivador);
                adapterSelectNino.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        idNino = ninoDisponibleArrayList.get(_myRecyclerViewNino.getChildAdapterPosition(v)).getIdNino();
                        ninosDialog.dismiss();
                        cargarReaccionHijoDialog();
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ninosDialog.dismiss();
                    }
                });

                ninosDialog.show();
            } else {
                idNino = ninoDisponibleArrayList.get(0).getIdNino();
                cargarReaccionHijoDialog();
            }
        }
    }

    private void cargarReaccionHijoDialog() {

        reaccionHijoDialog = new Dialog(frutas_select_activity.this);
        reaccionHijoDialog.setCanceledOnTouchOutside(false);
        reaccionHijoDialog.setContentView(R.layout.reaccion_consumo_dialog);
        reaccionHijoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        _btnTerrible = (LinearLayout) reaccionHijoDialog.findViewById(R.id.btnTerrible);
        _btnEstaBien = (LinearLayout) reaccionHijoDialog.findViewById(R.id.btnEstaBien);
        _btnGenia = (LinearLayout) reaccionHijoDialog.findViewById(R.id.btnGenial);

        _btnTerrible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarAlimento();
                reaccionHijoDialog.dismiss();
            }
        });
        _btnEstaBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarAlimento();
                reaccionHijoDialog.dismiss();
            }
        });
        _btnGenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarAlimento();
                reaccionHijoDialog.dismiss();
            }
        });

        reaccionHijoDialog.show();
    }

    private void registrarAlimento() {

        setHoraFecha();

        boolean insert = calculos.registrarDetalleReg(
                TAG,
                this,
                idNino,
                Integer.valueOf(f_idAlimento),
                Double.valueOf(f_equivalencia),
                Double.valueOf(_txtCantidadConsumo.getText().toString().trim()),
                hora,
                fecha,
                tipoAlimento
        );
        if (insert == true) {
            _txtCantidadConsumo.setText("");
            Toast.makeText(this, "Alimento registrado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarDatosFruta() {

        String complemento = "Alimento para";
        String recomendacionDos;
        String ventaja;
        String avisoTitulo;

        f_idAlimento = getIntent().getExtras().getString("fruta_idAlimentos");
        f_nombre.setText(getIntent().getExtras().getString("fruta_nombre"));
        f_equivalencia = getIntent().getExtras().getString("fruta_equivalencia");
        f_descripcion.setText(getIntent().getExtras().getString("fruta_descripcion"));
        f_recomendacion.setText(getIntent().getExtras().getString("fruta_recomendacion"));
        f_recomendacionDos.setText(getIntent().getExtras().getString("fruta_recomendacionDos"));
        ventaja = getIntent().getExtras().getString("fruta_ventaja");
        avisoTitulo = getIntent().getExtras().getString("fruta_avisoTitulo");
        f_fondo.setBackgroundColor(Color.parseColor(getIntent().getExtras().getString("fruta_fondo")));
        f_frase.setText(getIntent().getExtras().getString("fruta_frase"));
        recomendacionDos = getIntent().getExtras().getString("fruta_recomendacionDos");
        tipoAlimento = getIntent().getExtras().getString("fruta_tipoAlimento");

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
        if (!avisoTitulo.equals("")) {
            f_avisoTitulo.setText(avisoTitulo);
            f_aviso.setText(Html.fromHtml(getIntent().getExtras().getString("fruta_aviso")));
            _fruta_relativeAviso.setVisibility(View.VISIBLE);
        }

        int drawableResourceId = this.getResources().getIdentifier(getIntent().getExtras().getString("fruta_imagen"), "drawable", this.getPackageName());

        Glide.with(this).load(drawableResourceId).into(f_imagen);
    }

    private void setHoraFecha() {
        Calculos calculos = new Calculos();
        hora = calculos.getHora();
        fecha = calculos.getFecha();
    }

    private void init() {
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
