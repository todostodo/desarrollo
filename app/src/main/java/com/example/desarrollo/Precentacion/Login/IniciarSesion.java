package com.example.desarrollo.Precentacion.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.desarrollo.Datos.InicioSesionDao;
import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.Precentacion.MainActivity;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Toastp;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class IniciarSesion extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private Button _btnLoginUsuario, _btnLoginTutor;
    private ImageView _btnLoginGoogle;
    private RelativeLayout _btnInicarSesion, _btnLoginFacebook, _btnCerrarLogin;
    private TextView _txtCorreoUsuario, _txtPassUsuario, _btnOlvidePassword;
    private LoginButton _btnLoginFacebookOnclick;
    private String sEmail, sPassword;

    private boolean inicioSesionUsuario = true;
    private Toastp toastp;
    private InicioSesionDao inicioSesionDao;
    private UserDao userDao;

    private GoogleApiClient googleApiClient;
    private CallbackManager callbackManager;

    private static final String TAG = "IniciarSesion";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //iniciarSesionGoogle();
        //iniciarSesionFacebook();

        setContentView(R.layout.inicio_sesion_activity);
        init();

        _btnLoginUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnLoginUsuario.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8646FF")));
                _btnLoginUsuario.setTextColor(getResources().getColor(R.color.blanco));
                _btnLoginTutor.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blanco)));
                _btnLoginTutor.setTextColor(Color.parseColor("#A4A5AA"));


                inicioSesionUsuario = true;
            }
        });
        _btnLoginTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnLoginTutor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8646FF")));
                _btnLoginTutor.setTextColor(getResources().getColor(R.color.blanco));
                _btnLoginUsuario.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blanco)));
                _btnLoginUsuario.setTextColor(Color.parseColor("#A4A5AA"));

                inicioSesionUsuario = false;
            }
        });
        _btnCerrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _btnInicarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        _btnOlvidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecuperarPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void iniciarSesion() {
        String correo = _txtCorreoUsuario.getText().toString().trim();
        String password = _txtPassUsuario.getText().toString();

        if (inicioSesionUsuario == true) {

            if (correo.isEmpty()) {
                toastp.toastp(getApplicationContext(), "Ingrese el nombre de correo");
            } else {

                if (password.isEmpty()) {

                    toastp.toastp(getApplicationContext(), "Ingrese la contraseña");
                } else {

                    sEmail = correo;
                    sPassword = password;
                    new loadLogin().execute("login");

                }
            }
        }
        if (inicioSesionUsuario == false) {

            if (correo.isEmpty()) {
                toastp.toastp(getApplicationContext(), "Ingrese el correo");
            } else {

                if (password.isEmpty()) {

                    toastp.toastp(getApplicationContext(), "Ingrese la contraseña");
                } else {
                    boolean existeUsuarioTutor = inicioSesionDao.verificarUsuarioTutor(TAG, getApplicationContext(), correo, password);

                    if (existeUsuarioTutor == true) {

                        Intent activityPrincipal = new Intent(this, MainActivity.class);
                        startActivity(activityPrincipal);

                        //savePreferencesDato(correo);

                    } else {
                        toastp.toastp(getApplicationContext(), "El correo o contraseña son incorrectos");
                    }
                }
            }
        }
    }

    private void iniciarSesionGoogle() {

        GoogleSignInOptions sign = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, sign)
                .build();

        _btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent permitirAcceso = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(permitirAcceso, 777);
            }
        });
    }

    private void iniciarSesionFacebook() {

        _btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnLoginFacebookOnclick.performClick();
            }
        });

        callbackManager = CallbackManager.Factory.create();

        _btnLoginFacebookOnclick.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                toastp.toastp(getApplicationContext(), "Cancelado");
            }

            @Override
            public void onError(FacebookException error) {
                toastp.toastp(getApplicationContext(), "Error");
            }
        });
    }

    private void init() {
        _btnLoginUsuario = (Button) findViewById(R.id.btnLoginUsuario);
        _btnLoginTutor = (Button) findViewById(R.id.btnLoginTutor);
        _btnLoginGoogle = (ImageView) findViewById(R.id.btnLoginGoogle);
        _btnLoginFacebook = (RelativeLayout) findViewById(R.id.btnLoginFacebook);
        _btnInicarSesion = (RelativeLayout) findViewById(R.id.btnIniciarSesion);
        _btnCerrarLogin = (RelativeLayout) findViewById(R.id.btnCerrarLogin);
        _txtCorreoUsuario = (TextView) findViewById(R.id.txtCorreoUsuario);
        _txtPassUsuario = (TextView) findViewById(R.id.txtPassUsuario);
        _btnLoginFacebookOnclick = (LoginButton) findViewById(R.id.btnLoginFacebookOnclick);
        _btnOlvidePassword = (TextView) findViewById(R.id.btnOlvidePassword);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 777) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen();

        } else {
            //Toast.makeText(this, "No se pudo", Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        SharedPreferences preferences = getSharedPreferences("Usuario", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("inicioAutomatico", true);
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void loginUserWebService(final String correo, final String password) {

        String url = "http://161.35.14.188/Persuhabit/usuario/login";
        RequestQueue queue = Volley.newRequestQueue(this);

        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        stringObjectMap.put("correo", correo);
        stringObjectMap.put("pwdu", password);

        JSONObject jsonObject = new JSONObject(stringObjectMap);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String success = response.getString("message");
                    Log.v(TAG, "message -------------------------------- " + success);
                    if (success.equals("success")) {

                        int idGlobla = response.getInt("data");
                        boolean estadoUsuario = userDao.estadoUsuario(TAG, getApplicationContext());

                        if (estadoUsuario == true) {

                            Intent activityPrincipal = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(activityPrincipal);
                            savePreferencesDato(correo, idGlobla);

                        } else {

                            Intent introduccion = new Intent(getApplicationContext(), IntroduccionActivity.class);
                            startActivity(introduccion);

                            savePreferencesDato(correo, idGlobla);

                        }

                    } else {
                        toastp.toastp(getApplicationContext(), "El usuario o constraseña ingresados son incorrectos");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        queue.add(jsonObjectRequest);

    }

    private void savePreferencesDato(String correo, int idGlobal) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Usuario", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("inicioAutomatico", true);
        editor.putString("correoUsuario", correo);
        editor.putInt("idGlobal", idGlobal);
        editor.commit();
    }

    private class loadLogin extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(IniciarSesion.this,
                    "", "Iniciado sesión", true, false);
        }

        @Override
        protected String doInBackground(String... strings) {
            loginUserWebService(sEmail, sPassword);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }
    }

}
