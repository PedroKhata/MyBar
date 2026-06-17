package br.com.dupla.mybar.service;

import br.com.dupla.mybar.entity.ItensDaConta;
import br.com.dupla.mybar.repository.ItensDaContaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CozinhaService {

    private final ItensDaContaRepository itensDaContaRepository;

    public CozinhaService(ItensDaContaRepository itensDaContaRepository) {
        this.itensDaContaRepository = itensDaContaRepository;
    }

    public List<ItensDaConta> listarSolicitados() {
        return itensDaContaRepository.findPedidosSolicitados();
    }

    public List<ItensDaConta> listarEmPreparo() {
        return itensDaContaRepository.findPedidosEmPreparo();
    }

    public ItensDaConta receberPedido(Long id) {
        ItensDaConta item = itensDaContaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
        item.setDataRecebimentoCozinha(LocalDate.now());
        item.setHoraRecebimentoCozinha(LocalTime.now());
        return itensDaContaRepository.save(item);
    }

    public ItensDaConta entregarPedido(Long id) {
        ItensDaConta item = itensDaContaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
        item.setDataEntregaCozinha(LocalDate.now());
        item.setHoraEntregaCozinha(LocalTime.now());
        return itensDaContaRepository.save(item);
    }
}