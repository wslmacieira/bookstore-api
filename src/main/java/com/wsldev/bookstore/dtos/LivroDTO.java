package com.wsldev.bookstore.dtos;

import javax.validation.constraints.NotEmpty;

import com.wsldev.bookstore.domain.Livro;

import org.hibernate.validator.constraints.Length;

public class LivroDTO {

    private Integer id;

    @NotEmpty(message = "Campo TITULO é requerido")
    @Length(min = 3, max = 50, message = "O campo TITULO deve ter entre 3 e 50 caracteres")
    private String titulo;

    public LivroDTO() {
    }

    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}