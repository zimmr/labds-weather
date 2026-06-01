package model.dtos;

import java.util.List;

public class WeatherForecastItemDto {
    public long dt;
    public String dt_txt;
    public WeatherMainDto main;
    public List<WeatherDescriptionDto> weather;
    public WeatherCloudsDto clouds;
    public WeatherWindDto wind;
}
