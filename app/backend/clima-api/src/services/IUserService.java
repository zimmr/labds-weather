package services;

import java.io.IOException;
import java.net.MalformedURLException;

import model.entities.User;
import model.requests.GetUserRequest;
import model.requests.UserLoginRequest;
import model.requests.UserRequest;

public interface IUserService {
        public User get(GetUserRequest request) throws MalformedURLException, IOException, Exception;
        public User save(UserRequest request) throws MalformedURLException, IOException, Exception;
        public User login(UserLoginRequest request) throws MalformedURLException, IOException, Exception;
}
