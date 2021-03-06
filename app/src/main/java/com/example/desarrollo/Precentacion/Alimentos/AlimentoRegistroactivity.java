package com.example.desarrollo.Precentacion.Alimentos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.desarrollo.Datos.Calculos;
import com.example.desarrollo.Datos.GestosDao;
import com.example.desarrollo.Datos.MotivadoresDao;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.Entidades.MsgEducativos;
import com.example.desarrollo.ExportJSON.Model.ModelMsgEducativos;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewMotivadoresSelectNino;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AlimentoRegistroactivity extends AppCompatActivity {

    private TextView f_nombre, f_descripcion, f_recomendacion, f_recomendacionDos, f_frase, f_ventaja, f_avisoTitulo, f_aviso;
    private TextView _txtMensajeDialog;
    private String f_idAlimento, f_equivalencia, f_unidadMedida, hora, fecha, tipoAlimento;
    private int idNino;
    private ImageView f_imagen;
    private CardView f_fondo;
    private Button _btnRegistrarFruta;
    private Dialog reaccionHijoDialog, ninosDialog, registroAlimentoDialog, msgEducativoDialog;
    private LinearLayout _fruta_linearRecomendacionDos, _btnTerrible, _btnEstaBien, _btnGenia;
    private RelativeLayout _fruta_relativeAviso;
    private RelativeLayout _btmCerrarSelectFrutas;
    private boolean ganoFinaPrimerIntento = false;
    private String _txtCantidadConsumo;

    //Open dialog
    private Button _btnEpicFichaSalir;
    private TextView _txtEpicFichaTitulo, _txtEpicFichaMensaje, _txtEpicFichaCantidad;
    private ConstraintLayout _epicFichaContenido;
    private LinearLayout _epicFichaFondoNegro;
    private ImageView _epicFichaImg;
    private Animation fromsmall, fromnothing, forloci, togo;
    private ArrayList<Button> buttonsListDialogRegistro = new ArrayList<>();
    //---------------------

    private Toastp toast;
    private Calculos calculos;
    private NinoDao ninoDao;
    private UserDao userDao;
    private MotivadoresDao motivadoresDao;
    ModelMsgEducativos modelMsgEducativos = new ModelMsgEducativos();
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

        _epicFichaContenido.setAlpha(0);
        _epicFichaFondoNegro.setAlpha(0);
        _epicFichaImg.setVisibility(View.GONE);

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
    }


    private void validarCantidadConsumo() {

        registroAlimentoDialog = new Dialog(AlimentoRegistroactivity.this);
        registroAlimentoDialog.setContentView(R.layout.alimentos_generar_registro_dialog);
        registroAlimentoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        registroAlimentoDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        TextView _txtNombreAlimentoDialog = (TextView) registroAlimentoDialog.findViewById(R.id.txtNombreAlimentoDialog);
        TextView _txtBeneficioAlimentoDialog = (TextView) registroAlimentoDialog.findViewById(R.id.txtBeneficioAlimentoDialog);
        TextView _txtUnidadMedidaAlimentoDialog = (TextView) registroAlimentoDialog.findViewById(R.id.txtUnidadMedidaAlimentoDialog);
        final EditText _txtCantidadConsumoDialog = (EditText) registroAlimentoDialog.findViewById(R.id.txtCantidadConsumoDialog);
        final Button _btnUnCuartoDialog = (Button) registroAlimentoDialog.findViewById(R.id.btnUnCuartoDialog);
        final Button _btnUnMedioDialog = (Button) registroAlimentoDialog.findViewById(R.id.btnUnMedioDialog);
        final Button _btnTresCuartosDialog = (Button) registroAlimentoDialog.findViewById(R.id.btnTresCuartosDialog);
        Button _btnRegistrarAlimentoDialog = (Button) registroAlimentoDialog.findViewById(R.id.btnRegistrarAlimentoDialog);
        Button _btnCancelarAlimentosDialog = (Button) registroAlimentoDialog.findViewById(R.id.btnCancelarAlimentosDialog);

        _txtNombreAlimentoDialog.setText(f_nombre.getText().toString());
        _txtBeneficioAlimentoDialog.setText(f_ventaja.getText().toString());
        _txtUnidadMedidaAlimentoDialog.setText(f_unidadMedida);

        final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        _txtCantidadConsumoDialog.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                int indexPoint = dest.toString().indexOf(decimalFormatSymbols.getDecimalSeparator());

                if (indexPoint == -1)
                    return source;

                int decimals = dend - (indexPoint + 1);
                return decimals < 2 ? source : "";
            }
        }});

        _btnRegistrarAlimentoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ninoDisponibleArrayList = new ArrayList<>();
                motivadoresDao.consultarNino(TAG, getApplicationContext(), ninoDisponibleArrayList);
                _txtCantidadConsumo = _txtCantidadConsumoDialog.getText().toString().trim();

                if (_txtCantidadConsumo.equals("")) {

                    toast.toastp(getApplicationContext(), "Ingrese la cantidad de consumo");

                } else {
                    registroAlimentoDialog.dismiss();
                    int countNino = ninoDao.countNino(TAG, getApplicationContext());

                    if (countNino > 1) {

                        ninosDialog = new Dialog(AlimentoRegistroactivity.this);
                        ninosDialog.setContentView(R.layout.motivadores_select_nino);
                        ninosDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        ninosDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        ninosDialog.setCanceledOnTouchOutside(false);

                        _myRecyclerViewNino = (RecyclerView) ninosDialog.findViewById(R.id.myRecyclerViewMotivadoresSelectNino);
                        _txtMensajeDialog = (TextView) ninosDialog.findViewById(R.id.txtMensajeComprobacion);
                        _txtMensajeDialog.setText("Seleccione al niño que le registrara el alimento");

                        _myRecyclerViewNino.setLayoutManager(new LinearLayoutManager(AlimentoRegistroactivity.this));
                        adapterSelectNino = new RecyclerViewMotivadoresSelectNino(AlimentoRegistroactivity.this, ninoDisponibleArrayList);
                        _myRecyclerViewNino.setAdapter(adapterSelectNino);

                        final TextView cancelar = (TextView) ninosDialog.findViewById(R.id.btnCancelarSelectNinoMotivador);
                        adapterSelectNino.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                idNino = ninoDisponibleArrayList.get(_myRecyclerViewNino.getChildAdapterPosition(v)).getIdNino();
                                ninosDialog.dismiss();

                                if (idNino == 1) {
                                    if (lineaBaseGeneradaNinoUno())
                                        cargarReaccionHijoDialog();
                                    else
                                        registrarAlimento();
                                } else if (idNino == 2) {
                                    if (lineaBaseGeneradaNinoDos())
                                        cargarReaccionHijoDialog();
                                    else
                                        registrarAlimento();
                                }


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
                        if (lineaBaseGeneradaNinoUno()) {
                            cargarReaccionHijoDialog();
                        } else {
                            registrarAlimento();
                        }
                    }
                }
            }
        });

        _txtCantidadConsumoDialog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("0.25") ||
                        s.toString().equals("0.5") ||
                        s.toString().equals("0.75")) {
                } else {
                    for (int i = 0; i < buttonsListDialogRegistro.size(); i++) {
                        buttonsListDialogRegistro.get(i).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blanco)));
                        buttonsListDialogRegistro.get(i).setTextColor(getResources().getColor(R.color.negro));
                    }
                    buttonsListDialogRegistro.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        _btnUnCuartoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButtonDialogRegistro(_btnUnCuartoDialog);
                undeselectButtonDialogRegistro(_btnUnCuartoDialog);
                _txtCantidadConsumoDialog.setText("0.25");
            }
        });
        _btnUnMedioDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButtonDialogRegistro(_btnUnMedioDialog);
                undeselectButtonDialogRegistro(_btnUnMedioDialog);
                _txtCantidadConsumoDialog.setText("0.5");
            }
        });
        _btnTresCuartosDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButtonDialogRegistro(_btnTresCuartosDialog);
                undeselectButtonDialogRegistro(_btnTresCuartosDialog);
                _txtCantidadConsumoDialog.setText("0.75");
            }
        });

        _btnCancelarAlimentosDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registroAlimentoDialog.dismiss();
            }
        });

        registroAlimentoDialog.show();

    }

    //Validacion de botones con valor de decimales ---- Dialog alimentos_generar_registro_dialog
    private void selectButtonDialogRegistro(Button button) {
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#85C0AE")));
        button.setTextColor(getResources().getColor(R.color.blanco));
        buttonsListDialogRegistro.add(button);
    }

    private void undeselectButtonDialogRegistro(Button button) {
        if (buttonsListDialogRegistro.size() > 1) {
            if (!buttonsListDialogRegistro.get(0).getText().toString().equals(buttonsListDialogRegistro.get(1).getText().toString())) {
                buttonsListDialogRegistro.get(0).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blanco)));
                buttonsListDialogRegistro.get(0).setTextColor(getResources().getColor(R.color.negro));
            }
            buttonsListDialogRegistro.remove(0);
        }
    }

    //------------------------------


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
                registrarAlimento();
                fichaPrimerIntento();
                experienciaUsuario();
                reaccionHijoDialog.dismiss();
                //msgEducativoDialog();
                int ali = Integer.parseInt(f_idAlimento);
                GestosDao.insertGestoTerri("gestoTer", getApplicationContext(), idNino, ali, 1);
            }
        });
        _btnEstaBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarAlimento();
                fichaPrimerIntento();
                experienciaUsuario();
                reaccionHijoDialog.dismiss();
                //msgEducativoDialog();
                int ali = Integer.parseInt(f_idAlimento);
                GestosDao.insertGestoBien("gestoBien", getApplicationContext(), idNino, ali, 1);
            }
        });
        _btnGenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarAlimento();
                experienciaUsuario();
                fichaPrimerIntento();
                reaccionHijoDialog.dismiss();
                //msgEducativoDialog();
                int ali = Integer.parseInt(f_idAlimento);
                GestosDao.insertGestoGenial("gestoGenial", getApplicationContext(), idNino, ali, 1);
            }
        });

        reaccionHijoDialog.show();
    }

    private void msgEducativoDialog() {
        msgEducativoDialog = new Dialog(AlimentoRegistroactivity.this);
        msgEducativoDialog.setContentView(R.layout.mensaje_educativo_dialog);
        msgEducativoDialog.setCancelable(false);
        msgEducativoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        msgEducativoDialog.getWindow().getAttributes().windowAnimations = R.anim.fade_in;

        TextView txtMensajeEducativo = (TextView) msgEducativoDialog.findViewById(R.id.txtMensajeEducativoDialog);
        Button btnSalirMsgEdicativo = (Button) msgEducativoDialog.findViewById(R.id.btnSalirMsgEducativoDialog);
        ArrayList<MsgEducativos> msgEducativosList = new ArrayList<>();
        modelMsgEducativos.addItemsFromJSONMsgEducativos(msgEducativosList, TAG, getApplicationContext());
        int selectMsgRound = (int) (Math.random() * msgEducativosList.size());
        txtMensajeEducativo.setText(msgEducativosList.get(selectMsgRound).getMsgEducativo());

        btnSalirMsgEdicativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgEducativoDialog.dismiss();
            }
        });

        msgEducativoDialog.show();
    }

    //Suma de experiencia al usuario
    private void experienciaUsuario() {

        double cantidadConsumo = Double.valueOf(_txtCantidadConsumo.toString());
        int experiencia = 0;

        if (cantidadConsumo >= 1.0) {
            experiencia = 10;
        } else if (cantidadConsumo >= 0.5 && cantidadConsumo < 1) {
            experiencia = 8;
        } else if (cantidadConsumo < 0.5) {
            experiencia = 5;
        }

        if (idNino == 1) {
            if (lineaBaseGeneradaNinoUno()) {
                userDao.sumarExpUsuarario(TAG, getApplicationContext(), experiencia);
            }
        } else if (idNino == 2) {
            if (lineaBaseGeneradaNinoDos()) {
                userDao.sumarExpUsuarario(TAG, getApplicationContext(), experiencia);
            }
        }

    }

    //Manejo de los dialogos
    private void showDialogFicha(int cantidadFicha, String titulo, String mensaje) {

        _epicFichaImg.setVisibility(View.VISIBLE);
        _epicFichaImg.startAnimation(forloci);

        _epicFichaFondoNegro.setAlpha(1);
        _epicFichaFondoNegro.startAnimation(fromnothing);

        _epicFichaContenido.setAlpha(1);
        _epicFichaContenido.startAnimation(fromsmall);

        _txtEpicFichaTitulo.setText(titulo);
        _txtEpicFichaMensaje.setText(mensaje);
        _txtEpicFichaCantidad.setText("+" + String.valueOf(cantidadFicha));

        _btnEpicFichaSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ganoFinaPrimerIntento == true) {

                    ganoFinaPrimerIntento = false;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fichaNoConoceAlimento();
                        }
                    }, 1000);
                } else if (ganoFinaPrimerIntento == false) {
                    msgEducativoDialog();
                }
                _epicFichaFondoNegro.startAnimation(togo);
                _epicFichaContenido.startAnimation(togo);
                _epicFichaImg.startAnimation(togo);
                _epicFichaImg.setVisibility(View.GONE);

                ViewCompat.animate(_epicFichaContenido).setStartDelay(350).alpha(0).start();
                ViewCompat.animate(_epicFichaFondoNegro).setStartDelay(350).alpha(0).start();
            }
        });
    }

    //Metodos para la recoleccion de fichas

    private void fichaPrimerIntentoFruta() {
        showDialogFicha(2, "Buen trabajo", "lograste consumir las porciones de frutas del día en el primer intento");
    }

    private void fichaPrimerIntentoVerdura() {
        showDialogFicha(2, "Buen trabajo", "lograste consumir las porciones de verduras del día en el primer intento");
    }

    private void fichaPrimerIntento() {

        SharedPreferences sharedPreferences = getSharedPreferences("Calculo", MODE_PRIVATE);
        double baseEsfuerzoVerduras = ninoDao.consultarEsfuerzoConsumoVerduras(TAG, getApplicationContext(), idNino);
        double cantidadConsumo = Double.valueOf(_txtCantidadConsumo.toString().trim());
        double unidadMedida = calculos.obtenerEquivalencia(cantidadConsumo, Double.valueOf(f_equivalencia));

        if (unidadMedida >= baseEsfuerzoVerduras) {

            if (idNino == 1) {
                if (lineaBaseGeneradaNinoUno()) {
                    if (!sharedPreferences.getBoolean("primerIntento1", true)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("primerIntento1", true);
                        editor.commit();
                        ganoFinaPrimerIntento = true;
                        ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 2);

                        if (tipoAlimento.equals("Fruta"))
                            fichaPrimerIntentoFruta();
                        else
                            fichaPrimerIntentoVerdura();

                    } else {
                        ganoFinaPrimerIntento = false;
                        fichaNoConoceAlimento();
                    }
                } else {
                    fichaNoConoceAlimento();
                }
            }
            if (idNino == 2) {
                if (lineaBaseGeneradaNinoDos()) {
                    if (!sharedPreferences.getBoolean("primerIntento2", true)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("primerIntento2", true);
                        editor.commit();
                        ganoFinaPrimerIntento = true;
                        ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 2);

                        if (tipoAlimento.equals("Fruta"))
                            fichaPrimerIntentoFruta();
                        else
                            fichaPrimerIntentoVerdura();

                    } else {
                        ganoFinaPrimerIntento = false;
                        fichaNoConoceAlimento();
                    }
                } else {
                    fichaNoConoceAlimento();
                }
            }

        } else {
            if (idNino == 1) {
                if (!sharedPreferences.getBoolean("primerIntento1", true)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("primerIntento1", true);
                    fichaNoConoceAlimento();
                    editor.commit();
                } else {
                    ganoFinaPrimerIntento = false;
                    fichaNoConoceAlimento();
                }
            }
            if (idNino == 2) {
                if (!sharedPreferences.getBoolean("primerIntento2", true)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("primerIntento2", true);
                    fichaNoConoceAlimento();
                    editor.commit();
                } else {
                    ganoFinaPrimerIntento = false;
                    fichaNoConoceAlimento();
                }
            }
        }
    }

    private void fichaNoConoceAlimento() {

        double cantidad = Double.valueOf(_txtCantidadConsumo.toString());

        if (cantidad >= 1.0) {
            String nombreAlimento = f_nombre.getText().toString();
            boolean preferencias = ninoDao.consultarPreferencias(TAG, getApplicationContext(), idNino, nombreAlimento, tipoAlimento);

            if (preferencias == true) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Calculo", MODE_PRIVATE);
                if (idNino == 1) {
                    if (lineaBaseGeneradaNinoUno()) {
                        if (!sharedPreferences.getBoolean("noConoceAlimento1", true)) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("noConoceAlimento1", true);
                            editor.commit();
                            ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 4);
                            showDialogFicha(4, "Buen esfuerzo", "Te has esforzado al consumir un alimentos que no te gusta");
                        }
                    }
                }
                if (idNino == 2) {
                    if (lineaBaseGeneradaNinoDos()) {
                        if (!sharedPreferences.getBoolean("noConoceAlimento2", true)) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("noConoceAlimento2", true);
                            editor.commit();
                            ninoDao.acumularFichas(TAG, getApplicationContext(), idNino, 4);
                            showDialogFicha(4, "Buen esfuerzo", "Te has esforzado al consumir un alimentos que no te gusta");
                        }
                    }
                }
            }
        } else {
            msgEducativoDialog();
        }
    }
    //-----------------------------------------------------------

    private void registrarAlimento() {

        getHoraFecha();
        double equivalencia = calculos.obtenerEquivalencia(Double.valueOf(_txtCantidadConsumo), Double.valueOf(f_equivalencia));

        boolean insert = calculos.registrarDetalleReg(
                TAG,
                this,
                idNino,
                Integer.valueOf(f_idAlimento),
                Double.valueOf(f_equivalencia),
                Double.valueOf(_txtCantidadConsumo),
                equivalencia,
                hora,
                fecha,
                tipoAlimento,
                1
        );
        if (insert == true) {
            Toast.makeText(getApplicationContext(), "Porciones registradas", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarDatosFruta() {

        String recomendacionDos;
        String avisoTitulo;

        f_idAlimento = getIntent().getExtras().getString("fruta_idAlimentos");
        f_nombre.setText(getIntent().getExtras().getString("fruta_nombre"));
        f_equivalencia = getIntent().getExtras().getString("fruta_equivalencia");
        f_unidadMedida = getIntent().getExtras().getString("fruta_unidadMedida");
        f_descripcion.setText(getIntent().getExtras().getString("fruta_descripcion"));
        f_recomendacion.setText(getIntent().getExtras().getString("fruta_recomendacion"));
        f_recomendacionDos.setText(getIntent().getExtras().getString("fruta_recomendacionDos"));
        f_ventaja.setText(getIntent().getExtras().getString("fruta_ventaja"));
        avisoTitulo = getIntent().getExtras().getString("fruta_avisoTitulo");
        //f_fondo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + getIntent().getExtras().getString("fruta_fondo"))));
        f_fondo.setCardBackgroundColor(Color.parseColor("#" + getIntent().getExtras().getString("fruta_fondo")));
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

    private void getHoraFecha() {
        Calculos calculos = new Calculos();
        hora = calculos.getHora();
        fecha = calculos.getFecha();
    }

    private boolean lineaBaseGeneradaNinoUno() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Calculo", MODE_PRIVATE);
        Boolean lineaBaseGeneradaNino1 = sharedPreferences.getBoolean("LineaBaseGenerada1", false);
        return lineaBaseGeneradaNino1;
    }

    private boolean lineaBaseGeneradaNinoDos() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Calculo", MODE_PRIVATE);
        Boolean lineaBaseGeneradaNino2 = sharedPreferences.getBoolean("LineaBaseGenerada2", false);
        return lineaBaseGeneradaNino2;
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
        f_fondo = (CardView) findViewById(R.id.cardBackgroudInformacio);
        _btmCerrarSelectFrutas = (RelativeLayout) findViewById(R.id.btmCerrarSelectFrutas);
        _btnRegistrarFruta = (Button) findViewById(R.id.btnRegistrarFruta);
        _fruta_linearRecomendacionDos = (LinearLayout) findViewById(R.id.fruta_linearRecomendacionDos);
        _fruta_relativeAviso = (RelativeLayout) findViewById(R.id.fruta_relativeAviso);

        //Open dialog
        _btnEpicFichaSalir = (Button) findViewById(R.id.btnEpicFichaSalir);
        _txtEpicFichaTitulo = (TextView) findViewById(R.id.txtEpicFichaTitulo);
        _txtEpicFichaMensaje = (TextView) findViewById(R.id.txtEpicFichaMensaje);
        _txtEpicFichaCantidad = (TextView) findViewById(R.id.txtEpicFichaCantidad);
        _epicFichaContenido = (ConstraintLayout) findViewById(R.id.epicFichaContenido);
        _epicFichaFondoNegro = (LinearLayout) findViewById(R.id.epicFichaFondoNegro);
        _epicFichaImg = (ImageView) findViewById(R.id.epicFichaImg);

        fromsmall = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fromsmall);
        fromnothing = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fromnothing);
        forloci = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.forloci);
        togo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.togo);
    }
}
