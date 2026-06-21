package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;

import model.dtos.CurrentWeatherDto;
import model.dtos.WeatherForecastDto;
import model.dtos.WeatherForecastItemDto;
import model.entities.City;
import model.entities.History;
import model.entities.SearchLog;
import model.requests.WeatherRequest;
import model.responses.CurrentWeatherResponse;
import model.responses.WeatherForecastResponse;
import model.responses.WeatherResponse;
import repositories.IHistoryRepository;
import utils.JsonUtils;
import utils.WeatherConditions;

public class WeatherApiService extends BaseOpenWeatherApiService implements IWeatherApiService {

    private String domain = baseUrl + "data/2.5/";
    private HashMap<String, CurrentWeatherDto> cache = new HashMap<>();
    private HashMap<String, WeatherForecastDto> forecastCache = new HashMap<>();
    private final ISearchLogService searchLogService;
    private final IHistoryRepository historyRepository;
    private final IUserService userService;

    public WeatherApiService(ISearchLogService searchLogService, IHistoryRepository historyRepository, IUserService userService) {
        this.searchLogService = searchLogService;
        this.historyRepository = historyRepository;
        this.userService = userService;
    }

    public CurrentWeatherResponse getCurrentWeather(WeatherRequest request) throws Exception {
        
        enforceRequestLimit();
        saveLog(request);

        // Regra Aula 1.4 Não permitir espaços inválidos
        String key = getFormattedQuery(request);

        // Regra Aula 2.3 - Controle de duplicidade: Não consultar API se já consultou antes
        // Regra Aula 4.1 - Cache inteligente: Antes de chamar API → verificar lista
        // Regra Aula 4.2 - Cache inteligente: se existir → usar dado local
        var cacheResult = cache.get(key);
        if (cacheResult != null)
        {
            System.out.println("Em cache: " + key);
            saveHistory(request, cacheResult);
            return mapWeatherResponse(cacheResult);
        }
        
        // Regra Aula 4.3 - Cache inteligente: •se não → chamar API
        String path = domain + "weather?q=" + key + "&units=metric&appid=" + apiKey;

        var result = JsonUtils.deserialize(sendRequest(path), CurrentWeatherDto.class);
            
        // Regra Aula 2.2 - Controle de duplicidade: Não salvar se já existir registro
        // Regra Aula 2.4 - Controle de duplicidade: Impedir duplicação na lista
        if (!cache.containsKey(key))
            cache.put(key, result);

        // REQ20 - Salvar histórico do usuário autenticado
        saveHistory(request, result);

        return mapWeatherResponse(result);
    }

    public WeatherForecastResponse getWeatherForecast(WeatherRequest request) throws Exception {

        enforceRequestLimit();
        saveLog(request);

        // Regra Aula 1.4 Não permitir espaços inválidos
        String key = getFormattedQuery(request);

        // Regra Aula 2.3 - Controle de duplicidade: Não consultar API se já consultou antes
        // Regra Aula 4.1 - Cache inteligente: Antes de chamar API → verificar lista
        // Regra Aula 4.2 - Cache inteligente: se existir → usar dado local
        var cacheResult = forecastCache.get(key);
        if (cacheResult != null)
        {
            System.out.println("Em cache: " + key);
            return mapWeatherForecastResponse(cacheResult);
        }
        
        // Regra Aula 4.3 - Cache inteligente: •se não → chamar API
        String path = domain + "forecast?q=" + key + "&units=metric&appid=" + apiKey;

        var result = JsonUtils.deserialize(sendRequest(path), WeatherForecastDto.class);
            
        // Regra Aula 2.2 - Controle de duplicidade: Não salvar se já existir registro
        // Regra Aula 2.4 - Controle de duplicidade: Impedir duplicação na lista
        if (!forecastCache.containsKey(key))
            forecastCache.put(key, result);

        return mapWeatherForecastResponse(result);
    }

    private void saveLog(WeatherRequest request) throws MalformedURLException, IOException, Exception {
        var searchLog = new SearchLog(new City(request.city, request.state, request.country));
        searchLogService.save(searchLog);
    }

    /**
     * REQ20 - Salva histórico automaticamente se o usuário estiver autenticado.
     * A autenticação é não-obrigatória: se os headers estiverem vazios, não salva.
     */
    private void saveHistory(WeatherRequest request, CurrentWeatherDto result) {
        try {
            if (request.headers == null)
                return;

            var authResult = userService.authenticate(null, request.headers, false);
            if (authResult.isAuthenticated) {
                String searchData = result.main.temp + "°C, " + WeatherConditions.getDescription(result.weather.get(0).id);

                var history = new History(
                    authResult.user.getId(),
                    request.city,
                    request.state,
                    request.country,
                    0, 0,
                    searchData
                );
                historyRepository.save(history);
            }
        } catch (Exception e) {
            // Não quebrar a consulta se falhar ao salvar histórico
            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    private String getFormattedQuery(WeatherRequest request) {
        StringBuilder query = new StringBuilder();
        
        String city = request.city.trim().toLowerCase();
        query.append(city);
        
        if (request.state != null)
        {
            String state = request.state.trim().toLowerCase();
            query.append(", ");
            query.append(state);
        }
            
        String country = request.country.trim().toLowerCase();
        query.append(", ");
        query.append(country);

        return URLEncoder.encode(query.toString(), StandardCharsets.UTF_8);
    }

    private CurrentWeatherResponse mapWeatherResponse(CurrentWeatherDto result) {
        return new CurrentWeatherResponse(
            result.name,
            result.sys.country,
            new WeatherResponse(
                result.main.temp,
                result.main.feels_like,
                result.main.temp_min,
                result.main.temp_max,
                result.main.humidity,
                WeatherConditions.getDescription(result.weather.get(0).id),
                result.wind.speed,
                result.wind.deg,
                result.clouds.all,
                result.timezone,
                result.dt,
                result.weather.get(0).icon
            )
        );
    }

    private WeatherResponse mapWeatherResponse(WeatherForecastItemDto result, int timezone) {
        return new WeatherResponse(
            result.main.temp,
            result.main.feels_like,
            result.main.temp_min,
            result.main.temp_max,
            result.main.humidity,
            WeatherConditions.getDescription(result.weather.get(0).id),
            result.wind.speed,
            result.wind.deg,
            result.clouds.all,
            timezone,
            result.dt,
            result.weather.get(0).icon
        );
    }

    private WeatherForecastResponse mapWeatherForecastResponse(WeatherForecastDto result) {
        return new WeatherForecastResponse(
            result.city.name,
            result.city.country,
            result.list.stream().map(item -> mapWeatherResponse(item, result.city.timezone)).collect(Collectors.toList())
        );
    }
}
