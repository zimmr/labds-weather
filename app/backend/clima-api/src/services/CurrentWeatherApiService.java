package services;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import model.dtos.request.CurrentWeatherRequest;
import model.dtos.response.CurrentWeatherDto;
import utils.JsonUtils;

public class CurrentWeatherApiService extends BaseOpenWeatherApiService implements ICurrentWeatherApiService {

    private String domain = baseUrl + "data/2.5/weather";
    private HashMap<String, CurrentWeatherDto> cache = new HashMap<>();

    public CurrentWeatherDto getCurrentWeather(CurrentWeatherRequest request) {
        try {
            
            // Regra 1.4 Não permitir espaços inválidos
            String key = getFormattedQuery(request);

            // Regra 2.3 - Controle de duplicidade: Não consultar API se já consultou antes
            // Regra 4.1 - Cache inteligente: Antes de chamar API → verificar lista
            // Regra 4.2 - Cache inteligente: se existir → usar dado local
            var cacheResult = cache.get(key);
            if (cacheResult != null)
            {
                System.out.println("Resultado retornado do cache. Chave: " + key);
                return cacheResult;
            }
            
            // Regra 4.3 - Cache inteligente: •se não → chamar API
            String path = domain + "?q=" + key + "&appid=" + apiKey;

            var result = JsonUtils.deserialize(sendRequest(path), CurrentWeatherDto.class);
                
            // Regra 2.2 - Controle de duplicidade: Não salvar se já existir registro
            // Regra 2.4 - Controle de duplicidade: Impedir duplicação na lista
            if (!cache.containsKey(key))
                cache.put(key, result);

            return result;

        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }

    private String getFormattedQuery(CurrentWeatherRequest request) {
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
