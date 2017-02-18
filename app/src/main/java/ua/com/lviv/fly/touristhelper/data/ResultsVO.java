package ua.com.lviv.fly.touristhelper.data;

import com.google.gson.annotations.SerializedName;

import ua.com.lviv.fly.touristhelper.data.base.AbstractVO;


public class ResultsVO extends AbstractVO<String> {
    private String icon;
    private String name;
    private String rating;
    @SerializedName("place_id")
    private String placeId;

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "ResultsVO{" +
                "icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", placeId='" + placeId + '\'' +
                '}';
    }
}
