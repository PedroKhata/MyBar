package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.relatorio.RelatorioFaturamentoResponse;
import br.com.dupla.mybar.exception.RegraNegocioException;
import br.com.dupla.mybar.repository.ContaRepository;
import br.com.dupla.mybar.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RelatorioService {

    private final ContaRepository contaRepository;
    private final PagamentoRepository pagamentoRepository;

    public RelatorioService(ContaRepository contaRepository, PagamentoRepository pagamentoRepository) {
        this.contaRepository = contaRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    public RelatorioFaturamentoResponse gerarRelatorioFaturamento(LocalDate inicio, LocalDate fim) {
        if (inicio.isAfter(fim)) {
            throw new RegraNegocioException("A data de início não pode ser maior que a data de fim.");
        }

        Integer totalContas = contaRepository.countContasFechadasPorPeriodo(inicio, fim);
        var faturamentoTotal = pagamentoRepository.sumFaturamentoPorPeriodo(inicio, fim);

        return new RelatorioFaturamentoResponse(inicio, fim, totalContas, faturamentoTotal);
    }
}