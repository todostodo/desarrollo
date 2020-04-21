package com.example.desarrollo.Precentacion;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.text.DateFormat;
import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Deque;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.content.Context;
import android.widget.Toast;

import com.example.desarrollo.Datos.Calculos;
import com.example.desarrollo.Datos.ConexionSQLHelper;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.TutorDao;
import com.example.desarrollo.Entidades.Nino;
import com.example.desarrollo.Precentacion.Home.HomeFragment;

import com.example.desarrollo.Precentacion.Motivadores.MotivadoresFragment;
import com.example.desarrollo.Precentacion.Perfil.PerfilFragment;
import com.example.desarrollo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.Context.MODE_PRIVATE;


public class MainActivity extends AppCompatActivity {
    private int contador = 0, estado = 1;
    private int alarmID = 1;
    private String inicio = "";

    private FrameLayout mMainFrame;
    private String currentTag = "home";

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferenc = getSharedPreferences("Calculo", MODE_PRIVATE);
        int llave1 = preferenc.getInt("llave1", 0);
        int llave2 = preferenc.getInt("llave2", 0);
        int llave3 = preferenc.getInt("llave3", 0);

        System.out.println(llave1+" , "+llave2+" , "+llave3);
        if(llave1 ==0 || llave2==0 || llave3==0) {
            Calculos.generaLBF(this, 1);
            Calculos.generaLBF(this, 2);
            Calculos.generaLBV(this, 1);
            Calculos.generaLBV(this, 2);
            Calculos.generaLBUlPro(this, 1);
            Calculos.generaLBUlPro(this, 2);
        }else {
            Calculos.EsfuerzoF(this, 1);
            Calculos.EsfuerzoF(this, 2);
            Calculos.EsfuerzoV(this, 1);
            Calculos.EsfuerzoV(this, 2);
            Calculos.EsfuerzoUP(this, 1);
            Calculos.EsfuerzoUP(this, 2);
        }


        Date date = new Date();
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        inicio = "" + hora.format(date);

        Calculos.inicializarFichasAlimento(this);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btmNavegacion);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        mMainFrame = (FrameLayout) findViewById(R.id.fragmentContainer);


    }

    HomeFragment homeFragment = new HomeFragment();
    MotivadoresFragment motivadoresFragment = new MotivadoresFragment();
    PerfilFragment perfilFragment = new PerfilFragment();

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.menu_home:
                            loadFragment(homeFragment);
                            return true;

                        case R.id.menu_motivadores:
                            loadFragment(motivadoresFragment);
                            return true;
                    }
                    return false;
                }
            };

    private void loadFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        ft.commit();
    }

    @Override
    protected void onDestroy() {

        hora(inicio, this);

        super.onDestroy();

    }

    public static void hora(String inicio, Context con) {
        int inih = 0, inim = 0, inis = 0, finh = 0, finm = 0, fins = 0, segundos1 = 0, segundos2 = 0;
        String cadena = "", valor = "";

        Date date = new Date();
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        cadena = "" + hora.format(date);
        System.out.println(cadena);

        valor = "" + inicio.charAt(0);
        valor = valor + "" + inicio.charAt(1);
        inih = Integer.parseInt(valor);
        valor = "";
        valor = "" + inicio.charAt(3);
        valor = valor + "" + inicio.charAt(4);
        inim = Integer.parseInt(valor);
        valor = "";
        valor = "" + inicio.charAt(6);
        valor = valor + "" + inicio.charAt(7);
        inis = Integer.parseInt(valor);
        valor = "";
        System.out.println(inih + "," + inim + "," + inis);

        valor = "" + cadena.charAt(0);
        valor = valor + "" + cadena.charAt(1);
        finh = Integer.parseInt(valor);
        valor = "";
        valor = "" + cadena.charAt(3);
        valor = valor + "" + cadena.charAt(4);
        finm = Integer.parseInt(valor);
        valor = "";
        valor = "" + cadena.charAt(6);
        valor = valor + "" + cadena.charAt(7);
        fins = Integer.parseInt(valor);
        valor = "";
        System.out.println(finh + "," + finm + "," + fins);

        // metodosDB.registrar(con,""+finh,""+finm, ""+fins);

        segundos1 = (inih * 60) * 60;
        segundos1 += inim * 60;
        segundos1 += inis;

        segundos2 = (finh * 60) * 60;
        segundos2 += finm * 60;
        segundos2 += fins;

        segundos1 = segundos2 - segundos1;

        finh = segundos1 / 3600;
        finm = (segundos1 - (3600 * finh)) / 60;
        fins = segundos1 - ((finh * 3600) + (finm * 60));

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}
