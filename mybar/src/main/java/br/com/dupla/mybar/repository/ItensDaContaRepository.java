package br.com.dupla.mybar.repository;

import br.com.dupla.mybar.entity.ItensDaConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensDaContaRepository extends JpaRepository<ItensDaConta, Long> {
}