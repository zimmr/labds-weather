package model.requests.validation;

import java.util.ArrayList;

import model.requests.UpdateFavoriteRequest;
import model.responses.ErrorResponse;

public class UpdateFavoriteRequestValidator {

    public ErrorResponse validate(UpdateFavoriteRequest request) {
        ArrayList<String> errors = new ArrayList<String>();

        if (request == null)
            return new ErrorResponse("Requisição inválida", "Requisição não pode ser nula.");

        if (request.id == null || request.id.isBlank())
            errors.add("Parâmetro 'id' vazio.");

        // Pelo menos um campo de atualização deve ser informado
        boolean hasTitle = request.title != null && !request.title.isBlank();
        boolean hasCity  = request.cityName != null && !request.cityName.isBlank();
        if (!hasTitle && !hasCity)
            errors.add("Informe ao menos 'title' ou 'cityName' para atualizar.");

        if (request.title != null && request.title.length() > 200)
            errors.add("Parâmetro 'title' não deve ter mais que 200 caracteres.");

        if (request.cityName != null && request.cityName.length() > 50)
            errors.add("Parâmetro 'cityName' não deve ter mais que 50 caracteres.");

        if (request.state != null && request.state.length() > 50)
            errors.add("Parâmetro 'state' não deve ter mais que 50 caracteres.");

        if (request.country != null && request.country.length() > 50)
            errors.add("Parâmetro 'country' não deve ter mais que 50 caracteres.");

        return errors.size() > 0 ? new ErrorResponse("Requisição inválida", errors) : null;
    }
}
