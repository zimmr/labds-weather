package controller;

import com.sun.net.httpserver.HttpServer;

import services.IFavoriteService;
import services.IWeatherApiService;
import services.IGeoApiService;
import services.ISearchLogService;
import services.IUserService;

public class Router {
    
    private WeatherController currentWeatherHandler;
    private GeoController geoHandler;
    private UserController userHandler;
    private LogController logHandler;
    private FavoriteController favoriteHandler;

    public Router(IGeoApiService geoApiService, IWeatherApiService weatherApiService, IUserService userService, ISearchLogService searchLogService, IFavoriteService favoriteService) {
        geoHandler = new GeoController(geoApiService);
        currentWeatherHandler = new WeatherController(weatherApiService);
        userHandler = new UserController(userService);
        logHandler = new LogController(searchLogService);
        favoriteHandler = new FavoriteController(favoriteService);
    }

    public void createContext(HttpServer server) {
        currentWeatherHandler.create(server);
        geoHandler.create(server);
        userHandler.create(server);
        logHandler.create(server);
        favoriteHandler.create(server);
    }
}
