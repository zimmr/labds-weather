package model.requests.validation;

import java.util.ArrayList;

import model.requests.FavoriteRequest;
import model.responses.ErrorResponse;

public class FavoriteRequestValidator {

    public ErrorResponse validate(FavoriteRequest request) {
        ArrayList<String> errors = new ArrayList<String>();

        if (request == null)
            return new ErrorResponse("Requisição inválida", "Requisição não pode ser nula.");

        if (request.title == null || request.title.isBlank())
            errors.add("Parâmetro 'title' vazio.");

        if (request.title != null && request.title.length() > 200)
            errors.add("Parâmetro 'title' não deve ter mais que 200 caracteres.");

        if (request.cityName == null || request.cityName.isBlank())
            errors.add("Parâmetro 'cityName' vazio.");

        if (request.cityName != null && request.cityName.length() > 50)
            errors.add("Parâmetro 'cityName' não deve ter mais que 50 caracteres.");

        if (request.state != null && request.state.length() > 50)
            errors.add("Parâmetro 'state' não deve ter mais que 50 caracteres.");

        if (request.country == null || request.country.isBlank())
            errors.add("Parâmetro 'country' vazio.");

        if (request.country != null && request.country.length() > 50)
            errors.add("Parâmetro 'country' não deve ter mais que 50 caracteres.");

        return errors.size() > 0 ? new ErrorResponse("Requisição inválida", errors) : null;
    }
}
