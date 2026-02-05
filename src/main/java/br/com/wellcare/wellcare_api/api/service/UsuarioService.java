package br.com.wellcare.wellcare_api.api.service;

import br.com.wellcare.wellcare_api.api.domain.entity.Usuario;
import br.com.wellcare.wellcare_api.api.dto.UsuarioCreateDTO;
import br.com.wellcare.wellcare_api.api.dto.UsuarioResponseDTO;
import br.com.wellcare.wellcare_api.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public UsuarioResponseDTO cadastrar(UsuarioCreateDTO dto){
        usuarioRepository.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        });

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setAtivo(true);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return toResponseDTO(usuarioSalvo);
    }

    /**
     * Retorna todos os usuários cadastrados.
     */
    public List<UsuarioResponseDTO> listarUsuarios(){
        return  usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna o objeto UsuarioResponseDTO.
     */
    private UsuarioResponseDTO toResponseDTO(Usuario usuario){
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
