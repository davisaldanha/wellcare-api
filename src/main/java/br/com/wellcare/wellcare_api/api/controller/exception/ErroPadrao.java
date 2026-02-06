package br.com.wellcare.wellcare_api.api.controller.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErroPadrao {

    private int status;
    private String erro;
    private String mensagem;
    private LocalDateTime timestamp;

}
