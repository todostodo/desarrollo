package com.example.desarrollo.Ultilidades;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollo.R;

public class Toastp {

    public static void toastp(Context context, String text) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_personalizado, null);

        TextView txtToast1 = (TextView) layout.findViewById(R.id.txtToast);
        txtToast1.setText(text);

        Toast toast = new Toast(context.getApplicationContext());
        toast.setView(layout);
        toast.setDuration(android.widget.Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
