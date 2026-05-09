package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.sun.net.httpserver.HttpExchange;

import utils.JsonUtils;

public class BaseHandler {
    
    protected void setResponse(HttpExchange exchange, int statusCode, byte[] resposta) throws IOException {
        exchange.sendResponseHeaders(statusCode, resposta.length);
        exchange.getResponseBody().write(resposta);
        exchange.getResponseBody().close();
    }

    // Método GET
    protected <TRequest, TResponse> void get(HttpExchange exchange, Class<TRequest> requestClass, Function<TRequest, TResponse> action) throws IOException {
        var query = exchange.getRequestURI().getQuery();
        var request = GetQueryParams(query, requestClass);

        var response = action.apply(request);
        byte[] resposta = JsonUtils.toJson(response).getBytes(StandardCharsets.UTF_8);
        
        setResponse(exchange, 200, resposta);
    }

    // Método POST

    protected <T> T GetQueryParams(String query, Class<T> classType)
    {
        Map<String, Object> queryParamsMap = new HashMap();

        String[] queryParams = query.split("&");

        for (String param : queryParams) {
            var paramValuePair = param.split("=");
            queryParamsMap.put(paramValuePair[0], paramValuePair[1]);
        }

        String queryParamsJson = JsonUtils.toJson(queryParamsMap);
        return JsonUtils.deserialize(queryParamsJson, classType);
    }
        
        
    // TODO: método para obter parâmetros de headers

}
