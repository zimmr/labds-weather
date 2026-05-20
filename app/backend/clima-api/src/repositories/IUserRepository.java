package repositories;

import model.entities.User;

public interface IUserRepository {
    public void save(User user);
    public User get(String id);
    public User getByEmail(String email);
    public boolean exists(String email);
}
