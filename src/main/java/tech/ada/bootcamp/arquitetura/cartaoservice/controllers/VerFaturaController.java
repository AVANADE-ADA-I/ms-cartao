package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.serviceManager.PegarFatura;


@RestController
@RequestMapping("/fatura")
@Slf4j
public class VerFaturaController {
    private PegarFatura pegarFatura;

    public VerFaturaController (PegarFatura pegarFaturaPresenter) {
        this.pegarFatura = pegarFaturaPresenter;
    }
    @GetMapping(path = "/{numeroCartao}/{mes}/{ano}", produces = "application/json" )
    public FaturaResponse PegarFatura(@PathVariable("numeroCartao") String numeroCartao
            , @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano){
        return this.pegarFatura.execute(numeroCartao, mes, ano);
    }

}
