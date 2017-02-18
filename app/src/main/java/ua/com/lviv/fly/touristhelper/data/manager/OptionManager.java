package ua.com.lviv.fly.touristhelper.data.manager;


import android.content.SharedPreferences;
import android.location.Location;
import android.preference.PreferenceManager;

import com.ls.util.L;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.TemplateApplication;
import ua.com.lviv.fly.touristhelper.model.Model;

public class OptionManager {
    SharedPreferences radiusPref = PreferenceManager.getDefaultSharedPreferences(TemplateApplication.getSharedContext());
    String type;
    String radius;
    String keyword;
    Location myLocation;


    public String getType() {
        return getLocation() + getRadius() + "&types=" + radiusPref.getString(TemplateApplication.getSharedContext().getString(R.string.types_key), "bank")  + "&key=";
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRadius() {
        return "&radius=" + radiusPref.getString(TemplateApplication.getSharedContext().getString(R.string.radius_key), "1000");
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
        Location location = Model.instance().getOptionManager().getMyLocation();
        return location.getLatitude() + "," + location.getLongitude();
    }
}
