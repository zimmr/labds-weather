package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;

import javax.naming.AuthenticationException;

import model.dtos.AuthenticationResultDto;
import model.entities.User;
import model.requests.GetUserRequest;
import model.requests.RequestHeaders;
import model.requests.UserLoginRequest;
import model.requests.UserRequest;
import model.responses.UserResponse;

public interface IUserService {
        public User get(GetUserRequest request) throws MalformedURLException, IOException, Exception;
        public User save(UserRequest request) throws MalformedURLException, IOException, Exception;
        public UserResponse login(UserLoginRequest request) throws MalformedURLException, IOException, Exception;
        public AuthenticationResultDto authenticate(User user, RequestHeaders headers, boolean isObligatory) throws IllegalArgumentException, NoSuchElementException, AuthenticationException;
}
