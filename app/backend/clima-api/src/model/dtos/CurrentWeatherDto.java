package model.dtos;

import java.util.List;

public class CurrentWeatherDto {
    public int id;
    public int cod;
    public String name;
    public int dt;
    public int timezone;
    public int visibility;
    public WeatherMainDto main;
    public List<WeatherDescriptionDto> weather;
}

