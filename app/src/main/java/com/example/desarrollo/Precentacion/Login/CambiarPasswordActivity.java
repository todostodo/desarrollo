package com.example.desarrollo.Precentacion.Login;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

public class CambiarPasswordActivity extends AppCompatActivity {

    private EditText _txtNuevoPassword, _txtReNuevoPassword;
    private RelativeLayout _btnCerrarActivity;
    private Button _btnCambiarPassword;
    private String destinatarioCorreo;

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
                finish();
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
                                toastp.toastp(getApplicationContext(), "La contraseña coincide");

                                finish();
                                /*
                                1.- Verificar que la contrasena no sea la que tenia anteriormente
                                 */
                                /*
                                1.- ACTUALIZAR CONSTRASENA EN LAS 2 BASES DE DATOS
                                 */


                            } else {
                                toastp.toastp(getApplicationContext(), "La contraseña no coincide");
                            }
                        }
                    }
                }
            }
        });

        Toast.makeText(this, "" + destinatarioCorreo, Toast.LENGTH_SHORT).show();
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
}
