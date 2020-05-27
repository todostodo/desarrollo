package com.example.desarrollo.ExportJSON.Model;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desarrollo.Entidades.MsgEducativos;
import com.example.desarrollo.Entidades.PreferenciasNino;
import com.example.desarrollo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ModelMsgEducativos extends AppCompatActivity {

    public void addItemsFromJSONMsgEducativos(ArrayList arrayList, String TAG, Context context) {
        try {
            String jsonDataString = readJSONDataFromFile(context);
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray = object.optJSONArray("msgEducativo");

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                int id = itemObj.getInt("id");
                String msgEducativo = itemObj.getString("msg");

                MsgEducativos msgEducativos = new MsgEducativos(id, msgEducativo);
                arrayList.add(msgEducativos);
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
            inputStream = context.getResources().openRawResource(R.raw.msg_educativos);
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
