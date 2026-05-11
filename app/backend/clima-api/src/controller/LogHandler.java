package controller;

import com.sun.net.httpserver.HttpServer;

import services.ISearchLogService;

public class LogHandler extends BaseHandler {

    private final ISearchLogService searchLogService;

    public LogHandler(ISearchLogService searchLogService) {
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
