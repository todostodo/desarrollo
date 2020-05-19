package com.example.desarrollo.Precentacion.Login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.desarrollo.Datos.Mensajeria;
import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroUsuario extends AppCompatActivity {

    private TextView _txtNombre, _txtApellidoPaterno, _txtApellidoMaterno, _txtCorreo, _txtPassword, _btnIrLogin;
    private RelativeLayout _btnRegistrarUsuario, _btnCerrarRegistro;
    private Toastp toastp;
    private UserDao userDao;
    private Mensajeria estadoConexion;

    private String sNombre,
            sApellidoPaterno,
            sApellidoMaterno,
            sCorreo,
            sPassword;


    public boolean getUserConfirm;

    private static final String TAG = "RegistroUsuario";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario_activity);

        init();

        _btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        _btnCerrarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _btnIrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciarSesion = new Intent(getApplicationContext(), IniciarSesion.class);
                startActivity(iniciarSesion);
                finish();
            }
        });

    }

    private void registrarUsuario() {

        String nombre = _txtNombre.getText().toString().trim();
        String apellidoPaterno = _txtApellidoPaterno.getText().toString().trim();
        String apellidoMaterno = _txtApellidoMaterno.getText().toString().trim();
        String correo = _txtCorreo.getText().toString().trim();
        String password = _txtPassword.getText().toString();
        String validacionCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (nombre.isEmpty()) {
            toastp.toastp(getApplicationContext(), "Ingrese el nombre");
        } else {

            if (apellidoPaterno.isEmpty()) {
                toastp.toastp(getApplicationContext(), "Ingrese el apellido paterno");
            } else {

                if (apellidoMaterno.isEmpty()) {
                    toastp.toastp(getApplicationContext(), "Ingrese el apellido materno");
                } else {

                    if (correo.isEmpty()) {
                        toastp.toastp(getApplicationContext(), "Ingrese el correo");
                    } else {

                        if (!correo.matches(validacionCorreo)) {
                            toastp.toastp(getApplicationContext(), "Formato del correo invalido");
                        } else {

                            if (password.isEmpty()) {

                                toastp.toastp(getApplicationContext(), "Ingrese la contraseña");
                            } else {

                                if (password.length() < 8) {
                                    toastp.toastp(getApplicationContext(), "La contraseña debe de contener más de 8 caracteres");
                                } else {

                                    sNombre = nombre;
                                    sApellidoPaterno = apellidoPaterno;
                                    sApellidoMaterno = apellidoMaterno;
                                    sCorreo = correo;
                                    sPassword = password;

                                    new loadRegisterUser().execute("registro");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void init() {
        _txtNombre = (TextView) findViewById(R.id.txtNombreRegistro);
        _txtApellidoPaterno = (TextView) findViewById(R.id.txtApellidoPaternoRegistro);
        _txtApellidoMaterno = (TextView) findViewById(R.id.txtApellidoMaternoRegistro);
        _txtCorreo = (TextView) findViewById(R.id.txtCorreoRegistro);
        _txtPassword = (TextView) findViewById(R.id.txtPassRegistro);
        _btnRegistrarUsuario = (RelativeLayout) findViewById(R.id.btnRegistrarUsuario);
        _btnCerrarRegistro = (RelativeLayout) findViewById(R.id.btnCerrarLoginRegistro);
        _btnIrLogin = (TextView) findViewById(R.id.btnIrLogin);
    }


    private void addUserWebService(final String nomu, final String appu, final String apmu, final String correo, final String pwdu, final int nivel, final int experiencia, final int estadoReg) {

        String url = "http://68.183.148.243/Persuhabit/usuario/registro";
        RequestQueue queue = Volley.newRequestQueue(this);

        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        stringObjectMap.put("nomu", nomu);
        stringObjectMap.put("apmu", apmu);
        stringObjectMap.put("appu", appu);
        stringObjectMap.put("correo", correo);
        stringObjectMap.put("pwdu", pwdu);
        stringObjectMap.put("nivel", nivel);
        stringObjectMap.put("experiencia", experiencia);
        stringObjectMap.put("estadoReg", estadoReg);

        JSONObject jsonObject = new JSONObject(stringObjectMap);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String existeUsuario = response.getString("data");
                    if (!existeUsuario.equals("Fail")) {

                        int idGlobal = response.getInt("data");
                        System.out.println("el valor del id: " + idGlobal);
                        userDao.addUsuario(TAG, getApplicationContext(), nomu, appu, apmu, correo, pwdu, nivel, experiencia, estadoReg, idGlobal);
                        Intent introduccion = new Intent(getApplicationContext(), IntroduccionActivity.class);
                        startActivity(introduccion);

                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistroUsuario.this);
                        builder.setCancelable(false);
                        builder.setMessage("El correo ingresado ya se encuentra registrado");
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
    }

    private class loadRegisterUser extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(RegistroUsuario.this,
                    "", "Creando registro", true, false);
        }

        @Override
        protected String doInBackground(String... strings) {

            estadoConexion = new Mensajeria();
            boolean networkInfo = estadoConexion.estadoConexion(getApplicationContext());

            if (networkInfo == true) {

                addUserWebService(sNombre, sApellidoPaterno, sApellidoMaterno, sCorreo, sPassword, 0, 0, 0);

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegistroUsuario.this);
                builder.setCancelable(false);
                builder.setMessage("Conexion no valida: Revisa tu conexion a internet.");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }
    }
}
