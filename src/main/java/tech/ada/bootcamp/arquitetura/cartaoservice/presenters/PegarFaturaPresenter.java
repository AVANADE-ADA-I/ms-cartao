package tech.ada.bootcamp.arquitetura.cartaoservice.presenters;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CartaoService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CompraService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.FaturaService;

@Service
public class PegarFaturaPresenter {

    private FaturaService faturaService;
    private CompraService compraService;
    private CartaoService cartaoService;
    public PegarFaturaPresenter(FaturaService faturaService, CompraService compraService, CartaoService cartaoService) {
        this.faturaService = faturaService;
        this.compraService = compraService;
        this.cartaoService = cartaoService;
    }

    public FaturaResponse execute(String numeroCartao, int month, int year){
        return faturaService.pegarFatura(numeroCartao, month, year);

    }
}

