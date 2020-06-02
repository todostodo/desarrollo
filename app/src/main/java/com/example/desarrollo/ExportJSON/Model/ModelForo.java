package com.example.desarrollo.ExportJSON.Model;

import android.content.Context;
import android.util.Log;

import com.example.desarrollo.Entidades.Foro;
import com.example.desarrollo.Entidades.MensajesPersuasivos;
import com.example.desarrollo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ModelForo {

    public void addItemsFromJSONForo(ArrayList arrayList, String TAG, Context context){
        try {
            String jsonDataString = readJSONDataFromFile(context);
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray = object.optJSONArray("Recetas");

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                int id = itemObj.getInt("id");
                String titulo = itemObj.getString("titulo");
                String tiempoPreparacion = itemObj.getString("tiempoPreparacion");
                String ingredientes = itemObj.getString("ingredientes");
                String preparacion = itemObj.getString("preparacion");
                String background = itemObj.getString("background");

                Foro foro = new Foro(id, titulo, tiempoPreparacion, ingredientes, preparacion, background);
                arrayList.add(foro);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile(Context context) throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = context.getResources().openRawResource(R.raw.foro_recetas);
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
