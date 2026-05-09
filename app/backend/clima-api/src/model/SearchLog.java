package model;

import java.time.LocalDateTime;

public class SearchLog {

    private String id;
    private City city;
    private LocalDateTime dataConsulta;

    public SearchLog(String id, City city, LocalDateTime dataConsulta) {
        this.id = id;
        this.city = city;
        this.dataConsulta = dataConsulta;
    }


    public static SearchLog create(String id, City city) {
        return new SearchLog(id, city, LocalDateTime.now());
    }


    public String getId() {
        return id;
    }


    public City getCity() {
        return city;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }
}
