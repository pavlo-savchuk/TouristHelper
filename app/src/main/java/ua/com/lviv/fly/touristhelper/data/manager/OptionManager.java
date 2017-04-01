package ua.com.lviv.fly.touristhelper.data.manager;


import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.preference.PreferenceManager;

import com.ls.util.L;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.TemplateApplication;
import ua.com.lviv.fly.touristhelper.model.Model;

public class OptionManager {
    private Context context;
    private SharedPreferences radiusPref;
    private String type;
    private String radius;
    private String keyword;
    private Location myLocation;
    private String firstName;
    private String secondName;
    private String email;

    public OptionManager() {
        this.radiusPref = PreferenceManager.getDefaultSharedPreferences(TemplateApplication.getSharedContext());
        this.context = TemplateApplication.getSharedContext();
    }

    public String getType() {
        return getLocation() + getRadius() + "&types=" + radiusPref.getString(context.getString(R.string.types_key), context.getResources().getStringArray(R.array.types)[0]) + "&key=";
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRadius() {
        return "&radius=" + radiusPref.getString(context.getString(R.string.radius_key), "1000");
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Location getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
    }

    private String getLocation() {
        return myLocation.getLatitude() + "," + myLocation.getLongitude();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
