package crimen.com.ec.crimendroid_rest;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import crimen.com.ec.crimendroid_rest.fragments.ListCrimenFragment;

public class ListCrimenActivity extends SingleFragmentActivity {

    @Override
    public Fragment CreateFragment() {
        return new ListCrimenFragment();
    }
}
