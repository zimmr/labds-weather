package services;

import model.requests.WeatherRequest;
import model.responses.CurrentWeatherResponse;
import model.responses.WeatherForecastResponse;

public interface IWeatherApiService {
    public CurrentWeatherResponse getCurrentWeather(WeatherRequest request) throws Exception;
    public WeatherForecastResponse getWeatherForecast(WeatherRequest request) throws Exception;
}