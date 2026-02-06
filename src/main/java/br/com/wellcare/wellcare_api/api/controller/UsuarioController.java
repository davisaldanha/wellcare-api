package br.com.wellcare.wellcare_api.api.controller;

import br.com.wellcare.wellcare_api.api.domain.entity.Usuario;
import br.com.wellcare.wellcare_api.api.dto.UsuarioCreateDTO;
import br.com.wellcare.wellcare_api.api.dto.UsuarioResponseDTO;
import br.com.wellcare.wellcare_api.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
    @Operation(summary = "Cadastra um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO cadastrar(@RequestBody @Valid UsuarioCreateDTO dto){
        return service.cadastrar(dto);
    }

    /**
     * Endpoint para listagem de usuários.
     */
    @GetMapping
    public List<UsuarioResponseDTO> listar(){
        return service.listarUsuarios();
    }
}
