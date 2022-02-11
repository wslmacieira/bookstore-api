package com.wsldev.bookstore.services;

import java.util.Optional;

import com.wsldev.bookstore.domain.Categoria;
import com.wsldev.bookstore.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = this.repository.findById(id);
        return obj.orElse(null);
    }

}