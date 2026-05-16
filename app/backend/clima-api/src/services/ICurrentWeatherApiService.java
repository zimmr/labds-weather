package services;

import java.io.IOException;
import java.net.MalformedURLException;

import model.dtos.CurrentWeatherDto;
import model.requests.CurrentWeatherRequest;

public interface ICurrentWeatherApiService {
    public CurrentWeatherDto getCurrentWeather(CurrentWeatherRequest request) throws MalformedURLException, IOException, Exception;
}