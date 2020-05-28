package com.example.desarrollo.Precentacion.Perfil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.Entidades.Usuario;
import com.example.desarrollo.ExportJSON.Model.ModelNivelesUsuario;
import com.example.desarrollo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {

    private View view;

    private RelativeLayout _btnAjustesPerfil;
    private TextView _txtNombreUsuario, _txtNivelUsuario, _txtExperienciaUsuario, _txtProgresoNivelUsuario, _txtAvanceNivelPorcentaje;
    private ProgressBar _chartNivelUsuario;
    private Integer nivelUsuario = 0, expUsuario = 0, expSiguienteNivel = 0;

    //Open dialog
    private Button _btnEpicNivelSalir;
    private TextView _txtEpicNivelUsuario;
    private ConstraintLayout _epicNivelContenido;
    private LinearLayout _epicNivelFondoNegro;
    private ImageView _epicNivelImg;
    private Animation fromsmall, fromnothing, forloci, togo;
    //--------------------------------------

    private ArrayList<Usuario> listUsaurio = new ArrayList<>();
    private ArrayList<Usuario.Niveles> listNiveles;
    private UserDao userDao;
    private ModelNivelesUsuario nivelesUsuario = new ModelNivelesUsuario();
    private Usuario usuario = new Usuario();
    private NinoDao ninoDao;
    private static final String TAG = "PerfilFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.perfil_fragment, container, false);

        init();

        _epicNivelContenido.setAlpha(0);
        _epicNivelFondoNegro.setAlpha(0);
        _epicNivelImg.setVisibility(View.GONE);

        mostrarDatosUsuario();
        subirNivelUsuario(nivelUsuario);

        _btnAjustesPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ajustes = new Intent(getActivity(), PerfilAjustesActivity.class);
                startActivity(ajustes);
            }
        });

        return view;
    }

    private void mostrarDatosUsuario() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        String correo = sharedPreferences.getString("correoUsuario", "correo");
        listNiveles = new ArrayList<>();

        if (!correo.equals("correo")) {

            userDao.consultarDatosUsuatio(TAG, getContext(), correo, listUsaurio);

            for (int i = 0; i < listUsaurio.size(); i++) {
                _txtNombreUsuario.setText(listUsaurio.get(i).getNombre());
                nivelUsuario = listUsaurio.get(i).getNivel();
                expUsuario = listUsaurio.get(i).getExperiencia();
            }
            _txtNivelUsuario.setText(String.valueOf(nivelUsuario));
            _txtExperienciaUsuario.setText(String.valueOf(expUsuario));

            nivelesUsuario.addItemsFromJSONNNivelesUsuario(listNiveles, TAG, "Niveles", getContext());

            for (int i = 0; i < listNiveles.size(); i++) {
                if (nivelUsuario.equals(listNiveles.get(i).getNivel())) {
                    expSiguienteNivel = listNiveles.get(i + 1).getExperiencia();
                }
            }

            _chartNivelUsuario.setMax(expSiguienteNivel);
            _chartNivelUsuario.setProgress(expUsuario);

            _txtProgresoNivelUsuario.setText(String.valueOf(expUsuario) + "/" + String.valueOf(expSiguienteNivel));
            double porcentajeNivel = porcentajeNivel(expUsuario, expSiguienteNivel);
            _txtAvanceNivelPorcentaje.setText(String.valueOf(porcentajeNivel) + "%");

        }
    }

    private void subirNivelUsuario(Integer nivelUsuario) {

        if (!expSiguienteNivel.equals(0) && !expUsuario.equals(0)) {

            if (expUsuario >= expSiguienteNivel) {

                _txtEpicNivelUsuario.setText("Nivel " + String.valueOf(nivelUsuario + 1));

                _epicNivelImg.setVisibility(View.VISIBLE);
                _epicNivelImg.startAnimation(forloci);

                _epicNivelFondoNegro.setAlpha(1);
                _epicNivelFondoNegro.startAnimation(fromnothing);

                _epicNivelContenido.setAlpha(1);
                _epicNivelContenido.startAnimation(fromsmall);

                userDao.suvirNivelUsuario(TAG, getContext(), 1);

                _btnEpicNivelSalir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _epicNivelFondoNegro.startAnimation(togo);
                        _epicNivelContenido.startAnimation(togo);
                        _epicNivelImg.startAnimation(togo);
                        _epicNivelImg.setVisibility(View.GONE);

                        ViewCompat.animate(_epicNivelContenido).setStartDelay(1000).alpha(0).start();
                        ViewCompat.animate(_epicNivelFondoNegro).setStartDelay(1000).alpha(0).start();

                        mostrarDatosUsuario();
                    }
                });
            }
        }
    }

    private double porcentajeNivel(int expUsuario, int expSiguienteNivel) {

        double x1 = (100 * expUsuario);
        double x2 = (x1 / expSiguienteNivel);

        long factor = (long) Math.pow(10, 2);
        x2 = x2 * factor;
        long round = Math.round(x2);

        return (double) round / factor;
    }

    private void init() {
        _btnAjustesPerfil = (RelativeLayout) view.findViewById(R.id.btnAjustesPerfil);
        _txtNombreUsuario = (TextView) view.findViewById(R.id.txtNombreUsuario);
        _txtNivelUsuario = (TextView) view.findViewById(R.id.txtNivelUsuario);
        _txtExperienciaUsuario = (TextView) view.findViewById(R.id.txtExperienciaUsuario);
        _txtProgresoNivelUsuario = (TextView) view.findViewById(R.id.txtProgresoNivelUsuario);
        _txtAvanceNivelPorcentaje = (TextView) view.findViewById(R.id.txtAvanceNivelPorcentaje);
        _chartNivelUsuario = (ProgressBar) view.findViewById(R.id.chartProgresoNivel);

        //Nivel Dialog
        _txtEpicNivelUsuario = (TextView) view.findViewById(R.id.txtEpicNivelUsuario);

        _btnEpicNivelSalir = (Button) view.findViewById(R.id.btnEpicNivelSalir);
        _epicNivelContenido = (ConstraintLayout) view.findViewById(R.id.epicNivelContenido);
        _epicNivelFondoNegro = (LinearLayout) view.findViewById(R.id.epicNivelFondoNegro);
        _epicNivelImg = (ImageView) view.findViewById(R.id.epicNivelImg);

        fromsmall = AnimationUtils.loadAnimation(getContext(), R.anim.fromsmall);
        fromnothing = AnimationUtils.loadAnimation(getContext(), R.anim.fromnothing);
        forloci = AnimationUtils.loadAnimation(getContext(), R.anim.forloci);
        togo = AnimationUtils.loadAnimation(getContext(), R.anim.togo);
        //----
    }
}
