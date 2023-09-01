package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;

import java.time.LocalDate;
import java.util.Optional;



@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    @Query("SELECT f FROM Fatura f WHERE f.dataVencimento = :dataVencimento AND f.cartao = :cartao")
    Optional<Fatura> findByDataVencimentoAndCartao(@Param("dataVencimento") LocalDate dataVencimento, @Param("cartao") Cartao cartao);
}