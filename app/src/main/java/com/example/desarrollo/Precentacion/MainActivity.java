package com.example.desarrollo.Precentacion;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.text.DateFormat;
import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Deque;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.content.Context;
import com.example.desarrollo.ConexionApi.ConexionApi;

import com.example.desarrollo.Datos.Calculos;
import com.example.desarrollo.Datos.ConexionSQLHelper;
import com.example.desarrollo.Datos.Mensajeria;
import com.example.desarrollo.Datos.NinoDao;
import com.example.desarrollo.Datos.TiempoAplicacionDao;
import com.example.desarrollo.Datos.TutorDao;
import com.example.desarrollo.ConexionApi.consultasLocales;
import com.example.desarrollo.Precentacion.Home.HomeFragment;

import com.example.desarrollo.Precentacion.Login.BienbenidaActivity;
import com.example.desarrollo.Precentacion.Motivadores.MotivadoresFragment;
import com.example.desarrollo.Precentacion.Perfil.PerfilFragment;
import com.example.desarrollo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.Context.MODE_PRIVATE;


public class MainActivity extends AppCompatActivity {
    private int contador = 0, estado = 1;
    private int alarmID = 1;
    private String inicio = "";

    private FrameLayout mMainFrame;
    private String currentTag = "home";

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calculos.inicializarFichasAlimento(this);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btmNavegacion);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        mMainFrame = (FrameLayout) findViewById(R.id.fragmentContainer);


    }

    HomeFragment homeFragment = new HomeFragment();
    MotivadoresFragment motivadoresFragment = new MotivadoresFragment();
    PerfilFragment perfilFragment = new PerfilFragment();

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.menu_home:
                            loadFragment(homeFragment);
                            return true;

                        case R.id.menu_motivadores:
                            loadFragment(motivadoresFragment);
                            return true;

                        case  R.id.menu_perfil:
                            loadFragment(perfilFragment);
                            return true;
                    }
                    return false;
                }
            };

    private void loadFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();
    }

    public static void hora(String inicio, Context con) {
        int inih = 0, inim = 0, inis = 0, finh = 0, finm = 0, fins = 0, segundos1 = 0, segundos2 = 0;
        String cadena = "", valor = "";

        Date date = new Date();
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        cadena = "" + hora.format(date);
        System.out.println(cadena);

        valor = "" + inicio.charAt(0);
        valor = valor + "" + inicio.charAt(1);
        inih = Integer.parseInt(valor);
        valor = "";
        valor = "" + inicio.charAt(3);
        valor = valor + "" + inicio.charAt(4);
        inim = Integer.parseInt(valor);
        valor = "";
        valor = "" + inicio.charAt(6);
        valor = valor + "" + inicio.charAt(7);
        inis = Integer.parseInt(valor);
        valor = "";
        System.out.println(inih + "," + inim + "," + inis);

        valor = "" + cadena.charAt(0);
        valor = valor + "" + cadena.charAt(1);
        finh = Integer.parseInt(valor);
        valor = "";
        valor = "" + cadena.charAt(3);
        valor = valor + "" + cadena.charAt(4);
        finm = Integer.parseInt(valor);
        valor = "";
        valor = "" + cadena.charAt(6);
        valor = valor + "" + cadena.charAt(7);
        fins = Integer.parseInt(valor);
        valor = "";
        System.out.println(finh + "," + finm + "," + fins);

        // metodosDB.registrar(con,""+finh,""+finm, ""+fins);

        segundos1 = (inih * 60) * 60;
        segundos1 += inim * 60;
        segundos1 += inis;

        segundos2 = (finh * 60) * 60;
        segundos2 += finm * 60;
        segundos2 += fins;

        segundos1 = segundos2 - segundos1;

        finh = segundos1 / 3600;
        finm = (segundos1 - (3600 * finh)) / 60;
        fins = segundos1 - ((finh * 3600) + (finm * 60));
        String duracion = ""+finh+" : "+finm+" : "+fins;

        SharedPreferences sharedPreferences = con.getSharedPreferences("Usuario", MODE_PRIVATE);

        TiempoAplicacionDao.insertDuracion("TiempApp",con,sharedPreferences.getInt("idGlobal",0),duracion,1);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onStart() {

        SharedPreferences preferenci = getSharedPreferences("Calculo", MODE_PRIVATE);
        int llave1 = preferenci.getInt("llave1", 0);
        int llave2 = preferenci.getInt("llave2", 0);
        int llave3 = preferenci.getInt("llave3", 0);

        System.out.println(llave1+" , "+llave2+" , "+llave3);
        if(llave1 ==0 || llave2==0 || llave3==0) {
            Calculos.generaLBF(this, 1);
            Calculos.generaLBF(this, 2);
            Calculos.generaLBV(this, 1);
            Calculos.generaLBV(this, 2);
            Calculos.generaLBUlPro(this, 1);
            Calculos.generaLBUlPro(this, 2);
        }else {
            Calculos.EsfuerzoF(this, 1);
            Calculos.EsfuerzoF(this, 2);
            Calculos.EsfuerzoV(this, 1);
            Calculos.EsfuerzoV(this, 2);
            Calculos.EsfuerzoUP(this, 1);
            Calculos.EsfuerzoUP(this, 2);
        }

        System.out.println("Esto esssssss:  "+Calculos.caloriaFija(this,1)+" : "+Calculos.caloriaCambio(this,1)+" : "+Calculos.caloriaDia(this,1));
        Mensajeria estadoConexion;
        estadoConexion = new Mensajeria();
        boolean networkInfo = estadoConexion.estadoConexion(getApplicationContext());
        if (networkInfo == true) {

            //int arr[] = consultasLocales.obtenerDatosNino(this);
            //System.out.println(arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]);
            consultasLocales.obtenerDatosGustoFruta(this);
            consultasLocales.obtenerDatosGustoVerdura(this);
            consultasLocales.obtenerDatosRegistro(this);
            consultasLocales.obtenerDatosCanjeFi(this);
            consultasLocales.obtenerDatosDetalleRegistro(this);
            consultasLocales.obtenerDatosTiempoAplicacion(this);
            consultasLocales.obtenerDatosGestoTerrible(this);
            consultasLocales.obtenerDatosGestoBien(this);
            consultasLocales.obtenerDatosGestoGenial(this);
            consultasLocales.obtenerDatosVioNotificacion(this);
            consultasLocales.actualizarDatosLineaBase(this);
            consultasLocales.actualizarDatosesfuerzo(this);
        }

        Date date = new Date();
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        inicio = "" + hora.format(date);

        String valor = "";
        int inih;
        String cadena = cadena = "" + hora.format(date);
        valor = "" + inicio.charAt(0);
        valor = valor + "" + inicio.charAt(1);
        inih = Integer.parseInt(valor);

        SharedPreferences preferenc = this.getSharedPreferences("Calculo", this.MODE_PRIVATE);
        int llave = preferenc.getInt("valorNoti", 0);
        int noti = preferenc.getInt("noti", 0);

        if(noti == 1){
          if (llave == 0) {
              if (inih >= 14 && inih <= 24) {

                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("valorNoti");
                editor.putInt("valorNoti", 1);
                editor.commit();

                iniciarNotifi();

              }
          } else {
              if (inih >= 1 && inih < 14) {
                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("valorNoti");
                editor.putInt("valorNoti", 0);
                editor.commit();
              }
          }
        }

        super.onStart();
    }

    @Override
    protected void onStop() {
        hora(inicio, this);
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void iniciarNotifi(){
        setbienbenidaPending();
        createNotificationChannel();
        notifi();
    }

    private final static String CHANNEL_ID = "NOTIFICACION";
    public final static int NOTIFICACION_ID = 0;
    private PendingIntent bienbenida;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setbienbenidaPending() {
        Intent intent = new Intent(this, BienbenidaActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(BienbenidaActivity.class);
        intent.putExtra("title", "si");
        stackBuilder.addNextIntent(intent);
        bienbenida = stackBuilder.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void notifi() {
        String a= "";
        int valorEntero =(int) Math.floor(Math.random()*(18-1+1)+1);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.sms);
        builder.setContentTitle("Persuhabit");
        builder.setContentText("Despliega la notificacion");
        builder.setColor(Color.BLUE);
        if(valorEntero == 1) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi1)));
        } else if(valorEntero == 2){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi2)));
        }else if(valorEntero == 3){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi3)));
        }else if(valorEntero == 4){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi4)));
        }else if(valorEntero == 5){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi5)));
        }else if(valorEntero == 6){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi6)));
        }else if(valorEntero == 7){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi7)));
        }else if(valorEntero == 8){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi8)));
        }else if(valorEntero == 9){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi9)));
        }else if(valorEntero == 10){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi10)));
        }else if(valorEntero == 11){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi11)));
        }else if(valorEntero == 12){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi12)));
        }else if(valorEntero == 13){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi13)));
        }else if(valorEntero == 14){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi14)));
        }else if(valorEntero == 15){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi15)));
        }else if(valorEntero == 16){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi16)));
        }else if(valorEntero == 17){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi17)));
        }else if(valorEntero == 18){
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.Notifi18)));
        }
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        builder.addAction(R.drawable.sms,"He leido la recomendacion",bienbenida);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notificacion";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
