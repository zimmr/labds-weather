import com.sun.net.httpserver.HttpServer;
import controller.Router;
import repositories.ISearchLogRepository;
import repositories.IUserRepository;
import repositories.MockSearchLogRepository;
import repositories.MockUserRepository;
import services.IGeoApiService;
import services.ISearchLogService;
import services.IUserService;
import services.SearchLogService;
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
        ISearchLogRepository searchLogRepository = new MockSearchLogRepository();
        ISearchLogService searchLogService = new SearchLogService(searchLogRepository);

        IGeoApiService geoApiService = new GeoApiService();
        ICurrentWeatherApiService currentWeatherApiService = new CurrentWeatherApiService(searchLogService);
        
        IUserRepository userRepository = new MockUserRepository();
        IUserService userService = new UserService(userRepository);

        router = new Router(geoApiService, currentWeatherApiService, userService, searchLogService);

        router.createContext(server);

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor rodando em https://localhost:8080");
    }
}