package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import java.math.BigDecimal;

public record CompraResponse (String numeroCartao, String loja, BigDecimal valor, String ){
    private String numeroCartao;
    private String loja;
    private double valor;

    private String statusCompra;
}
