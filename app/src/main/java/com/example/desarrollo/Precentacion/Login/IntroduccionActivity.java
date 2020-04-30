package com.example.desarrollo.Precentacion.Login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.desarrollo.Entidades.IntroduccionScreenItem;
import com.example.desarrollo.LogicaNegocio.Adapter.IntroduccionViewPagerAdapter;
import com.example.desarrollo.Precentacion.Home.HijoRegistroActivity;
import com.example.desarrollo.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroduccionActivity extends AppCompatActivity {

    private ViewPager screenPager;
    private IntroduccionViewPagerAdapter introduccionViewPagerAdapter;
    private TabLayout tabIndicador;
    private Button btnNext;
    private int position = 0;
    private Button btnGetStarted;
    private Animation btnAnim;
    private RelativeLayout tvSkip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduccion_activity);
        init();

        //getSupportActionBar().hide();

        final List<IntroduccionScreenItem> listScreemItem = new ArrayList<>();
        listScreemItem.add(new IntroduccionScreenItem("Antes de comenzar", "Te mostraremos algunas indicaciones de la funcionalidad de la aplicación para que pueda entender la dinámica.", R.drawable.introduccion_img_4));
        listScreemItem.add(new IntroduccionScreenItem("Frutas", "El niño deberá de consumir las porciones de frutas que el sistema le indica por día, este se aumentara cada 7 días de acuerdo a su desempeño, el objetivo es llegar a 2 porciones de consumo diarios.", R.drawable.introduccion_img_1));
        listScreemItem.add(new IntroduccionScreenItem("Verduras", "Al igual que las frutas deberá consumir las porciones que el sistema le indica el objetivo es llegar a 3 porciones de consumo diario.", R.drawable.introduccion_img_2));
        listScreemItem.add(new IntroduccionScreenItem("Fichas y motivadores", "Se le darán fichas al niño si logra consumir las porciones de frutas y verduras del día al igual si logra consumir uno que no le gusta o no conoce también se le darán fichas por disminuir el consumo de comida ultra procesada, máximo de niños a registrar 2.", R.drawable.introduccion_img_3));
        listScreemItem.add(new IntroduccionScreenItem("¡¡Importante!!", "Los primero 7 días el niño no podrá generar fichas ya que mediremos su línea base de frutas y verduras para poder asignarle las porciones a consumir en el día", R.drawable.introduccion_img_5));

        screenPager = findViewById(R.id.screen_viewpager);
        introduccionViewPagerAdapter = new IntroduccionViewPagerAdapter(this, listScreemItem);
        screenPager.setAdapter(introduccionViewPagerAdapter);

        tabIndicador.setupWithViewPager(screenPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < listScreemItem.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == listScreemItem.size() - 1) {
                    cargarRegistroNino();
                }
            }
        });

        tabIndicador.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == listScreemItem.size() - 1) {
                    cargarRegistroNino();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registroNino = new Intent(getApplicationContext(), HijoRegistroActivity.class);
                startActivity(registroNino);
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(listScreemItem.size());
            }
        });

    }

    private void cargarRegistroNino() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicador.setVisibility(View.INVISIBLE);

        btnGetStarted.setAnimation(btnAnim);
    }

    private void init() {
        btnNext = (Button) findViewById(R.id.btn_next);
        btnGetStarted = (Button) findViewById(R.id.btn_get_started);
        tabIndicador = (TabLayout) findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = (RelativeLayout) findViewById(R.id.tv_skip);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}
