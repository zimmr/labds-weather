package model.dtos;

public class CityCount {
    public String city;
    public String state;
    public String country;
    public int count;

    public CityCount(String city, String state, String country, int count) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.count = count;
    }
}
