package com.example.desarrollo.Precentacion.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.R;

public class login_registro extends AppCompatActivity {

    EditText
            _txtUsuario,
            _txtCorreo,
            _txtPassword,
            _txtRePassword,
            _txtUsuarioApellidoPaterno,
            _txtUsuarioApellidoMaterno;
    ImageView _fondoImagen;
    UserDao userDao;

    private static final String TAG = "login_registro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_registro_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();

        _txtUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    _txtUsuario.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtUsuario.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    _txtUsuario.setTextColor(getResources().getColor(R.color.negro));
                    _txtUsuario.setTypeface(Typeface.DEFAULT);
                }
            }
        });

        _txtUsuarioApellidoPaterno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    _txtUsuarioApellidoPaterno.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtUsuarioApellidoPaterno.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    _txtUsuarioApellidoPaterno.setTextColor(getResources().getColor(R.color.negro));
                    _txtUsuarioApellidoPaterno.setTypeface(Typeface.DEFAULT);
                }
            }
        });

        _txtUsuarioApellidoMaterno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    _txtUsuarioApellidoMaterno.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtUsuarioApellidoMaterno.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    _txtUsuarioApellidoMaterno.setTextColor(getResources().getColor(R.color.negro));
                    _txtUsuarioApellidoMaterno.setTypeface(Typeface.DEFAULT);
                }
            }
        });

        _txtCorreo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    _txtCorreo.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtCorreo.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    _txtCorreo.setTextColor(getResources().getColor(R.color.negro));
                    _txtCorreo.setTypeface(Typeface.DEFAULT);
                }
            }
        });


        _txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    _txtPassword.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtPassword.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    _txtPassword.setTextColor(getResources().getColor(R.color.negro));
                    _txtPassword.setTypeface(Typeface.DEFAULT);
                }
            }
        });


        _txtRePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    _txtRePassword.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtRePassword.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    _txtRePassword.setTextColor(getResources().getColor(R.color.negro));
                    _txtRePassword.setTypeface(Typeface.DEFAULT);
                }
            }
        });
    }

    public void btmPantallaInicioSesion(View view) {
        finish();
    }

    public void btmRegistrar(View view) {
        Toast aviso;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        String nomUsuario = _txtUsuario.getText().toString().trim();
        String apellidoPaterno = _txtUsuarioApellidoPaterno.getText().toString().trim();
        String apellidoMaterno = _txtUsuarioApellidoMaterno.getText().toString().trim();
        String correo = _txtCorreo.getText().toString().trim();
        String password = _txtPassword.getText().toString().trim();
        String rePassword = _txtRePassword.getText().toString().trim();

        if (nomUsuario.equals("")) {
            _txtUsuario.setBackgroundResource(R.drawable.login_textview_error);

            aviso = Toast.makeText(getApplicationContext(), "Ingrese el nombre de usuario", Toast.LENGTH_SHORT);
            aviso.show();
        } else {
            _txtUsuario.setBackgroundResource(R.drawable.login_textview_backgound);

            if (apellidoPaterno.equals(""))
                _txtUsuarioApellidoPaterno.setBackgroundResource(R.drawable.login_textview_error);
            else {
                _txtUsuarioApellidoPaterno.setBackgroundResource(R.drawable.login_textview_backgound);

                if (apellidoMaterno.equals("")) {
                    _txtUsuarioApellidoMaterno.setBackgroundResource(R.drawable.login_textview_error);
                } else {
                    _txtUsuarioApellidoMaterno.setBackgroundResource(R.drawable.login_textview_backgound);

                    if (correo.equals("")) {
                        _txtCorreo.setBackgroundResource(R.drawable.login_textview_error);

                        aviso = Toast.makeText(getApplicationContext(), "Ingrese el correo", Toast.LENGTH_SHORT);
                        aviso.show();
                    } else {
                        _txtCorreo.setBackgroundResource(R.drawable.login_textview_backgound);

                        if (!correo.matches(emailPattern)) {
                            _txtCorreo.setBackgroundResource(R.drawable.login_textview_error);
                            aviso = Toast.makeText(getApplicationContext(), "Correo invalido", Toast.LENGTH_SHORT);
                            aviso.show();
                        } else {
                            _txtCorreo.setBackgroundResource(R.drawable.login_textview_backgound);

                            if (password.equals("")) {
                                _txtPassword.setBackgroundResource(R.drawable.login_textview_error);
                                aviso = Toast.makeText(getApplicationContext(), "Ingrese alguna contrasena", Toast.LENGTH_SHORT);
                                aviso.show();
                            } else {
                                _txtPassword.setBackgroundResource(R.drawable.login_textview_backgound);

                                if (rePassword.equals("")) {
                                    _txtRePassword.setBackgroundResource(R.drawable.login_textview_error);
                                    aviso = Toast.makeText(getApplicationContext(), "Reescriba la contrasena", Toast.LENGTH_SHORT);
                                    aviso.show();
                                } else {
                                    if (password.equals(rePassword)) {

                                        boolean addUser = userDao.addUsuario(
                                                TAG,
                                                getApplicationContext(),
                                                nomUsuario,
                                                apellidoPaterno,
                                                apellidoMaterno,
                                                correo,
                                                password,
                                                0
                                        );

                                        if (addUser == true){
                                            aviso = Toast.makeText(getApplicationContext(), "Cuenta creada correctamente", Toast.LENGTH_SHORT);
                                            aviso.show();
                                        }
                                        else{
                                            aviso = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                                            aviso.show();
                                        }
                                    } else {
                                        aviso = Toast.makeText(getApplicationContext(), "La contrasena no coincide", Toast.LENGTH_SHORT);
                                        aviso.show();
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
        _txtUsuario = (EditText) findViewById(R.id.txtUsuarioNombre);
        _txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        _txtPassword = (EditText) findViewById(R.id.txtPassword);
        _txtRePassword = (EditText) findViewById(R.id.txtRePassword);
        _txtUsuarioApellidoPaterno = (EditText) findViewById(R.id.txtUsuarioApellidoPaterno);
        _txtUsuarioApellidoMaterno = (EditText) findViewById(R.id.txtUsuarioApellidoMaterno);
    }

}
