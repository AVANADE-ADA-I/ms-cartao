package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.FaturaResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.FaturaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class FaturaService {
    private FaturaRepository faturaRepository;
    private CartaoService cartaoService;
    private CompraService compraService;
    private int diasParaSubtrair = 9;

    public FaturaService(CartaoService cartaoService, FaturaRepository faturaRepository, CompraService compraService) {
        this.cartaoService = cartaoService;
        this.faturaRepository = faturaRepository;
        this.compraService = compraService;
    }

    public FaturaResponse pegarFatura(String numeroCartao, int month, int year) {
        Cartao cartao = cartaoService.getCartao(numeroCartao);

        LocalDate dataVencimento = LocalDate.of(year, month, cartao.getDiaVencimento().getDia());
        var fatura = faturaRepository.findByDataVencimentoAndCartao(dataVencimento,cartao);

        if(fatura.isEmpty()) {
            return this.criarFatura(cartao, dataVencimento);
        }

        LocalDate dataFinal = dataVencimento.minusDays(diasParaSubtrair);
        LocalDate dataInicial = dataFinal.minusMonths(1).plusDays(1);

        List<CompraResponse> compras = compraService.getCompra(cartao, dataInicial.atStartOfDay(), dataFinal.atTime(LocalTime.MAX));

        return new FaturaResponse(fatura.get(),compras);
    }

    private FaturaResponse criarFatura( Cartao cartao,  LocalDate dataVencimento) {

        LocalDate dataFinal = dataVencimento.minusDays(diasParaSubtrair);
        LocalDate dataInicial = dataFinal.minusMonths(1).plusDays(1);

        List<CompraResponse> compras = compraService.getCompra(cartao, dataInicial.atStartOfDay(), dataFinal.atTime(LocalTime.MAX));
        BigDecimal totalCompras = compras.stream()
                .map(CompraResponse::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valor = totalCompras.add(this.balancoFaturaAnterior(cartao, dataVencimento));

        Fatura novaFatura = new Fatura(dataVencimento, dataInicial,valor, BigDecimal.ZERO, cartao );
        faturaRepository.save(novaFatura);
        return new FaturaResponse(novaFatura,compras);
    }


    private BigDecimal balancoFaturaAnterior(Cartao cartao, LocalDate dataVencimento) {

        LocalDate dataVencimentoAnterior = dataVencimento.minusMonths(1);
        var faturaAnterior = faturaRepository.findByDataVencimentoAndCartao(dataVencimentoAnterior, cartao);

        return faturaAnterior.map(fatura -> fatura.getValor().subtract(fatura.getValorPago())).orElse(BigDecimal.ZERO);
    }
  }
