package br.com.dupla.mybar.repository;

import br.com.dupla.mybar.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("SELECT COALESCE(SUM(p.valor), 0) FROM Pagamento p WHERE p.ativo = true AND p.conta.status = 'FECHADA' AND p.conta.dataAbertura BETWEEN :inicio AND :fim")
    BigDecimal sumFaturamentoPorPeriodo(LocalDate inicio, LocalDate fim);

    List<Pagamento> findByContaIdAndAtivoTrue(Long contaId);

}