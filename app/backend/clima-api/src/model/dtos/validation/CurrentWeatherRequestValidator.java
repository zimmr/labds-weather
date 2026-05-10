package model.dtos.validation;

import model.dtos.request.CurrentWeatherRequest;
import model.dtos.response.ErrorResponse;

public class CurrentWeatherRequestValidator {

    public ErrorResponse validate(CurrentWeatherRequest request)
    {
        // Regra 1.1. Não aceitar entrada vazia
        if (request == null || request.city == null || request.city.isEmpty() || request.city.isBlank())
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'city' vazio.");

        if (request == null || request.country == null || request.country.isEmpty() || request.country.isBlank())
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'country' vazio.");

        // Regra 1.2. Validar tamanho
        if (request.city.length() > 50)
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'city' tem tamanho máximo de 50 caracteres.");

        if (request.state.length() > 50)
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'state' tem tamanho máximo de 50 caracteres.");

        if (request.country.length() > 50)
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'country' tem tamanho máximo de 50 caracteres.");
        
        // Regra 1.3. Cidade não pode ter número
        if (request.city.matches(".*\\d.*"))
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'city' contém números.");

        if (request.state != null && request.state.matches(".*\\d.*"))
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'state' contém números.");

        if (request.country.matches(".*\\d.*"))
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'country' contém números.");

        return null;
    }
}
