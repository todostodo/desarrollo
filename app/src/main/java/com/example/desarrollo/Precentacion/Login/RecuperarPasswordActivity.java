package com.example.desarrollo.Precentacion.Login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.desarrollo.Datos.Mensajeria;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarPasswordActivity extends AppCompatActivity {

    //Pantalla uno
    private RelativeLayout _btnCerrarRecuperarPassword;
    private Button _btnEnviarCodigo;
    private TextView _txtCorreoUsuario;
    private ConstraintLayout _activityVerificarCodigo, _activityMandarCodigo;
    private String codigoRecuperacionGenerado;

    //Pantalla dos
    private EditText _txtDigitoUno, _txtDigitoDos, _txtDigitoTres, _txtDigitoCuatro;
    private TextView _txtCorreoEnviado;
    private Button _btnVerificarCodigo, _btnReenviarCodigo;
    private RelativeLayout _btnCerrarPasswordOlvidado;
    private String destinatarioCorreo;

    private Toastp toastp;
    Mensajeria mensajeria;

    protected String sEmail = "persuhabit@gmail.com";
    protected String sPassword = "#HealthyFoodTec#";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperar_password_activity);

        init();
        validacionDigitos();

        //Pantalla Uno
        _btnCerrarRecuperarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _btnEnviarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean estadoConeccion = new Mensajeria().estadoConexion(getApplicationContext());

                if (estadoConeccion == true) {
                    enviarCodigoCorreo();
                } else {
                    mensajeDialog("Debe de tener conección a internet para poder enviar el código de recuperación");
                }
            }
        });

        //Pantalla dos
        _btnCerrarPasswordOlvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //Pantalla Uno
    private void enviarCodigoCorreo() {

        destinatarioCorreo = _txtCorreoUsuario.getText().toString().trim(); //Correo aquien se le mandara el codigo
        String[] confirmarDominioCorreo = destinatarioCorreo.split("");
        String validacionCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String txtAsuntoCorreo = "Recuperación de contraseña"; //Agregar un asunto --


        if (destinatarioCorreo.isEmpty()) {
            toastp.toastp(getApplicationContext(), "Ingrese el correo");
        } else {

            if (!destinatarioCorreo.matches(validacionCorreo)) {
                toastp.toastp(getApplicationContext(), "El correo ingresado tiene un formato invalido");
            } else {

                String obtenerDominioCorreo = "";
                boolean existeDominio = false;

                almacenaDominio:
                for (int i = 0; i < confirmarDominioCorreo.length; i++) {
                    if (confirmarDominioCorreo[i].equals("@")) {
                        for (int x = i + 1; x < confirmarDominioCorreo.length; x++) {
                            obtenerDominioCorreo += confirmarDominioCorreo[x];
                            if (x >= confirmarDominioCorreo.length)
                                break almacenaDominio;
                        }
                    }
                }

                String dominiosExistentes[] = mensajeria.getDominios();

                comparaDominio:
                for (int i = 0; i < dominiosExistentes.length; i++) {
                    if (obtenerDominioCorreo.equals(dominiosExistentes[i])) {
                        existeDominio = true;
                        break comparaDominio;
                    }
                }

                if (existeDominio == true) {

                    /*
                    VERIFICAR ANTES SI EL CORREO SE ENCUENTRA REGISTRADO EN LA BASE DE DATOS
                    */

                    String url = "http://161.35.14.188/Persuhabit/usuario/correo";
                    RequestQueue queue = Volley.newRequestQueue(this);

                    Map<String, Object> stringObjectMap = new HashMap<String, Object>();
                    stringObjectMap.put("correo", destinatarioCorreo);

                    JSONObject jsonObject = new JSONObject(stringObjectMap);

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String success = response.getString("message");

                                if (success.equals("success")) {

                                    int idRecoveryPass = response.getInt("data");
                                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Usuario", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("idRecoveryPass", idRecoveryPass);
                                    editor.commit();

                                    Log.v("RecuprarPassword", "idRecoveryPass --------------------------- " + idRecoveryPass);

                                    preparCorreo();

                                } else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(RecuperarPasswordActivity.this);
                                    builder.setCancelable(false);
                                    builder.setMessage("El correo ingresado no se encuentra registrado en la aplicación");
                                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.dismiss();

                                        }
                                    });

                                    builder.show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                    queue.add(jsonObjectRequest);

                } else {
                    mensajeDialog("El mensaje no se pudo entregar. El nombre de dominio del destinatario no existe o no se encuentra en nuestra lista de dominios aceptados");
                }
            }
        }
    }

    public void mensajeDialog(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RecuperarPasswordActivity.this);
        builder.setCancelable(false);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //Limpiar los edit text
            }
        });

        //Mostrar dialogo de alerta
        builder.show();
    }

    //Pantalla dos
    private void validacionDigitos() {

        //Text Changer

        _txtDigitoUno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {
                    _txtDigitoDos.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _txtDigitoDos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {
                    _txtDigitoTres.requestFocus();
                }

                if (s.length() == 0) {

                    _txtDigitoDos.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            _txtDigitoDos.setSelection(_txtDigitoDos.getText().length());
                            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {

                                if (_txtDigitoDos.getText().length() >= 1) {
                                    _txtDigitoDos.requestFocus();
                                } else {
                                    _txtDigitoUno.setText("");
                                    _txtDigitoUno.requestFocus();
                                }
                            }
                            return false;
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _txtDigitoTres.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() >= 1) {
                    _txtDigitoCuatro.requestFocus();
                }

                if (s.length() == 0) {

                    _txtDigitoTres.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {

                                if (_txtDigitoTres.getText().length() >= 1) {
                                    _txtDigitoTres.requestFocus();
                                } else {
                                    _txtDigitoDos.setText("");
                                    _txtDigitoDos.requestFocus();
                                }
                            }
                            return false;
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _txtDigitoCuatro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {

                    _txtDigitoCuatro.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {

                                if (_txtDigitoCuatro.getText().length() >= 1) {
                                    _txtDigitoCuatro.requestFocus();
                                } else {
                                    _txtDigitoTres.setText("");
                                    _txtDigitoTres.requestFocus();
                                }
                            }
                            return false;
                        }
                    });
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void verificarCodigo() {

        _btnVerificarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String validarCodigo = _txtDigitoUno.getText().toString() +
                        _txtDigitoDos.getText().toString() +
                        _txtDigitoTres.getText().toString() +
                        _txtDigitoCuatro.getText().toString();

                if (validarCodigo.length() == 0) {
                    toastp.toastp(getApplicationContext(), "Ingrese el código de recuperación");
                } else {

                    if (validarCodigo.length() >= 1 && validarCodigo.length() <= 3) {
                        toastp.toastp(getApplicationContext(), "El código de recuperación esta incompleto");
                    } else {

                        if (validarCodigo.equals(codigoRecuperacionGenerado)) {
                            //toastp.toastp(getApplicationContext(), "Codigo correcto " + validarCodigo + " - " + codigoRecuperacionGenerado);

                            Intent cambiaPass = new Intent(getApplicationContext(), CambiarPasswordActivity.class);
                            cambiaPass.putExtra("destinatarioCorreo", destinatarioCorreo);
                            startActivity(cambiaPass);
                            finish();

                        } else {
                            toastp.toastp(getApplicationContext(), "Codigo incorrecto");
                        }
                    }
                }
            }
        });

        _btnReenviarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preparCorreo();
            }
        });
    }

    private void init() {
        //Pantalla Uno
        _activityMandarCodigo = (ConstraintLayout) findViewById(R.id.constMandarCodig);
        _btnCerrarRecuperarPassword = (RelativeLayout) findViewById(R.id.btnCerrarRecuperarPassword);
        _btnEnviarCodigo = (Button) findViewById(R.id.btnEnviarCodigo);
        _txtCorreoUsuario = (TextView) findViewById(R.id.txtCorreoUsuario);

        //Pantalla dos
        _activityVerificarCodigo = (ConstraintLayout) findViewById(R.id.constVerificarCodigo);
        _txtDigitoUno = (EditText) findViewById(R.id.txtDigitoUno);
        _txtDigitoDos = (EditText) findViewById(R.id.txtDigitoDos);
        _txtDigitoTres = (EditText) findViewById(R.id.txtDigitoTres);
        _txtDigitoCuatro = (EditText) findViewById(R.id.txtDigitoCuatro);
        _txtCorreoEnviado = (TextView) findViewById(R.id.txtCorreoEnviado);
        _btnVerificarCodigo = (Button) findViewById(R.id.btnVerificarCodigo);
        _btnReenviarCodigo = (Button) findViewById(R.id.btnReenviarCodigo);
        _btnCerrarPasswordOlvidado = (RelativeLayout) findViewById(R.id.btnCerrarPasswordOlvidado);

    }

    private void preparCorreo() {

        mensajeria = new Mensajeria();
        _txtCorreoEnviado.setText(destinatarioCorreo);
        codigoRecuperacionGenerado = mensajeria.generarCodigoRecuperacion();

        if (!codigoRecuperacionGenerado.isEmpty()) {

            String txtAsuntoCorreo = "Recuperación de la contraseña";
            String txtMensajeCorreo = "Codigo de recuperación : " + codigoRecuperacionGenerado; //Lo que va contener el Correo

            _activityVerificarCodigo.setVisibility(View.VISIBLE);
            _activityMandarCodigo.setVisibility(View.GONE);
            verificarCodigo();
            //Iniciarlizar propiedades


            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            //Inicializar sesion
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mensajeria.getsEmail(), mensajeria.getsPassword());
                }
            });

            try {
                //Inicializar contenido del correo
                Message message = new MimeMessage(session);
                //Enviar correo
                message.setFrom(new InternetAddress(mensajeria.getsEmail()));
                //Recipiente correo
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(destinatarioCorreo.trim()));
                //Asunto del correo
                message.setSubject(txtAsuntoCorreo.trim());
                //Mensaje de correo
                message.setText(txtMensajeCorreo.trim());

                //Enviar correo
                new enviarCorreo().execute(message);


            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }
    }

    private class enviarCorreo extends AsyncTask<Message, String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(RecuperarPasswordActivity.this,
                    "Porfavor espere", "Enviando el código de recuperación", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Enviado";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            if (s.equals("Enviado")) {

                //Cuando termine
                AlertDialog.Builder builder = new AlertDialog.Builder(RecuperarPasswordActivity.this);
                builder.setCancelable(false);
                builder.setMessage("El código de recuperación se envio correctamente");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //Limpiar los edit text
                        //Cambia de pantalla a verificar codigo
                        _activityVerificarCodigo.setVisibility(View.VISIBLE);
                        _activityMandarCodigo.setVisibility(View.GONE);
                        verificarCodigo();
                    }
                });

                //Mostrar dialogo de alerta
                builder.show();
                //Inicializar dialogo de alerta
            } else {
                toastp.toastp(getApplicationContext(), "Algo salio mal");
            }
        }
    }
}

