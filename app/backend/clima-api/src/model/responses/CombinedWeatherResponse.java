package model.responses;

public class CombinedWeatherResponse {
    public CurrentWeatherResponse currentWeather;
    public WeatherForecastResponse forecast;
    
    public CombinedWeatherResponse(CurrentWeatherResponse currentWeather, WeatherForecastResponse forecast) {
        this.currentWeather = currentWeather;
        this.forecast = forecast;
    }
}