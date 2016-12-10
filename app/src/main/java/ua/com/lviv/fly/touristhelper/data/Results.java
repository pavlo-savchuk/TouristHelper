package ua.com.lviv.fly.touristhelper.data;

/**
 * Created by PASHA on 10.12.2016.
 */

public class Results {
//    private Geometry[] geometry;
    private String icon;
    private String name;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Results{" +
                "icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
