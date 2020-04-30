package com.example.desarrollo.ExportJSON.Model;

import android.content.Context;
import android.util.Log;

import com.example.desarrollo.Entidades.MensajesPersuasivos;
import com.example.desarrollo.Entidades.Usuario;
import com.example.desarrollo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ModelNivelesUsuario {

    public void addItemsFromJSONNNivelesUsuario(ArrayList arrayList, String TAG, String nombre, Context context){
        try {
            String jsonDataString = readJSONDataFromFile(context);
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray = object.optJSONArray(nombre);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                int nivel = itemObj.getInt("nivel");
                int experiencia = itemObj.getInt("experiencia");

                Usuario.Niveles niveles = new Usuario.Niveles(nivel, experiencia);
                arrayList.add(niveles);
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
            inputStream = context.getResources().openRawResource(R.raw.niveles_usuario);
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
