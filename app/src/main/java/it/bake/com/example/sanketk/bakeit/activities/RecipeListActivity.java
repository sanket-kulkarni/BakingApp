package it.bake.com.example.sanketk.bakeit.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.bake.com.example.sanketk.bakeit.R;
import it.bake.com.example.sanketk.bakeit.fragments.FragmentRecipeDetailsList;
import it.bake.com.example.sanketk.bakeit.fragments.FragmentRecipeList;
import it.bake.com.example.sanketk.bakeit.fragments.FragmentRecipeSteps;
import it.bake.com.example.sanketk.bakeit.fragments.MasterDetailsFragment;
import it.bake.com.example.sanketk.bakeit.interfaces.OnBackPressedListener;
import it.bake.com.example.sanketk.bakeit.interfaces.ShowOrHideBackButtonInActionBar;
import it.bake.com.example.sanketk.bakeit.model.Recipe;
import it.bake.com.example.sanketk.bakeit.model.Steps;

public class RecipeListActivity extends AppCompatActivity implements FragmentRecipeList.DataPassListener, FragmentRecipeDetailsList.DataPassToStepsListener, ShowOrHideBackButtonInActionBar,FragmentRecipeDetailsList.DataPassToStepsListenerNew{
    protected OnBackPressedListener onBackPressedListener;
    private boolean isTwoPane;
    private changeFragment mChangeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        isTwoPane = getResources().getBoolean(R.bool.is_two_pane);
        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.container, new FragmentRecipeList())
                    .addToBackStack("f1")
                    .commit();
        }

    }



    public void setChangeFragment(changeFragment changeFragmentCall) {
        this.mChangeFragment = changeFragmentCall;
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void passData(Recipe recipe) {

        if (isTwoPane) {
            MasterDetailsFragment fragmentRecipeDetails = new MasterDetailsFragment();
            Bundle b = new Bundle();
            b.putParcelable("recipe", recipe);
            fragmentRecipeDetails.setArguments(b);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragmentRecipeDetails)
                    .addToBackStack("f4")
                    .commit();
        } else {
            FragmentRecipeDetailsList fragmentRecipeDetailsList = new FragmentRecipeDetailsList();
            Bundle b = new Bundle();
            b.putParcelable("recipe", recipe);
            fragmentRecipeDetailsList.setArguments(b);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragmentRecipeDetailsList)
                    .addToBackStack("f2")
                    .commit();
        }
    }

    @Override
    public void showOrHide(Boolean b) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(b);
    }

    @Override
    public void passDataToStepsN(Steps steps, int position, Recipe recipe) {
        FragmentRecipeSteps fragmentRecipeSteps = new FragmentRecipeSteps();
        Bundle b = new Bundle();
        b.putSerializable("steps", steps);
        b.putInt("position",position);
        b.putParcelable("recipe", recipe);
        fragmentRecipeSteps.setArguments(b);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragmentRecipeSteps)
                .commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public interface changeFragment {
        void changeFragmentInStepsFragment(Steps steps);
    }


    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.doBack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void passDataToSteps(Steps steps,int position,Recipe recipe) {

        if (isTwoPane) {
            mChangeFragment.changeFragmentInStepsFragment(steps);

        } else {

            FragmentRecipeSteps fragmentRecipeSteps = new FragmentRecipeSteps();
            Bundle b = new Bundle();
            b.putSerializable("steps", steps);
            b.putInt("position",position);
            b.putParcelable("recipe", recipe);
            fragmentRecipeSteps.setArguments(b);


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragmentRecipeSteps)
                    .addToBackStack("f3")
                    .commit();



        }


    }
}
