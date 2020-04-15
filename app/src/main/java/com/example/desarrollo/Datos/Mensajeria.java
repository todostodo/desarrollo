package com.example.desarrollo.Datos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Stack;

public class Mensajeria {

    protected String sEmail = "persuhabit@gmail.com"; //Correo el cual mandara la contraseña
    protected String sPassword = "#HealthyFoodTec#"; //La contraseña del correo para poder ingresar y mandar el correo automaticamente;

    public String getsEmail() {
        return sEmail;
    }

    public String getsPassword() {
        return sPassword;
    }

    public static boolean estadoConexion(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static String[] getDominios() {
        String dominios[] = new String[]{"hotmail.com", "outlook.es", "outlook.com", "gmail.com"};
        return dominios;
    }

    public static String generarCodigoRecuperacion() {
        int digito;
        int maximoDigitos = 4;
        Stack<Integer> codigoGenerado = new Stack<Integer>();

        for (int i = 0; i < maximoDigitos; i++) {
            digito = (int) Math.floor(Math.random() * maximoDigitos);
            while (codigoGenerado.contains(digito)) {
                digito = (int) Math.floor(Math.random() * maximoDigitos);
            }
            codigoGenerado.push(digito);
        }

        Object[] validarCodigo = codigoGenerado.toArray();
        String codigoValidado = "";

        for (int i = 0; i < validarCodigo.length; i++) {
            codigoValidado += validarCodigo[i];
        }
        return codigoValidado;
    }
}
