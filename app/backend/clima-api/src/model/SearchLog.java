package model;

import java.time.LocalDateTime;
import java.util.UUID;

// TODO: talvez mover para uma pasta "entities"
public class SearchLog {
    private String id;
    private City city;
    private LocalDateTime date;

    public SearchLog(City city) {
        this.id = UUID.randomUUID().toString();
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
