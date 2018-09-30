package it.bake.com.example.sanketk.bakeit.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;



import java.util.ArrayList;
import java.util.List;

import it.bake.com.example.sanketk.bakeit.R;
import it.bake.com.example.sanketk.bakeit.model.Ingredients;
import it.bake.com.example.sanketk.bakeit.util.Utility;

public class IngredientsListProvider implements RemoteViewsService.RemoteViewsFactory {

    private List<Ingredients> mIngredientsList = new ArrayList<>();
    private Context ctxt=null;
    private int appWidgetId;

    public IngredientsListProvider(Context ctxt, Intent intent) {
        this.ctxt = ctxt;
        appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

        mIngredientsList = Utility.loadSharedPreferencesRecipeList(ctxt);


    }

    @Override
    public void onDataSetChanged() {
        mIngredientsList = Utility.loadSharedPreferencesRecipeList(ctxt);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {


        return mIngredientsList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews row=new RemoteViews(ctxt.getPackageName(),
                R.layout.row_widget);
        row.setTextViewText(R.id.tv_widget_ingredients,mIngredientsList.get(i).getIngredient());
        row.setTextViewText(R.id.tv_widget_quantity,mIngredientsList.get(i).getQuantity() +mIngredientsList.get(i).getMeasure());

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


}
