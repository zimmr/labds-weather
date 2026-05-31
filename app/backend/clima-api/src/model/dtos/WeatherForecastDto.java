package model.dtos;

import java.util.List;

public class WeatherForecastDto {
    public String cod;
    public int message;
    public int cnt;
    public List<WeatherForecastItemDto> list;
}
