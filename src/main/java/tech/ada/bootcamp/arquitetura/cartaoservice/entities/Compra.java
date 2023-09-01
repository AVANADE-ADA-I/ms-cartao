package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "compra")
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime dataCompra;
    private String loja;
    private BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "numeroCartao")
    private Cartao cartao;
    private StatusCompra statusCompra;

    public Compra(CompraRequest dto, Cartao cartao) {
        this.dataCompra = LocalDateTime.now();
        this.loja = dto.loja();
        this.valor = dto.valor();
        this.statusCompra = StatusCompra.FINALIZADA;
        this.cartao = cartao;
    }

    public CompraResponse dto() {
        return new CompraResponse(
                this.cartao.getNumeroCartao(),
                this.loja,
                this.valor,
                this.statusCompra);
    }
}