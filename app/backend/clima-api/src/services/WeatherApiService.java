package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import model.dtos.CurrentWeatherDto;
import model.dtos.WeatherForecastDto;
import model.dtos.WeatherForecastItemDto;
import model.entities.City;
import model.entities.SearchLog;
import model.requests.WeatherRequest;
import model.responses.CombinedWeatherResponse;
import model.responses.CurrentWeatherResponse;
import model.responses.WeatherForecastResponse;
import model.responses.WeatherResponse;
import utils.JsonUtils;
import utils.WeatherConditions;

public class WeatherApiService extends BaseOpenWeatherApiService implements IWeatherApiService {

    private String domain = baseUrl + "data/2.5/";
    private HashMap<String, CurrentWeatherDto> cache = new HashMap<>();
    private HashMap<String, WeatherForecastDto> forecastCache = new HashMap<>();
    private final ISearchLogService searchLogService;

    public WeatherApiService(ISearchLogService searchLogService) {
        this.searchLogService = searchLogService;
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
            return mapWeatherResponse(cacheResult);
        }
        
        // Regra Aula 4.3 - Cache inteligente: •se não → chamar API
        String path = domain + "weather?q=" + key + "&units=metric&appid=" + apiKey;

        var result = JsonUtils.deserialize(sendRequest(path), CurrentWeatherDto.class);
            
        // Regra Aula 2.2 - Controle de duplicidade: Não salvar se já existir registro
        // Regra Aula 2.4 - Controle de duplicidade: Impedir duplicação na lista
        if (!cache.containsKey(key))
            cache.put(key, result);

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

    public CombinedWeatherResponse getCombinedWeather(WeatherRequest request) throws Exception
    {
        var currentWeahter = getCurrentWeather(request);
        var forecast = getWeatherForecast(request);

        return new CombinedWeatherResponse(currentWeahter, forecast);
    }

    private void saveLog(WeatherRequest request) throws MalformedURLException, IOException, Exception {
        var searchLog = new SearchLog(new City(request.city, request.state, request.country));
        searchLogService.save(searchLog);
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
                WeatherConditions.getDescription(result.weather.getFirst().id),
                result.wind.speed,
                result.wind.deg,
                result.clouds.all,
                result.timezone,
                result.dt,
                result.weather.getFirst().icon
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
            WeatherConditions.getDescription(result.weather.getFirst().id),
            result.wind.speed,
            result.wind.deg,
            result.clouds.all,
            timezone,
            result.dt,
            result.weather.getFirst().icon
        );
    }

    private WeatherForecastResponse mapWeatherForecastResponse(WeatherForecastDto result) {
        return new WeatherForecastResponse(
            result.city.name,
            result.city.country,
            result.list.stream().map(item -> mapWeatherResponse(item, result.city.timezone)).toList()
        );
    }
}
