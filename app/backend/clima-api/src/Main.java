import com.sun.net.httpserver.HttpServer;

import controller.Router;

import java.net.InetSocketAddress;

public class Main {

    private static Router router = new Router();

    public static void main(String[] args) throws Exception {
        
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        router.createContext(server);
        
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor rodando https://localhost:8080");
    }

}