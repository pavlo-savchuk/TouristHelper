package ua.com.lviv.fly.touristhelper;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import ua.com.lviv.fly.touristhelper.data.base.DatabaseFacade;
import ua.com.lviv.fly.touristhelper.model.Model;


/**
 * Created on 21.05.2015.
 */
public final class TemplateApplication extends Application {

    private static Context sharedContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedContext = this;
        Model.instance(sharedContext);
        DatabaseFacade.instance(sharedContext);
    }

    @NonNull
    public static Context getSharedContext() {
        if (sharedContext == null) {
            throw new IllegalStateException(
                    "getSharedContext() called before Application.onCreate()");
        }
        return sharedContext;
    }
}
