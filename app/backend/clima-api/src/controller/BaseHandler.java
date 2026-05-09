package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.sun.net.httpserver.HttpExchange;

import model.dtos.response.ErrorResponse;
import utils.JsonUtils;

public class BaseHandler {

    // Método GET
    protected <TRequest, TResponse> void get(HttpExchange exchange, Class<TRequest> requestClass, Function<TRequest, TResponse> action) throws IOException {
        var query = exchange.getRequestURI().getQuery();
        var request = GetQueryParams(query, requestClass);

        var response = action.apply(request);
        byte[] resposta = JsonUtils.toJson(response).getBytes(StandardCharsets.UTF_8);
        setResponse(exchange, 200, resposta);
    }

    // Método GET
    protected <TRequest, TResponse> void get(HttpExchange exchange, Class<TRequest> requestClass, Function<TRequest, TResponse> action, Function<TRequest, ErrorResponse> validation) throws IOException {
        var query = exchange.getRequestURI().getQuery();
        var request = GetQueryParams(query, requestClass);

        var validationError = validation.apply(request);
        if (validationError != null)
        {
            setResponse(exchange, 400, JsonUtils.toJson(validationError).getBytes(StandardCharsets.UTF_8));
            return;
        }

        var response = action.apply(request);
        byte[] resposta = JsonUtils.toJson(response).getBytes(StandardCharsets.UTF_8);
        setResponse(exchange, 200, resposta);
    }

    // TODO: Método POST
    // ...
    //
   
    // TODO: método para obter parâmetros de headers
    // ...
    //    

    private <T> T GetQueryParams(String query, Class<T> classType)
    {
        try {
            Map<String, Object> queryParamsMap = new HashMap<String, Object>();

            String[] queryParams = query.split("&");

            for (String param : queryParams) {
                var paramValuePair = param.split("=");
                
                var key = getValueFromArray(paramValuePair, 0);
                var value = getValueFromArray(paramValuePair, 1);

                if (key != null)
                    queryParamsMap.put(key.toString(), value);
            }

            String queryParamsJson = JsonUtils.toJson(queryParamsMap);
            System.out.println(queryParamsJson);
            return JsonUtils.deserialize(queryParamsJson, classType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object getValueFromArray(String[] paramValuePair, int index)
    {
        try {
            return paramValuePair[index];
        } catch (Exception e) {
            return null;
        }
    }

    private void setResponse(HttpExchange exchange, int statusCode, byte[] resposta) throws IOException {
        exchange.sendResponseHeaders(statusCode, resposta.length);
        exchange.getResponseBody().write(resposta);
        exchange.getResponseBody().close();
    }
}
