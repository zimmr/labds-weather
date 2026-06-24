package repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.dtos.CityCount;
import model.entities.SearchLog;

public interface ISearchLogRepository {
    public ArrayList<SearchLog> getAll();
    public void save(SearchLog log);
    public List<CityCount> getTopCities(LocalDate since, int limit);
    public int getTotalSearches(LocalDate since);
}