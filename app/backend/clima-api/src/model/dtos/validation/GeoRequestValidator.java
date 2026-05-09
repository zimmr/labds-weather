package model.dtos.validation;

import model.dtos.request.GeoRequest;
import model.dtos.response.ErrorResponse;

public class GeoRequestValidator {

    public ErrorResponse validate(GeoRequest request)
    {
        // Regra 1.1. Não aceitar entrada vazia
        if (request == null || request.city == null || request.city.isEmpty() || request.city.isBlank())
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'city' vazio.");

        // Regra 1.2. Validar tamanho
        if (request.city.length() > 50)
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'city' tem tamanho máximo de 50 caracteres.");
        
        // Regra 1.3. Cidade não pode ter número
        if (request.city.matches(".*\\d.*"))
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'city' contém números.");

        return null;
    }
}
