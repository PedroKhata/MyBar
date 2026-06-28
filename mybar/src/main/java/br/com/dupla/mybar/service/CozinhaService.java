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
public class CozinhaService {

    private final ItensDaContaRepository itensDaContaRepository;

    public CozinhaService(ItensDaContaRepository itensDaContaRepository) {
        this.itensDaContaRepository = itensDaContaRepository;
    }

    public List<ItensDaContaResponse> listarSolicitados() {
        return itensDaContaRepository.findPedidosSolicitados()
                .stream()
                .map(ItensDaContaResponse::new)
                .collect(Collectors.toList());
    }

    public List<ItensDaContaResponse> listarEmPreparo() {
        return itensDaContaRepository.findPedidosEmPreparo()
                .stream()
                .map(ItensDaContaResponse::new)
                .collect(Collectors.toList());
    }

    public ItensDaContaResponse receberPedido(Long id) {
        ItensDaConta item = itensDaContaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pedido não encontrado: " + id));

        item.setDataRecebimentoCozinha(LocalDate.now());
        item.setHoraRecebimentoCozinha(LocalTime.now());

        return new ItensDaContaResponse(itensDaContaRepository.save(item));
    }

    public ItensDaContaResponse entregarPedido(Long id) {
        ItensDaConta item = itensDaContaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pedido não encontrado: " + id));

        item.setDataEntregaCozinha(LocalDate.now());
        item.setHoraEntregaCozinha(LocalTime.now());

        return new ItensDaContaResponse(itensDaContaRepository.save(item));
    }
}