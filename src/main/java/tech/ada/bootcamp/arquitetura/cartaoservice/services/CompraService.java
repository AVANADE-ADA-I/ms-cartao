package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CompraRepository;

@Service
public class CompraService {
    private CompraRepository compraRepository;
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public CompraResponse execute(CompraRequest dto, Cartao cartao) {
        var compra = new Compra(dto, cartao);
        return compra.dto();
    }
}
