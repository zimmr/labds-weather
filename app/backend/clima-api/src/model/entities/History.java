package model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class History {
    private String id;
    private String userId;
    private LocalDateTime searchDate;
    private String cityName;
    private String state;
    private String country;
    private float latitude;
    private float longitude;
    private String searchData;

    /** Construtor para restaurar um registro vindo do banco (ID e data já existentes). */
    public History(String id, String userId, LocalDateTime searchDate, String cityName, String state, String country, float latitude, float longitude, String searchData) {
        this.id = id;
        this.userId = userId;
        this.searchDate = searchDate;
        this.cityName = cityName;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.searchData = searchData;
    }

    /** Construtor para criar um novo registro de histórico (gera UUID e data automaticamente). */
    public History(String userId, String cityName, String state, String country, float latitude, float longitude, String searchData) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.searchDate = LocalDateTime.now();
        this.cityName = cityName != null ? cityName.trim() : null;
        this.state = state != null ? state.trim() : null;
        this.country = country != null ? country.trim() : null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.searchData = searchData;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getSearchDate() {
        return searchDate;
    }

    public String getCityName() {
        return cityName;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getSearchData() {
        return searchData;
    }
}
