package model.entities;

import java.util.UUID;

public class Favorite {
    private String id;
    private String userId;
    private String title;
    private String cityName;
    private String state;
    private String country;
    private float latitude;
    private float longitude;

    /** Construtor para restaurar um favorito vindo do banco (ID já existente). */
    public Favorite(String id, String userId, String title, String cityName, String state, String country, float latitude, float longitude) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.cityName = cityName;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /** Construtor para criar um novo favorito (gera UUID automaticamente). */
    public Favorite(String userId, String title, String cityName, String state, String country, float latitude, float longitude) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.title = title.trim();
        this.cityName = cityName.trim();
        this.state = state != null ? state.trim() : null;
        this.country = country.trim();
        this.latitude = latitude;
        this.longitude = longitude;
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

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state != null ? state.trim() : null;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country.trim();
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
