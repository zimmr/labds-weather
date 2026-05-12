package controller;

import com.sun.net.httpserver.HttpServer;

import services.ISearchLogService;

public class LogController extends BaseController {

    private final ISearchLogService searchLogService;

    public LogController(ISearchLogService searchLogService) {
        this.searchLogService = searchLogService;
    }

    public void create(HttpServer server) {
        String basePath = "/logs";

        server.createContext(basePath, exchange -> {
                var method = exchange.getRequestMethod().toUpperCase();
                    switch (method) {
                    case "GET":
                        get(exchange, searchLogService::getAll);
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        break;
                }
        });
    }
}
