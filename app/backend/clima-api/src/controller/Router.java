package controller;

import com.sun.net.httpserver.HttpServer;

public class Router {
    
    private WeatherHandler _weatherHandler = new WeatherHandler();
    private GeoHandler _geoHandler = new GeoHandler();

    public Router() { }

    public void createContext(HttpServer server) {
        _weatherHandler.create(server);
        _geoHandler.create(server);
    }
}
