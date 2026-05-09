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
public class OpenWeatherApiService extends BaseService {

    // "https://api.openweathermap.org/data/2.5/weather?lat=-29.9202596&lon=-51.18361&appid={{apiKey}}&units=metric"
    private String BaseUrl = "http://api.openweathermap.org/";
    private String geoDomain = "geo/1.0/";
    private String ApiKey = "51bc103d2f82205d5f7fc5edc7a5a921";

    public List<CityDto> buscarCidade(String cidade) {

        try {
            // TODO: abstrair tratamento da string
            String cidadeString = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

            // TODO: abstrair
            String path = BaseUrl + "geo/1.0/direct?q=" + cidadeString + "&limit=5&appid=" + ApiKey;

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