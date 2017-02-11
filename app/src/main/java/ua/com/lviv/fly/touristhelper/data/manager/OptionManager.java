package ua.com.lviv.fly.touristhelper.data.manager;


import android.location.Location;

import com.ls.util.L;

public class OptionManager {
    String type;
    String radius;
    String keyword;
    Location myLocation;


    public String getType() {
        L.e("Type = " + type);
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRadius() {
        return radius;
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
}
