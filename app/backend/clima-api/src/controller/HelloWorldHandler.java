package controller;

import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpServer;

public class HelloWorldHandler {

    public HelloWorldHandler() {    }

    public void createHelloWorldHandler(HttpServer server) {
        String basePath = "/hello";

        server.createContext(basePath, exchange -> {

            String responseMessage = "Olá, mundo";

            // Método GET
            if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
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
