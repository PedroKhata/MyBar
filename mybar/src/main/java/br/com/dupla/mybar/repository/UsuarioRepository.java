package br.com.dupla.mybar.repository;

import br.com.dupla.mybar.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // O Spring Data JPA traduz isso automaticamente para: SELECT * FROM usuario WHERE ativo = true
    List<Usuario> findByAtivoTrue();
}