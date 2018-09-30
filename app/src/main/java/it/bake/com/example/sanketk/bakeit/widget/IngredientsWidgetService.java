package it.bake.com.example.sanketk.bakeit.widget;


import android.content.Intent;
import android.widget.RemoteViewsService;
import android.widget.Toast;


public class IngredientsWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return (new IngredientsListProvider(this.getApplicationContext(), intent));

    }


}
