package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.itens.ItensDaContaResponse;
import br.com.dupla.mybar.entity.ItensDaConta;
import br.com.dupla.mybar.exception.RegraNegocioException;
import br.com.dupla.mybar.repository.ItensDaContaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregaService {

    private final ItensDaContaRepository itensDaContaRepository;

    public EntregaService(ItensDaContaRepository itensDaContaRepository) {
        this.itensDaContaRepository = itensDaContaRepository;
    }

    public List<ItensDaContaResponse> listarProntosParaRetirada() {
        return itensDaContaRepository.findPedidosProntosParaRetirada()
                .stream()
                .map(ItensDaContaResponse::new)
                .collect(Collectors.toList());
    }

    public List<ItensDaContaResponse> listarEmEntrega() {
        return itensDaContaRepository.findPedidosEmEntrega()
                .stream()
                .map(ItensDaContaResponse::new)
                .collect(Collectors.toList());
    }

    public ItensDaContaResponse receberDoBar(Long id) {
        ItensDaConta item = itensDaContaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pedido não encontrado: " + id));

        item.setDataRecebimentoBar(LocalDate.now());
        item.setHoraRecebimentoBar(LocalTime.now());

        return new ItensDaContaResponse(itensDaContaRepository.save(item));
    }

    public ItensDaContaResponse entregarAoGarcom(Long id) {
        ItensDaConta item = itensDaContaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pedido não encontrado: " + id));

        item.setDataEntregaBar(LocalDate.now());
        item.setHoraEntregaBar(LocalTime.now());

        return new ItensDaContaResponse(itensDaContaRepository.save(item));
    }
}