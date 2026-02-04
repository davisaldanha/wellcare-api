package br.com.wellcare.wellcare_api.api.service;

import br.com.wellcare.wellcare_api.api.domain.entity.Usuario;
import br.com.wellcare.wellcare_api.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de serviço responsável pelas regras de negócio
 * relacionadas ao usuário do sistema.
 */

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repository){
        this.usuarioRepository = repository;
    }

    /**
     * Cadastra um novo usuário no sistema.
     * Não permite emails duplicados.
     */
    public Usuario cadastrar(Usuario usuario){
        usuarioRepository.findByEmail(usuario.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        });

        return usuarioRepository.save(usuario);
    }

    /**
     * Retorna todos os usuários cadastrados.
     */
    public List<Usuario> listarUsuarios(){
        return  usuarioRepository.findAll();
    }
}
