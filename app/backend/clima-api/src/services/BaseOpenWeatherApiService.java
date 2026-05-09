package services;

public class BaseOpenWeatherApiService extends BaseService {

    // "https://api.openweathermap.org/data/2.5/weather?lat=-29.9202596&lon=-51.18361&appid={{apiKey}}&units=metric"
    protected String baseUrl = "http://api.openweathermap.org/";
    protected String apiKey = "51bc103d2f82205d5f7fc5edc7a5a921";

}