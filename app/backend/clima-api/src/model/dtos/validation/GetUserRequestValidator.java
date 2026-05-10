package model.dtos.validation;

import model.dtos.request.GetUserRequest;
import model.dtos.response.ErrorResponse;

public class GetUserRequestValidator {

    public ErrorResponse validate(GetUserRequest request)
    {
        if (request == null || request.id == null || request.id.isEmpty() || request.id.isBlank())
            return new ErrorResponse(400, "Requisição inválida: Parâmetro 'id' vazio.");

        return null;
    }
}
