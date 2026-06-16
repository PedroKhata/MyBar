package br.com.dupla.mybar.service;

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

    public Configuracao salvar(Configuracao configuracao) {
        configuracao.setData(LocalDate.now());
        configuracao.setHora(LocalTime.now());
        return configuracaoRepository.save(configuracao);
    }

    public Optional<Configuracao> buscarHoje() {
        return configuracaoRepository.findTopByDataOrderByHoraDesc(LocalDate.now());
    }

    public List<Configuracao> listarTodas() {
        return configuracaoRepository.findAll();
    }
}