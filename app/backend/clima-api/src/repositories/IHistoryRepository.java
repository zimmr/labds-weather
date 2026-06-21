package repositories;

import java.util.List;

import model.entities.History;

public interface IHistoryRepository {
    List<History> getByUserId(String userId);
    void save(History history);
    void deleteByUserId(String userId);
}
