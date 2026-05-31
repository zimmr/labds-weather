package repositories;

import java.util.List;

import model.entities.Favorite;

public interface IFavoriteRepository {
    List<Favorite> getByUserId(String userId);
    Favorite getById(String id);
    void save(Favorite favorite);
    void update(Favorite favorite);
    void delete(String id);
    int countByUserId(String userId);
}
