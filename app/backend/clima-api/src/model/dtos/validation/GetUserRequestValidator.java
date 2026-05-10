package model.dtos.validation;

import java.util.ArrayList;

import model.dtos.request.GetUserRequest;
import model.dtos.response.ErrorResponse;

public class GetUserRequestValidator {

    public ErrorResponse validate(GetUserRequest request)
    {
        ArrayList<String> errors = new ArrayList<String>();

        if (request == null || request.id == null || request.id.isEmpty() || request.id.isBlank())
            errors.add("Parâmetro 'id' vazio.");

        return (errors.size() > 0) ? new ErrorResponse("Requisição inválida.", errors) :null;
    }
}
