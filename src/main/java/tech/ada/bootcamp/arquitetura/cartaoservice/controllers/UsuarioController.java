package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    private UsuarioService usuarioService;
    public UsuarioController (UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping(path = "", produces = "application/json" )
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid CadastroPrincipalRequest dto){
        return this.usuarioService.criarPrincipal(dto);
    }

    @PostMapping(path = "/dependente", produces = "application/json" )
    public ResponseEntity<String> adicionarDependente(@RequestBody @Valid CadastroDependenteRequest dto){
        return this.usuarioService.criarDependente(dto);
    }


}
