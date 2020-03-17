package com.example.desarrollo.Precentacion.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.Precentacion.MainActivity;
import com.example.desarrollo.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragInicioUsu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragInicioUsu extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageButton _btn;


    EditText _txtUsuario, _txtPassword;
    ImageButton _btnIniciarSesion;
    View vista;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragInicioUsu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragInicioUsu.
     */
    // TODO: Rename and change types and number of parameters
    public static FragInicioUsu newInstance(String param1, String param2) {
        FragInicioUsu fragment = new FragInicioUsu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void btmPantallaRegistro(View view) {
        Intent intent = new Intent(getContext(), login_registro.class);
        startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_frag_inicio_usu, container, false);

        _txtUsuario = (EditText) vista.findViewById(R.id.txtNombreUsuario1);
        _txtPassword = (EditText) vista.findViewById(R.id.txtPassword1);
        _btnIniciarSesion = (ImageButton) vista.findViewById(R.id.btnIniciarSesion1);


        _btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int res= UserDao.consultaUser(getContext(),_txtUsuario.getText().toString(),_txtPassword.getText().toString());
                if(res == 1){
                    SharedPreferences preferences=getActivity().getSharedPreferences("Archivo",getContext().MODE_PRIVATE);
                    //SharedPreferences preferences1=getActivity().getPreferences(getContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("volor",1);
                    editor.commit();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
               }
        });
        return vista;
    }


}
