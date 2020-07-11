package com.example.desarrollo.Precentacion.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CambiarPasswordActivity extends AppCompatActivity {

    private EditText _txtNuevoPassword, _txtReNuevoPassword;
    private RelativeLayout _btnCerrarActivity;
    private Button _btnCambiarPassword;
    private String destinatarioCorreo;
    private boolean confirmExit;

    private Toastp toastp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambiar_password_activity);

        init();
        getCorreoDestinatario();

        _btnCerrarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirRecoveryPass();
            }
        });


        _btnCambiarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoPassword = _txtNuevoPassword.getText().toString();
                String reNuevoPassword = _txtReNuevoPassword.getText().toString();

                if (nuevoPassword.isEmpty()) {
                    toastp.toastp(getApplicationContext(), "Ingrese la nueva contraseña");
                } else {
                    if (reNuevoPassword.isEmpty()) {
                        toastp.toastp(getApplicationContext(), "Ingrese de nuevo la contraseña");
                    } else {
                        if (nuevoPassword.length() < 8) {
                            toastp.toastp(getApplicationContext(), "La contraseña debe de contener mas de 8 caracteres");
                        } else {
                            if (nuevoPassword.equals(reNuevoPassword)) {

                                finish();

                                /*
                                1.- ACTUALIZAR CONSTRASENA EN LAS 2 BASES DE DATOS
                                 */

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Usuario", MODE_PRIVATE);
                                String url = "http://161.35.14.188/Persuhabit/usuario/password";
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                                Map<String, Object> params = new HashMap<String, Object>();
                                params.put("pwdu", nuevoPassword);
                                params.put("idusu", sharedPreferences.getInt("idRecoveryPass", 0));

                                JSONObject jsonObj = new JSONObject(params);

                                JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                                        (Request.Method.PUT, url, jsonObj, new Response.Listener<JSONObject>() {
                                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                            @Override
                                            public void onResponse(JSONObject response) {

                                                try {
                                                    String success = response.getString("data");
                                                    if (success.equals("success")) {

                                                        toastp.toastp(getApplicationContext(), "La contraseña fue cambiada correctamente");
                                                        finish();
                                                        Intent login = new Intent(getApplicationContext(), IniciarSesion.class);
                                                        startActivity(login);
                                                    } else {
                                                        toastp.toastp(getApplicationContext(), "Algo salio mal");
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

                                queue.add(jsonObjRequest);

                            } else {
                                toastp.toastp(getApplicationContext(), "La contraseña no coincide");
                            }
                        }
                    }
                }
            }
        });
    }

    private void init() {
        _btnCerrarActivity = (RelativeLayout) findViewById(R.id.btnCerrarCambiarPassword);
        _txtNuevoPassword = (EditText) findViewById(R.id.txtNuevoPassword);
        _txtReNuevoPassword = (EditText) findViewById(R.id.txtReNuevoPassword);
        _btnCambiarPassword = (Button) findViewById(R.id.btnCambiarPassword);
    }

    private void getCorreoDestinatario() {
        destinatarioCorreo = getIntent().getStringExtra("destinatarioCorreo");
    }

    private void salirRecoveryPass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CambiarPasswordActivity.this);
        builder.setCancelable(false);
        builder.setMessage("Si sale ahora mismo no podrá recuperar su contraseña ¿De verdad quiere salir?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    @Override
    public void onBackPressed() {
        salirRecoveryPass();
    }
}
