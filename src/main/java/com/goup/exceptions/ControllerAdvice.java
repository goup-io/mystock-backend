package com.goup.exceptions;

import com.goup.exceptions.produto.modelo.ModeloComETPException;
import com.goup.exceptions.produto.modelo.ModeloComProdutoException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {
    static class ErrorResponse {
        public LocalDateTime timestamp;
        public int status;
        public String error;
        public String message;
        public String path;
        public Map<String, String> errors;

        public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
        }
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(RegistroNaoEncontradoException ex, WebRequest request) {
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.NOT_FOUND.value();
        String error = HttpStatus.NOT_FOUND.toString();
        String message = ex.getMessage();
        String path = request.getDescription(false);

        ErrorResponse response = new ErrorResponse(timestamp, status, error, message, path);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistroConflitanteException.class)
    public ResponseEntity<ErrorResponse> handleResourceConflictException(RegistroConflitanteException ex, WebRequest request) {
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.CONFLICT.value();
        String error = HttpStatus.CONFLICT.toString();
        String message = ex.getMessage();
        String path = request.getDescription(false);

        ErrorResponse response = new ErrorResponse(timestamp, status, error, message, path);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ConstraintViolationException ex, WebRequest request) {
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.BAD_REQUEST.value();
        String error = HttpStatus.BAD_REQUEST.name();
        String message = ex.getMessage();
        String path = request.getDescription(false);

        ErrorResponse response = new ErrorResponse(timestamp, status, error, message, path);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleLoginInvalido(LoginInvalidoException ex, WebRequest request) {
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.UNAUTHORIZED.value();
        String error = HttpStatus.UNAUTHORIZED.toString();
        String message = "Credenciais inválidas!";
        String path = request.getDescription(false);

        ErrorResponse response = new ErrorResponse(timestamp, status, error, message, path);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OperacaoInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleOperacaoInvalidaException(OperacaoInvalidaException ex, WebRequest request) {
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.BAD_REQUEST.value();
        String error = HttpStatus.BAD_REQUEST.toString();
        String message = ex.getMessage();
        String path = request.getDescription(false);


        ErrorResponse response = new ErrorResponse(timestamp, status, error, message, path);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        return new ResponseEntity<>("Conflito de dados: " + ex.getMostSpecificCause().getMessage(), HttpStatus.CONFLICT);
    }

    //produtos
    @ExceptionHandler(ModeloComETPException.class)
    public ResponseEntity<String> handleModeloComEtpException(ModeloComETPException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ModeloComProdutoException.class)
    public ResponseEntity<String> handleModeloComProdutoException(ModeloComProdutoException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}