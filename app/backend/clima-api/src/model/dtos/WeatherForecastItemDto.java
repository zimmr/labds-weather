package model.dtos;

import java.util.List;

public class WeatherForecastItemDto {

    public long dt;
    public WeatherMainDto main;
    public List<WeatherDescriptionDto> weather;
    public CloudsDto clouds;
    public WindDto wind;
    public int visibility;
    public double pop;
    public String dt_txt;

    public static class CloudsDto {
        public int all;
    }

    public static class WindDto {
        public double speed;
        public int deg;
        public double gust;
    }
}
