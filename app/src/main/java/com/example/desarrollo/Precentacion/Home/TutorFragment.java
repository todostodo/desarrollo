package com.example.desarrollo.Precentacion.Home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.desarrollo.Datos.TutorDao;
import com.example.desarrollo.R;

public class TutorFragment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText _txtTutorNombre,
            _txtTutorApellidoPaterno,
            _txtTutorApellidoMaterno,
            _txtTutorCorreo,
            _txtTutorContra,
            _txtTutorReContra;

    CheckBox _checkTutorMensaje;
    Spinner _txtTutorParentesco;
    Button _btnAddTutor;
    TutorDao tutorDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_registro_activity);
        init();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.parentesco, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _txtTutorParentesco.setAdapter(adapter);
        _txtTutorParentesco.setOnItemSelectedListener(this);

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
        String reContra = _txtTutorReContra.getText().toString();
        int idUsuario = 1;
        String parentesco = "Tio";
        int mensaje;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (_checkTutorMensaje.isChecked() == true)
            mensaje = 1;
        else
            mensaje = 0;

        if (nombre.isEmpty()) {
            _txtTutorNombre.setBackgroundResource(R.drawable.rectangulo_border_rojo);
            Toast.makeText(this, "Ingrese el nombre del tutor", Toast.LENGTH_SHORT).show();
        } else {
            _txtTutorNombre.setBackgroundResource(R.drawable.rectangulo_gris);

            if (apellidoP.isEmpty()) {
                _txtTutorApellidoPaterno.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                Toast.makeText(this, "Ingrese el apellido paterno", Toast.LENGTH_SHORT).show();
            } else {
                _txtTutorApellidoPaterno.setBackgroundResource(R.drawable.rectangulo_gris);

                if (apellidoM.isEmpty()) {
                    _txtTutorApellidoMaterno.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                    Toast.makeText(this, "Ingrese el apellido materno", Toast.LENGTH_SHORT).show();
                } else {
                    _txtTutorApellidoMaterno.setBackgroundResource(R.drawable.rectangulo_gris);

                    if (correo.isEmpty()) {
                        _txtTutorCorreo.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                        Toast.makeText(this, "Ingrese el correo electronico", Toast.LENGTH_SHORT).show();
                    } else {
                        _txtTutorCorreo.setBackgroundResource(R.drawable.rectangulo_gris);

                        if (!correo.trim().matches(emailPattern)) {
                            _txtTutorCorreo.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                            Toast.makeText(this, "El correo electronico no es valido", Toast.LENGTH_SHORT).show();
                        } else {
                            _txtTutorCorreo.setBackgroundResource(R.drawable.rectangulo_gris);
                            if (contra.isEmpty()) {
                                _txtTutorContra.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                                Toast.makeText(this, "Ingrese la contraseña", Toast.LENGTH_SHORT).show();
                            } else {
                                _txtTutorContra.setBackgroundResource(R.drawable.rectangulo_gris);

                                if (reContra.isEmpty()) {
                                    _txtTutorReContra.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                                    Toast.makeText(this, "Confirme la contraseña", Toast.LENGTH_SHORT).show();
                                } else {
                                    _txtTutorReContra.setBackgroundResource(R.drawable.rectangulo_gris);

                                    if (contra.equals(reContra)) {

                                        boolean add = tutorDao.addTutor(this, nombre, apellidoP, apellidoM, correo, contra, idUsuario, parentesco, mensaje);

                                        if (add == true) {
                                            Toast.makeText(this, "Tutor Agregado", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        _txtTutorReContra.setBackgroundResource(R.drawable.rectangulo_border_rojo);
                                        Toast.makeText(this, "La contraseña no conicide", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void init() {
        _txtTutorNombre = (EditText) findViewById(R.id.txtTutorNombre);
        _txtTutorApellidoPaterno = (EditText) findViewById(R.id.txtTutorApellidoPaterno);
        _txtTutorApellidoMaterno = (EditText) findViewById(R.id.txtTutorApellidoMaterno);
        _txtTutorCorreo = (EditText) findViewById(R.id.txtTutorCorreo);
        _txtTutorContra = (EditText) findViewById(R.id.txtTutorContra);
        _txtTutorReContra = (EditText) findViewById(R.id.txtTutorReContra);

        _checkTutorMensaje = (CheckBox) findViewById(R.id.checkTutorMensaje);
        _txtTutorParentesco = (Spinner) findViewById(R.id.txtTutorParentesco);

        _btnAddTutor = (Button) findViewById(R.id.btnAddTutor);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#475369"));
        ((TextView) parent.getChildAt(0)).setTextSize(18);
        ((TextView) parent.getChildAt(0)).setTypeface(ResourcesCompat.getFont(this, R.font.roboto_regular));

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
