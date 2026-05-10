package controller;

import java.io.IOException;
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
    protected <TRequest, TResponse> void get(HttpExchange exchange, Class<TRequest> requestClass, Action<TRequest, TResponse> action) throws Exception {
        try {
            var query = exchange.getRequestURI().getQuery();
            var request = GetQueryParams(query, requestClass);
    
            var response = action.execute(request);
            handleSuccess(exchange, response);
        } catch (Exception e) {
            handleError(exchange, e);
        }
    }

    // Método GET com validação
    protected <TRequest, TResponse> void get(HttpExchange exchange, Class<TRequest> requestClass, Action<TRequest, TResponse> action, Function<TRequest, ErrorResponse> validation) throws IOException {
        try {
            var query = exchange.getRequestURI().getQuery();
            var request = GetQueryParams(query, requestClass);

            var validationError = validation.apply(request);
            if (validationError != null)
            {
                handleError(exchange, validationError);
                return;
            }

            TResponse response = action.execute(request);
            handleSuccess(exchange, response);
        } catch (Exception e) {
            handleError(exchange, e);
        }
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

    private <TResponse> void handleSuccess(HttpExchange exchange, TResponse response) throws IOException {
        byte[] resposta = JsonUtils.toJson(response).getBytes(StandardCharsets.UTF_8);
        setResponse(exchange, 200, resposta);
    }
    
    private void handleError(HttpExchange exchange, ErrorResponse validationError) throws IOException {
        setResponse(exchange, 400, JsonUtils.toJson(validationError).getBytes(StandardCharsets.UTF_8));
    }

    private void handleError(HttpExchange exchange, Exception e) throws IOException {
        e.printStackTrace();
        var errorResponse = new ErrorResponse(400, e.getMessage());
        handleError(exchange, errorResponse);
    }

    private void setResponse(HttpExchange exchange, int statusCode, byte[] resposta) throws IOException {
        exchange.sendResponseHeaders(statusCode, resposta.length);
        exchange.getResponseBody().write(resposta);
        exchange.getResponseBody().close();
    }
}
