package controller;

import com.sun.net.httpserver.HttpServer;

import model.requests.WeatherRequest;
import model.requests.validation.WeatherRequestValidator;
import services.IWeatherApiService;


public class WeatherController extends BaseController {

    private final IWeatherApiService weatherApiService;
    private final WeatherRequestValidator currentWeatherRequestValidator = new WeatherRequestValidator();

    public WeatherController(IWeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    public void create(HttpServer server) {

        server.createContext("/weather/current", exchange -> {
                var method = exchange.getRequestMethod().toUpperCase();
                
                switch (method) {
                    case "GET":
                        get(exchange, WeatherRequest.class, weatherApiService::getCurrentWeather, currentWeatherRequestValidator::validate);
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        break;
                }
        });

        server.createContext("/weather/forecast", exchange -> {
                var method = exchange.getRequestMethod().toUpperCase();
                
                switch (method) {
                    case "GET":
                        get(exchange, WeatherRequest.class, weatherApiService::getWeatherForecast, currentWeatherRequestValidator::validate);
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        break;
                }
        });

        server.createContext("/weather", exchange -> {
                var method = exchange.getRequestMethod().toUpperCase();
                
                switch (method) {
                    case "GET":
                        get(exchange, WeatherRequest.class, weatherApiService::getCombinedWeather, currentWeatherRequestValidator::validate);
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        break;
                }
        });
    }
}
