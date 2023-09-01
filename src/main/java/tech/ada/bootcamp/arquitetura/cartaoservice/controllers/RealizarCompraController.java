package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.presenters.CriarCompraPresenter;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CompraService;

import java.util.List;

@RestController
@RequestMapping("/compra")
@Slf4j
public class RealizarCompraController {

    private CriarCompraPresenter compraPresenter;
    public RealizarCompraController(CriarCompraPresenter compraPresenter) {
        this.compraPresenter = compraPresenter;
    }

    @PostMapping(path = "", produces = "application/json" )
    public CompraResponse realizarCompra(@RequestBody @Valid CompraRequest compraRequest){
        return compraPresenter.execute(compraRequest);
    }
}
