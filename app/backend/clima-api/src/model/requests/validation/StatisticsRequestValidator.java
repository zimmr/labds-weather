package model.requests.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.requests.StatisticsRequest;
import model.responses.ErrorResponse;

public class StatisticsRequestValidator {

    private static final List<String> VALID_PERIODS = Arrays.asList("day", "week", "month", "all");

    public ErrorResponse validate(StatisticsRequest request) {
        ArrayList<String> errors = new ArrayList<String>();

        // Se period for nulo ou vazio, será tratado como "all" no service — não é erro
        if (request != null && request.period != null && !request.period.isBlank()) {
            if (!VALID_PERIODS.contains(request.period.toLowerCase())) {
                errors.add("Parâmetro 'period' inválido. Valores aceitos: day, week, month, all.");
            }
        }

        return (errors.size() > 0) ? new ErrorResponse("Requisição inválida", errors) : null;
    }
}
