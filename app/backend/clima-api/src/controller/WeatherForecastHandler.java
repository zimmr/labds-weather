package controller;

import com.sun.net.httpserver.HttpServer;


public class WeatherForecastHandler {

    public WeatherForecastHandler() {    }

    public void create(HttpServer server) {
        String basePath = "/weather/forecast";

        server.createContext(basePath, exchange -> {
                var method = exchange.getRequestMethod().toUpperCase();
                
                switch (method) {
                    case "GET":
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        // TODO: implementar
                        System.out.println("Método não implementado: GET /weather/forecast");
                        // get(exchange, GeoRequest.class, geoApiService::searchByName, geoRequestValidator::validate);
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        break;
                }
        });
    }
}
