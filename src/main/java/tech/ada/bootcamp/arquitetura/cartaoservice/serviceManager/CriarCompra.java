package tech.ada.bootcamp.arquitetura.cartaoservice.serviceManager;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CartaoService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CompraService;

@Service
public class CriarCompra {
    private CompraService compraService;
    private CartaoService cartaoService;
    public CriarCompra(CompraService compraService, CartaoService cartaoService) {
        this.compraService = compraService;
        this.cartaoService = cartaoService;
    }

    public CompraResponse execute(CompraRequest dto){
        Cartao cartao = cartaoService.getCartao(dto.numeroCartao());
        return compraService.execute(dto, cartao);
    }
}
