package tech.ada.bootcamp.arquitetura.cartaoservice.serviceManager;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Endereco;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CartaoService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.UsuarioService;

@Service
public class CriarCartao {
    private UsuarioService usuarioService;
    private CartaoService cartaoService;
    public CriarCartao(UsuarioService usuarioService, CartaoService cartaoService) {
        this.usuarioService = usuarioService;
        this.cartaoService = cartaoService;
    }

    public CadastroUsuarioResponse execute(CadastroPrincipalRequest dto){
        Principal titular = usuarioService.criarPrincipal(dto);
        Endereco endereco = usuarioService.getEndereco(titular.getIdentificador());
        return cartaoService.cartaoTitular(titular, endereco, dto.tipoCartao(), dto.diaVencimento());
    }

    public CadastroUsuarioResponse execute(CadastroDependenteRequest dto){
        Principal titular = usuarioService.getPrincipal(dto.identificadorTitular());
        Endereco endereco = usuarioService.getEndereco(titular.getIdentificador());
        Dependente dependente = usuarioService.criarDependente(dto, titular);
        return cartaoService.cartaoDependente(dependente, titular, endereco, dto.tipoCartao(), dto.diaVencimento());
    }

}
