package com.example.desarrollo.Precentacion.EpicMensajes;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.R;

public class EpicChildr extends AppCompatActivity {

    //Componentes para animacion
    ImageView _imgFicha;
    TextView _fhTitle, _fhDesc, _fhFichas;
    Animation btt, bttwo, imgbounce;
    Button _btmAceptarFicha;
    //-----------------------


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.epic_fichas);

        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        bttwo = AnimationUtils.loadAnimation(this, R.anim.bttwo);
        imgbounce = AnimationUtils.loadAnimation(this, R.anim.imgbounce);

        _imgFicha = (ImageView) findViewById(R.id.dotmenu);
        _fhTitle = (TextView) findViewById(R.id.fhTitle);
        _fhDesc = (TextView) findViewById(R.id.fhDesc);
        _fhFichas = (TextView) findViewById(R.id.fhFichas);

        _fhTitle.setText("Muy bién");
        _fhDesc.setText("Felicidades por el esfuerzo que realisaste por comerte la fruta o verdura del dia");
        _fhFichas.setText("+2");

        //Animación
        _imgFicha.startAnimation(imgbounce);
        _fhTitle.startAnimation(btt);
        _fhDesc.startAnimation(btt);
        _fhFichas.startAnimation(bttwo);


        _btmAceptarFicha = (Button) findViewById(R.id.btmAceptarFicha);
        _btmAceptarFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
