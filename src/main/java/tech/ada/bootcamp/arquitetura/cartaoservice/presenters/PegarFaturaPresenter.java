package tech.ada.bootcamp.arquitetura.cartaoservice.presenters;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.FaturaService;

@Service
public class PegarFaturaPresenter {

    private FaturaService faturaService;

    public PegarFaturaPresenter(FaturaService faturaService) {
        this.faturaService = faturaService;

    }

    public FaturaResponse execute(String numeroCartao, int month, int year){
        return faturaService.pegarFatura(numeroCartao, month, year);

    }
}

