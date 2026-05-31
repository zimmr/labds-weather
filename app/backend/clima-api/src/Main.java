import com.sun.net.httpserver.HttpServer;
import controller.Router;
import repositories.IFavoriteRepository;
import repositories.ISearchLogRepository;
import repositories.IUserRepository;
import repositories.FavoriteRepository;
import repositories.SearchLogRepository;
import repositories.UserRepository;
import services.IFavoriteService;
import services.IGeoApiService;
import services.ISearchLogService;
import services.IUserService;
import services.FavoriteService;
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
        ISearchLogRepository searchLogRepository = new SearchLogRepository();
        ISearchLogService searchLogService = new SearchLogService(searchLogRepository);

        IGeoApiService geoApiService = new GeoApiService();
        ICurrentWeatherApiService currentWeatherApiService = new CurrentWeatherApiService(searchLogService);
        
        IUserRepository userRepository = new UserRepository();
        IUserService userService = new UserService(userRepository);

        IFavoriteRepository favoriteRepository = new FavoriteRepository();
        IFavoriteService favoriteService = new FavoriteService(favoriteRepository, userService);

        router = new Router(geoApiService, currentWeatherApiService, userService, searchLogService, favoriteService);

        router.createContext(server);

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor rodando em https://localhost:8080");
    }
}