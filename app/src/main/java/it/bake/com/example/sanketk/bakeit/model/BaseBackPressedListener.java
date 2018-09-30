package it.bake.com.example.sanketk.bakeit.model;

import android.support.v7.app.AppCompatActivity;
import it.bake.com.example.sanketk.bakeit.interfaces.OnBackPressedListener;

public class BaseBackPressedListener implements OnBackPressedListener {
    private final AppCompatActivity activity;

    public BaseBackPressedListener(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void doBack() {
        if (activity.getSupportFragmentManager().getBackStackEntryCount() <= 1){
            activity.finish();
        }
        activity.getSupportFragmentManager().popBackStack();
    }
}