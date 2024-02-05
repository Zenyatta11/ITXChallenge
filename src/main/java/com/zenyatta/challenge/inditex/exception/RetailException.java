package com.zenyatta.challenge.inditex.exception;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatusCode;

public class RetailException extends Exception {
    @Getter
    private final HttpStatusCode statusCode;

    public RetailException(final HttpStatusCode statusCode, @NonNull final String errorMessage) {
        super(errorMessage);
        this.statusCode = statusCode;
    }
}
