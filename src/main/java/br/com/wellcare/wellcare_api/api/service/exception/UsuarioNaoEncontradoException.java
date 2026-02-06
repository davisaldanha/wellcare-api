package br.com.wellcare.wellcare_api.api.service.exception;

/**
 * Exceção lançada quando um usuário não for encontrado no sistema.
 */
public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
