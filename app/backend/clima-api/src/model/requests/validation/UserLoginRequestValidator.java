package model.requests.validation;

import java.util.ArrayList;

import model.requests.UserLoginRequest;
import model.responses.ErrorResponse;

public class UserLoginRequestValidator extends EmailValidator {

    public ErrorResponse validate(UserLoginRequest request) {
        
        ArrayList<String> errors = new ArrayList<String>();

        if (request == null || request.email == null || request.email.isEmpty() || request.email.isBlank())
            errors.add("Parâmetro 'email' vazio.");

        if (request.email != null && request.email.length() > 250)
            errors.add("Parâmetro 'email' não deve ter mais que 250 caracteres.");

        if(!validateEmail(request.email))
            errors.add("Parâmetro 'email' inválido: " + request.email + ".");

        if (request == null || request.password == null || request.password.isEmpty() || request.password.isBlank())
            errors.add("Parâmetro 'password' vazio.");

        if (request.password != null && request.password.length() > 250)
            errors.add("Parâmetro 'password' não deve ter mais que 250 caracteres.");

        return (errors.size() > 0) ? new ErrorResponse("Requisição inválida", errors) :null;
    }
}
