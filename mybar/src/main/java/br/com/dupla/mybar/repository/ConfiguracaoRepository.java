package br.com.dupla.mybar.repository;

import br.com.dupla.mybar.entity.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Integer> {
    Optional<Configuracao> findTopByDataOrderByHoraDesc(LocalDate data);
}