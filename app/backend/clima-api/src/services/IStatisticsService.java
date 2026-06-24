package services;

import model.requests.StatisticsRequest;
import model.responses.StatisticsResponse;

public interface IStatisticsService {
    public StatisticsResponse getStatistics(StatisticsRequest request) throws Exception;
}
