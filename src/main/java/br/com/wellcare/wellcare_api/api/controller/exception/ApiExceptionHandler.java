package br.com.wellcare.wellcare_api.api.controller.exception;

import br.com.wellcare.wellcare_api.api.service.exception.EmailJaCadastradoException;
import br.com.wellcare.wellcare_api.api.service.exception.UsuarioNaoEncontradoException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Centraliza o tratamento de erros da API.
 */
@RestControllerAdvice
public class ApiExceptionHandler {
    
    /* -------------------
        EXCEÇÕES DE NEGÓCIO
       -------------------*/

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ErroPadrao> tratarEmailDuplicado(EmailJaCadastradoException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErroPadrao.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .erro("Regra de negócio violada")
                        .mensagem(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
    
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> tratarUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErroPadrao.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .erro("Recurso não encontrado")
                        .mensagem(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
    
    /* -------------------
        BEAN VALIDATION
       -------------------*/
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> tratarValidacaoDTO( MethodArgumentNotValidException ex){

        Map<String, String> campos = new HashMap<>();
        
        for(FieldError erro : ex.getBindingResult().getFieldErrors()){
            campos.put(erro.getField(), erro.getDefaultMessage());
        }
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErroValidacao.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .erro("Erro de validação")
                        .mensagem("Um ou mais campos estão inválidas")
                        .campos(campos)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroPadrao> tratarValidacaoParametros(ConstraintViolationException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErroPadrao.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .erro("Erro de validação")
                        .mensagem(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
    
    /* -------------------
        FALLBACK
       -------------------*/

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadrao> tratarErroGenerico(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErroPadrao.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .erro("Erro interno do servidor")
                        .mensagem("Ocorreu um erro inesperado. Tente novamente.")
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}