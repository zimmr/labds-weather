package services;

import java.io.IOException;
import java.net.MalformedURLException;
import model.User;
import model.dtos.request.GetUserRequest;
import model.dtos.request.UserRequest;

public interface IUserService {
        public User get(GetUserRequest request) throws MalformedURLException, IOException, Exception;
        public User save(UserRequest request) throws MalformedURLException, IOException, Exception;
}
