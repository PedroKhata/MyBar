package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.usuario.UsuarioRequest;
import br.com.dupla.mybar.dto.usuario.UsuarioResponse;
import br.com.dupla.mybar.entity.Usuario;
import br.com.dupla.mybar.exception.RegraNegocioException;
import br.com.dupla.mybar.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse salvar(UsuarioRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setTipo(dto.tipo());
        usuario.setAtivo(true);

        try {
            usuario = usuarioRepository.save(usuario);
            return new UsuarioResponse(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("E-mail já cadastrado no sistema.");
        }
    }

    public List<UsuarioResponse> listarAtivos() {
        return usuarioRepository.findAll().stream()
                .filter(Usuario::getAtivo)
                .map(UsuarioResponse::new)
                .collect(Collectors.toList());
    }

    public void excluirOuDesativar(Integer codigo) {
        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado."));

        try {
            usuarioRepository.delete(usuario);
        } catch (DataIntegrityViolationException e) {
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
        }
    }
}