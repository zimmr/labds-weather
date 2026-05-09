package model;

// TODO: passar variáveis para camelCase, conforma padrão do Java
// TODO: talvez mover para uma pasta "entities"
// TODO: criar métodos
public class User {
    private String Id;
    private String Name;
    private String Email;
    private String Password;
    private boolean Celsius;
    // TODO: histórico
    // TODO: favoritos
    // TODO: gets e sets
    // TODO: outros métodos

    public User(String id, String name, String email, String password, boolean celsius) {
        Id = id;
        Name = name;
        Email = email;
        Password = password;
        Celsius = celsius;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }
}
