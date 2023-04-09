package com.labanovich.user.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ErrorResponse(Instant time,
                            HttpStatus httpStatus,
                            String errorMessage) {
}
