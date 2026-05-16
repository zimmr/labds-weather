package model.requests;

public class RequestHeaders {
    private String login;
    private String password;

    public RequestHeaders(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
