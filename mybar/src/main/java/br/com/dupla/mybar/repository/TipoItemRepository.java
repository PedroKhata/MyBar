package br.com.dupla.mybar.repository;

import br.com.dupla.mybar.entity.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoItemRepository extends JpaRepository<TipoItem, Integer> {
}