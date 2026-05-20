package services;

import java.io.IOException;
import java.net.MalformedURLException;

import model.entities.User;
import model.requests.GetUserRequest;
import model.requests.UserLoginRequest;
import model.requests.UserRequest;
import repositories.IUserRepository;

public class UserService extends BaseService implements IUserService {

    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public User get(GetUserRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();
        var user = repository.get(request.id);

        if (user == null)
            throw new Exception("Usuário não encontrado.");

        return user;
    }

    public User save(UserRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();
        User user = new User(request.name, request.email, request.password, request.celsius);
        
        if (repository.exists(user.getEmail()))
            throw new Exception("Já existe um usuário cadastrado com este email.");

        repository.save(user);

        return user;
    }

    public User login(UserLoginRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();

        User user = repository.getByEmail(request.email);

        if (user == null)
            throw new Exception("Usuário não encontrado.");
        
        var isAuthenticated = user.authenticate(request.email, request.password);

        if (!isAuthenticated)
            throw new Exception();
            
        return user;
    }

}