package repositories;

import java.util.HashMap;

import model.User;

public class MockUserRepository implements IUserRepository {

    private HashMap<String, User> users = new HashMap<String, User>();

    public void save(User user) {
        users.put(user.getId(), user);
    }

    public User get(String id) {
        return users.getOrDefault(id, null);
    }
}