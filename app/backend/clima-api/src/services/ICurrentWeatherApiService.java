package services;

import model.dtos.request.CurrentWeatherRequest;
import model.dtos.response.CurrentWeatherDto;

public interface ICurrentWeatherApiService {
    public CurrentWeatherDto getCurrentWeather(CurrentWeatherRequest request);
}