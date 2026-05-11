package controller;

import com.sun.net.httpserver.HttpServer;

import services.ICurrentWeatherApiService;
import services.IGeoApiService;
import services.ISearchLogService;
import services.IUserService;

public class Router {
    
    private CurrentWeatherHandler currentWeatherHandler;
    private GeoHandler geoHandler;
    private UserHandler userHandler;
    private LogHandler logHandler;

    public Router(IGeoApiService geoApiService, ICurrentWeatherApiService currentWeatherApiService, IUserService userService, ISearchLogService searchLogService) {
        geoHandler = new GeoHandler(geoApiService);
        currentWeatherHandler = new CurrentWeatherHandler(currentWeatherApiService);
        userHandler = new UserHandler(userService);
        logHandler = new LogHandler(searchLogService);
    }

    public void createContext(HttpServer server) {
        currentWeatherHandler.create(server);
        geoHandler.create(server);
        userHandler.create(server);
        logHandler.create(server);
    }
}
