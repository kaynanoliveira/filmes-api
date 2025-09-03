package com.kaynan.filmesapi.controller;

import com.kaynan.filmesapi.model.Filme;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private static final Map<Long, Filme> filmes = new HashMap<>();
    private static final AtomicLong idFilme = new AtomicLong();

    static {
        long id1 = idFilme.incrementAndGet();
        filmes.put(id1, new Filme(id1, "Vingadores Guerra Infinita", "Ficção Científica", 2018));

        long id2 = idFilme.incrementAndGet();
        filmes.put(id2, new Filme(id2, "Star Wars", "Ficção Científica", 2015));
    }

    @GetMapping
    public Collection<Filme> listarTodos() {
        return filmes.values();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Filme adicionarFilme(@RequestBody Filme filmeAdicionar) {
        long id = idFilme.incrementAndGet();
        Filme novoFilme = new Filme(id, filmeAdicionar.getNomeFilme(), filmeAdicionar.getGenero(), filmeAdicionar.getAno());
        filmes.put(id, novoFilme);
        return novoFilme;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFilme(@PathVariable Long id) {
        if(!filmes.containsKey(id)){
            return ResponseEntity.notFound().build();
        }
        filmes.remove(id);
        return ResponseEntity.ok().build();
    }
}
