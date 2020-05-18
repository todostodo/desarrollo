package com.example.desarrollo.ConexionApi;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.desarrollo.Datos.UserDao;
import com.example.desarrollo.Precentacion.Login.RegistroUsuario;
import com.example.desarrollo.Ultilidades.Toastp;


public class ConexionApi extends AppCompatActivity {

    public Toastp toast;
    public static RequestQueue queue;
    private static final String TAG = "ConexionApi";

    //////////////////////////////////////////[*********Metodos para el manejo de la tabla Recompensas**********]
    //-----------------------------------[Obtener todos los datos de la tabla recompensas]
    public static void ObtenerDatosRecompensas(Context context) {

        queue = Volley.newRequestQueue(context);


        String url = "http://68.183.148.243/Persuhabit/recompensas";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String descrip = jsonObject.getString("descrip");
                        System.out.println(descrip);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    //-----------------------------------------[Poner una nueva Recompensa]
    //****************este metodo recive la recompensa en la variable "descrip", y el valor corespondiente
    //****************a esta en la variable "valor".
    public static void PonerRecompensaNueva(Context context, String descrip, int valor) {
        String url = "http://68.183.148.243/Persuhabit/recompensas";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("descrip", descrip);
        params.put("valor", valor);

        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    //-----------------------------------------[Eliminar una recompensa]
    //********************Este metodo recive el id de la recompensa a eliminar en la variable "id"
    public static void EliminarRecompensa(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/recompensas/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla recompensas]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //////////////////////////////////////////// [********Metodos para el manejo de USUARIOS**********]
    /////////////////////////////////[Consulta general de todos los usuarios]
    public static void ObtenerDatosUsuarios(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/usuarios";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ////////////////////////[Actualizar Contraseña del Usuario]
    public static void ActualizarContraseñaUsuario(Context context, String pwdu, int id) {
        String url = "http://68.183.148.243/Persuhabit/usuario/password";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pwdu", pwdu);
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.PUT, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////[Actualizar estado del usuario]

    public static void updateEstadoUsuario(Context context, int idUsuario) {
        String url = "http://68.183.148.243/Persuhabit/usuario/estado";
        RequestQueue queue = Volley.newRequestQueue(context);

        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
        stringObjectMap.put("idusu", idUsuario);

        JSONObject jsonObject = new JSONObject(stringObjectMap);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        queue.add(jsonObjectRequest);
    }

    ////////////////////////////////////////[eliminar un usuario]
    public static void EliminaUsuario(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/usuarios/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla Usuarios]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla Niño**************]
////////////////////****************[Consulta general de todos los Niños]
    public static void ObtenerDatosNiños(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/nino";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    //////////////////////////////////[Insertar un nuevo niño]
    public static void PonerNiñoNuevo(Context context, int idusu, String genero, String nomn, String appn, String apmn, int edad, double peso, double estat, double medi, double lineabultra, double lineabv, double leneabf, int totfich, double esfuerzoultra, double esfuerzof, double esfuerzov) {
        String url = "http://68.183.148.243/Persuhabit/nino";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idusu", idusu);
        params.put("genero", genero);
        params.put("nomn", nomn);
        params.put("appn", appn);
        params.put("apmn", apmn);
        params.put("edad", edad);
        params.put("peso", peso);
        params.put("estat", estat);
        params.put("medi", medi);
        params.put("lineabultra", lineabultra);
        params.put("lineabv", lineabv);
        params.put("leneabf", leneabf);
        params.put("totfich", totfich);
        params.put("esfuerzoultra", esfuerzoultra);
        params.put("esfuerzof", esfuerzof);
        params.put("esfuerzov", esfuerzov);

        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////[Actualizar linea base de ultra procesados, frutas y verduras del niño]
    public static void ActualizaLineaBase_Fruta_Verdura_Ultra(Context context, double lineabultra, double lineabv, double lineabf, int id) {
        String url = "http://68.183.148.243/Persuhabit/nino/LineaBase";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("lineabultra", lineabultra);
        params.put("lineabv", lineabv);
        params.put("lineabf", lineabf);
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.PUT, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////[Actualizar Esfuerzo de ultra procesados, frutas y verduras del niño]
    public static void ActualizaEsfuerzo_Fruta_Verdura_Ultra(Context context, double esfuerzoultra, double esfuerzof, double esfuerzov, int id) {
        String url = "http://68.183.148.243/Persuhabit/nino/esfuerzo";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("esfuerzoultra", esfuerzoultra);
        params.put("esfuerzof", esfuerzof);
        params.put("esfuerzov", esfuerzov);
        params.put("id", id);
        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.PUT, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar niño]
    public static void EliminaNiño(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/nino/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla Nino]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla Registro**************]
////////////////////*************[Consulta general de todos los Registros]
    public static void ObtenerDatosRegistro(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/registro";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    //////////////////////////////////[Insertar un nuevo registro]
    public static void PonerRegistroNuevo(Context context, int idNino, String fechar) {
        String url = "http://68.183.148.243/Persuhabit/registro";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idNino", idNino);
        params.put("fechar", fechar);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar registro]
    public static void EliminaRegistro(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/registro/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla Registro]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla Mensajes_Persuasivos**************]
////////////////////[Consulta general de todos los Mensajes_Persuasivos]
    public static void ObtenerDatosMensajes_Persuasivos(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/MsgPersuasivo";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar un Mensaje Persuasivo nuevo]
    public static void PonerMensajes_PersuasivosNuevo(Context context, String tipo, String msg) {
        String url = "http://68.183.148.243/Persuhabit/MsgPersuasivo";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tipo", tipo);
        params.put("msg", msg);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar Mensaje Persuasivo]
    public static void EliminaMensajes_Persuasivos(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/MsgPersuasivo/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla Mensajes_Persuasivos]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla Historial_Nutricion**************]
////////////////////***************[Consulta general de Historial_Nutricion]
    public static void ObtenerDatosHistorial_Nutricion(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/HistorialNutri";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar Historial_Nutricion nuevo]
    public static void PonerHistorial_NutricionNuevo(Context context, int idusu, int Respuesta_Nutri) {
        String url = "http://68.183.148.243/Persuhabit/HistorialNutri";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idusu", idusu);
        params.put("Respuesta_Nutri", Respuesta_Nutri);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar Historial_Nutricion]
    public static void EliminaHistorial_Nutricion(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/HistorialNutri/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla Historial_Nutricion]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla Historial_Autoeficacia**************]
////////////////////***********[Consulta general de Historial_Autoeficacia]
    public static void ObtenerDatosHistorial_Autoeficacia(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/HistorialAuto";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar Historial_Autoeficacia nuevo]
    public static void PonerHistorial_AutoeficaciaNuevo(Context context, int idusu, String RespuestaAuto) {
        String url = "http://68.183.148.243/Persuhabit/HistorialAuto";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idusu", idusu);
        params.put("RespuestaAuto", RespuestaAuto);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar Historial_Autoeficacia]
    public static void EliminaHistorial_Autoeficacia(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/HistorialAuto/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla Historial_Autoeficacia]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician rutas de la tabla GustoVerdura**************]
////////////////////[Consulta general de GustoVerdura]
    public static void ObtenerDatosGustoVerdura(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/GustoVerdura";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar GustoVerdura nuevo]
    public static void PonerGustoVerduraNuevo(Context context, String nombreV, int siGustaV, int noGustaV, int conoscoV, int idNino) {
        String url = "http://68.183.148.243/Persuhabit/GustoVerdura";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nombreV", nombreV);
        params.put("siGustaV", siGustaV);
        params.put("noGustaV", noGustaV);
        params.put("conoscoV", conoscoV);
        params.put("idNino", idNino);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar GustoVerdura]
    public static void EliminaGustoVerdura(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/GustoVerdura/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla GustoVerdura]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla GustoFrutas**************]
////////////////////************[Consulta general de GustoFrutas]
    public static void ObtenerDatosGustoFrutas(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/GustoFrutas";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar GustoFrutas nuevo]
    public static void PonerGustoFrutasNuevo(Context context, int idNino, String nombreF, int siGustaF, int noGustaF, int conoscoF) {
        String url = "http://68.183.148.243/Persuhabit/GustoFrutas";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idNino", idNino);
        params.put("nombreF", nombreF);
        params.put("siGustaF", siGustaF);
        params.put("noGustaF", noGustaF);
        params.put("conoscoF", conoscoF);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar GustoFrutas]
    public static void EliminaGustoFrutas(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/GustoFrutas/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla GustoFrutas]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla Envia_Msg**************]
////////////////////**************[Consulta general de Envia_Msg]
    public static void ObtenerDatosEnvia_Msg(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/EnviaMsg";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar Envia_Msg nuevo]
    public static void PonerEnvia_MsgNuevo(Context context, int idusu, int idmsg, String horame, String Fechame) {
        String url = "http://68.183.148.243/Persuhabit/EnviaMsg";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idusu", idusu);
        params.put("idmsg", idmsg);
        params.put("horame", horame);
        params.put("Fechame", Fechame);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar Envia_Msg]
    public static void EliminaEnvia_Msg(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/EnviaMsg/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla Envia_Msg]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla DetalleReg**************]
////////////////////[Consulta general de DetalleReg]
    public static void ObtenerDatosDetalleReg(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/DetalleReg";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar DetalleReg nuevo]
    public static void PonerDetalleRegNuevo(Context context, int idNino, int idalimento, double equi, double cad, double umedr, String hora, String tipo) {
        String url = "http://68.183.148.243/Persuhabit/DetalleReg";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idNino", idNino);
        params.put("idalimento", idalimento);
        params.put("equi", equi);
        params.put("cad", cad);
        params.put("umedr", umedr);
        params.put("hora", hora);
        params.put("tipo", tipo);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar DetalleReg]
    public static void EliminaDetalleReg(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/DetalleReg/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla DetalleReg]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla Cuestionario_Nutricion**************]
////////////////////*************[Consulta general de Cuestionario_Nutricion]
    public static void ObtenerCuestionario_Nutricion(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/CuestionarioNutri";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar Cuestionario_Nutricion nuevo]
    public static void PonerCuestionario_NutricionNuevo(Context context, int idHistoNutri, String Preg_Nutri, String Res_Pre_Nutri, int Msg) {
        String url = "http://68.183.148.243/Persuhabit/CuestionarioNutri";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idHistoNutri", idHistoNutri);
        params.put("Preg_Nutri", Preg_Nutri);
        params.put("Res_Pre_Nutri", Res_Pre_Nutri);
        params.put("Msg", Msg);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar Cuestionario_Nutricion]
    public static void EliminaCuestionario_Nutricion(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/CuestionarioNutri/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla Cuestionario_Nutricion]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla CanjeFi**************]
////////////////////*************[Consulta general de CanjeFi]
    public static void ObtenerCanjeFi(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/CanjeFi";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar CanjeFi nuevo]
    public static void PonerCanjeFiNuevo(Context context, int idNino, int idrecom, String fechacanje, int Activo) {
        String url = "http://68.183.148.243/Persuhabit/CanjeFi";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idNino", idNino);
        params.put("idrecom", idrecom);
        params.put("fechacanje", fechacanje);
        params.put("Activo", Activo);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar CanjeFi]
    public static void EliminaCanjeFi(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/CanjeFi/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla CanjeFi]

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ////////////////////////////////////////[*************Inician Metodos de la tabla Tutor**************]
////////////////////****************[Consulta general de Tutor]
    public static void ObtenerTutor(Context context) {

        queue = Volley.newRequestQueue(context);

        String url = "http://68.183.148.243/Persuhabit/tutor";//establece ruta al servidor para obtener los datos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");//

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    ///////////////////////[Insertar Tutor nuevo]
    public static void PonerTutorNuevo(Context context, int idusu, String nomt, String appt, String appmt, String parent, int msg, String correo, int pwdt) {
        String url = "http://68.183.148.243/Persuhabit/tutor";
        RequestQueue queue = Volley.newRequestQueue(context);

// POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idusu", idusu);
        params.put("nomt", nomt);
        params.put("appt", appt);
        params.put("appmt", appmt);
        params.put("parent", parent);
        params.put("msg", msg);
        params.put("correo", correo);
        params.put("pwdt", pwdt);


        JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }

    ////////////////////////////////////////[eliminar Tutor]
    public static void EliminaTutor(Context context, int id) {
        String url = "http://68.183.148.243/Persuhabit/tutor/eliminar";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST parameters
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        JSONObject jsonObj = new JSONObject(params);

        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
    //***************
    //**************************
    //***********************************
    //**************************************[FIN de los metodos para la tabla Tutor]


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


}
