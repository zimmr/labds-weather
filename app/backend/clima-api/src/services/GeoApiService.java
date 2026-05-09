package services;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import model.dtos.CityDto;
import utils.JsonUtils;

// TODO: criar interface
// TODO: criar base service ??
public class GeoApiService extends BaseOpenWeatherApiService implements IGeoApiService {

    private String geoDomain = baseUrl + "geo/1.0/";

    public List<CityDto> buscarCidade(String cidade) {

        try {
            // TODO: abstrair tratamento da string
            String cidadeString = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

            // TODO: abstrair
            String path = geoDomain + "direct?q=" + cidadeString + "&limit=5&appid=" + apiKey;

            URL url = URI.create(path).toURL();
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            var json = getJsonFromStream(conn);
            
            return JsonUtils.deserializeList(json, CityDto.class);

        } catch (Exception e) {
            System.out.println("erro:" + e.getMessage());
            return null;
        }
    }
}