package com.example.desarrollo.CLIENTE.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.desarrollo.R;

public class login extends AppCompatActivity {

    EditText _txtUsuario, _txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        _txtPassword = (EditText) findViewById(R.id.txtPassword);
        _txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //_txtPassword.setCompoundDrawablesWithIntrinsicBounds(R.color.borde_azul, 0, 0,0);
                    _txtPassword.setTextColor(getResources().getColor(R.color.borde_azul));
                    _txtPassword.setTypeface(Typeface.DEFAULT_BOLD);
                }
                else{
                    //_txtPassword.setCompoundDrawablesWithIntrinsicBounds(R.color.gris, 0, 0,0);
                    _txtPassword.setTextColor(getResources().getColor(R.color.negro));
                    _txtPassword.setTypeface(Typeface.DEFAULT);
                }
            }
        });

    }


    public void btmPantallaRegistro(View view) {
        Intent intent = new Intent(this, login_registro.class);
        startActivity(intent);
    }
}
