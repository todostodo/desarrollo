package com.example.desarrollo.Precentacion.Alimentos.Ultraprocesados;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.desarrollo.R;

public class UltraprocesadosFragment extends Fragment {


    BebidasFragment bebidasFragment = new BebidasFragment();
    /*
    FriturasFragment friturasFragment = new FriturasFragment();
    GolosinasFragment golosinasFragment = new GolosinasFragment();
    GalletasPanesillosFragment galletasPanesillosFragment = new GalletasPanesillosFragment();
    OtrosFragment otrosFragment = new OtrosFragment();
     */

    FrameLayout _fragmentContainerUltraprocesador;
    Button
            _btnBebidas,
            _btnFrituras,
            _btnGolosinas,
            _btnGalletasPanesillos,
            _btnOtros;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ultraprocesados_fragment, container, false);



        //Manejo de los botones para mostrar los fragmentos correspondientes
        //de cada uno

        _btnBebidas = (Button) view.findViewById(R.id.btnBebidas);
        _btnFrituras = (Button) view.findViewById(R.id.btnFrituras);
        _btnGolosinas = (Button) view.findViewById(R.id.btnGolosinas);
        _btnGalletasPanesillos = (Button) view.findViewById(R.id.btnGalletasPanesillos);
        _btnOtros = (Button) view.findViewById(R.id.btnOtros);

        _fragmentContainerUltraprocesador = (FrameLayout) view.findViewById(R.id.fragmentContainerUltraprocesados);

        if (savedInstanceState == null){
            _btnBebidas.setBackgroundResource(R.drawable.custom_bottom_background_azul);
            _btnBebidas.setTextColor(getResources().getColor(R.color.blanco));

            loadFirstFragment();
        }


        _btnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnBebidas.setBackgroundResource(R.drawable.custom_bottom_background_azul);
                _btnBebidas.setTextColor(getResources().getColor(R.color.blanco));

                //Desactivar

                _btnFrituras.setBackgroundColor(Color.TRANSPARENT);
                _btnGolosinas.setBackgroundColor(Color.TRANSPARENT);
                _btnGalletasPanesillos.setBackgroundColor(Color.TRANSPARENT);
                _btnOtros.setBackgroundColor(Color.TRANSPARENT);

                _btnFrituras.setTextColor(Color.parseColor("#747576"));
                _btnGolosinas.setTextColor(Color.parseColor("#747576"));
                _btnGalletasPanesillos.setTextColor(Color.parseColor("#747576"));
                _btnOtros.setTextColor(Color.parseColor("#747576"));


                BebidasFragment bebidasFragment = new BebidasFragment();
                loadFragment(bebidasFragment);
            }
        });

        _btnFrituras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _btnFrituras.setBackgroundResource(R.drawable.custom_bottom_background_azul);
                _btnFrituras.setTextColor(getResources().getColor(R.color.blanco));

                //Desactivar
                _btnBebidas.setBackgroundColor(Color.TRANSPARENT);
                _btnGolosinas.setBackgroundColor(Color.TRANSPARENT);
                _btnGalletasPanesillos.setBackgroundColor(Color.TRANSPARENT);
                _btnOtros.setBackgroundColor(Color.TRANSPARENT);

                _btnBebidas.setTextColor(Color.parseColor("#747576"));
                _btnGolosinas.setTextColor(Color.parseColor("#747576"));
                _btnGalletasPanesillos.setTextColor(Color.parseColor("#747576"));
                _btnOtros.setTextColor(Color.parseColor("#747576"));

                FriturasFragment friturasFragment = new FriturasFragment();
                loadFragment(friturasFragment);
            }
        });

        _btnGolosinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnGolosinas.setBackgroundResource(R.drawable.custom_bottom_background_azul);
                _btnGolosinas.setTextColor(getResources().getColor(R.color.blanco));

                //Desactivar
                _btnBebidas.setBackgroundColor(Color.TRANSPARENT);
                _btnFrituras.setBackgroundColor(Color.TRANSPARENT);
                _btnGalletasPanesillos.setBackgroundColor(Color.TRANSPARENT);
                _btnOtros.setBackgroundColor(Color.TRANSPARENT);

                _btnBebidas.setTextColor(Color.parseColor("#747576"));
                _btnFrituras.setTextColor(Color.parseColor("#747576"));
                _btnGalletasPanesillos.setTextColor(Color.parseColor("#747576"));
                _btnOtros.setTextColor(Color.parseColor("#747576"));

                GolosinasFragment golosinasFragment = new GolosinasFragment();
                loadFragment(golosinasFragment);
            }
        });

        _btnGalletasPanesillos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnGalletasPanesillos.setBackgroundResource(R.drawable.custom_bottom_background_azul);
                _btnGalletasPanesillos.setTextColor(getResources().getColor(R.color.blanco));

                //Desactivar
                _btnBebidas.setBackgroundColor(Color.TRANSPARENT);
                _btnFrituras.setBackgroundColor(Color.TRANSPARENT);
                _btnGolosinas.setBackgroundColor(Color.TRANSPARENT);
                _btnOtros.setBackgroundColor(Color.TRANSPARENT);

                _btnBebidas.setTextColor(Color.parseColor("#747576"));
                _btnFrituras.setTextColor(Color.parseColor("#747576"));
                _btnGolosinas.setTextColor(Color.parseColor("#747576"));
                _btnOtros.setTextColor(Color.parseColor("#747576"));

                GalletasPanesillosFragment galletasPanesillosFragment = new GalletasPanesillosFragment();
                loadFragment(galletasPanesillosFragment);
            }
        });

        _btnOtros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnOtros.setBackgroundResource(R.drawable.custom_bottom_background_azul);
                _btnOtros.setTextColor(getResources().getColor(R.color.blanco));

                //Desactivar
                _btnBebidas.setBackgroundColor(Color.TRANSPARENT);
                _btnFrituras.setBackgroundColor(Color.TRANSPARENT);
                _btnGolosinas.setBackgroundColor(Color.TRANSPARENT);
                _btnGalletasPanesillos.setBackgroundColor(Color.TRANSPARENT);

                _btnBebidas.setTextColor(Color.parseColor("#747576"));
                _btnFrituras.setTextColor(Color.parseColor("#747576"));
                _btnGolosinas.setTextColor(Color.parseColor("#747576"));
                _btnGalletasPanesillos.setTextColor(Color.parseColor("#747576"));

                OtrosFragment otrosFragment = new OtrosFragment();
                loadFragment(otrosFragment);
            }
        });

        //-------------------------------------------------------------------------------------------------------------

        return view;
    }


    private void loadFirstFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainerUltraprocesados, bebidasFragment, "bebidas");
        transaction.commit();
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainerUltraprocesados, fragment);
        //ft.addToBackStack(null);
        ft.commit();
    }

}
