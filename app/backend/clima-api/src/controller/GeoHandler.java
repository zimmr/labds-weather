package controller;

import com.sun.net.httpserver.HttpServer;

import model.dtos.request.GeoRequest;
import services.IGeoApiService;

public class GeoHandler extends BaseHandler {

    private final IGeoApiService geoApiService;

    public GeoHandler(IGeoApiService geoApiService) {
        this.geoApiService = geoApiService;
    }

    public void create(HttpServer server) {
        String basePath = "/geo";

        server.createContext(basePath, exchange -> {

            var method = exchange.getRequestMethod().toUpperCase();
            
            switch (method) {
                case "GET":
                    get(exchange, GeoRequest.class, geoApiService::searchByName);
                    break;
                case "POST":
                    exchange.sendResponseHeaders(405, -1);
                    exchange.close();
                    break;
                case "PUT":
                    exchange.sendResponseHeaders(405, -1);
                    exchange.close();
                    break;
                case "DELETE":
                    exchange.sendResponseHeaders(405, -1);
                    exchange.close();
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1);
                    exchange.close();
                    break;
            }
            
            return;
        });
    }
}
