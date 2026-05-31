package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import model.dtos.CurrentWeatherDto;
import model.dtos.WeatherForecastDto;
import model.entities.City;
import model.entities.SearchLog;
import model.requests.WeatherRequest;
import utils.JsonUtils;

public class WeatherApiService extends BaseOpenWeatherApiService implements IWeatherApiService {

    private String domain = baseUrl + "data/2.5/";
    private HashMap<String, CurrentWeatherDto> cache = new HashMap<>();
    private HashMap<String, WeatherForecastDto> forecastCache = new HashMap<>();
    private final ISearchLogService searchLogService;

    public WeatherApiService(ISearchLogService searchLogService) {
        this.searchLogService = searchLogService;
    }

    public CurrentWeatherDto getCurrentWeather(WeatherRequest request) throws Exception {
        
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
            return cacheResult;
        }
        
        // Regra Aula 4.3 - Cache inteligente: •se não → chamar API
        String path = domain + "weather?q=" + key + "&appid=" + apiKey;

        var result = JsonUtils.deserialize(sendRequest(path), CurrentWeatherDto.class);
            
        // Regra Aula 2.2 - Controle de duplicidade: Não salvar se já existir registro
        // Regra Aula 2.4 - Controle de duplicidade: Impedir duplicação na lista
        if (!cache.containsKey(key))
            cache.put(key, result);

        return result;
    }

    public WeatherForecastDto getWeatherForecast(WeatherRequest request) throws Exception {

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
            return cacheResult;
        }
        
        // Regra Aula 4.3 - Cache inteligente: •se não → chamar API
        String path = domain + "forecast?q=" + key + "&appid=" + apiKey;

        var result = JsonUtils.deserialize(sendRequest(path), WeatherForecastDto.class);
            
        // Regra Aula 2.2 - Controle de duplicidade: Não salvar se já existir registro
        // Regra Aula 2.4 - Controle de duplicidade: Impedir duplicação na lista
        if (!forecastCache.containsKey(key))
            forecastCache.put(key, result);

        return result;
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
    
}
