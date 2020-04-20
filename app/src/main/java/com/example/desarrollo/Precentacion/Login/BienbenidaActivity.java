package com.example.desarrollo.Precentacion.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.R;

public class BienbenidaActivity extends AppCompatActivity {

    private Button _btnOptionLogin, _btnOptionSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienbenida_activity);

        init();


        _btnOptionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciarSesion = new Intent(getApplicationContext(), IniciarSesion.class);
                startActivity(iniciarSesion);
            }
        });
        _btnOptionSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrarse = new Intent(getApplicationContext(), RegistroUsuario.class);
                startActivity(registrarse);
            }
        });

    }

    private void init(){
        _btnOptionLogin = (Button) findViewById(R.id.btnOptionLogin);
        _btnOptionSignUp = (Button) findViewById(R.id.btnOptionSignUp);
    }
}
