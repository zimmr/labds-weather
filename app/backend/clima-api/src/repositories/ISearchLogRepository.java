package repositories;

import java.util.ArrayList;
import model.SearchLog;

public interface ISearchLogRepository {
    public ArrayList<SearchLog> getAll();
    public void save(SearchLog log);
    // TODO: talvez as ordenações já possam ficar no repository
    // public ArrayList<SearchLog> getMostSearched();
}