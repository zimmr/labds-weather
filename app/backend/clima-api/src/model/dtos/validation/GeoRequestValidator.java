package model.dtos.validation;

import java.util.ArrayList;

import model.dtos.request.GeoRequest;
import model.dtos.response.ErrorResponse;

public class GeoRequestValidator {

    public ErrorResponse validate(GeoRequest request)
    {
        ArrayList<String> errors = new ArrayList<String>();

        // Regra 1.1. Não aceitar entrada vazia
        if (request == null || request.city == null || request.city.isEmpty() || request.city.isBlank())
            errors.add("Parâmetro 'city' vazio.");

        // Regra 1.2. Validar tamanho
        if (request.city != null && request.city.length() > 50)
            errors.add("Parâmetro 'city' tem tamanho máximo de 50 caracteres.");
        
        // Regra 1.3. Cidade não pode ter número
        if (request.city != null && request.city.matches(".*\\d.*"))
            errors.add("Parâmetro 'city' contém números.");

        return (errors.size() > 0) ? new ErrorResponse("Requisição inválida", errors) :null;
    }
}
