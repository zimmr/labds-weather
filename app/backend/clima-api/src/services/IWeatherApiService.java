package services;

import model.dtos.CurrentWeatherDto;
import model.dtos.WeatherForecastDto;
import model.requests.WeatherRequest;

public interface IWeatherApiService {
    public CurrentWeatherDto getCurrentWeather(WeatherRequest request) throws Exception;
    public WeatherForecastDto getWeatherForecast(WeatherRequest request) throws Exception;
}