package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import model.entities.History;
import model.requests.DeleteHistoryRequest;
import model.requests.GetHistoryRequest;

public interface IHistoryService {
    List<History> getByUser(GetHistoryRequest request) throws MalformedURLException, IOException, Exception;
    void deleteByUser(DeleteHistoryRequest request) throws MalformedURLException, IOException, Exception;
}
