package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private boolean celsius;
    private List<History> history;
    private List<Favorite> favorites;

    public User(String id, String name, String email, String password, boolean celsius) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.celsius = celsius;
        this.history = new ArrayList<>();
        this.favorites = new ArrayList<>();
    }

    public static User create(String id, String name, String email, String password) {
        return new User(id, name, email, password, true);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isCelsius() {
        return celsius;
    }

    public List<History> getHistory() {
        return history;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }


    public void updateName(String name) {
        this.name = name;
    }


    public void updateEmail(String email) {
        this.email = email;
    }

    public void resetPassword(String hashedPassword) {
        this.password = hashedPassword;
    }

    public void updateCelsius(boolean celsius) {
        this.celsius = celsius;
    }


    public boolean authenticate(String email, String hashedPassword) {
        return this.email.equalsIgnoreCase(email) && this.password.equals(hashedPassword);
    }


    public void addHistory(History item) {
        this.history.add(item);
    }


    public void addFavorite(Favorite favorite) {
        this.favorites.add(favorite);
    }


    public void removeFavorite(String favoriteId) {
        this.favorites.removeIf(f -> f.getId().equals(favoriteId));
    }

    public void clearFavorites() {
        this.favorites = new ArrayList<>();
    }

    public List<Favorite> filterFavorites(String termo) {
        String t = termo.toLowerCase();
        return favorites.stream()
                .filter(f -> f.getTitle().toLowerCase().contains(t)
                        || f.getCity().getName().toLowerCase().contains(t))
                .collect(Collectors.toList());
    }
}
