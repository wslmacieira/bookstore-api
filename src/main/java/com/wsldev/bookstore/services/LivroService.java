package com.wsldev.bookstore.services;

import java.util.Optional;

import com.wsldev.bookstore.domain.Livro;
import com.wsldev.bookstore.repositories.LivroRepository;
import com.wsldev.bookstore.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro findById(Integer id) {
        Optional<Livro> livro = repository.findById(id);
        return livro.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
    }

}