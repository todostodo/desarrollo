package com.example.desarrollo.Precentacion.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.Precentacion.Home.HijoRegistroActivity;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

public class RegistroUsuario extends AppCompatActivity {

    private TextView _txtNombre, _txtApellidoPaterno, _txtApellidoMaterno, _txtCorreo, _txtPassword, _btnIrLogin;
    private RelativeLayout _btnRegistrarUsuario, _btnCerrarRegistro;

    private Toastp toastp;
    private UserDao userDao;

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
        String nombre = _txtNombre.getText().toString();
        String apellidoPaterno = _txtApellidoPaterno.getText().toString();
        String apellidoMaterno = _txtApellidoMaterno.getText().toString();
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

                                     /*
                                        Guardar id generada de la base de datos (WEB SERVIDE) antes de crear la cuenta localmente
                                        enviar la id de la web service a la base de datos local
                                        despues almacenar el correo y la id en sharepreferences
                                     */

                                    boolean addUser = userDao.addUsuario(
                                            TAG,
                                            getApplicationContext(),
                                            nombre,
                                            apellidoPaterno,
                                            apellidoMaterno,
                                            correo,
                                            password,
                                            0,
                                            0,
                                            0
                                    );

                                    if (addUser == true) {

                                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Usuario", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putBoolean("inicioAutomatico", true);
                                        editor.putString("correoUsuario", correo);
                                        editor.commit();

                                        Intent introduccion = new Intent(getApplicationContext(), IntroduccionActivity.class);
                                        startActivity(introduccion);
                                    }
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
}
