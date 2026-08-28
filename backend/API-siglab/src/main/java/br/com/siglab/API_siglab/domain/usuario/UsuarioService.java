package br.com.siglab.API_siglab.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.Optional;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuraio(Usuario usuario){
        // Hash da senha usando o BCrypt
        usuario.setSenha(hashSenha(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }
 
    public Usuario getUsuarioById(UUID id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + id));
    }

    public Usuario getUsuarioByEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o email: " + email));
    }

    public Usuario atualizarUsuario(UUID id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + id));

        // Atualiza os campos necessários
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenha(hashSenha(usuarioAtualizado.getSenha()));
        usuarioExistente.setEstaAtivo(usuarioAtualizado.isEstaAtivo());
        usuarioExistente.setRole(usuarioAtualizado.getRole());

        return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(UUID id) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + id));
        usuarioRepository.delete(usuarioExistente);
    }

    private String hashSenha(String senha) {
        // Aqui você pode implementar a lógica de hash da senha usando uma biblioteca como BCrypt
        // Por exemplo:
        // return BCrypt.hashpw(senha, BCrypt.gensalt());

        return BCrypt.hashpw(senha, BCrypt.gensalt()); // Substitua isso pela implementação real de hash
    }

}
