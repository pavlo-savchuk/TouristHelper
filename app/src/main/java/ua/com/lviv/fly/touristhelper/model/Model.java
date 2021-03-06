package ua.com.lviv.fly.touristhelper.model;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.ls.http.base.client.LSClient;

import java.net.CookieStore;

import ua.com.lviv.fly.touristhelper.data.manager.OptionManager;
import ua.com.lviv.fly.touristhelper.data.manager.PlaceItemManager;
import ua.com.lviv.fly.touristhelper.data.manager.ProfileManager;

/**
 * Created on 21.05.2015.
 */
public class Model {

    private static Model instance;

    public static Model instance(Context theContext) {
        if (instance == null) {
            instance = new Model(theContext);
        }

        return instance;
    }


    public static Model instance() {
        if (instance == null) {
            throw new IllegalStateException("Called method on uninitialized model");
        }

        return instance;
    }

    private LSClient client;
    private CookieStore cookieStore;
    private RequestQueue queue;

    private PlaceItemManager stubManager;
    private OptionManager optionManager;
    private ProfileManager profileManager;
    private Gson gson;


    public LSClient getClient() {
        return client;
    }

    public RequestQueue getQueue() {
        return queue;
    }


    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public PlaceItemManager getStubManager() {
        return stubManager;
    }

    public OptionManager getOptionManager() {
        return optionManager;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public Gson getGson() {
        return gson;
    }

    /**
     * NOTE: login is performed in synchroneus way so you must never call it from UI thread.
     */


    private Model(Context context) {
        client = new LSClient.Builder(context).build();

        stubManager = new PlaceItemManager(client);
        optionManager = new OptionManager();
        profileManager = new ProfileManager();
        gson = new Gson();
    }

}
