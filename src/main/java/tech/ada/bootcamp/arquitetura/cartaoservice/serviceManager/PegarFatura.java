package tech.ada.bootcamp.arquitetura.cartaoservice.serviceManager;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.FaturaService;

@Service
public class PegarFatura {

    private FaturaService faturaService;

    public PegarFatura(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    public FaturaResponse execute(String numeroCartao, int month, int year){
        return faturaService.pegarFatura(numeroCartao, month, year);
    }
}

