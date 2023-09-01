package tech.ada.bootcamp.arquitetura.cartaoservice.presenters;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CartaoService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CompraService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.UsuarioService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CriarCompraPresenter {
    private CompraService compraService;
    private CartaoService cartaoService;
    public CriarCompraPresenter(CompraService compraService, CartaoService cartaoService) {
        this.compraService = compraService;
        this.cartaoService = cartaoService;
    }

    public CompraResponse execute(CompraRequest dto){
        Cartao cartao = cartaoService.getCartao(dto.numeroCartao());
        return compraService.execute(dto, cartao);
    }
}
