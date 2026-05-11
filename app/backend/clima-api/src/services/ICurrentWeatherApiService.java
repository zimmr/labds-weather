package services;

import java.io.IOException;
import java.net.MalformedURLException;

import model.dtos.request.CurrentWeatherRequest;
import model.dtos.response.CurrentWeatherDto;

public interface ICurrentWeatherApiService {
    public CurrentWeatherDto getCurrentWeather(CurrentWeatherRequest request) throws MalformedURLException, IOException, Exception;
}