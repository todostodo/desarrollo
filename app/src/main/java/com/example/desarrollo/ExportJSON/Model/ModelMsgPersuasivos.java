package com.example.desarrollo.ExportJSON.Model;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

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

public class ModelMsgPersuasivos extends AppCompatActivity {

    public void addItemsFromJSONMsgPersuasivos(ArrayList arrayList, String TAG, String nombreMsg, Context context){
        try {
            String jsonDataString = readJSONDataFromFile(context);
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray = object.optJSONArray(nombreMsg);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String id = itemObj.getString("id");
                String titulo = itemObj.getString("titulo");
                String mensaje = itemObj.getString("mensaje");

                MensajesPersuasivos mensajesPersuasivos = new MensajesPersuasivos(id, titulo, mensaje);
                arrayList.add(mensajesPersuasivos);
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
            inputStream = context.getResources().openRawResource(R.raw.mensajes_persuasivos);
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
