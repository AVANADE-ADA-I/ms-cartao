package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.FaturaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class FaturaService {
    private FaturaRepository faturaRepository;
    private CartaoRepository cartaoRepository;
    private int diasParaSubtrair = 9;
    
    public FaturaService(CartaoRepository cartaoRepository, FaturaRepository faturaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.faturaRepository = faturaRepository;
    }

    public Fatura criarFatura(String numeroCartao, int month, int year) {
        var cartao = cartaoRepository.findById(numeroCartao);
        if (cartao.isEmpty()) {
            //erro
        }
        LocalDate dataVencimento = LocalDate.of(year, month, card.getDueDay());
        LocalDate dataFinal = dataVencimento.minusDays(diasParaSubtrair);
        LocalDate dataInicial = dataFinal.minusMonths(1).plusDays(1);

        List<Co> purchases = purchaseRepository.findByCardAndDateBetween(card, startDate, endDate);



    }
}
