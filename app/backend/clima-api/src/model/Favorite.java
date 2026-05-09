package model;


public class Favorite {

    private String id;
    private String userId;
    private String title;
    private City city;

    public Favorite(String id, String userId, String title, City city) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.city = city;
    }

    public static Favorite create(String id, String userId, String title, City city) {
        return new Favorite(id, userId, title, city);
    }


    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }


    public String getTitle() {
        return title;
    }

    public City getCity() {
        return city;
    }

    public void updateTitle(String title) {
        this.title = title;
    }


    public void updateCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return title + " – " + city.toString();
    }
}
