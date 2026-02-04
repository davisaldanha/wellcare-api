package br.com.wellcare.wellcare_api.api.controller;

import br.com.wellcare.wellcare_api.api.domain.entity.Usuario;
import br.com.wellcare.wellcare_api.api.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável por expor os endpoints
 * relacionados aos usuários do sistema.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    /**
     * Endpoint para cadastro de um novo usuário.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrar(@RequestBody Usuario usuario){
        return service.cadastrar(usuario);
    }

    /**
     * Endpoint para listagem de usuários.
     */
    public List<Usuario> listar(){
        return service.listarUsuarios();
    }
}
