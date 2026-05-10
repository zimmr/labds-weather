package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import model.SearchLog;

public class MockSearchLogRepository implements ISearchLogRepository {

    private HashMap<String, SearchLog> logs = new HashMap<String, SearchLog>();

    public ArrayList<SearchLog> getAll() {
        return new ArrayList<>(logs.values());
    }

    public void save(SearchLog log) {
        logs.put(log.getId(), log);
    }

    // public ArrayList<SearchLog> getMostSearched() {
        
    //     var list = new ArrayList<SearchLog>(logs.values());

    //     // TODO: ordenar

    //     return list;
    // }
}
