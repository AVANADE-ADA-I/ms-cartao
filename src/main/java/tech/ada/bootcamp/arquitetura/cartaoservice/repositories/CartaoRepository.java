package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Dependente;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Principal;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {
    Optional<Cartao> findFirstByPrincipalAndDependenteIsNullOrderByTipoCartaoDesc (Principal principal);
}