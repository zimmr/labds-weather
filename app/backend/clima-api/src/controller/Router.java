package controller;

import com.sun.net.httpserver.HttpServer;

import services.ICurrentWeatherApiService;
import services.IFavoriteService;
import services.IGeoApiService;
import services.ISearchLogService;
import services.IUserService;

public class Router {
    
    private CurrentWeatherController currentWeatherHandler;
    private GeoController geoHandler;
    private UserController userHandler;
    private LogController logHandler;
    private FavoriteController favoriteHandler;

    public Router(IGeoApiService geoApiService, ICurrentWeatherApiService currentWeatherApiService, IUserService userService, ISearchLogService searchLogService, IFavoriteService favoriteService) {
        geoHandler = new GeoController(geoApiService);
        currentWeatherHandler = new CurrentWeatherController(currentWeatherApiService);
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
