package ua.com.lviv.fly.touristhelper.data;

public class JsonVO {
    private String name;
    private String address;
    private String telephone;
    private String coordinates;
    private String cheapestService;
    private String mostExpensiveService;
    private String image;
    private String info;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getCheapestService() {
        return cheapestService;
    }

    public String getMostExpensiveService() {
        return mostExpensiveService;
    }

    public String getImage() {
        return image;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "JsonVO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", cheapestService='" + cheapestService + '\'' +
                ", mostExpensiveService='" + mostExpensiveService + '\'' +
                ", image='" + image + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
