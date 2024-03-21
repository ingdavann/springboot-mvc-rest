package co.istad.sbdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionValidation {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException exception){
        List<Map<String, Object>> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(
                        fieldError -> {
                            Map<String, Object> error = new HashMap<>();
                            error.put("field", fieldError.getField());
                            error.put("message", fieldError.getDefaultMessage());
                            errors.add(error);
                        }
                );
        return Map.of("errors", errors);
    }
}
