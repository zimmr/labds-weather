package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import model.SearchLog;
import repositories.ISearchLogRepository;

public class SearchLogService implements ISearchLogService {

    private ISearchLogRepository repository;

    public SearchLogService(ISearchLogRepository repository) {
        this.repository = repository;
    }

    public List<SearchLog> getAll() throws MalformedURLException, IOException, Exception {
        return repository.getAll();
    }

    public void save(SearchLog searchLog) throws MalformedURLException, IOException, Exception {
        repository.save(searchLog);
    }
    
}
