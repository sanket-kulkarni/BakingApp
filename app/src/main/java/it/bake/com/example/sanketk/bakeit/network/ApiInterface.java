package it.bake.com.example.sanketk.bakeit.network;



import java.util.List;

import it.bake.com.example.sanketk.bakeit.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("baking.json")
    Call<List<Recipe>> fetchRecipes();
}
