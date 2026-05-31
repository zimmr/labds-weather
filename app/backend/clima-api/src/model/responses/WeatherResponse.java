package model.responses;

import java.time.LocalDate;

public class WeatherResponse {
    public float tempC;
    public float tempF;
    public float feelsLike;

    public float tempMinC;
    public float tempMaxC;
    public float tempMinF;
    public float tempMaxF;

    public float humidity;
    public String description;

    public float wind;
    public float rain;
    public float clouds;

    public LocalDate date;

    public WeatherResponse(float temp, float feelsLike, float tempMin, float tempMax, float humidity, String description, float wind, float rain, float clouds, int date) {
        // this.tempC = tempC;
        // this.tempF = tempF;
        this.feelsLike = feelsLike;
        // this.tempMinC = tempMinC;
        // this.tempMaxC = tempMaxC;
        // this.tempMinF = tempMinF;
        // this.tempMaxF = tempMaxF;
        this.humidity = humidity;
        this.description = description;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        // this.date = date;
    }
}

