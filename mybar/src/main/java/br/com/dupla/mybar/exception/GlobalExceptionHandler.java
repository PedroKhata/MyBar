package br.com.dupla.mybar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura as nossas regras de negócio e converte para JSON
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Map<String, String>> handleRegraNegocioException(RegraNegocioException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Captura qualquer outro erro inesperado (ex: NullPointerException) e converte para JSON
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("message", "Erro interno do servidor: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}