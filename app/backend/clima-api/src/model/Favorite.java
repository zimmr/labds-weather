package model;

public class Favorite {
    private String Id;
    private String UserId;
    private String Title;
    private City City;
    
    public Favorite(String id, String userId, String title, model.City city) {
        Id = id;
        UserId = userId;
        Title = title;
        City = city;
    }
}
