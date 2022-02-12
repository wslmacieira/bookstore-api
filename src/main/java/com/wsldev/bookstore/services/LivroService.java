package com.wsldev.bookstore.services;

import java.util.List;
import java.util.Optional;

import com.wsldev.bookstore.domain.Categoria;
import com.wsldev.bookstore.domain.Livro;
import com.wsldev.bookstore.repositories.LivroRepository;
import com.wsldev.bookstore.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id) {
        Optional<Livro> livro = repository.findById(id);
        return livro.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
    }

    public List<Livro> findAll(Integer id_cat) {
        categoriaService.findById(id_cat);
        return repository.findAllByCategoria(id_cat);
    }

    public Livro update(Integer id, Livro livro) {
        Livro livroUpdate = findById(id);
        updateData(livroUpdate, livro);
        return repository.save(livroUpdate);
    }

    private void updateData(Livro livroUpdate, Livro livro) {
        livroUpdate.setTitulo(livro.getTitulo());
        livroUpdate.setNome_autor(livro.getNome_autor());
        livroUpdate.setTexto(livro.getTexto());
    }

    public Livro create(Integer id_cat, Livro livro) {
        livro.setId(null);
        Categoria categoria = categoriaService.findById(id_cat);
        livro.setCategoria(categoria);
        return repository.save(livro);
    }

    public void delete(Integer id) {
        Livro livro = findById(id);
        repository.delete(livro);
    }

}