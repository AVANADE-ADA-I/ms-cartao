package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.serviceManager.CriarCompra;

@RestController
@RequestMapping("/compra")
@Slf4j
public class RealizarCompraController {

    private CriarCompra compra;
    public RealizarCompraController(CriarCompra compraPresenter) {
        this.compra = compraPresenter;
    }

    @PostMapping(path = "", produces = "application/json" )
    public CompraResponse realizarCompra(@RequestBody @Valid CompraRequest compraRequest){
        return compra.execute(compraRequest);
    }
}
