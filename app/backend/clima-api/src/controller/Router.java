package controller;

import com.sun.net.httpserver.HttpServer;

import services.ICurrentWeatherApiService;
import services.IGeoApiService;
import services.IUserService;

public class Router {
    
    private CurrentWeatherHandler currentWeatherHandler;
    private GeoHandler geoHandler;
    private UserHandler userHandler;

    public Router(IGeoApiService geoApiService, ICurrentWeatherApiService currentWeatherApiService, IUserService userService) {
        geoHandler = new GeoHandler(geoApiService);
        currentWeatherHandler = new CurrentWeatherHandler(currentWeatherApiService);
        userHandler = new UserHandler(userService);
    }

    public void createContext(HttpServer server) {
        currentWeatherHandler.create(server);
        geoHandler.create(server);
        userHandler.create(server);
    }
}
