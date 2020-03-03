package com.example.desarrollo.Precentacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.content.Context;

import com.example.desarrollo.Precentacion.Home.HomeFragment;

import com.example.desarrollo.Precentacion.Motivadores.MotivadoresFragment;
import com.example.desarrollo.Precentacion.Perfil.PerfilFragment;
import com.example.desarrollo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private int contador=0,estado=1 ;
    HomeFragment homeFragment = new HomeFragment();
    MotivadoresFragment motivadoresFragment = new MotivadoresFragment();
    PerfilFragment perfilFragment = new PerfilFragment();
    private String inicio="";

    FrameLayout mMainFrame;
    private String currentTag = "home";

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date date = new Date();
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        inicio= ""+hora.format(date);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btmNavegacion);
        mMainFrame = (FrameLayout) findViewById(R.id.fragmentContainer);

        if (savedInstanceState == null){
            loadFirstFragment();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        estado++;
                        currentTag = "home";
                        loadFragment(homeFragment, currentTag);
                        break;
                    case R.id.menu_motivadores:
                        estado++;
                        contador=0;
                        currentTag = "motivadores";
                        loadFragment(motivadoresFragment, currentTag);
                        break;
                    case R.id.menu_perfil:
                        estado++;
                        contador=0;
                        currentTag = "perfil";
                        loadFragment(perfilFragment, currentTag);
                        break;
                }

                return true;
            }
        });
    }

    //Desmadre
    private void loadFirstFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainer, homeFragment, "home");
        transaction.commit();
    }

    private void loadFragment(Fragment fragment, String tag) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        //ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        contador++;
        if(estado == 1){
            super.onBackPressed();
            super.onBackPressed();
        }

        bottomNavigationView.setSelectedItemId(R.id.menu_home);


        if(contador == 2){
            super.onBackPressed();
            super.onBackPressed();
        }

    }

    @Override
    protected void onDestroy() {

        hora(inicio,this);

        super.onDestroy();

    }

    public static void hora(String inicio, Context con){
        int inih=0,inim=0,inis=0,finh=0,finm=0,fins=0,segundos1=0,segundos2=0;
        String cadena="",valor="";

        Date date = new Date();
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        cadena= ""+hora.format(date);
        System.out.println(cadena);

        valor=""+inicio.charAt(0);
        valor=valor+""+inicio.charAt(1);
        inih = Integer.parseInt(valor);
        valor="";
        valor=""+inicio.charAt(3);
        valor=valor+""+inicio.charAt(4);
        inim= Integer.parseInt(valor);
        valor="";
        valor=""+inicio.charAt(6);
        valor=valor+""+inicio.charAt(7);
        inis= Integer.parseInt(valor);
        valor="";
        System.out.println(inih+","+inim+","+inis);

        valor=""+cadena.charAt(0);
        valor=valor+""+cadena.charAt(1);
        finh = Integer.parseInt(valor);
        valor="";
        valor=""+cadena.charAt(3);
        valor=valor+""+cadena.charAt(4);
        finm= Integer.parseInt(valor);
        valor="";
        valor=""+cadena.charAt(6);
        valor=valor+""+cadena.charAt(7);
        fins= Integer.parseInt(valor);
        valor="";
        System.out.println(finh+","+finm+","+fins);

       // metodosDB.registrar(con,""+finh,""+finm, ""+fins);

        segundos1 = (inih*60)*60;
        segundos1 += inim*60;
        segundos1 += inis;

        segundos2 = (finh*60)*60;
        segundos2 += finm*60;
        segundos2 += fins;

        segundos1=segundos2-segundos1;

        finh=segundos1/3600;
        finm=(segundos1-(3600*finh))/60;
        fins=segundos1-((finh*3600)+(finm*60));

    }
}

