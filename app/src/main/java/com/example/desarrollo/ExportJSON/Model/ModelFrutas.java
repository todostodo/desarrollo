package com.example.desarrollo.ExportJSON.Model;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.ExportJSON.Reader.ReaderFrutas;
import com.example.desarrollo.ExportJSON.Reader.ReaderUltraprocesados;
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


    public void addItemsFromJSON(ArrayList arrayList, String TAG, String nombreChatarra, Context context) {
        try {
            String jsonDataString = readJSONDataFromFile(context);
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray = object.optJSONArray(nombreChatarra);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String id = itemObj.getString("id");
                String nombre = itemObj.getString("nombre");
                String descripcion = itemObj.getString("descripcion");
                String recomendacion = itemObj.getString("recomendacion");
                //String beneficio = itemObj.getString("beneficio");
                String imgUrl = itemObj.getString("imgUrl");
                String background = itemObj.getString("backgroud");

                ReaderFrutas readerFrutas = new ReaderFrutas(id, nombre, descripcion, recomendacion, imgUrl, background);
                arrayList.add(readerFrutas);
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
