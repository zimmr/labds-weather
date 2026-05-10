import com.sun.net.httpserver.HttpServer;
import controller.Router;
import repositories.IUserRepository;
import repositories.MockUserRepository;
import services.IGeoApiService;
import services.IUserService;
import services.UserService;
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
        
        IUserRepository userRepository = new MockUserRepository();
        IUserService userService = new UserService(userRepository);

        router = new Router(geoApiService, currentWeatherApiService, userService);

        router.createContext(server);

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor rodando em https://localhost:8080");
    }
}