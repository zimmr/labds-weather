package controller;

import com.sun.net.httpserver.HttpServer;

import services.ICurrentWeatherApiService;
import services.IGeoApiService;
import services.ISearchLogService;
import services.IUserService;

public class Router {
    
    private CurrentWeatherController currentWeatherHandler;
    private GeoController geoHandler;
    private UserController userHandler;
    private LogController logHandler;

    public Router(IGeoApiService geoApiService, ICurrentWeatherApiService currentWeatherApiService, IUserService userService, ISearchLogService searchLogService) {
        geoHandler = new GeoController(geoApiService);
        currentWeatherHandler = new CurrentWeatherController(currentWeatherApiService);
        userHandler = new UserController(userService);
        logHandler = new LogController(searchLogService);
    }

    public void createContext(HttpServer server) {
        currentWeatherHandler.create(server);
        geoHandler.create(server);
        userHandler.create(server);
        logHandler.create(server);
    }
}
