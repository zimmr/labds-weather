package controller;

import com.sun.net.httpserver.HttpServer;

import services.IFavoriteService;
import services.IHistoryService;
import services.IStatisticsService;
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
    private StatisticsController statisticsController;

    public Router(IGeoApiService geoApiService, IWeatherApiService weatherApiService, IUserService userService, ISearchLogService searchLogService, IFavoriteService favoriteService, IHistoryService historyService, IStatisticsService statisticsService) {
        geoController = new GeoController(geoApiService);
        weatherController = new WeatherController(weatherApiService);
        userController = new UserController(userService);
        logController = new LogController(searchLogService);
        favoriteController = new FavoriteController(favoriteService);
        historyController = new HistoryController(historyService);
        statisticsController = new StatisticsController(statisticsService);
    }

    public void createContext(HttpServer server) {
        weatherController.create(server);
        geoController.create(server);
        userController.create(server);
        logController.create(server);
        favoriteController.create(server);
        historyController.create(server);
        statisticsController.create(server);
    }
}
