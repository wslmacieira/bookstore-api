package com.wsldev.bookstore.repositories;

import com.wsldev.bookstore.domain.Livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    
}