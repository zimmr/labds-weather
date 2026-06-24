package controller;

import com.sun.net.httpserver.HttpServer;

import model.requests.DeleteFavoriteRequest;
import model.requests.FavoriteRequest;
import model.requests.GetFavoritesRequest;
import model.requests.UpdateFavoriteRequest;
import model.requests.validation.DeleteFavoriteRequestValidator;
import model.requests.validation.FavoriteRequestValidator;
import model.requests.validation.GetFavoritesRequestValidator;
import model.requests.validation.UpdateFavoriteRequestValidator;
import services.IFavoriteService;

public class FavoriteController extends BaseController {

    private final IFavoriteService favoriteService;
    private final GetFavoritesRequestValidator  getFavoritesValidator  = new GetFavoritesRequestValidator();
    private final FavoriteRequestValidator      favoriteValidator      = new FavoriteRequestValidator();
    private final UpdateFavoriteRequestValidator updateFavoriteValidator = new UpdateFavoriteRequestValidator();
    private final DeleteFavoriteRequestValidator deleteFavoriteValidator = new DeleteFavoriteRequestValidator();

    public FavoriteController(IFavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    public void create(HttpServer server) {

        server.createContext("/user/favorites", exchange -> {
            var method = exchange.getRequestMethod().toUpperCase();
            switch (method) {
                case "GET":
                    get(exchange, GetFavoritesRequest.class, favoriteService::getByUser, getFavoritesValidator::validate);
                    break;
                case "POST":
                    post(exchange, FavoriteRequest.class, favoriteService::save, favoriteValidator::validate);
                    break;
                case "PUT":
                    post(exchange, UpdateFavoriteRequest.class, favoriteService::update, updateFavoriteValidator::validate);
                    break;
                case "DELETE":
                    post(exchange, DeleteFavoriteRequest.class, favoriteService::delete, deleteFavoriteValidator::validate);
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
