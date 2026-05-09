package controller;

import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpServer;

import services.IGeoApiService;
import utils.JsonUtils;

public class GeoHandler {

    private final IGeoApiService geoApiService;

    public GeoHandler(IGeoApiService geoApiService) {
        this.geoApiService = geoApiService;
    }

    public void create(HttpServer server) {
        String basePath = "/geo";

        // TODO: alterar e criar métodos
        // TODO: dá pra criar BaseHandler com os métodos genéricos
        server.createContext(basePath, exchange -> {

            // Método GET
            if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                
                var query = exchange.getRequestURI().getQuery();
                var queryParams = query.split("&");
                var city = "";
                
                for (String param : queryParams) {
                    var paramValuePair = param.split("=");
                    if (paramValuePair[0].equalsIgnoreCase("city"))
                    {
                        city = paramValuePair[1];
                    }
                }

                var response = geoApiService.buscarCidade(city);
                byte[] resposta = JsonUtils.toJson(response).getBytes(StandardCharsets.UTF_8);
                
                exchange.sendResponseHeaders(200, resposta.length);
                exchange.getResponseBody().write(resposta);
                exchange.getResponseBody().close();
                return;
            }
            
            exchange.sendResponseHeaders(405, -1);
            exchange.close();
            return;
        });
    }
}
