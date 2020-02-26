package com.example.desarrollo.CLIENTE.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.desarrollo.R;

import java.util.regex.Pattern;

public class login_registro extends AppCompatActivity {

    EditText _txtUsuario, _txtCorreo, _txtPassword, _txtRePassword;
    ImageView _fondoImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        _txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        _txtUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //_txtUsuario.setCompoundDrawablesWithIntrinsicBounds(R.drawable.usuario_select, 0, 0,0);
                    _txtUsuario.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtUsuario.setTypeface(Typeface.DEFAULT_BOLD);
                }
                else{
                    //_txtUsuario.setCompoundDrawablesWithIntrinsicBounds(R.drawable.usuario_default, 0, 0,0);
                    _txtUsuario.setTextColor(getResources().getColor(R.color.negro));
                    _txtUsuario.setTypeface(Typeface.DEFAULT);
                }
            }
        });

        _txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        _txtCorreo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //_txtCorreo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gmail_select, 0, 0,0);
                    _txtCorreo.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtCorreo.setTypeface(Typeface.DEFAULT_BOLD);
                }
                else{
                    //_txtCorreo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gmail_default, 0, 0,0);
                    _txtCorreo.setTextColor(getResources().getColor(R.color.negro));
                    _txtCorreo.setTypeface(Typeface.DEFAULT);
                }
            }
        });

        _txtPassword = (EditText) findViewById(R.id.txtPassword);
        _txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //_txtPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_select, 0, 0,0);
                    _txtPassword.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtPassword.setTypeface(Typeface.DEFAULT_BOLD);
                }
                else{
                    //_txtPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_default, 0, 0,0);
                    _txtPassword.setTextColor(getResources().getColor(R.color.negro));
                    _txtPassword.setTypeface(Typeface.DEFAULT);
                }
            }
        });

        _txtRePassword = (EditText) findViewById(R.id.txtRePassword);
        _txtRePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //_txtRePassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_select, 0, 0,0);
                    _txtRePassword.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtRePassword.setTypeface(Typeface.DEFAULT_BOLD);
                }
                else{
                    //_txtRePassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_default, 0, 0,0);
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

        if (_txtUsuario.getText().toString().isEmpty() ){
            aviso = Toast.makeText(getApplicationContext(), "Ingrese el nombre de usuario", Toast.LENGTH_SHORT);
            aviso.show();
        }
        else{
            if (_txtCorreo.getText().toString().isEmpty()){
                aviso = Toast.makeText(getApplicationContext(), "Ingrese el correo", Toast.LENGTH_SHORT);
                aviso.show();
            }
            else{
                if (!_txtCorreo.getText().toString().trim().matches(emailPattern)){
                    aviso = Toast.makeText(getApplicationContext(), "Correo invalido", Toast.LENGTH_SHORT);
                    aviso.show();
                }
                else{
                    if (_txtPassword.getText().toString().isEmpty()){
                        aviso = Toast.makeText(getApplicationContext(), "Ingrese alguna contrasena", Toast.LENGTH_SHORT);
                        aviso.show();
                    }
                    else{
                        if (_txtRePassword.getText().toString().isEmpty()){
                            aviso = Toast.makeText(getApplicationContext(), "Reescriba la contrasena", Toast.LENGTH_SHORT);
                            aviso.show();
                        }
                        else{
                            if (_txtPassword.getText().toString().equals(_txtRePassword.getText().toString())){
                                aviso = Toast.makeText(getApplicationContext(), "Inicio de sesion CORRECTO", Toast.LENGTH_SHORT);
                                aviso.show();
                            }

                            else{
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
