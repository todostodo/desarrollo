package com.example.desarrollo.Precentacion.Alimentos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
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
import com.example.desarrollo.Ultilidades.Toastp;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class AlimentoRegistroactivity extends AppCompatActivity {

    private TextView f_nombre, f_descripcion, f_recomendacion, f_recomendacionDos, f_frase, f_ventaja, f_avisoTitulo, f_aviso;
    private TextView _txtMensajeDialog;
    private String f_idAlimento, f_equivalencia, hora, fecha, tipoAlimento;
    private int idNino;
    private ImageView f_imagen;
    private RelativeLayout f_fondo;
    private Button _btnRegistrarFruta;
    private Dialog reaccionHijoDialog, ninosDialog;
    private TextView _txtCantidadConsumo;
    private LinearLayout _fruta_linearRecomendacionDos, _btnTerrible, _btnEstaBien, _btnGenia;
    private RelativeLayout _fruta_relativeAviso;
    private RelativeLayout _btmCerrarSelectFrutas;
    private Pattern mPattern;

    private Toastp toast;
    private Calculos calculos;
    private NinoDao ninoDao;
    private MotivadoresDao motivadoresDao;
    private ArrayList<MotivadoresSelect.MotivadoresNinoDisponible> ninoDisponibleArrayList;
    private RecyclerView _myRecyclerViewNino;
    private RecyclerViewMotivadoresSelectNino adapterSelectNino;

    private static final String TAG = "alimento_registro_activity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alimento_registro_activity);

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
                validarCantidadConsumo();
            }
        });

        //Validad cantidad de consumo
        _txtCantidadConsumo.setFilters(new InputFilter[]{new InputFilter() {
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                int indexPoint = dest.toString().indexOf(decimalFormatSymbols.getDecimalSeparator());

                if (indexPoint == -1)
                    return source;

                int decimals = dend - (indexPoint + 1);
                return decimals < 2 ? source : "";

            }
        }});
    }


    private void validarCantidadConsumo() {

        ninoDisponibleArrayList = new ArrayList<>();
        motivadoresDao.consultarNino(TAG, this, ninoDisponibleArrayList);

        if (_txtCantidadConsumo.getText().toString().trim().equals("")) {
            toast.toastp(getApplicationContext(), "Ingrese la cantidad de consumo");
        } else {
            int countNino = ninoDao.countNino(TAG, getApplicationContext());
            if (countNino > 1) {
                ninosDialog = new Dialog(AlimentoRegistroactivity.this);
                ninosDialog.setContentView(R.layout.motivadores_select_nino);
                ninosDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ninosDialog.setCanceledOnTouchOutside(false);

                _myRecyclerViewNino = (RecyclerView) ninosDialog.findViewById(R.id.myRecyclerViewMotivadoresSelectNino);
                _txtMensajeDialog = (TextView) ninosDialog.findViewById(R.id.txtMensajeComprobacion);
                _txtMensajeDialog.setText("Seleccione al niño que le registrara el alimento");

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

        reaccionHijoDialog = new Dialog(AlimentoRegistroactivity.this);
        reaccionHijoDialog.setCanceledOnTouchOutside(false);
        reaccionHijoDialog.setContentView(R.layout.reaccion_consumo_dialog);
        reaccionHijoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        _btnTerrible = (LinearLayout) reaccionHijoDialog.findViewById(R.id.btnTerrible);
        _btnEstaBien = (LinearLayout) reaccionHijoDialog.findViewById(R.id.btnEstaBien);
        _btnGenia = (LinearLayout) reaccionHijoDialog.findViewById(R.id.btnGenial);

        _btnTerrible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerIntento();

                //registrarAlimento();
                reaccionHijoDialog.dismiss();
            }
        });
        _btnEstaBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerIntento();
               // registrarAlimento();
                reaccionHijoDialog.dismiss();
            }
        });
        _btnGenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerIntento();
                noConoceGusta();
                //registrarAlimento();
                reaccionHijoDialog.dismiss();
            }
        });

        reaccionHijoDialog.show();
    }

    //Metodos para la recoleccion de fichas
    private void primerIntento() {
        double cantidadConsumo = Double.valueOf(_txtCantidadConsumo.getText().toString().trim());
        double unidadMedida = calculos.obtenerUnidadMedida(cantidadConsumo, Double.valueOf(f_equivalencia));
        if ( unidadMedida >= 1){
            toast.toastp(getApplicationContext(), "porciones: " + unidadMedida + " Equivalencia " + Double.valueOf(f_equivalencia));
        }
    }
    private void noConoceGusta(){
        String nombreAlimento = f_nombre.getText().toString();

        boolean preferencias =  ninoDao.consultarPreferencias(TAG, getApplicationContext(), idNino, nombreAlimento, tipoAlimento);

        if (preferencias == true){
            toast.toastp(getApplicationContext(), "Has consumido una frutas que no te gusta");
        }
    }
    //-----------------------------------------------------------

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
            toast.toastp(getApplicationContext(), "Alimento registrado");
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void cargarDatosFruta() {

        String recomendacionDos;
        String avisoTitulo;

        f_idAlimento = getIntent().getExtras().getString("fruta_idAlimentos");
        f_nombre.setText(getIntent().getExtras().getString("fruta_nombre"));
        f_equivalencia = getIntent().getExtras().getString("fruta_equivalencia");
        f_descripcion.setText(getIntent().getExtras().getString("fruta_descripcion"));
        f_recomendacion.setText(getIntent().getExtras().getString("fruta_recomendacion"));
        f_recomendacionDos.setText(getIntent().getExtras().getString("fruta_recomendacionDos"));
        f_ventaja.setText(getIntent().getExtras().getString("fruta_ventaja"));
        avisoTitulo = getIntent().getExtras().getString("fruta_avisoTitulo");
        f_fondo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(getIntent().getExtras().getString("fruta_fondo"))));
        f_frase.setText(getIntent().getExtras().getString("fruta_frase"));
        recomendacionDos = getIntent().getExtras().getString("fruta_recomendacionDos");
        tipoAlimento = getIntent().getExtras().getString("fruta_tipoAlimento");

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
        _btmCerrarSelectFrutas = (RelativeLayout) findViewById(R.id.btmCerrarSelectFrutas);
        _btnRegistrarFruta = (Button) findViewById(R.id.btnRegistrarFruta);
        _txtCantidadConsumo = (TextView) findViewById(R.id.txtCantidadConsumo);
        _fruta_linearRecomendacionDos = (LinearLayout) findViewById(R.id.fruta_linearRecomendacionDos);
        _fruta_relativeAviso = (RelativeLayout) findViewById(R.id.fruta_relativeAviso);
    }
}
