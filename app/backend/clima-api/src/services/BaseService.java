package services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BaseService {

    protected String getJsonFromStream(HttpURLConnection conn) throws IOException {
        Scanner sc = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder json = new StringBuilder();
        
        while (sc.hasNextLine())
            json.append(sc.nextLine());

        sc.close();
        return json.toString();
    }

}
