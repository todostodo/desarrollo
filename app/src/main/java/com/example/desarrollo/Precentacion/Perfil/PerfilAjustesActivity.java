package com.example.desarrollo.Precentacion.Perfil;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.desarrollo.Datos.InicioSesionDao;
import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.Precentacion.Login.BienbenidaActivity;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PerfilAjustesActivity extends AppCompatActivity {

    private RelativeLayout _btnCerrarAjustes;
    private LinearLayout _btnEditarPerfil, _btnCambiarPassword;
    private CardView _btnCerrarSesion;

    private Dialog dialogEditCorreo, dialogEditPassword, dialogConfirmPasswordEdit;

    private InicioSesionDao inicioSesionDao;
    private UserDao userDao;
    private Toastp toastp;

    private static final String TAG = "PerfilAjustesActivity";

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
                editCorreo();
            }
        });

        _btnCambiarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPassword();
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
                editor.remove("idGlobal");
                editor.commit();

                finishAffinity();
                Intent bienbenenida = new Intent(getApplicationContext(), BienbenidaActivity.class);
                startActivity(bienbenenida);
            }
        });

    }

    private void editCorreo() {

        dialogEditCorreo = new Dialog(PerfilAjustesActivity.this);
        dialogEditCorreo.setContentView(R.layout.perfil_edit_correo);
        dialogEditCorreo.setCancelable(false);
        dialogEditCorreo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEditCorreo.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btnAceptarEditCorreo = (Button) dialogEditCorreo.findViewById(R.id.btnAceptarEditCorreo);
        Button btnCancelarEditCorreo = (Button) dialogEditCorreo.findViewById(R.id.btnCancelarEditCorreo);
        final EditText txtCorreoUsuarioEdit = (EditText) dialogEditCorreo.findViewById(R.id.txtCorreoUsuarioEdit);

        btnAceptarEditCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String correo = txtCorreoUsuarioEdit.getText().toString();
                String validacionCorreo = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (correo.equals("")) {
                    toastp.toastp(getApplicationContext(), "Ingrese el nuevo correo electrónico");
                } else {
                    if (!correo.matches(validacionCorreo)) {
                        toastp.toastp(getApplicationContext(), "El formato del correo electrónico ingresado es incorrecto");
                    } else {

                        dialogConfirmPasswordEdit = new Dialog(PerfilAjustesActivity.this);
                        dialogConfirmPasswordEdit.setContentView(R.layout.confirmar_password_usuario);
                        dialogConfirmPasswordEdit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialogConfirmPasswordEdit.setCancelable(false);

                        final EditText _txtConfirmarPasswordUsuario = (EditText) dialogConfirmPasswordEdit.findViewById(R.id.txtConfirmarPasswordUsuario);
                        Button _btnConfirmarPassword = (Button) dialogConfirmPasswordEdit.findViewById(R.id.btnConfirmarPassword);
                        Button _btnCancelarConfirmarPassw = (Button) dialogConfirmPasswordEdit.findViewById(R.id.btnCancelarConfirmarPassw);

                        _btnConfirmarPassword.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String password = _txtConfirmarPasswordUsuario.getText().toString();
                                final int idUsuario = readerPreferencesIdUser();
                                boolean confirmPass = inicioSesionDao.verificarPasswordUser(TAG, getApplicationContext(), idUsuario, password);

                                if (confirmPass == true) {

                                    String url = "http://68.183.148.243/Persuhabit/usuario/correo";
                                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("correo", correo);
                                    params.put("idusu", idUsuario);

                                    JSONObject jsonObj = new JSONObject(params);

                                    JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                                            (Request.Method.PUT, url, jsonObj, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    try {
                                                        String success = response.getString("data");
                                                        if (success.equals("success")) {

                                                            boolean editEmail = userDao.editEmailUser(TAG, getApplicationContext(), idUsuario, correo);

                                                            if (editEmail == true) {

                                                                toastp.toastp(getApplicationContext(), "El correo se ha editado correctamente");
                                                                dialogEditCorreo.dismiss();
                                                                dialogConfirmPasswordEdit.dismiss();
                                                            }
                                                        } else {
                                                            toastp.toastp(getApplicationContext(), "Algo salio mal");
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            },
                                                    new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError error) {
                                                        }
                                                    });

                                    queue.add(jsonObjRequest);

                                } else {
                                    toastp.toastp(getApplicationContext(), "La contraseña ingresada es incorrecta");
                                }
                            }
                        });

                        _btnCancelarConfirmarPassw.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogConfirmPasswordEdit.dismiss();
                            }
                        });

                        dialogConfirmPasswordEdit.show();
                    }
                }
            }
        });

        btnCancelarEditCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEditCorreo.dismiss();
            }
        });

        dialogEditCorreo.show();

    }

    private void editPassword() {

        dialogEditPassword = new Dialog(PerfilAjustesActivity.this);
        dialogEditPassword.setContentView(R.layout.perfil_edit_password);
        dialogEditPassword.setCancelable(false);
        dialogEditPassword.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEditPassword.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final EditText _txtNewPasswordUser = (EditText) dialogEditPassword.findViewById(R.id.txtNewPasswordUser);
        final EditText _txtRePasswordUser = (EditText) dialogEditPassword.findViewById(R.id.txtRePasswordUser);
        Button _btnAceptarEditPassword = (Button) dialogEditPassword.findViewById(R.id.btnAceptarEditPassword);
        Button _btnCancelarEditPassword = (Button) dialogEditPassword.findViewById(R.id.btnCancelarEditPassword);

        _btnAceptarEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password = _txtNewPasswordUser.getText().toString();
                String rePassword = _txtRePasswordUser.getText().toString();

                if (password.equals("")) {
                    toastp.toastp(getApplicationContext(), "Ingrese la contraseña");
                } else {
                    if (rePassword.equals("")) {
                        toastp.toastp(getApplicationContext(), "Reescriba la contraseña");
                    } else {
                        if (!password.equals(rePassword)) {
                            toastp.toastp(getApplicationContext(), "La contraseña no conicide");
                        } else {

                            dialogConfirmPasswordEdit = new Dialog(PerfilAjustesActivity.this);
                            dialogConfirmPasswordEdit.setContentView(R.layout.confirmar_password_usuario);
                            dialogConfirmPasswordEdit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialogConfirmPasswordEdit.setCancelable(false);

                            final EditText _txtConfirmarPasswordUsuario = (EditText) dialogConfirmPasswordEdit.findViewById(R.id.txtConfirmarPasswordUsuario);
                            TextView _textConfirmarPass = (TextView) dialogConfirmPasswordEdit.findViewById(R.id.textConfirmarPass);
                            Button _btnConfirmarPassword = (Button) dialogConfirmPasswordEdit.findViewById(R.id.btnConfirmarPassword);
                            Button _btnCancelarConfirmarPassw = (Button) dialogConfirmPasswordEdit.findViewById(R.id.btnCancelarConfirmarPassw);

                            _textConfirmarPass.setText("Confirmar constraseña actual");

                            _btnConfirmarPassword.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    String confirmPass = _txtConfirmarPasswordUsuario.getText().toString();
                                    final int idUsuario = readerPreferencesIdUser();

                                    if (confirmPass.equals("")) {
                                        toastp.toastp(getApplicationContext(), "Cofirme la contraseña");
                                    } else {

                                        boolean passCorrect = inicioSesionDao.verificarPasswordUser(TAG, getApplicationContext(), idUsuario, confirmPass);
                                        if (!passCorrect == true) {
                                            toastp.toastp(getApplicationContext(), "La contraseña ingresada es incorrecta");
                                        } else {

                                            String url = "http://68.183.148.243/Persuhabit/usuario/password";
                                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                                            Map<String, Object> params = new HashMap<String, Object>();
                                            params.put("pwdu", password);
                                            params.put("idusu", idUsuario);

                                            JSONObject jsonObj = new JSONObject(params);

                                            JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                                                    (Request.Method.PUT, url, jsonObj, new Response.Listener<JSONObject>() {
                                                        @Override
                                                        public void onResponse(JSONObject response) {

                                                            try {
                                                                String success = response.getString("data");
                                                                if (success.equals("success")) {

                                                                    boolean edit = userDao.editPasswordUser(TAG, getApplicationContext(), idUsuario, password);
                                                                    if (edit == true) {
                                                                        toastp.toastp(getApplicationContext(), "La contraseña fue actualizada correctamente");
                                                                        dialogEditPassword.dismiss();
                                                                        dialogConfirmPasswordEdit.dismiss();
                                                                    }
                                                                } else {
                                                                    toastp.toastp(getApplicationContext(), "Algo salio mal");
                                                                }
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }

                                                        }
                                                    },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {
                                                                }
                                                            });

                                            queue.add(jsonObjRequest);

                                        }
                                    }
                                }
                            });

                            _btnCancelarConfirmarPassw.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogConfirmPasswordEdit.dismiss();
                                }
                            });

                            dialogConfirmPasswordEdit.show();

                        }
                    }
                }
            }
        });

        _btnCancelarEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEditCorreo.dismiss();
            }
        });

        dialogEditPassword.show();

    }

    private int readerPreferencesIdUser() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Usuario", MODE_PRIVATE);
        int idUsuario = sharedPreferences.getInt("idGlobal", 0);
        return idUsuario;
    }

    private void init() {
        _btnCerrarAjustes = (RelativeLayout) findViewById(R.id.btnCerrarAjustes);
        _btnEditarPerfil = (LinearLayout) findViewById(R.id.btnEditarPerfilAjustes);
        _btnCambiarPassword = (LinearLayout) findViewById(R.id.btnCambiarPasswordPerfil);
        _btnCerrarSesion = (CardView) findViewById(R.id.btnCerrarSesion);
    }
}
