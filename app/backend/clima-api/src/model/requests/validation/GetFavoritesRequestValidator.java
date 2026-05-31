package model.requests.validation;

import model.requests.GetFavoritesRequest;
import model.responses.ErrorResponse;

public class GetFavoritesRequestValidator {

    public ErrorResponse validate(GetFavoritesRequest request) {
        if (request == null)
            return new ErrorResponse("Requisição inválida", "Requisição não pode ser nula.");

        return null;
    }
}
