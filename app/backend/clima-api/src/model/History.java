package model;

import java.time.LocalDateTime;


public class History {

    private String id;
    private String userId;
    private City city;
    private LocalDateTime dataConsulta; 

    public History(String id, String userId, City city, String dadosTempo, LocalDateTime dataConsulta) {
        this.id = id;
        this.userId = userId;
        this.city = city;
        this.dataConsulta = dataConsulta;
    }

    public static History create(String id, String userId, City city, String dadosTempo) {
        return new History(id, userId, city, dadosTempo, LocalDateTime.now());
    }


    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public City getCity() {
        return city;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

}
