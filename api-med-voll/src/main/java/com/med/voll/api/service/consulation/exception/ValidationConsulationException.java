package com.med.voll.api.service.consulation.exception;

import java.io.Serial;

public class ValidationConsulationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2968686820134191332L;

    public ValidationConsulationException(String message) {
        super(message);
    }
}
