package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.DiaVencimento;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class CartaoService {
    private CartaoRepository cartaoRepository;
    private static Random random;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }


    public CadastroUsuarioResponse cartaoTitular(Principal titular, TipoCartao tipoCartao, DiaVencimento diaVencimento){
        var cartao = emitirCartaoTitular(titular, tipoCartao, diaVencimento);
        var cartaoCadastrado = cartaoRepository.save(cartao);
        System.out.println(DiaVencimento.CINCO.getDia());
        return cartaoCadastrado.dto(titular.getNome());
    }

    public CadastroUsuarioResponse cartaoDependente(Dependente dependente, Principal titular, TipoCartao tipoCartao, DiaVencimento diaVencimento){
        var cartao = emitirCartaoDependente(dependente, titular, tipoCartao, diaVencimento);
        var cartaoCadastrado = cartaoRepository.save(cartao);
        return cartaoCadastrado.dto(titular.getNome());
    }



    private Cartao emitirCartaoTitular(Principal principal, TipoCartao tipoCartao, DiaVencimento diaVencimento) {
        LocalDate dataAtual = LocalDate.now();
        Cartao cartao = new Cartao();
        cartao.setTipoCartao(tipoCartao);
        cartao.setPrincipal(principal);
        cartao.setIdContaBanco(UUID.randomUUID().toString());
        cartao.setNomeTitular(principal.getNome());
        cartao.setDiaVencimento(diaVencimento);
        cartao.setVencimentoCartao(dataAtual.plusYears(5));
        cartao.setCodigoSeguranca(gerarNumeroAleatorio(3));
        cartao.setNumeroCartao(gerarNumeroAleatorio(12));
        return cartao;
    }

    private Cartao emitirCartaoDependente(Dependente dependente, Principal principal, TipoCartao tipoCartao, DiaVencimento diaVencimento) {
        LocalDate dataAtual = LocalDate.now();
        Cartao cartao = new Cartao();
        cartao.setTipoCartao(tipoCartao);
        cartao.setDependente(dependente);
        cartao.setPrincipal(principal);
        cartao.setIdContaBanco(UUID.randomUUID().toString());
        cartao.setNomeTitular(dependente.getNome());
        cartao.setDiaVencimento(diaVencimento);
        cartao.setVencimentoCartao(dataAtual.plusYears(5));
        cartao.setCodigoSeguranca(gerarNumeroAleatorio(3));
        cartao.setNumeroCartao(gerarNumeroAleatorio(12));
        return cartao;
    }

    private String gerarNumeroAleatorio(int length) {

        final int tamanho = length<=0?1:length;
        IntStream stream =  getRandom().ints(tamanho,0,9);
        StringBuilder builder = new StringBuilder();
        stream.forEachOrdered(builder::append);
        return builder.toString();
    }

    private static Random getRandom(){
        if(Objects.isNull(random)){
            random = new Random();
        }
        return random;
    }
}
