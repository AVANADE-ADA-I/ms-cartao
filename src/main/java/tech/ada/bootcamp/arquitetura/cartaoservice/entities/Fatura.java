package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.EnderecoRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fatura")
@NoArgsConstructor
public class Fatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate dataVencimento;

    private LocalDate dataProcessamento;

    private BigDecimal valor;

    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "numeroCartao")
    private Cartao cartao;


    public Fatura(LocalDate dataVencimento, LocalDate dataProcessamento, BigDecimal valor, BigDecimal valorPago, Cartao cartao ) {
        this.dataVencimento = dataVencimento;
        this.dataProcessamento = dataProcessamento;
        this.valor = valor;
        this.valorPago = valorPago;
        this.cartao = cartao;
    }

}