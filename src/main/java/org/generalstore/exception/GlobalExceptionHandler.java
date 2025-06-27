package org.generalstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

     /*
        Global exception handler for catch all uncaught errors
        todo: delete e.printStackTrace() and Sout
    */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllUncaught(Exception e) {
        Map<String, Object> errorBody = Map.of(
                "message", "Unexpected error."
        );
        e.printStackTrace();
        System.out.println("-----------Błąd-----------");
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        Map<String, Object> body = Map.of(
                "message", "Validation failed.",
                "details", errors
        );

        return ResponseEntity.badRequest().body(body);
    }
}
