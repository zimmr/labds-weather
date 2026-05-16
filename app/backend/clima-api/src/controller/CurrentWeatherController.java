package controller;

import com.sun.net.httpserver.HttpServer;

import model.requests.CurrentWeatherRequest;
import model.requests.validation.CurrentWeatherRequestValidator;
import services.ICurrentWeatherApiService;


public class CurrentWeatherController extends BaseController {

    private final ICurrentWeatherApiService currentWeatherApiService;
    private final CurrentWeatherRequestValidator currentWeatherRequestValidator = new CurrentWeatherRequestValidator();

    public CurrentWeatherController(ICurrentWeatherApiService currentWeatherApiService) {
        this.currentWeatherApiService = currentWeatherApiService;
    }

    public void create(HttpServer server) {
        String basePath = "/weather/current";

        server.createContext(basePath, exchange -> {
                var method = exchange.getRequestMethod().toUpperCase();
                
                switch (method) {
                    case "GET":
                        get(exchange, CurrentWeatherRequest.class, currentWeatherApiService::getCurrentWeather, currentWeatherRequestValidator::validate);
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        break;
                }
        });
    }
}
