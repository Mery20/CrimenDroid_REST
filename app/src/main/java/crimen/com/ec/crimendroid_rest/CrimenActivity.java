package crimen.com.ec.crimendroid_rest;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
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

import java.util.List;

import crimen.com.ec.crimendroid_rest.dominio.Crimen;
import crimen.com.ec.crimendroid_rest.fragments.CrimenFragment;
import crimen.com.ec.crimendroid_rest.fragments.ListCrimenFragment;

public class CrimenActivity extends SingleFragmentActivity {


    private static final String EXTRA_CRIMEN_ID = "ec.com.crimen.crimendroid_fragmentarguments.crimen_id";

    public static Intent newIntent(Context context, int crimenId) {
        Intent intent = new Intent(context, CrimenActivity.class);
        intent.putExtra(EXTRA_CRIMEN_ID, crimenId);
        return intent;
    }

    @Override
    public Fragment CreateFragment() {
        int crimenId = getIntent().getIntExtra(EXTRA_CRIMEN_ID, 0);
        return CrimenFragment.newInstance(crimenId);
    }
}




