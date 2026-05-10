package services;

import utils.Config;

public class BaseOpenWeatherApiService extends BaseService {
    protected String baseUrl = Config.get("openweather.baseUrl");
    protected String apiKey = Config.get("openweather.apiKey");
}