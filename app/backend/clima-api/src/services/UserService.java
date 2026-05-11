package services;

import java.io.IOException;
import java.net.MalformedURLException;
import model.User;
import model.dtos.request.GetUserRequest;
import model.dtos.request.UserRequest;
import repositories.IUserRepository;

public class UserService implements IUserService {

    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public User get(GetUserRequest request) throws MalformedURLException, IOException, Exception {
        return repository.get(request.id);
    }

    public User save(UserRequest request) throws MalformedURLException, IOException, Exception {
        User user = new User(request.name, request.email, request.password, request.celsius);
        
        if (repository.exists(user.getEmail()))
            throw new Exception("Já existe um usuário cadastrado com este email.");

        repository.save(user);

        return user;
    }
}