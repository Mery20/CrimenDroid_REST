package crimen.com.ec.crimendroid_rest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import crimen.com.ec.crimendroid_rest.R;
import crimen.com.ec.crimendroid_rest.dominio.Crimen;
import crimen.com.ec.crimendroid_rest.util.RequestQueueSingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimenFragment extends Fragment {


    private Crimen crimen;

    private EditText edTitulo;
    private Button btnFecha;
    private CheckBox chResuelto;
    private Button btnguardar;

    private static final String ARG_ID="id";
    private static final String DIALOG_DATE="DialogDate";
    private static final int REQUEST_DATE=0;

    public static Fragment newInstance(int crimenId){
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_ID,crimenId);
        CrimenFragment crimenFragment=new CrimenFragment();
        crimenFragment.setArguments(bundle);
        return crimenFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_crimen, container, false);

        edTitulo=(EditText)view.findViewById(R.id.fragment_crimen_edTitulo);
        btnFecha=(Button)view.findViewById(R.id.fragment_crimen_btnFecha);
        chResuelto=(CheckBox)view.findViewById(R.id.fragment_crimen_chResuelto);
        btnguardar=(Button)view.findViewById(R.id.fragment_crimen_btnguardar);

        edTitulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                crimen.setTitulo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        chResuelto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crimen.setResuelto(isChecked);
            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),crimen.toString(),Toast.LENGTH_LONG).show();

            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),crimen.toString(),Toast.LENGTH_LONG).show();
                postCrimen();

            }
        });

        return view;
    }

    public void fillControls(){
        edTitulo.setText(crimen.getTitulo());
        chResuelto.setChecked(crimen.isResuelto());
        btnFecha.setText(crimen.getFecha().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        int crimenId=getArguments().getInt(ARG_ID);
        if(crimenId!=0)
            getCrimen(crimenId);
        else
            crimen=new Crimen();
    }

    private void postCrimen(){
        String url ="http://10.101.26.197:8080/crimenes/";
        StringRequest getCrimentes=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        crimen= gson.fromJson(response,Crimen.class);
                        fillControls();
                        Log.d("CRIMEN",crimen.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }

        );

        RequestQueueSingleton.getInstance(getActivity()).addToRequestQueue(getCrimentes);
    }

    private void getCrimen(int id){
        String url ="http://10.101.26.197:8080/crimenes/"+id;
        StringRequest getCrimentes=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        crimen= gson.fromJson(response,Crimen.class);
                        fillControls();
                        Log.d("CRIMEN",crimen.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueueSingleton.getInstance(getActivity()).addToRequestQueue(getCrimentes);
    }

}
