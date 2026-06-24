package model.responses;

public class UserResponse {
    public String id;
    public String name;
    public String email;
    public boolean useCelsius;
    
    public UserResponse(String id, String name, String email, boolean useCelsius) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.useCelsius = useCelsius;
    }
}
