package crimen.com.ec.crimendroid_rest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cesaralcivar on 5/8/16.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment CreateFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_crimen);

        FragmentManager manager=getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);
        if(fragment==null){
            fragment=CreateFragment();
            manager.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }

    }
}
