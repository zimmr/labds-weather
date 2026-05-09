package services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import model.dtos.request.GeoRequest;
import model.dtos.response.CityDto;
import utils.JsonUtils;

public class GeoApiService extends BaseOpenWeatherApiService implements IGeoApiService {

    private String geoDomain = baseUrl + "geo/1.0/";
    
    public List<CityDto> searchByName(GeoRequest request) {
        try {
            // ValidateRequest(request);
    
            // Regra 1.4 Não permitir espaços inválidos   
            String cidadeString = URLEncoder.encode(request.city.trim(), StandardCharsets.UTF_8);
    
            String path = geoDomain + "direct?q=" + cidadeString + "&limit=5&appid=" + apiKey;
    
            URL url = URI.create(path).toURL();
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    
            var json = getJsonFromStream(conn);
            
            return JsonUtils.deserializeList(json, CityDto.class);
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }

    // public void ValidateRequest(GeoRequest request)
    // {
    //     // Regra 1.1. Não aceitar entrada vazia
    //     if (request.city == null || request.city.isEmpty() || request.city.isBlank())
    //         throw new Exception("Requisição inválida: Parâmetro 'city' vazio.");

    //     // Regra 1.2. Validar tamanho
    //     if (request.city.length() > 50)
    //         throw new Exception("Requisição inválida: Parâmetro 'city' tem tamanho máximo de 50 caracteres.");
        
    //     // Regra 1.3. Cidade não pode ter número
    //     if (request.city.contains(".*\\d.*"))
    //         throw new Exception("Requisição inválida: Parâmetro 'city' contém números.");
    // }
}