package br.com.dupla.mybar.service;
import br.com.dupla.mybar.dto.ClienteRequestDTO;
import br.com.dupla.mybar.dto.ClienteResponseDTO;
import br.com.dupla.mybar.entity.Cliente;
import br.com.dupla.mybar.exception.RegraNegocioException;
import br.com.dupla.mybar.repository.ClienteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());

        cliente.setCpf(dto.cpf().replaceAll("[^0-9]", ""));

        cliente.setCelular(dto.celular());
        cliente.setSexo(dto.sexo());

        try {
            cliente = clienteRepository.save(cliente);
            return new ClienteResponseDTO(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Este CPF já está cadastrado no sistema.");
        }
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void excluir(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado."));

        try {
            clienteRepository.delete(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Exclusão negada: Este cliente possui contas registradas no bar.");
        }
    }
}