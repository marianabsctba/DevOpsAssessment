package edu.br.infnet.q06.controller;

import edu.br.infnet.q06.model.Estudante;
import edu.br.infnet.q06.service.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteService;

    @GetMapping
    public Flux<Estudante> getAllEstudantes() {
        return estudanteService.getAllEstudantes();
    }

    @PostMapping
    public Mono<Estudante> createEstudante(@RequestBody Estudante estudante) {
        return estudanteService.createEstudante(estudante);
    }

    @GetMapping("/{id}")
    public Mono<Estudante> getEstudanteById(@PathVariable Long id) {
        return estudanteService.getEstudanteById(id);
    }

    @GetMapping("/nome/{nome}")
    public Flux<Estudante> getEstudantesByNome(@PathVariable String nome) {
        return estudanteService.getEstudantesByNome(nome);
    }

    @PutMapping("/{id}")
    public Mono<Estudante> updateEstudante(@PathVariable Long id, @RequestBody Estudante estudanteAtualizado) {
        return estudanteService.updateEstudante(id, estudanteAtualizado);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteEstudante(@PathVariable Long id) {
        return estudanteService.deleteEstudante(id);
    }
}
