package controller;

import com.sun.net.httpserver.HttpServer;

import services.IGeoApiService;

public class Router {
    
    private WeatherHandler weatherHandler;
    private GeoHandler geoHandler;

    public Router(IGeoApiService geoApiService) {
        geoHandler = new GeoHandler(geoApiService);
        weatherHandler = new WeatherHandler();
    }

    public void createContext(HttpServer server) {
        weatherHandler.create(server);
        geoHandler.create(server);
    }
}
