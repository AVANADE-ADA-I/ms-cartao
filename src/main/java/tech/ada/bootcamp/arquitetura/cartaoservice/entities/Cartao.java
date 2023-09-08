package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.DiaVencimento;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "cartao")
@NoArgsConstructor
public class Cartao {
    @Id
    private String numeroCartao;
    private String nomeTitular;
    private LocalDate vencimentoCartao;

    private String codigoSeguranca;
    private DiaVencimento diaVencimento;

    private TipoCartao tipoCartao;

    private String idContaBanco;

    @ManyToOne
    @JoinColumn(name = "principal_identificador")
    private Principal principal;

    @ManyToOne
    @JoinColumn(name = "dependente_identificador")
    private Dependente dependente;

    public Cartao(Principal principal, TipoCartao tipoCartao, DiaVencimento diaVencimento) {
        this.tipoCartao = tipoCartao;
        this.principal = principal;
        this.diaVencimento = diaVencimento;
        this.idContaBanco = UUID.randomUUID().toString();
        this.nomeTitular = principal.getNome();
        this.vencimentoCartao = LocalDate.now().plusYears(5);
    }

    public Cartao(Dependente dependente, Principal principal, TipoCartao tipoCartao, DiaVencimento diaVencimento) {
        this.tipoCartao = tipoCartao;
        this.principal = principal;
        this.dependente = dependente;
        this.diaVencimento = diaVencimento;
        this.idContaBanco = UUID.randomUUID().toString();
        this.nomeTitular = dependente.getNome();
        this.vencimentoCartao = LocalDate.now().plusYears(5);
    }
}