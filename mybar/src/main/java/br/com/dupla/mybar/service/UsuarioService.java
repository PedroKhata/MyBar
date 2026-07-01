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
        if (usuarioRepository.existsById(dto.codigo())) {
            throw new RegraNegocioException("Já existe um utilizador registrado com o código " + dto.codigo());
        }

        Usuario usuario = new Usuario();
        usuario.setCodigo(dto.codigo());
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setTipo(dto.tipo());
        usuario.setAtivo(true);

        return new UsuarioResponse(usuarioRepository.save(usuario));
    }

    public List<UsuarioResponse> listarAtivos() {
        return usuarioRepository.findByAtivoTrue().stream()
                .map(UsuarioResponse::new)
                .toList();
    }

    public void excluirOuDesativar(Integer codigo) {
        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado."));

        try {
            usuarioRepository.delete(usuario);
            usuarioRepository.flush();
        } catch (DataIntegrityViolationException e) {

            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
        }
    }

    public UsuarioResponse buscarPorCodigo(Integer codigo) {
        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        return new UsuarioResponse(usuario);
    }

    public UsuarioResponse atualizar(Integer codigo, UsuarioRequest dto) {
        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setTipo(dto.tipo());

        return new UsuarioResponse(usuarioRepository.save(usuario));
    }
}