package com.example.desarrollo.Precentacion.Home;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.Datos.ConexionSQLHelper;
import com.example.desarrollo.Datos.TutorDao;
import com.example.desarrollo.R;
import com.example.desarrollo.Ultilidades.Utilidades;

public class TutorFragment extends AppCompatActivity {

    EditText _txtTutorNombre,
            _txtTutorApellidoPaterno,
            _txtTutorApellidoMaterno,
            _txtTutorCorreo,
            _txtTutorContra;

    Button _btnAddTutor;
    TutorDao tutorDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_registro_activity);
        init();

        _btnAddTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarTutor();
            }
        });

    }

    private void registrarTutor() {

        String nombre = _txtTutorNombre.getText().toString();
        String apellidoP = _txtTutorApellidoPaterno.getText().toString();
        String apellidoM = _txtTutorApellidoMaterno.getText().toString();
        String correo = _txtTutorCorreo.getText().toString();
        String contra = _txtTutorContra.getText().toString();
        int idUsuario = 1;
        String parentesco = "Tio";
        int mensaje = 1;

        tutorDao.addTutor(this, nombre, apellidoP, apellidoM, correo, contra, idUsuario, parentesco, mensaje);
    }

    private void init() {
        _txtTutorNombre = (EditText) findViewById(R.id.txtTutorNombre);
        _txtTutorApellidoPaterno = (EditText) findViewById(R.id.txtTutorApellidoPaterno);
        _txtTutorApellidoMaterno = (EditText) findViewById(R.id.txtTutorApellidoMaterno);
        _txtTutorCorreo = (EditText) findViewById(R.id.txtTutorCorreo);
        _txtTutorContra = (EditText) findViewById(R.id.txtTutorContra);

        _btnAddTutor = (Button) findViewById(R.id.btnAddTutor);
    }
}
