package br.com.wellcare.wellcare_api.api.controller.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class ErroValidacao {

    private int status;
    private String erro;
    private String mensagem;
    private Map<String, String> campos;
    private LocalDateTime timestamp;
}
