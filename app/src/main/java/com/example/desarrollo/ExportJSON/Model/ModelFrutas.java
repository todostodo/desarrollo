package com.example.desarrollo.ExportJSON.Model;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.Entidades.Frutas;
import com.example.desarrollo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ModelFrutas extends AppCompatActivity {


    public void addItemsFromJSON(ArrayList arrayList, String TAG, String nombreAlimento, Context context) {
        try {
            String jsonDataString = readJSONDataFromFile(context);
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray = object.optJSONArray(nombreAlimento);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String id = itemObj.getString("id");
                String nombre = itemObj.getString("nombre");
                String equivalencia = itemObj.getString("equivalencia");
                String descripcion = itemObj.getString("descripcion");
                String recomendacion = itemObj.getString("recomendacion");
                String recomendacionDos = itemObj.getString("recomendacionDos");
                String frase = itemObj.getString("frase");
                String ventaja = itemObj.getString("ventaja");
                String avisoTitulo = itemObj.getString("avisoTitulo");
                String aviso = itemObj.getString("aviso");
                String imgUrl = itemObj.getString("imgUrl");
                String background = itemObj.getString("backgroud");

                Frutas frutas = new Frutas(id, nombre, equivalencia, descripcion, recomendacion, recomendacionDos, frase, ventaja, avisoTitulo, aviso, imgUrl, background);
                arrayList.add(frutas);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    public void addItemsFromJSONHistorial(ArrayList arrayList, String TAG, String nombreAlimento, Context context){
        try {
            String jsonDataString = readJSONDataFromFile(context);
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray = object.optJSONArray(nombreAlimento);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String id = itemObj.getString("id");
                String nombre = itemObj.getString("nombre");
                String equivalencia = itemObj.getString("equivalencia");
                String imgUrl = itemObj.getString("imgUrl");
                String background = itemObj.getString("backgroud");

                Frutas frutas = new Frutas(id, nombre, equivalencia, imgUrl, background);
                arrayList.add(frutas);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
        ///return arrayList;
    }

    private String readJSONDataFromFile(Context context) throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = context.getResources().openRawResource(R.raw.alimentos);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}
