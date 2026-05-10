package model.dtos.validation;

import java.util.ArrayList;
import model.dtos.request.CurrentWeatherRequest;
import model.dtos.response.ErrorResponse;

public class CurrentWeatherRequestValidator {

    public ErrorResponse validate(CurrentWeatherRequest request)
    {
        ArrayList<String> errors = new ArrayList<String>();

        // Regra 1.1. Não aceitar entrada vazia
        if (request == null || request.city == null || request.city.isEmpty() || request.city.isBlank())
            errors.add("Parâmetro 'city' vazio.");

        if (request == null || request.country == null || request.country.isEmpty() || request.country.isBlank())
            errors.add("Parâmetro 'country' vazio.");

        // Regra 1.2. Validar tamanho
        if (request.city != null && request.city.length() > 50)
            errors.add("Parâmetro 'city' tem tamanho máximo de 50 caracteres.");

        if (request.state != null && request.state.length() > 50)
            errors.add("Parâmetro 'state' tem tamanho máximo de 50 caracteres.");

        if (request.country != null && request.country.length() > 50)
            errors.add("Parâmetro 'country' tem tamanho máximo de 50 caracteres.");
        
        // Regra 1.3. Cidade não pode ter número
        if (request.city != null && request.city.matches(".*\\d.*"))
            errors.add("Parâmetro 'city' contém números.");

        if (request.state != null && request.state.matches(".*\\d.*"))
            errors.add("Parâmetro 'state' contém números.");

        if (request.city != null && request.country.matches(".*\\d.*"))
            errors.add("Parâmetro 'country' contém números.");

        return (errors.size() > 0) ? new ErrorResponse("Requisição inválida.", errors) :null;
    }
}
