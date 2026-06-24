package controller;

import com.sun.net.httpserver.HttpServer;

import model.requests.StatisticsRequest;
import model.requests.validation.StatisticsRequestValidator;
import services.IStatisticsService;

public class StatisticsController extends BaseController {

    private final IStatisticsService statisticsService;
    private final StatisticsRequestValidator statisticsRequestValidator = new StatisticsRequestValidator();

    public StatisticsController(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public void create(HttpServer server) {
        String basePath = "/statistics";

        server.createContext(basePath, exchange -> {
                var method = exchange.getRequestMethod().toUpperCase();
                    switch (method) {
                    case "GET":
                        get(exchange, StatisticsRequest.class, statisticsService::getStatistics, statisticsRequestValidator::validate);
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        break;
                }
        });
    }
}
