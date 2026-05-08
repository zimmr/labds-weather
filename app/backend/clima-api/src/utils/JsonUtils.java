package utils;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class JsonUtils {

    private static Gson gson = new Gson();

    public static <T> T deserialize(String json, Class<T> typeCLass) {
        Type type = new TypeToken<T>(){}.getType();
        return gson.fromJson(json, type);
    }
    
    public static <T> List<T> deserializeList(String json, Class<T> typeClass) {
        Type tipo = TypeToken.getParameterized(List.class, typeClass).getType();
        return gson.fromJson(json, tipo);
    }

    public static String toJson(Object objeto) {
        return gson.toJson(objeto);
    }
}
