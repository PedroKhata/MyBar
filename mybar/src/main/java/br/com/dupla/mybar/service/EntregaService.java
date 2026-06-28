package br.com.dupla.mybar.service;

import br.com.dupla.mybar.entity.ItensDaConta;
import br.com.dupla.mybar.repository.ItensDaContaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class EntregaService {

    private final ItensDaContaRepository itensDaContaRepository;

    public EntregaService(ItensDaContaRepository itensDaContaRepository) {
        this.itensDaContaRepository = itensDaContaRepository;
    }

    public List<ItensDaConta> listarProntosParaRetirada() {
        return itensDaContaRepository.findPedidosProntosParaRetirada();
    }

    public List<ItensDaConta> listarEmEntrega() {
        return itensDaContaRepository.findPedidosEmEntrega();
    }

    public ItensDaConta receberDoBar(Long id) {
        ItensDaConta item = itensDaContaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
        item.setDataRecebimentoBar(LocalDate.now());
        item.setHoraRecebimentoBar(LocalTime.now());
        return itensDaContaRepository.save(item);
    }

    public ItensDaConta entregarAoGarcom(Long id) {
        ItensDaConta item = itensDaContaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
        item.setDataEntregaBar(LocalDate.now());
        item.setHoraEntregaBar(LocalTime.now());
        return itensDaContaRepository.save(item);
    }
}