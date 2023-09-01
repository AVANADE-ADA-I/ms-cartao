package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.presenters.CriarCartaoPresenter;
import tech.ada.bootcamp.arquitetura.cartaoservice.presenters.PegarFaturaPresenter;


@RestController
@RequestMapping("/fatura")
@Slf4j
public class VerFaturaController {
    private PegarFaturaPresenter pegarFaturaPresenter;

    public VerFaturaController (PegarFaturaPresenter pegarFaturaPresenter) {
        this.pegarFaturaPresenter = pegarFaturaPresenter;
    }
    @GetMapping(path = "/{numeroCartao}/{mes}/{ano}", produces = "application/json" )
    public FaturaResponse PegarFatura(@PathVariable("numeroCartao") String numeroCartao
            , @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano){
        return this.pegarFaturaPresenter.execute(numeroCartao, mes, ano);
    }

}
