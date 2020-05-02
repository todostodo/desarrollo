package com.example.desarrollo.Precentacion.Perfil;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.Datos.InicioSesionDao;
import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.Entidades.Usuario;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import java.util.ArrayList;

public class PerfilEditar extends AppCompatActivity {

    private TextView _txtNombreUsuarioEdit, _txtApellidoPaternoUsuarioEdit, _txtApellidoMaternoUsuarioEdit, _txtCorreoUsuarioEdit;
    private RelativeLayout _btnCerrarEditarPerfil;
    private ArrayList<Usuario> listUsuario = new ArrayList<>();
    private Button _btnEditarPerfilUsuario;
    private String confirmarPassUsuario, correoUsuarioEdit;
    private Dialog confirmarPasswordEditarDialog;
    private EditText _txtConfirmarPasswordUsuario;
    private Button _btnCancelarConfirmarPassw, _btnConfirmarPassword;


    private UserDao userDao;
    private Toastp toast;
    private InicioSesionDao inicioSesionDao;

    private static final String TAG = "PerfilEditar";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_editar_activity);

        init();
        obtenerDatosUsuario();

        _btnCerrarEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _btnEditarPerfilUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarPerfilUsuario();
            }
        });
    }

    private void editarPerfilUsuario() {
        String nombre = _txtNombreUsuarioEdit.getText().toString();
        String apellidoP = _txtApellidoPaternoUsuarioEdit.getText().toString();
        String apelldioM = _txtApellidoMaternoUsuarioEdit.getText().toString();
        String correo = _txtCorreoUsuarioEdit.getText().toString();
        String validacionCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (nombre.equals("")) {
            toast.toastp(getApplicationContext(), "Ingrese su nombre");
        } else {
            if (apellidoP.equals("")) {
                toast.toastp(getApplicationContext(), "Ingrese su apellido paterno");
            } else {
                if (apelldioM.equals("")) {
                    toast.toastp(getApplicationContext(), "Ingrese su apellido materno");
                } else {
                    if (correo.equals("")) {
                        toast.toastp(getApplicationContext(), "Ingrese su correo electronico");
                    } else {
                        if (!correo.matches(validacionCorreo)) {
                            toast.toastp(getApplicationContext(), "El formato del correo es invalido");
                        } else {
                            confirmarPasswordEditarDialog = new Dialog(PerfilEditar.this);
                            confirmarPasswordEditarDialog.setContentView(R.layout.confirmar_password_usuario);
                            confirmarPasswordEditarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            confirmarPasswordEditarDialog.setCancelable(false);
                            _txtConfirmarPasswordUsuario = (EditText) confirmarPasswordEditarDialog.findViewById(R.id.txtConfirmarPasswordUsuario);
                            _btnConfirmarPassword = (Button) confirmarPasswordEditarDialog.findViewById(R.id.btnConfirmarPassword);
                            _btnCancelarConfirmarPassw = (Button) confirmarPasswordEditarDialog.findViewById(R.id.btnCancelarConfirmarPassw);

                            _btnConfirmarPassword.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String password = _txtConfirmarPasswordUsuario.getText().toString();
                                    boolean confirmacion = inicioSesionDao.verificarUsuario(TAG, getApplicationContext(), correoUsuarioEdit, password);

                                    if (confirmacion == true) {
                                        toast.toastp(getApplicationContext(), "Password correcto");
                                        confirmarPasswordEditarDialog.dismiss();
                                        finish();
                                    } else {
                                        toast.toastp(getApplicationContext(), "Password incorrecto");
                                    }
                                }
                            });

                            _btnCancelarConfirmarPassw.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmarPasswordEditarDialog.dismiss();
                                }
                            });

                            confirmarPasswordEditarDialog.show();
                        }
                    }
                }
            }
        }
    }

    private void obtenerDatosUsuario() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Usuario", MODE_PRIVATE);
        correoUsuarioEdit = sharedPreferences.getString("correoUsuario", "no existe");

        if (!correoUsuarioEdit.equals("no existe")) {
            userDao.consultarDatosUsuatio(TAG, getApplicationContext(), correoUsuarioEdit, listUsuario);

            for (int i = 0; i < listUsuario.size(); i++) {
                _txtNombreUsuarioEdit.setText(listUsuario.get(i).getNombre());
                _txtApellidoPaternoUsuarioEdit.setText(listUsuario.get(i).getApellidoPaterno());
                _txtApellidoMaternoUsuarioEdit.setText(listUsuario.get(i).getApellidoMaterno());
                _txtCorreoUsuarioEdit.setText(listUsuario.get(i).getCorreo());
            }
        }
    }

    private void init() {
        _btnCerrarEditarPerfil = (RelativeLayout) findViewById(R.id.btnCerrarEditarPerfil);
        _txtNombreUsuarioEdit = (TextView) findViewById(R.id.txtNombreUsuarioEdit);
        _txtApellidoPaternoUsuarioEdit = (TextView) findViewById(R.id.txtApellidoPaternoUsuarioEdit);
        _txtApellidoMaternoUsuarioEdit = (TextView) findViewById(R.id.txtApellidoMaternoUsuarioEdit);
        _txtCorreoUsuarioEdit = (TextView) findViewById(R.id.txtCorreoUsuarioEdit);
        _btnEditarPerfilUsuario = (Button) findViewById(R.id.btnEditarPerfilUsuario);
    }
}
