package controller;

import com.sun.net.httpserver.HttpServer;

import services.IFavoriteService;
import services.IHistoryService;
import services.IWeatherApiService;
import services.IGeoApiService;
import services.ISearchLogService;
import services.IUserService;

public class Router {
    
    private WeatherController weatherController;
    private GeoController geoController;
    private UserController userController;
    private LogController logController;
    private FavoriteController favoriteController;
    private HistoryController historyController;

    public Router(IGeoApiService geoApiService, IWeatherApiService weatherApiService, IUserService userService, ISearchLogService searchLogService, IFavoriteService favoriteService, IHistoryService historyService) {
        geoController = new GeoController(geoApiService);
        weatherController = new WeatherController(weatherApiService);
        userController = new UserController(userService);
        logController = new LogController(searchLogService);
        favoriteController = new FavoriteController(favoriteService);
        historyController = new HistoryController(historyService);
    }

    public void createContext(HttpServer server) {
        weatherController.create(server);
        geoController.create(server);
        userController.create(server);
        logController.create(server);
        favoriteController.create(server);
        historyController.create(server);
    }
}
