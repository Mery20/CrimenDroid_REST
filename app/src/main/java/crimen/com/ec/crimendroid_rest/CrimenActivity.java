package crimen.com.ec.crimendroid_rest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import crimen.com.ec.crimendroid_rest.util.RequestQueueSingleton;

public class CrimenActivity extends AppCompatActivity {


    private List<Crimen> crimenes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crimen);

        // OJO CON LA IP
        String url="http://192.168.1.100:8080/crimenes";

        RequestQueue queue=Volley.newRequestQueue(this);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("CRIMEN",response);
                        Gson gson=new Gson();
                        List<Crimen> result= gson.fromJson(response,
                                new TypeToken<List<Crimen>>(){}.getType());

                        // Paso la lista de crimenes para asignar al adapter
                        llenarCrimenes(crimenes);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("CRIMEN",error.getMessage());
                    }
        });

        queue.add(stringRequest);

    }

    public void llenarCrimenes(List<Crimen> crimenes){
        this.crimenes=crimenes;
    }
}
