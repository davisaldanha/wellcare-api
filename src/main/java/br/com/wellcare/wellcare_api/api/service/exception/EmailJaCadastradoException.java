package br.com.wellcare.wellcare_api.api.service.exception;

/**
 * Exceção lançada quando um email já existe no sistema.
 */
public class EmailJaCadastradoException extends RuntimeException{

    public EmailJaCadastradoException(String mensagem){
        super(mensagem);
    }
}
