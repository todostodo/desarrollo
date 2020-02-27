package com.example.desarrollo.CLIENTE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.desarrollo.CLIENTE.Home.HomeFragment;

import com.example.desarrollo.CLIENTE.Motivadores.MotivadoresFragment;
import com.example.desarrollo.CLIENTE.Perfil.PerfilFragment;
import com.example.desarrollo.DB.metodosDB;
import com.example.desarrollo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    MotivadoresFragment motivadoresFragment = new MotivadoresFragment();
    PerfilFragment perfilFragment = new PerfilFragment();

    FrameLayout mMainFrame;
    private String currentTag = "home";

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        currentTag = "home";
                        loadFragment(homeFragment, currentTag);
                        break;
                    case R.id.menu_motivadores:
                        currentTag = "motivadores";
                        loadFragment(motivadoresFragment, currentTag);
                        break;
                    case R.id.menu_perfil:
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
}

