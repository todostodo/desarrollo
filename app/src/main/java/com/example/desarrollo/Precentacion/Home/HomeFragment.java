package com.example.desarrollo.Precentacion.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.desarrollo.Precentacion.Alimentos.Frutas.FrutasFragment;
import com.example.desarrollo.Precentacion.Alimentos.Ultraprocesados.UltraprocesadosFragment;
import com.example.desarrollo.Precentacion.Alimentos.Verduras.VerdurasFragment;
import com.example.desarrollo.R;


public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getView().findViewById(R.id.btmFrutas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FrutasFragment frutasFragment = null;
                frutasFragment = new FrutasFragment();
                loadFragment(frutasFragment);
                /*
                FrutasFragment frutas = null;
                FragmentManager manager = getActivity().getSupportFragmentManager();
                frutas = new FrutasFragment();
                manager.beginTransaction()
                        //.setCustomAnimations(R.anim.enter_rigth_to_left, R.anim.exit_rigth_to_left,
                        //        R.anim.enter_left_to_right, R.anim.exit_left_to_rigth)
                        .replace(R.id.fragmentContainer, frutas)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                 */
            }
        });

        getView().findViewById(R.id.btmVerduras).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerdurasFragment verdurasFragment;
                verdurasFragment = new VerdurasFragment();
                loadFragment(verdurasFragment);
                /*
                VerdurasFragment verduras = null;
                FragmentManager manager = getActivity().getSupportFragmentManager();
                verduras = new VerdurasFragment();
                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, verduras)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                 */
            }
        });

        getView().findViewById(R.id.btmUltraprocesados).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UltraprocesadosFragment ultraprocesadosFragment;
                ultraprocesadosFragment = new UltraprocesadosFragment();
                loadFragment(ultraprocesadosFragment);
                /*
                UltraprocesadosFragment verduras = null;
                FragmentManager manager = getActivity().getSupportFragmentManager();
                UltraprocesadosFragment ultraprocesados = new UltraprocesadosFragment();
                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, ultraprocesados)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                 */
            }
        });

        getView().findViewById(R.id.detalleConsumoDia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalleConsumoDia detalleConsumoDia = new DetalleConsumoDia();
                loadFragment(detalleConsumoDia);
            }
        });
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
