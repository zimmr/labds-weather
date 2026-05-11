package model;


// TODO: talvez mover para uma pasta "entities"
// TODO: verificar tipo da latitude e longitude
public class City {
    private String name;
    private String state;
    private String country;
    private float latitude;
    private float longitude;

    public City(String name, String state, String country) {
        this.name = name;
        this.state = state;
        this.country = country;
    }
    
    public City(String name, String state, String country, float latitude, float longitude) {
        this.name = name;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
