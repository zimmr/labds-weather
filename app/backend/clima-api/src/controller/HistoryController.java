package controller;

import com.sun.net.httpserver.HttpServer;

import model.requests.DeleteHistoryRequest;
import model.requests.GetHistoryRequest;
import model.requests.validation.DeleteHistoryRequestValidator;
import model.requests.validation.GetHistoryRequestValidator;
import services.IHistoryService;

public class HistoryController extends BaseController {

    private final IHistoryService historyService;
    private final GetHistoryRequestValidator getHistoryValidator = new GetHistoryRequestValidator();
    private final DeleteHistoryRequestValidator deleteHistoryValidator = new DeleteHistoryRequestValidator();

    public HistoryController(IHistoryService historyService) {
        this.historyService = historyService;
    }

    public void create(HttpServer server) {

        server.createContext("/user/history", exchange -> {
            var method = exchange.getRequestMethod().toUpperCase();
            switch (method) {
                case "GET":
                    get(exchange, GetHistoryRequest.class, historyService::getByUser, getHistoryValidator::validate);
                    break;
                case "DELETE":
                    post(exchange, DeleteHistoryRequest.class, historyService::deleteByUser, deleteHistoryValidator::validate);
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1);
                    exchange.close();
                    break;
            }
        });
    }
}
