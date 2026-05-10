package services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import utils.JsonUtils;

public class BaseService {

    protected String sendRequest(String path) throws MalformedURLException, IOException {
        URL url = URI.create(path).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        return JsonUtils.getJsonFromStream(conn.getInputStream());
    }
}
