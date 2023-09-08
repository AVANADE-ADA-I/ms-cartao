package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.serviceManager.CriarCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CartaoService;

@RestController
@RequestMapping("/cartao")
@Slf4j
public class CartaoController {
    public CartaoController(CriarCartao cartao) {
        this.cartao = cartao;
    }

    private CriarCartao cartao;

    @PostMapping(path = "/principal", produces = "application/json" )
    public CadastroUsuarioResponse cadastrarUsuario(@RequestBody @Valid CadastroPrincipalRequest dto){
        return this.cartao.execute(dto);
    }

    @PostMapping(path = "/dependente", produces = "application/json" )
    public CadastroUsuarioResponse adicionarDependente(@RequestBody @Valid CadastroDependenteRequest dto){
        return this.cartao.execute(dto);
    }
}
