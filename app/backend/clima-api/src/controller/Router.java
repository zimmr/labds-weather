package controller;

import com.sun.net.httpserver.HttpServer;

import services.ICurrentWeatherApiService;
import services.IGeoApiService;

public class Router {
    
    private CurrentWeatherHandler currentWeatherHandler;
    private GeoHandler geoHandler;

    public Router(IGeoApiService geoApiService, ICurrentWeatherApiService currentWeatherApiService) {
        geoHandler = new GeoHandler(geoApiService);
        currentWeatherHandler = new CurrentWeatherHandler(currentWeatherApiService);
    }

    public void createContext(HttpServer server) {
        currentWeatherHandler.create(server);
        geoHandler.create(server);
    }
}
