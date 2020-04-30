package com.example.desarrollo.Precentacion.Perfil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.desarrollo.Precentacion.Login.BienbenidaActivity;
import com.example.desarrollo.R;

public class PerfilAjustesActivity extends AppCompatActivity {

    private RelativeLayout _btnCerrarAjustes;
    private LinearLayout _btnEditarPerfil, _btnCambiarPassword;
    private CardView _btnCerrarSesion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_ajustes_activity);

        init();

        _btnCerrarAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editarPerfil = new Intent(getApplicationContext(), PerfilEditar.class);
                startActivity(editarPerfil);
            }
        });

        _btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Usuario", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("inicioAutomatico", false);
                editor.remove("correoUsuario");
                editor.commit();

                finishAffinity();
                Intent bienbenenida = new Intent(getApplicationContext(), BienbenidaActivity.class);
                startActivity(bienbenenida);
            }
        });

    }

    private void init(){
        _btnCerrarAjustes = (RelativeLayout) findViewById(R.id.btnCerrarAjustes);
        _btnEditarPerfil = (LinearLayout) findViewById(R.id.btnEditarPerfilAjustes);
        _btnCambiarPassword = (LinearLayout) findViewById(R.id.btnCambiarPasswordPerfil);
        _btnCerrarSesion  = (CardView) findViewById(R.id.btnCerrarSesion);
    }
}
