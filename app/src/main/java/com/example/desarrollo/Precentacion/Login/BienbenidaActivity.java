package com.example.desarrollo.Precentacion.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.example.desarrollo.Datos.NotificacionDao;
import com.example.desarrollo.Precentacion.MainActivity;
import com.example.desarrollo.R;

import java.io.File;

import static com.example.desarrollo.Precentacion.MainActivity.NOTIFICACION_ID;

public class BienbenidaActivity extends AppCompatActivity {

    private Button _btnOptionLogin, _btnOptionSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        Intent bundle= getIntent();
        if (bundle != null) {
            String Titulo = bundle.getStringExtra("title");
            if(Titulo != null) {
                if (Titulo.equals("si")) {
                    NotificacionDao.insertVioNotificacion("notificacion", this, 1, 1);

                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                    notificationManagerCompat.cancel(NOTIFICACION_ID);
                }
            }
        }

        if (restorePreferenceData()){
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
        }

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

    private boolean restorePreferenceData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Usuario", MODE_PRIVATE);
        Boolean isIntroActivityOpenBefore = preferences.getBoolean("inicioAutomatico", false);
        return isIntroActivityOpenBefore;
    }

    private void init() {
        _btnOptionLogin = (Button) findViewById(R.id.btnOptionLogin);
        _btnOptionSignUp = (Button) findViewById(R.id.btnOptionSignUp);
    }
}
