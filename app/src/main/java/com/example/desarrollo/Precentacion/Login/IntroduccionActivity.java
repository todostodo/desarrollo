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

        final List<IntroduccionScreenItem> listScreemItem = new ArrayList<>();
        listScreemItem.add(new IntroduccionScreenItem("Antes de comenzar", "Los consumos habituales de tu hijo de frutas verduras y ultra procesados, se deberán registrar en la aplicación durante una semana (línea base de consumos).", R.drawable.introduccion_img_4));
        listScreemItem.add(new IntroduccionScreenItem("Recompensas", "Deberán escoger entre papá e hijo, los motivadores más apropiados para usar.", R.drawable.introduccion_img_1));
        listScreemItem.add(new IntroduccionScreenItem("Consumo", "Después de calcular la línea base de consumos, la aplicación sugerirá pequeños incrementos para el consumo de frutas y verduras.", R.drawable.introduccion_img_2));
        listScreemItem.add(new IntroduccionScreenItem("Alimentación sana", "La aplicación promoverá que se decrementen los consumos de alimentos ultra procesados.", R.drawable.introduccion_img_3));
        listScreemItem.add(new IntroduccionScreenItem("Fichas", "Por cada consumo cumplido, la aplicación otorgará fichas que el niño podrá intercambiar por motivadores.", R.drawable.introduccion_img_5));

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
