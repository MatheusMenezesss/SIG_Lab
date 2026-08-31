package br.com.siglab.API_siglab.domain.categoria;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria createCategoria(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório.");
        }

        categoria.setUpdatedAt(LocalDateTime.now());
        return categoriaRepository.save(categoria);
    }

    public Categoria getCategoriaById(UUID id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        return categoriaOptional.orElseThrow(() ->
            new IllegalArgumentException("Categoria não encontrada para o ID: " + id));
    }

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria updateCategoria(UUID id, Categoria updateCategoria) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada para o ID: " + id));

        if (updateCategoria.getNome() != null && !updateCategoria.getNome().isBlank()) {
            categoriaExistente.setNome(updateCategoria.getNome());
        }

        categoriaExistente.setUpdatedAt(LocalDateTime.now());
        return categoriaRepository.save(categoriaExistente);
    }

    public void deleteCategoria(UUID id) {
        try {
            categoriaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Categoria não encontrada para o ID: " + id);
        }
    }
}
