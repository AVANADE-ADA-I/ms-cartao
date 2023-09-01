package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CompraService;

@RestController
@RequestMapping("/compra")
@Slf4j
public class RealizarCompraController {

    private CompraService service;
    public RealizarCompraController(CompraService service) {
        this.service = service;
    }

    @PostMapping(path = "", produces = "application/json" )
    public CompraResponse realizarCompra(@RequestBody CompraRequest compraRequest){
        return service.execute(compraRequest);
    }
}
