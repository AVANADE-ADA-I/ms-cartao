package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response;

import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.DiaVencimento;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.EnderecoDTO;

import java.time.LocalDate;

public record CadastroUsuarioResponse (String numeroCartao, String nomeTitularCartao, DiaVencimento diaVencimento, TipoCartao tipoCartao, String nomeTitularConta, EnderecoDTO endereco, LocalDate vencimentoCartao){}
