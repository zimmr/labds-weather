package model.responses;

public class CurrentWeatherResponse {
    public String city;
    public String country;
    public WeatherResponse current;
    
    public CurrentWeatherResponse(String city, String country, WeatherResponse current) {
        this.city = city;
        this.country = country;
        this.current = current;
    }
}
