package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.StatusCompra;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.AppException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CompraRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {
    private CompraRepository compraRepository;
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public CompraResponse execute(CompraRequest dto, Cartao cartao) {
        var compra = new Compra(dto, cartao);
        if (cartao.getVencimentoCartao().isBefore(compra.getDataCompra().toLocalDate())) {
            throw new AppException("Cartão está vencido.");
        }
        compra.setStatusCompra(StatusCompra.FINALIZADA);
        var compraRegistrada = compraRepository.save(compra);
        return compraRegistrada.dto();
    }

    public List<CompraResponse> getCompra(Cartao cartao, LocalDateTime inicio, LocalDateTime fim) {
        var listaDeCompra = compraRepository.findAllByCartaoAndDataCompraBetween(cartao, inicio, fim);
        List<CompraResponse> listaCompraResponse = new ArrayList<>();
        for (var compra : listaDeCompra) {
            listaCompraResponse.add(compra.dto());
        }
        return listaCompraResponse;
    }
}
