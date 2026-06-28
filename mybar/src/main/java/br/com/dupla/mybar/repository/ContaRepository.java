package br.com.dupla.mybar.repository;

import br.com.dupla.mybar.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT COALESCE(MAX(c.numero), 0) FROM Conta c")
    Integer findMaxNumero();

    @Query("SELECT COUNT(c) FROM Conta c WHERE c.status = 'FECHADA' AND c.dataAbertura BETWEEN :inicio AND :fim")
    Integer countContasFechadasPorPeriodo(LocalDate inicio, LocalDate fim);

    List<Conta> findByStatus(String status);
}

