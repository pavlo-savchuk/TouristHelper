package ua.com.lviv.fly.touristhelper.data;

import ua.com.lviv.fly.touristhelper.data.base.AbstractVO;

/**
 * Created by PASHA on 10.12.2016.
 */

public class ResultsVO extends AbstractVO<String> {
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
        return "ResultsVO{" +
                "icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
