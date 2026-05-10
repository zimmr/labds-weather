package model;

import java.time.LocalDateTime;

// TODO: talvez mover para uma pasta "entities"
public class SearchLog {
    private String id;
    private City city;
    private LocalDateTime date;

    public SearchLog(String id, City city) {
        this.id = id;
        this.city = city;
        this.date = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
