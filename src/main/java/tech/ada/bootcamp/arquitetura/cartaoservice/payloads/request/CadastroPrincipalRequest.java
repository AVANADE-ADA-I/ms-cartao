package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.DiaVencimento;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

public record CadastroPrincipalRequest (

        //@CPF
        @NotBlank
        @Pattern(regexp = "^\\d{11}$")
        String identificador,
        @NotBlank
        String nome,
        @NotNull
        @Valid
        EnderecoDTO endereco,
        @NotNull
        TipoCartao tipoCartao,
        @NotNull
        DiaVencimento diaVencimento
){}
