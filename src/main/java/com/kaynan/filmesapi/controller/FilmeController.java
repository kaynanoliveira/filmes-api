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

    @GetMapping
    public ResponseEntity<Collection<Filme>> listarTodos() {
        return ResponseEntity.ok(filmes.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable Long id) {
        Filme filme = filmes.get(id);

        if (filme != null) {
            return ResponseEntity.ok(filme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Filme adicionarFilme(@RequestBody Filme filmeAdicionar) {
        long id = idFilme.incrementAndGet();
        Filme novoFilme = new Filme(id, filmeAdicionar.getNomeFilme(), filmeAdicionar.getGenero(), filmeAdicionar.getAno());
        filmes.put(id, novoFilme);
        return novoFilme;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        if (!filmes.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        filmeAtualizado.setId(id);
        filmes.put(id, filmeAtualizado);  // Atualiza o filme
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Filme> atualizarParcialmenteFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        Filme filmeExistente = filmes.get(id);
        if (filmeExistente == null) {
            return ResponseEntity.notFound().build();
        }

        if (filmeAtualizado.getNomeFilme() != null) {
            filmeExistente.setNomeFilme(filmeAtualizado.getNomeFilme());
        }

        if (filmeAtualizado.getGenero() != null) {
            filmeExistente.setGenero(filmeAtualizado.getGenero());
        }

        if (filmeAtualizado.getAno() != null) {
            filmeExistente.setAno(filmeAtualizado.getAno());
        }

        filmes.put(id, filmeExistente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFilme(@PathVariable Long id) {
        if (!filmes.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        filmes.remove(id);
        return ResponseEntity.ok().build();
    }
}
