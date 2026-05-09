package model;

public class City {
    private String name;
    private String state;
    private String country;
    private String latitude;
    private String longitude;


    public City(String name, String state, String country, String latitude, String longitude) {
        this.name = name;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static City create(String name, String state, String country, String latitude, String longitude) {
        return new City(name, state, country, latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return name + (state != null && !state.isEmpty() ? ", " + state : "") + ", " + country;
    }
}
