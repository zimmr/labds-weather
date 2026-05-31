package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import model.entities.Favorite;
import model.requests.DeleteFavoriteRequest;
import model.requests.FavoriteRequest;
import model.requests.GetFavoritesRequest;
import model.requests.UpdateFavoriteRequest;

public interface IFavoriteService {
    List<Favorite> getByUser(GetFavoritesRequest request) throws MalformedURLException, IOException, Exception;
    Favorite save(FavoriteRequest request) throws MalformedURLException, IOException, Exception;
    Favorite update(UpdateFavoriteRequest request) throws MalformedURLException, IOException, Exception;
    void delete(DeleteFavoriteRequest request) throws MalformedURLException, IOException, Exception;
}
