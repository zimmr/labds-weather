package model.dtos;

import model.entities.User;

public class AuthenticationResultDto {
    public User user;
    public boolean isAuthenticated;

    public AuthenticationResultDto(User user) {
        this.user = user;
    }

    public AuthenticationResultDto(User user, boolean isAuthenticated) {
        this.user = user;
        this.isAuthenticated = isAuthenticated;
    }
}