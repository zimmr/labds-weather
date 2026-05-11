package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import model.SearchLog;

public interface ISearchLogService {
        public List<SearchLog> getAll() throws MalformedURLException, IOException, Exception;
        public void save(SearchLog searchLog) throws MalformedURLException, IOException, Exception;
}
