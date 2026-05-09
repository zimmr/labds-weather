package services;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import model.dtos.request.GeoRequest;
import model.dtos.response.CityDto;
import utils.JsonUtils;

public class GeoApiService extends BaseOpenWeatherApiService implements IGeoApiService {

    private String geoDomain = baseUrl + "geo/1.0/";

    // TODO: substituir por implementação com prazo de expiração
    private HashMap<String, List<CityDto>> cache = new HashMap<>();
    
    public List<CityDto> searchByName(GeoRequest request) {
        try {
            
            // Regra 1.4 Não permitir espaços inválidos   
            String city = URLEncoder.encode(request.city.trim(), StandardCharsets.UTF_8).toLowerCase();

            // Regra 2.3 - Controle de duplicidade: Não consultar API se já consultou antes
            // Regra 4.1 - Cache inteligente: Antes de chamar API → verificar lista
            // Regra 4.2 - Cache inteligente: se existir → usar dado local
            var cacheResult = cache.get(city);
            if (cacheResult != null)
            {
                System.out.println("Resultado retornado do cache. Chave: " + city);
                return cacheResult;
            }
            
            // Regra 4.3 - Cache inteligente: •se não → chamar API
            String path = geoDomain + "direct?q=" + city + "&limit=5&appid=" + apiKey;    
            URL url = URI.create(path).toURL();
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            var result = JsonUtils.deserializeList(getJsonFromStream(conn), CityDto.class);
                
            // Regra 2.2 - Controle de duplicidade: Não salvar se já existir registro
            // Regra 2.4 - Controle de duplicidade: Impedir duplicação na lista
            if (!cache.containsKey(city))
                cache.put(city, result);

            return result;

        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }
}