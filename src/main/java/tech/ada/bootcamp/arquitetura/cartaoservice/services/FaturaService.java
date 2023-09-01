package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CompraRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.FaturaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FaturaService {
    private FaturaRepository faturaRepository;
    private CartaoRepository cartaoRepository;
    private CompraRepository compraRepository;
    private int diasParaSubtrair = 9;
    
    public FaturaService(CartaoRepository cartaoRepository, FaturaRepository faturaRepository, CompraRepository compraRepository) {
        this.cartaoRepository = cartaoRepository;
        this.faturaRepository = faturaRepository;
        this.compraRepository = compraRepository;
    }

    public Fatura pegarFatura(String numeroCartao, int month, int year) {
        Cartao cartao = cartaoRepository.findById(numeroCartao);
        if (cartao.isEmpty()) {
            throw new IllegalArgumentException("Cartão não encontrado");
        }
        LocalDate dataVencimento = LocalDate.of(year, month, cartao.getDiaVencimento());
        Fatura fatura = faturaRepository.pegarFatura(numeroCartao, dataVencimento);

        if(fatura.isEmpty()) {
            return this.criarFatura(cartao, dataVencimento);
        }

        return fatura;
    }

    private Fatura criarFatura( Cartao cartao,  LocalDate dataVencimento) {

            LocalDate dataFinal = dataVencimento.minusDays(diasParaSubtrair);
        LocalDate dataInicial = dataFinal.minusMonths(1).plusDays(1);

        List<Compra> compras = compraRepository.findByCardAndDateBetween(numeroCartao, dataInicial, dataFinal);
        BigDecimal totalCompras = compras.stream().map(Compra::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valor = totalCompras.add(this.balancoFaturaAnterior(numeroCartao, dataVencimento));

        Fatura novaFatura = new Fatura(dataVencimento, dataInicial,valor, BigDecimal.ZERO, cartao );
        faturaRepository.save(novaFatura);
        return novaFatura;
    }


    private BigDecimal balancoFaturaAnterior(String numeroCartao, LocalDate dataVencimento) {

        LocalDate dataVencimentoAnterior = dataVencimento.minusMonths(1);
        var faturaAnterior = faturaRepository.pegarFatura(numeroCartao, dataVencimentoAnterior);

        return faturaAnterior.isEmpty() ? BigDecimal.ZERO : faturaAnterior.getValor() - faturaAnterior.getValorPago();
    }
}
