package controller;

import java.nio.charset.StandardCharsets;
import com.sun.net.httpserver.HttpServer;

import services.OpenWeatherApiService;

public class GeoHandler {

    private OpenWeatherApiService openWeatherApiService = new OpenWeatherApiService();

    public GeoHandler() {}

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

                // TODO: chamar api de geolocalização
                var response = openWeatherApiService.buscarCidade(city);
                System.out.println("feito.");
                // TODO: mapear retorno
                
                String responseMessage = "Tempo agora em "+city+": 19°C";
                byte[] resposta = responseMessage.getBytes(StandardCharsets.UTF_8);
                
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
