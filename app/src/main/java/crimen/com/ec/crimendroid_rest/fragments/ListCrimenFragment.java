package crimen.com.ec.crimendroid_rest.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import crimen.com.ec.crimendroid_rest.CrimenActivity;
import crimen.com.ec.crimendroid_rest.R;
import crimen.com.ec.crimendroid_rest.dominio.Crimen;
import crimen.com.ec.crimendroid_rest.util.RequestQueueSingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListCrimenFragment extends Fragment {


    private RecyclerView recyclerView;
    private CrimenAdapter crimenAdapter;
    private boolean subTituloVisible;
    private static final String SAVED_SUBTITLE_VISIBLE="subtitle";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(savedInstanceState!=null){
            subTituloVisible=savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        View view=inflater.inflate(R.layout.fragment_list_crimen, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_crimen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crimen_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_crimen:
                Crimen crimen=new Crimen();
                Intent intent=CrimenActivity.newIntent(getActivity(),crimen.getCrimenId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getCrimenes(){

        String url ="http://10.101.26.197:8080/crimenes";
        StringRequest getCrimentes=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        List<Crimen> result= gson.fromJson(response,
                                new TypeToken<List<Crimen>>(){}.getType());
                        crimenAdapter=new CrimenAdapter(result);
                        recyclerView.setAdapter(crimenAdapter);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE,subTituloVisible);
    }

    @Override
    public void onResume() {
        super.onResume();
        getCrimenes();
    }

    private class CrimenAdapter extends RecyclerView.Adapter<CrimenHoler>{

        private List<Crimen> crimens;

        public CrimenAdapter(List<Crimen> crimens){
            this.crimens=crimens;
        }

        @Override
        public CrimenHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.list_item_crimen,parent,false);
            return new CrimenHoler(view);
        }

        @Override
        public void onBindViewHolder(CrimenHoler holder, int position) {
            Crimen crimen=crimens.get(position);
            holder.bindCrimen(crimen);
        }

        @Override
        public int getItemCount() {
            return crimens.size();
        }
    }

    private class CrimenHoler extends RecyclerView.ViewHolder implements View.OnClickListener{

        CheckBox chResuelto;
        TextView txtTitulo;
        TextView txtFecha;

        private Crimen crimen;

        public CrimenHoler(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            chResuelto =(CheckBox)itemView.findViewById(R.id.list_item_crimen_chResuelto);
            txtTitulo=(TextView)itemView.findViewById(R.id.list_item_crimen_txtTitulo);
            txtFecha=(TextView)itemView.findViewById(R.id.list_item_crimen_txtFecha);
        }

        public void bindCrimen(Crimen crimen){
            this.crimen=crimen;
            txtTitulo.setText(crimen.getTitulo());
            txtFecha.setText(crimen.getFecha().toString());
            chResuelto.setChecked(crimen.isResuelto());
        }

        @Override
        public void onClick(View v) {
            Intent intent= CrimenActivity.newIntent(getContext(),crimen.getCrimenId());
            startActivity(intent);
        }
    }
}








