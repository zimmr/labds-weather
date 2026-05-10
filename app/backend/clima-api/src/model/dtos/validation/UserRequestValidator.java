package model.dtos.validation;

import java.util.ArrayList;

import model.dtos.request.UserRequest;
import model.dtos.response.ErrorResponse;

public class UserRequestValidator {

    public ErrorResponse validate(UserRequest request)
    {
        ArrayList<String> errors = new ArrayList<String>();
        // TODO: adicionar validações
        
        if (request == null || request.name == null || request.name.isEmpty() || request.name.isBlank())
            errors.add("Requisição inválida: Parâmetro 'name' vazio.");

        return (errors.size() > 0) ? new ErrorResponse("Requisição inválida.", errors) :null;
    }
}
