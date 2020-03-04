package com.example.desarrollo.Precentacion.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.desarrollo.R;

public class login extends AppCompatActivity {

    EditText _txtUsuario, _txtPassword;
    ImageButton _btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();

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

        _btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = _txtUsuario.getText().toString().trim();
                String password = _txtPassword.getText().toString().trim();

                if (usuario.equals("")){
                    _txtUsuario.setBackgroundResource(R.drawable.login_textview_error);
                }
                else{
                    _txtUsuario.setBackgroundResource(R.drawable.login_textview_backgound);

                    if (password.equals("")){
                        _txtPassword.setBackgroundResource(R.drawable.login_textview_error);
                    }
                    else{
                        _txtPassword.setBackgroundResource(R.drawable.login_textview_backgound);


                    }
                }
            }
        });


    }

    private void init() {
        _txtUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
        _txtPassword = (EditText) findViewById(R.id.txtPassword);
        _btnIniciarSesion = (ImageButton) findViewById(R.id.btnIniciarSesion);
    }


    public void btmPantallaRegistro(View view) {
        Intent intent = new Intent(this, login_registro.class);
        startActivity(intent);
    }
}
