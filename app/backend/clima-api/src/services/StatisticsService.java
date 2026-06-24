package services;

import java.time.LocalDate;
import java.util.List;

import model.dtos.CityCount;
import model.requests.StatisticsRequest;
import model.responses.StatisticsResponse;
import repositories.ISearchLogRepository;

public class StatisticsService extends BaseService implements IStatisticsService {

    private static final int TOP_CITIES_LIMIT = 5;
    private final ISearchLogRepository searchLogRepository;

    public StatisticsService(ISearchLogRepository searchLogRepository) {
        this.searchLogRepository = searchLogRepository;
    }

    @Override
    public StatisticsResponse getStatistics(StatisticsRequest request) throws Exception {
        enforceRequestLimit();

        String period = normalizePeriod(request);
        LocalDate since = calculateSinceDate(period);

        List<CityCount> topCities = searchLogRepository.getTopCities(since, TOP_CITIES_LIMIT);
        int totalSearches = searchLogRepository.getTotalSearches(since);

        return new StatisticsResponse(totalSearches, period, topCities);
    }

    private String normalizePeriod(StatisticsRequest request) {
        if (request == null || request.period == null || request.period.isBlank()) {
            return "all";
        }
        return request.period.toLowerCase();
    }

    private LocalDate calculateSinceDate(String period) {
        return switch (period) {
            case "day"   -> LocalDate.now().minusDays(1);
            case "week"  -> LocalDate.now().minusWeeks(1);
            case "month" -> LocalDate.now().minusMonths(1);
            default      -> null; // "all" — sem filtro de data
        };
    }
}
