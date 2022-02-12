package com.wsldev.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.wsldev.bookstore.domain.Livro;
import com.wsldev.bookstore.dtos.LivroDTO;
import com.wsldev.bookstore.services.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/livros")
public class LivroResource {

    @Autowired
    private LivroService service;

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id) {
        Livro livro = service.findById(id);
        return ResponseEntity.ok().body(livro);
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(
            @RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
        List<Livro> list = service.findAll(id_cat);
        List<LivroDTO> listDTO = list.stream().map(LivroDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro livro) {
        Livro livroUpdate = service.update(id, livro);
        return ResponseEntity.ok().body(livroUpdate);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro livro) {
        Livro livroUpdate = service.update(id, livro);
        return ResponseEntity.ok().body(livroUpdate);
    }

    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,
            @RequestBody Livro livro) {
        Livro livroCreate = service.create(id_cat, livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}")
                .buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(livroCreate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}