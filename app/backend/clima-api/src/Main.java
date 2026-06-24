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
import services.IStatisticsService;
import services.IUserService;
import services.FavoriteService;
import services.SearchLogService;
import services.StatisticsService;
import services.UserService;
import services.WeatherApiService;
import services.GeoApiService;
import services.IWeatherApiService;
import repositories.IHistoryRepository;
import repositories.HistoryRepository;
import services.IHistoryService;
import services.HistoryService;

import java.net.InetSocketAddress;

public class Main {

    private static Router router;

    public static void main(String[] args) throws Exception {
        
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Dependências
        ISearchLogRepository searchLogRepository = new SearchLogRepository();
        ISearchLogService searchLogService = new SearchLogService(searchLogRepository);

        IUserRepository userRepository = new UserRepository();
        IUserService userService = new UserService(userRepository);

        IHistoryRepository historyRepository = new HistoryRepository();
        IHistoryService historyService = new HistoryService(historyRepository, userService);

        IGeoApiService geoApiService = new GeoApiService();
        IWeatherApiService currentWeatherApiService = new WeatherApiService(searchLogService, historyRepository, userService);

        IFavoriteRepository favoriteRepository = new FavoriteRepository();
        IFavoriteService favoriteService = new FavoriteService(favoriteRepository, userService);

        IStatisticsService statisticsService = new StatisticsService(searchLogRepository);

        router = new Router(geoApiService, currentWeatherApiService, userService, searchLogService, favoriteService, historyService, statisticsService);

        router.createContext(server);

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor rodando em https://localhost:8080");
    }
}