package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.sun.net.httpserver.HttpExchange;

import controller.interfaces.Action;
import model.dtos.response.ErrorResponse;
import utils.JsonUtils;

public class BaseHandler {

    // Método GET
    protected <TRequest, TResponse> void get(HttpExchange exchange, Class<TRequest> requestClass, Action<TRequest, TResponse> action, Function<TRequest, ErrorResponse> validation) throws IOException {
        try {
            var query = exchange.getRequestURI().getQuery();
            var request = getQueryParams(query, requestClass);

            var validationError = validation.apply(request);
            if (validationError != null)
            {
                handleError(exchange, validationError);
                return;
            }

            // TODO: try get headers

            TResponse response = action.execute(request);
            handleSuccess(exchange, response);
        } catch (Exception e) {
            handleError(exchange, e);
        }
    }

    // Método POST
    protected <TRequest, TResponse> void post(HttpExchange exchange, Class<TRequest> requestClass, Action<TRequest, TResponse> action, Function<TRequest, ErrorResponse> validation) throws IOException {
        try {
            InputStream inputStream = exchange.getRequestBody();
            TRequest requestBody = getRequestBody(requestClass, inputStream);
            
            var validationError = validation.apply(requestBody);
            if (validationError != null)
            {
                setResponse(exchange, 400, JsonUtils.toJson(validationError).getBytes(StandardCharsets.UTF_8));
                return;
            }
    
            // TODO: try get headers
    
            TResponse response = action.execute(requestBody);
            handleSuccess(exchange, response);
        } catch (Exception e) {
            handleError(exchange, e);
        }
    }

    // TODO: Provavelmente não vai precisar de métodos específicos para PUT e DELETE, podemos usar o de POST, recebendo os parâmetros todos pelo body

    private <TRequest> TRequest getRequestBody(Class<TRequest> requestClass, InputStream inputStream) {
        String json = JsonUtils.getJsonFromStream(inputStream);
        return JsonUtils.deserialize(json, requestClass);
    }
   
    // TODO: método para obter parâmetros de headers
    // ...
    //    

    private <T> T getQueryParams(String query, Class<T> classType)
    {
        try {
            Map<String, Object> queryParamsMap = new HashMap<String, Object>();

            String[] queryParams = query.split("&");

            for (String param : queryParams) {
                var paramValuePair = param.split("=");
                
                var key = getValueFromArray(paramValuePair, 0);
                if (key == "headers") // impede de receber parâmetro 'headers' pela query
                    continue;

                var value = getValueFromArray(paramValuePair, 1);

                if (key != null)
                    queryParamsMap.put(key.toString(), value);
            }

            String queryParamsJson = JsonUtils.toJson(queryParamsMap);
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

    private <TResponse> void handleSuccess(HttpExchange exchange, TResponse response) throws IOException {
        byte[] responseBytes = JsonUtils.toJson(response).getBytes(StandardCharsets.UTF_8);
        setResponse(exchange, 200, responseBytes);
    }
    
    private void handleError(HttpExchange exchange, ErrorResponse validationError) throws IOException {
        setResponse(exchange, 400, JsonUtils.toJson(validationError).getBytes(StandardCharsets.UTF_8));
    }

    private void handleError(HttpExchange exchange, Exception e) throws IOException {
        e.printStackTrace();
        var errorResponse = new ErrorResponse(e.getMessage());
        handleError(exchange, errorResponse);
    }

    private void setResponse(HttpExchange exchange, int statusCode, byte[] responseBytes) throws IOException {
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        exchange.getResponseBody().write(responseBytes);
        exchange.getResponseBody().close();
    }
}
