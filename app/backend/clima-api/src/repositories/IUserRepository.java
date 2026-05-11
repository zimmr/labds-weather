package repositories;

import model.User;

public interface IUserRepository {
    public void save(User user);
    public User get(String id);
    public boolean exists(String email);
}
