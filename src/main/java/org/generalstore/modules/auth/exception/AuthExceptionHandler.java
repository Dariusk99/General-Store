package org.generalstore.modules.auth.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Order(1)
public class AuthExceptionHandler {

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<?> handleBadCredentials(InvalidLoginException e) {
        Map<String, Object> errorBody = Map.of(
                "message", "Bad credentials."
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);
    }
}
