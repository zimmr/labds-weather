package model.dtos.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErrorResponse {
    public String message;
    public List<String> errors;

    public ErrorResponse(String message) {
        this.message = message;
        errors = new ArrayList<String>();
    }

    public ErrorResponse(String message, String error) {
        this.message = message;
        errors = Arrays.asList(error);
    }

    public ErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
