package edu.br.infnet.q06.controller;

import edu.br.infnet.q06.model.Aluno;
import edu.br.infnet.q06.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public Flux<Aluno> getAllAlunos() {
        return alunoService.getAllAlunos();
    }

    @PostMapping
    public Mono<Aluno> createAluno(@RequestBody Aluno aluno) {
        return alunoService.createAluno(aluno);
    }

    @GetMapping("/{id}")
    public Mono<Aluno> getAlunoById(@PathVariable Long id) {
        return alunoService.getAlunoById(id);
    }

    @GetMapping("/nome/{nome}")
    public Flux<Aluno> getAlunosByNome(@PathVariable String nome) {
        return alunoService.getAlunosByNome(nome);
    }

    @PutMapping("/{id}")
    public Mono<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        return alunoService.updateAluno(id, alunoAtualizado);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAluno(@PathVariable Long id) {
        return alunoService.deleteAluno(id);
    }
}
