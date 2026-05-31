package model.requests.validation;

import model.requests.DeleteFavoriteRequest;
import model.responses.ErrorResponse;

public class DeleteFavoriteRequestValidator {

    public ErrorResponse validate(DeleteFavoriteRequest request) {
        if (request == null)
            return new ErrorResponse("Requisição inválida", "Requisição não pode ser nula.");

        if (request.id == null || request.id.isBlank())
            return new ErrorResponse("Requisição inválida", "Parâmetro 'id' vazio.");

        return null;
    }
}
