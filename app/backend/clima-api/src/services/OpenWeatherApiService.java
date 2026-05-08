package services;

import model.dtos.CityDto;

import java.net.*;
import java.util.List;
import java.util.Scanner;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class OpenWeatherApiService {

    // "https://api.openweathermap.org/data/2.5/weather?lat=-29.9202596&lon=-51.18361&appid={{apiKey}}&units=metric"
    private String BaseUrl = "http://api.openweathermap.org/";
    private String geoDomain = "geo/1.0/";
    private String ApiKey = "51bc103d2f82205d5f7fc5edc7a5a921";

    public List<CityDto> buscarCidade(String cidade) {

        try {
            URL url = new URL(BaseUrl + "geo/1.0/direct?q=" + cidade + "&limit=5&appid=" + ApiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            Scanner sc = new Scanner(conn.getInputStream(), "UTF-8");
            // String json = "";

            // while (sc.hasNext()) 
            // {
            //     json += sc.nextLine();
            // }
            
            // sc.close();
            // System.out.print(json);
            
            // String temp = extrairNumero(json, "\"temp_C\":");
            // String desc = extrairTexto(json, "\"value\": \"");

            // return new Clima(cidade, temp, desc);
            
            return null; // TODO: alterar

        } catch (Exception e) {
            System.out.println("erro:" + e.getMessage());
            return null;
        }
    }

    // private String extrairNumero(String json, String chave) {
    //     try {
    //         int i = json.indexOf(chave) + chave.length();
    //         return json.substring(i, json.indexOf(",", i)).trim();
    //     } catch (Exception e) {
    //         return "N/A";
    //     }
    // }

    // private String extrairTexto(String json, String chave) {
    //     try {
    //         int i = json.indexOf(chave) + chave.length();
    //         return json.substring(i, json.indexOf("\"", i));
    //     } catch (Exception e) {
    //         return "N/A";
    //     }
    // }
}