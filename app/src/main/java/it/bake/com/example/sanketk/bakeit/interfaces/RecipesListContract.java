package it.bake.com.example.sanketk.bakeit.interfaces;

import java.util.List;

import it.bake.com.example.sanketk.bakeit.model.Recipe;


public interface RecipesListContract {

    interface View extends BaseView<Presenter> {

        void showRecipesList(List<Recipe> recipeList);

        void showProgressBar();

        void hidProgressBar();

        void showError(String errorMsg);



    }


    interface Presenter extends BasePresenter {

        void fetchRecipesFromServer();



    }


}
