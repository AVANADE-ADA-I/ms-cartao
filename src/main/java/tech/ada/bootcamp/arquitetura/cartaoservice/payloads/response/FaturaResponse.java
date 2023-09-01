package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class FaturaResponse {
    private BigDecimal valor;
    private LocalDate dataFaturaGerada;

    private LocalDate referenciaFatura;

    private List<CompraResponse> resumoCompra;

    public FaturaResponse(Fatura fatura, List<CompraResponse> resumoCompra) {
        this.valor = fatura.getValor();
        this.referenciaFatura = fatura.getDataVencimento();
        this.dataFaturaGerada = fatura.getDataProcessamento();
        this.resumoCompra = resumoCompra;
    }
}
