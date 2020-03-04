package com.example.desarrollo.Precentacion.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.R;

public class HijoRegistroActivity extends AppCompatActivity {
    Button _btnAddTutor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hijo_registro_activity);
        init();

        _btnAddTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), preferenciasActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init() {
        _btnAddTutor = (Button) findViewById(R.id.btnAddTutor);
    }
}
