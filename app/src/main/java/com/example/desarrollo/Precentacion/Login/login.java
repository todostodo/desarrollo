package com.example.desarrollo.Precentacion.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.desarrollo.Precentacion.MainActivity;
import com.example.desarrollo.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    FragInicioUsu frgU;
    FagInicioTutor frgT;
    private GoogleApiClient googleApiClient;
    private SignInButton _btn;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        Context con=this;
        SharedPreferences preferences=getSharedPreferences("Archivo",con.MODE_PRIVATE);
        int inpref=preferences.getInt("volor",0);

        if(inpref==1){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        frgU=new FragInicioUsu();
        frgT=new FagInicioTutor();


        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFrag,frgU).commit();

        //////////////////////////////////////////////////CODIGO PARA LOGIN GOOGLE---------------------------
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        _btn = (SignInButton) findViewById(R.id.btn);

        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,777);
            }
        });
        /////////////////////////////////////////////////END GOOGLE------------------------------------------

        /////////////////////////////////////////////CODIGO PARA LOGIN   FACEBOOK----------------------------

        callbackManager = CallbackManager.Factory.create();
        loginButton=(LoginButton) findViewById(R.id.btnF);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }

            @Override
            public void onCancel() {

                Toast.makeText(getApplicationContext(),"Cancelado",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
        /////////////////////////////////////////////////END FACEBOOK------------------------------------------
    }

    public void btmPantallaRegistro(View view) {
        Intent intent = new Intent(this, login_registro.class);
        startActivity(intent);
    }

    public void onClick(View view)  {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

        switch (view.getId()){

            case R.id.btnUs:
                fragmentTransaction.replace(R.id.contenedorFrag,frgU);
                break;
            case R.id.btnTu:
                fragmentTransaction.replace(R.id.contenedorFrag,frgT);
                break;
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==777){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }else{
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            goMainScreen();

        }else {
            Toast.makeText(this,"No se pudo",Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        SharedPreferences preferences=getSharedPreferences("Archivo",this.MODE_PRIVATE);
        //SharedPreferences preferences1=getActivity().getPreferences(getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("volor",1);
        editor.commit();
        Intent intent= new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}