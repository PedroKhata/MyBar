package br.com.dupla.mybar.repository;

import br.com.dupla.mybar.entity.ItensDaConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensDaContaRepository extends JpaRepository<ItensDaConta, Long> {

    // UC7 - Cozinha: pedidos solicitados (ainda não recebidos pela cozinha)
    @Query("SELECT i FROM ItensDaConta i WHERE i.ativo = true AND i.dataRecebimentoCozinha IS NULL")
    List<ItensDaConta> findPedidosSolicitados();

    // UC7 - Cozinha: pedidos recebidos pela cozinha mas ainda não entregues
    @Query("SELECT i FROM ItensDaConta i WHERE i.ativo = true AND i.dataRecebimentoCozinha IS NOT NULL AND i.dataEntregaCozinha IS NULL")
    List<ItensDaConta> findPedidosEmPreparo();

    // UC8 - Balcão: pedidos entregues pela cozinha mas ainda não recebidos pelo bar
    @Query("SELECT i FROM ItensDaConta i WHERE i.ativo = true AND i.dataEntregaCozinha IS NOT NULL AND i.dataRecebimentoBar IS NULL")
    List<ItensDaConta> findPedidosProntosParaRetirada();

    // UC8 - Balcão: pedidos recebidos pelo bar mas ainda não entregues ao garçom
    @Query("SELECT i FROM ItensDaConta i WHERE i.ativo = true AND i.dataRecebimentoBar IS NOT NULL AND i.dataEntregaBar IS NULL")
    List<ItensDaConta> findPedidosEmEntrega();
}