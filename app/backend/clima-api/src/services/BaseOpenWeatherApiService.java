package services;

import utils.Config;

public class BaseOpenWeatherApiService extends BaseService {

    // "https://api.openweathermap.org/data/2.5/weather?lat=-29.9202596&lon=-51.18361&appid={{apiKey}}&units=metric"
    protected String baseUrl = Config.get("openweather.baseUrl");
    protected String apiKey = Config.get("openweather.apiKey");

}