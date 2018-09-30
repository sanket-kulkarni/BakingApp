package it.bake.com.example.sanketk.bakeit.model;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import it.bake.com.example.sanketk.bakeit.BuildConfig;
import it.bake.com.example.sanketk.bakeit.interfaces.RecipesListContract;
import it.bake.com.example.sanketk.bakeit.network.ApiInterface;
import it.bake.com.example.sanketk.bakeit.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipePresenter implements RecipesListContract.Presenter {
    private static final String TAG = "RecipePresenter";
    private RecipesListContract.View mView;
    List<Recipe> recipeList;

    public RecipePresenter(@NonNull RecipesListContract.View view) {
        mView = view;
        mView.setPresenter(this);
        recipeList = new ArrayList<>();
    }


    @Override
    public void start() {

    }

    @Override
    public void fetchRecipesFromServer() {
        mView.showProgressBar();
        recipeList.clear();

        ApiInterface api = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<Recipe>> call = api.fetchRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {


                recipeList = response.body();
                if (  null != recipeList && !recipeList.isEmpty()){
                    mView.showRecipesList(recipeList);
                    mView.hidProgressBar();
                }

                if (BuildConfig.DEBUG) {

                    Log.d(TAG, "onResponse: " + recipeList.size());
                    Log.d(TAG, "onResponse: " + recipeList.get(0).getName());
                    Log.d(TAG, "onResponse: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                mView.showError(t.getMessage());
                mView.hidProgressBar();
                t.printStackTrace();
            }
        });

    }


}
