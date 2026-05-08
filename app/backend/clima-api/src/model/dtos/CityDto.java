package model.dtos;

public class CityDto {
    private String Name;
    private String Lat;
    private String Lon;
    private String Country;
    private String State;

    // "name": "Canoas",
    // "lat": -22.3979146,
    // "lon": -42.8855703,
    // "country": "BR",
    // "state": "Rio de Janeiro"

    public CityDto(String name, String lat, String lon, String country, String state) {
        Name = name;
        Lat = lat;
        Lon = lon;
        Country = country;
        State = state;
    }

}
