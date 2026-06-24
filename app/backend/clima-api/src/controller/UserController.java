package controller;

import com.sun.net.httpserver.HttpServer;

import model.requests.GetUserRequest;
import model.requests.UserLoginRequest;
import model.requests.UserRequest;
import model.requests.validation.GetUserRequestValidator;
import model.requests.validation.UserLoginRequestValidator;
import model.requests.validation.UserRequestValidator;
import services.IUserService;

public class UserController extends BaseController {

    private final IUserService userService;
    private final GetUserRequestValidator getUserRequestValidator = new GetUserRequestValidator();
    private final UserRequestValidator userRequestValidator = new UserRequestValidator();
    private final UserLoginRequestValidator userLoginRequestValidator = new UserLoginRequestValidator();

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    public void create(HttpServer server) {

        server.createContext("/user", exchange -> {
            var method = exchange.getRequestMethod().toUpperCase();
                switch (method) {
                case "GET":
                    get(exchange, GetUserRequest.class, userService::get, getUserRequestValidator::validate);
                    break;
                case "POST":
                    post(exchange, UserRequest.class, userService::save, userRequestValidator::validate);
                    break;
                case "OPTIONS":
                    handleOptions(exchange);
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1);
                    exchange.close();
                    break;
            }
        });

        server.createContext("/user/login", exchange -> {
            var method = exchange.getRequestMethod().toUpperCase();
                switch (method) {
                case "POST":
                    post(exchange, UserLoginRequest.class, userService::login, userLoginRequestValidator::validate);
                    break;
                case "OPTIONS":
                    handleOptions(exchange);
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1);
                    exchange.close();
                    break;
            }
        });

    }
}
