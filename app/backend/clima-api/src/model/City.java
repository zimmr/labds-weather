package model;

// TODO: passar variáveis para camelCase, conforma padrão do Java
// TODO: talvez mover para uma pasta "entities"
// TODO: criar métodos
public class City {
    private String Name;
    private String State;
    private String Country;
    private String Latitude;
    private String Longitude;
    
    public City(String name, String state, String country, String latitude, String longitude) {
        Name = name;
        State = state;
        Country = country;
        Latitude = latitude;
        Longitude = longitude;
    }

    public String getName() {
        return Name;
    }

    public String getState() {
        return State;
    }

    public String getCountry() {
        return Country;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }
}
