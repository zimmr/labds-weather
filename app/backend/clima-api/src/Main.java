import com.sun.net.httpserver.HttpServer;
import controller.Router;
import services.IGeoApiService;
import services.CurrentWeatherApiService;
import services.GeoApiService;
import services.ICurrentWeatherApiService;

import java.net.InetSocketAddress;

public class Main {

    private static Router router;

    public static void main(String[] args) throws Exception {
        
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Dependências
        IGeoApiService geoApiService = new GeoApiService();
        ICurrentWeatherApiService currentWeatherApiService = new CurrentWeatherApiService();
        router = new Router(geoApiService, currentWeatherApiService);

        router.createContext(server);

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor rodando em https://localhost:8080");
    }
}