package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.configuracao.ConfiguracaoRequest;
import br.com.dupla.mybar.dto.configuracao.ConfiguracaoResponse;
import br.com.dupla.mybar.entity.Configuracao;
import br.com.dupla.mybar.repository.ConfiguracaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoService {

    private final ConfiguracaoRepository configuracaoRepository;

    public ConfiguracaoService(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }

    public ConfiguracaoResponse salvar(ConfiguracaoRequest request) {
        Configuracao configuracao = new Configuracao();
        configuracao.setValorIngressoMasc(request.valorIngressoMasc());
        configuracao.setValorIngressoFemin(request.valorIngressoFemin());
        configuracao.setModoOperacao(request.modoOperacao());
        configuracao.setData(LocalDate.now());
        configuracao.setHora(LocalTime.now());

        // Ajuste: Utilizando o construtor do Response padronizado
        return new ConfiguracaoResponse(configuracaoRepository.save(configuracao));
    }

    public Optional<ConfiguracaoResponse> buscarHoje() {
        // Ajuste: Mapeando com o construtor
        return configuracaoRepository.findTopByDataOrderByHoraDesc(LocalDate.now())
                .map(ConfiguracaoResponse::new);
    }

    public List<ConfiguracaoResponse> listarTodas() {
        // Ajuste: Mapeando a lista com o construtor
        return configuracaoRepository.findAll()
                .stream()
                .map(ConfiguracaoResponse::new)
                .toList();
    }
}