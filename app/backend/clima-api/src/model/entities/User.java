package model.entities;

import java.util.UUID;

import utils.HashUtils;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean useCelsius;
    // TODO: histórico
    // TODO: favoritos
    // TODO: outros métodos

    public User() {}

    public User(String id, String name, String email, String password, boolean useCelsius) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.useCelsius = useCelsius;
    }

    public User(String name, String email, String password, boolean useCelsius) {
        this.id =  UUID.randomUUID().toString();
        setName(name);
        setEmail(email);
        setPassword(password);
        setUseCelsius(useCelsius);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = HashUtils.create(password);
    }

    public boolean authenticate(String email, String password){
        if (email.equals(email) && HashUtils.compare(password, this.password))
            return true;

        return false;
    }

    public boolean getUseCelsius() {
        return useCelsius;
    }

    public void setUseCelsius(boolean celsius) {
        this.useCelsius = celsius;
    }
}
