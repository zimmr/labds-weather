package controller;

import com.sun.net.httpserver.HttpServer;

import model.dtos.request.GeoRequest;
import model.dtos.validation.GeoRequestValidator;
import services.IGeoApiService;

public class GeoController extends BaseController {

    private final IGeoApiService geoApiService;
    private final GeoRequestValidator geoRequestValidator = new GeoRequestValidator();

    public GeoController(IGeoApiService geoApiService) {
        this.geoApiService = geoApiService;
    }

    public void create(HttpServer server) {
        String basePath = "/geo";

        server.createContext(basePath, exchange -> {
                var method = exchange.getRequestMethod().toUpperCase();
                    switch (method) {
                    case "GET":
                        get(exchange, GeoRequest.class, geoApiService::searchByName, geoRequestValidator::validate);
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        break;
                }
        });
    }
}
