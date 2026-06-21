package model.requests.validation;

import model.requests.GetHistoryRequest;
import model.responses.ErrorResponse;

public class GetHistoryRequestValidator {

    public ErrorResponse validate(GetHistoryRequest request) {
        if (request == null)
            return new ErrorResponse("Requisição inválida", "Requisição não pode ser nula.");

        return null;
    }
}
