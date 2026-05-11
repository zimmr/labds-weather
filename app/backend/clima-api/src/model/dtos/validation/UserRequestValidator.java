package model.dtos.validation;

import java.util.ArrayList;

import model.dtos.request.UserRequest;
import model.dtos.response.ErrorResponse;

public class UserRequestValidator {

    public ErrorResponse validate(UserRequest request)
    {
        ArrayList<String> errors = new ArrayList<String>();

        // TODO: revisar e completar validações
        
        if (request == null || request.name == null || request.name.isEmpty() || request.name.isBlank())
            errors.add("Parâmetro 'name' vazio.");

        if (request.name != null && request.name.length() > 250)
            errors.add("Parâmetro 'name' não deve ter mais que 250 caracteres.");

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

    private boolean validateEmail(String email) {

        if (email.contains(" ") || !email.contains("@"))
            return false;

        var splitEmail = email.split("@");
        if (splitEmail.length < 2)
            return false;

        if (splitEmail.length >= 2)
        {
            if (!splitEmail[1].contains("."))
                return false;

            var splitDomain = splitEmail[1].split("\\.");
            if (splitDomain.length < 2)
                return false;

            if (splitDomain.length >= 2 && !splitDomain[1].equals("org") && !splitDomain[1].equals("com") && !splitDomain[1].equals("edu"))
                return false;
        }

        return true;
    }
}
