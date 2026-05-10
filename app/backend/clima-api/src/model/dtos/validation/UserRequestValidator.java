package model.dtos.validation;

import model.dtos.request.UserRequest;
import model.dtos.response.ErrorResponse;

public class UserRequestValidator {

    public ErrorResponse validate(UserRequest request)
    {
        // TODO: adicionar validações
        
        if (request == null || request.name == null || request.name.isEmpty() || request.name.isBlank())
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'name' vazio.");

        return null;
    }
}
