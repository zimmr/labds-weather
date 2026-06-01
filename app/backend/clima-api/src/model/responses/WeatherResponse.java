package model.responses;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class WeatherResponse {
    public float tempC;
    public float tempF;
    public float feelsLikeC;
    public float feelsLikeF;

    public float tempMinC;
    public float tempMaxC;
    public float tempMinF;
    public float tempMaxF;

    public float humidity;
    public String description;

    public float windSpeed;
    public float windAngle;
    public float clouds;

    public String icon;

    public LocalDateTime date;

    public WeatherResponse(float temp, float feelsLike, float tempMin, float tempMax, float humidity, String description, float windSpeed, float windAngle, float clouds, int timezone, long date, String icon) {
        this.tempC = temp;
        this.tempF = celsiusToFahrenheit(temp);
        this.feelsLikeC = feelsLike;
        this.feelsLikeF = celsiusToFahrenheit(feelsLike);
        this.tempMinC = tempMin;
        this.tempMaxC = tempMax;
        this.tempMinF = celsiusToFahrenheit(tempMin);
        this.tempMaxF = celsiusToFahrenheit(tempMax);
        this.humidity = humidity;
        this.description = description;
        this.windSpeed = windSpeed;
        this.windAngle = windAngle;
        this.clouds = clouds;
        this.date = Instant.ofEpochSecond(date).atOffset(ZoneOffset.ofTotalSeconds(timezone)).toLocalDateTime();
        this.icon = icon;
    }

    public float celsiusToFahrenheit(float tempC)
    {
        return tempC * 9 / 5 + 32;
    }
}

