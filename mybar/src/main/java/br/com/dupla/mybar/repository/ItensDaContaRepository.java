package br.com.dupla.mybar.repository;

import br.com.dupla.mybar.entity.ItensDaConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensDaContaRepository extends JpaRepository<ItensDaConta, Long> {

    // UC1 - Listar itens filtrando apenas pela conta correta (apenas os não cancelados)
    List<ItensDaConta> findByContaIdAndAtivoTrue(Long contaId);

    // UC7 - Cozinha: pedidos solicitados (somente itens que vão para cozinha)
    @Query("SELECT i FROM ItensDaConta i WHERE i.ativo = true AND i.itemCardapio.tipo.cozinha = true AND i.dataRecebimentoCozinha IS NULL ORDER BY i.dataSolicitacao ASC, i.horaSolicitacao ASC")
    List<ItensDaConta> findPedidosSolicitados();

    // UC7 - Cozinha: pedidos em preparo
    @Query("SELECT i FROM ItensDaConta i WHERE i.ativo = true AND i.itemCardapio.tipo.cozinha = true AND i.dataRecebimentoCozinha IS NOT NULL AND i.dataEntregaCozinha IS NULL ORDER BY i.dataSolicitacao ASC, i.horaSolicitacao ASC")
    List<ItensDaConta> findPedidosEmPreparo();

    // UC8 - Balcão: pedidos prontos (Bebidas recém-lançadas OU comidas terminadas pela cozinha)
    @Query("SELECT i FROM ItensDaConta i WHERE i.ativo = true AND i.dataRecebimentoBar IS NULL AND " +
            "( (i.itemCardapio.tipo.cozinha = true AND i.dataEntregaCozinha IS NOT NULL) OR " +
            "  (i.itemCardapio.tipo.cozinha = false) ) " +
            "ORDER BY i.dataSolicitacao ASC, i.horaSolicitacao ASC")
    List<ItensDaConta> findPedidosProntosParaRetirada();

    // UC8 - Balcão: pedidos em entrega (recebidos pelo bar, mas não entregues ao garçom)
    @Query("SELECT i FROM ItensDaConta i WHERE i.ativo = true AND i.dataRecebimentoBar IS NOT NULL AND i.dataEntregaBar IS NULL ORDER BY i.dataSolicitacao ASC, i.horaSolicitacao ASC")
    List<ItensDaConta> findPedidosEmEntrega();
}