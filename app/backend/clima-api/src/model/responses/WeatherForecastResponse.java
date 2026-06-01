package model.responses;

import java.util.List;

public class WeatherForecastResponse {
    public String city;
    public String country;
    public List<WeatherResponse> forecast;

    public WeatherForecastResponse(String city, String country, List<WeatherResponse> forecast) {
        this.city = city;
        this.country = country;
        this.forecast = forecast;
    }
}
