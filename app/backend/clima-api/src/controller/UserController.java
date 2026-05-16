package controller;

import com.sun.net.httpserver.HttpServer;

import model.requests.GetUserRequest;
import model.requests.UserRequest;
import model.requests.validation.GetUserRequestValidator;
import model.requests.validation.UserRequestValidator;
import services.IUserService;

public class UserController extends BaseController {

    private final IUserService userService;
    private final GetUserRequestValidator getUserRequestValidator = new GetUserRequestValidator();
    private final UserRequestValidator userRequestValidator = new UserRequestValidator();

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    public void create(HttpServer server) {
        String basePath = "/user";

        server.createContext(basePath, exchange -> {
                var method = exchange.getRequestMethod().toUpperCase();
                    switch (method) {
                    case "GET":
                        get(exchange, GetUserRequest.class, userService::get, getUserRequestValidator::validate);
                        break;
                    case "POST":
                        post(exchange, UserRequest.class, userService::save, userRequestValidator::validate);
                        break;
                    default:
                        exchange.sendResponseHeaders(405, -1);
                        exchange.close();
                        break;
                }
        });
    }
}
