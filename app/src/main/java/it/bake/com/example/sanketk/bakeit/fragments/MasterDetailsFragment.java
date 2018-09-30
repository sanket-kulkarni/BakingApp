package it.bake.com.example.sanketk.bakeit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import it.bake.com.example.sanketk.bakeit.R;
import it.bake.com.example.sanketk.bakeit.activities.RecipeListActivity;
import it.bake.com.example.sanketk.bakeit.interfaces.ShowOrHideBackButtonInActionBar;
import it.bake.com.example.sanketk.bakeit.model.Recipe;
import it.bake.com.example.sanketk.bakeit.model.Steps;


public class MasterDetailsFragment extends Fragment implements RecipeListActivity.changeFragment {
    private Recipe mRecipe;
    private ShowOrHideBackButtonInActionBar callBackActionbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_y_, container, false);

        Bundle args = getArguments();
        ((RecipeListActivity) getActivity()).setChangeFragment(this);


        if (null != args && args.containsKey("recipe")) {
            mRecipe = args.getParcelable("recipe");

            if (savedInstanceState == null) {


                FragmentRecipeDetailsList fragmentRecipeDetailsList = new FragmentRecipeDetailsList();
                Bundle b = new Bundle();
                b.putParcelable("recipe", mRecipe);
                fragmentRecipeDetailsList.setArguments(b);
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.listcontainer, fragmentRecipeDetailsList)
                        .commit();


            }
        }

        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Make sure that container activity implement the callback interface
        try {
            callBackActionbar = (ShowOrHideBackButtonInActionBar) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataPassListener");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isInTwoPane", true);
    }


    @Override
    public void changeFragmentInStepsFragment(Steps steps) {
        FragmentRecipeSteps fragmentRecipeSteps = new FragmentRecipeSteps();
        Bundle b = new Bundle();
        b.putSerializable("steps", steps);
        fragmentRecipeSteps.setArguments(b);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.detailscontainer, fragmentRecipeSteps)
                .commit();
    }
}
