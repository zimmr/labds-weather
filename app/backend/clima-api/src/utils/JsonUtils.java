package utils;

import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;


public class JsonUtils {

    private static Gson gson = new Gson();

    public static <T> T deserialize(String json, Class<T> classType) {
        return gson.fromJson(json, classType);
    }
    
    public static <T> List<T> deserializeList(String json, Class<T> classType) {
        Type tipo = TypeToken.getParameterized(List.class, classType).getType();
        return gson.fromJson(json, tipo);
    }

    public static String toJson(Object objeto) {
        return gson.toJson(objeto);
    }

    public static String getJsonFromStream(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8);
        StringBuilder json = new StringBuilder();
        
        while (sc.hasNextLine())
            json.append(sc.nextLine());

        sc.close();
        return json.toString();
    }
}
