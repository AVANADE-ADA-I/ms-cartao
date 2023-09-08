package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Endereco;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.AppException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.DiaVencimento;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.EnderecoDTO;
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


    public CadastroUsuarioResponse cartaoTitular(Principal titular, Endereco endereco, TipoCartao tipoCartao, DiaVencimento diaVencimento){
        var cartao = new Cartao(titular, tipoCartao, diaVencimento);
        cartao.setCodigoSeguranca(gerarNumeroAleatorio(3));
        cartao.setNumeroCartao(gerarNumeroAleatorio(12));

        var cartaoCadastrado = cartaoRepository.save(cartao);
        return new CadastroUsuarioResponse(
                cartaoCadastrado.getNumeroCartao(),
                cartaoCadastrado.getNomeTitular(),
                cartaoCadastrado.getDiaVencimento(),
                cartaoCadastrado.getTipoCartao(),
                titular.getNome(),
                endereco.dto(),
                cartaoCadastrado.getVencimentoCartao()
                );
    }

    public CadastroUsuarioResponse cartaoDependente(Dependente dependente, Principal titular, Endereco endereco, TipoCartao tipoCartao, DiaVencimento diaVencimento){
        var cartaoTitular = getCartaoPrincipal(titular);
        if (cartaoTitular.getTipoCartao().getValor() < tipoCartao.getValor()) {
            throw new AppException("Tipo do cartão deve ser igual ou inferior ao do titular.");
        }
        var cartao =  new Cartao(dependente, titular, tipoCartao, diaVencimento);
        cartao.setCodigoSeguranca(gerarNumeroAleatorio(3));
        cartao.setNumeroCartao(gerarNumeroAleatorio(12));

        var cartaoCadastrado = cartaoRepository.save(cartao);
        return new CadastroUsuarioResponse(
                cartaoCadastrado.getNumeroCartao(),
                cartaoCadastrado.getNomeTitular(),
                cartaoCadastrado.getDiaVencimento(),
                cartaoCadastrado.getTipoCartao(),
                titular.getNome(),
                endereco.dto(),
                cartaoCadastrado.getVencimentoCartao()
        );
    }

    private Cartao getCartaoPrincipal(Principal principal) {
        var cartao = cartaoRepository.findFirstByPrincipalAndDependenteIsNullOrderByTipoCartaoDesc(principal);
        if (cartao.isEmpty()) {
            throw new EntityNotFoundException("Cartão informado não existe");
        }
        return cartao.get();
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
