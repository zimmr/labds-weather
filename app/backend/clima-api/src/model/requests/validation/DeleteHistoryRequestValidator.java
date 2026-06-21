package model.requests.validation;

import model.requests.DeleteHistoryRequest;
import model.responses.ErrorResponse;

public class DeleteHistoryRequestValidator {

    public ErrorResponse validate(DeleteHistoryRequest request) {
        if (request == null)
            return new ErrorResponse("Requisição inválida", "Requisição não pode ser nula.");

        return null;
    }
}
