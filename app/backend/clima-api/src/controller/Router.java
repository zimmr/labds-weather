package controller;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpServer;

public class Router {
    
    // private HttpServer _server;
    private HelloWorldHandler _helloWorldHandler = new HelloWorldHandler();

    public Router() {
    }

    public void createContext(HttpServer server) {

        _helloWorldHandler.createHelloWorldHandler(server);

    }
}
