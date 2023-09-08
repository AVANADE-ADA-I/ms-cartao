package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;

@Data
@Entity
@Table(name = "principal")
@NoArgsConstructor
public class Principal extends Usuario {
    @Id
    private String identificador;
    private String nome;

    public Principal(CadastroPrincipalRequest dto) {
        this.identificador = dto.identificador();
        this.nome = dto.nome();
    }
}
