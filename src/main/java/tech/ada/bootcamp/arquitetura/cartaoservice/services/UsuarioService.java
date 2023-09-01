package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Endereco;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroDependenteRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroPrincipalRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.DependenteRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.EnderecoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.PrincipalRepository;

import java.util.List;

@Service
public class UsuarioService {
    private PrincipalRepository principalRepository;
    private DependenteRepository dependenteRepository;
    private EnderecoRepository enderecoRepository;

    public UsuarioService(PrincipalRepository principalRepository, DependenteRepository dependenteRepository, EnderecoRepository enderecoRepository) {
        this.principalRepository = principalRepository;
        this.dependenteRepository = dependenteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Dependente criarDependente(CadastroDependenteRequest dto, Principal titular) {
        var dependente = new Dependente(dto, titular);
        dependenteRepository.save(dependente);
        return dependente;
    }

    public Principal criarPrincipal(CadastroPrincipalRequest  dto) {
        var principal = new Principal(dto);
        principalRepository.save(principal);
        var endereco = new Endereco(dto.endereco(), principal);
        enderecoRepository.save(endereco);
        return principal;
    }

    public Principal getPrincipal(String idTitular) {
        var principalOp = principalRepository.findById(idTitular);
        if(principalOp.isEmpty()) {
            throw new EntityNotFoundException("Titular não encontrado.");
        }
        return principalOp.get();
    }
}
