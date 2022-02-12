package com.wsldev.bookstore.services;

import java.util.List;
import java.util.Optional;

import com.wsldev.bookstore.domain.Categoria;
import com.wsldev.bookstore.dtos.CategoriaDTO;
import com.wsldev.bookstore.repositories.CategoriaRepository;
import com.wsldev.bookstore.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = this.repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria create(Categoria categoria) {
        categoria.setId(null);
        return this.repository.save(categoria);
    }

    public Categoria update(Integer id, CategoriaDTO dto) {
        Categoria categoria = findById(id);
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return repository.save(categoria);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new com.wsldev.bookstore.services.exceptions.DataIntegrityViolationException(
                    "Categoria não pode ser deletado! Possui livros associados");
        }
    }

}