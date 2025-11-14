package com.deliverytech.delivery.api;

import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse {

    private Map<String, String> errors;

    public ValidationErrorResponse(int status, String message, String path, Map<String, String> errors) {
        super(status, message, path);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
