package model.responses;

import java.util.List;

import model.dtos.CityCount;

public class StatisticsResponse {
    public int totalSearches;
    public String period;
    public List<CityCount> topCities;

    public StatisticsResponse(int totalSearches, String period, List<CityCount> topCities) {
        this.totalSearches = totalSearches;
        this.period = period;
        this.topCities = topCities;
    }
}
