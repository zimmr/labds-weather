package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import model.entities.History;
import model.requests.DeleteHistoryRequest;
import model.requests.GetHistoryRequest;
import repositories.IHistoryRepository;

public class HistoryService extends BaseService implements IHistoryService {

    private final IHistoryRepository repository;
    private final IUserService userService;

    public HistoryService(IHistoryRepository repository, IUserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<History> getByUser(GetHistoryRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();

        var authResult = userService.authenticate(null, request.headers, true);
        var user = authResult.user;

        return repository.getByUserId(user.getId());
    }

    @Override
    public void deleteByUser(DeleteHistoryRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();

        var authResult = userService.authenticate(null, request.headers, true);
        var user = authResult.user;

        repository.deleteByUserId(user.getId());
    }
}
