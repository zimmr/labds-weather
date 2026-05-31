package services;

import model.dtos.WeatherForecastDto;
import model.requests.WeatherRequest;
import model.responses.WeatherResponse;

public interface IWeatherApiService {
    public WeatherResponse getCurrentWeather(WeatherRequest request) throws Exception;
    public WeatherForecastDto getWeatherForecast(WeatherRequest request) throws Exception;
}