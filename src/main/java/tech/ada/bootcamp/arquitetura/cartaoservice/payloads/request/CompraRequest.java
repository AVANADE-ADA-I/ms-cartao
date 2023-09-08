package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record CompraRequest (
        @NotBlank
        @Pattern(regexp = "^\\d{12}$")
        String numeroCartao,
        @NotBlank
        String loja,
        @NotNull
        BigDecimal valor){
}