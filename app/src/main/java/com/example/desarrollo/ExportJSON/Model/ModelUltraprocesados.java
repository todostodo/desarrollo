package com.example.desarrollo.ExportJSON.Model;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.Entidades.UltraProcesados;
import com.example.desarrollo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ModelUltraprocesados extends AppCompatActivity {


    public void addItemsFromJSON(ArrayList arrayList, String TAG, String nombreChatarra, Context context) {
        try {
            String jsonDataString = readJSONDataFromFile(context);
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray = object.optJSONArray(nombreChatarra);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                int idAlimentoUltrap = itemObj.getInt("id");
                String nombre = itemObj.getString("nombre");
                double porcion = itemObj.getDouble("porcion");
                String contenido = itemObj.getString("contenido");
                int kcalorias = itemObj.getInt("Kcalorias");

                UltraProcesados readerUltraProcesados = new UltraProcesados(idAlimentoUltrap, nombre, porcion, contenido, kcalorias);
                arrayList.add(readerUltraProcesados);
            }
        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemFromJSON", e);
        }
    }

    private String readJSONDataFromFile(Context context) throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = context.getResources().openRawResource(R.raw.alimentos_ultra_procesados);
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
