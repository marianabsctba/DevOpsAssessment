package edu.br.infnet.q06.service;

import edu.br.infnet.q06.model.Aluno;
import edu.br.infnet.q06.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Flux<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public Mono<Aluno> createAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Mono<Aluno> getAlunoById(Long id) {
        return alunoRepository.findById(id);
    }

    public Flux<Aluno> getAlunosByNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public Mono<Aluno> updateAluno(Long id, Aluno alunoAtualizado) {
        return alunoRepository.findById(id)
                .flatMap(alunoExistente -> {
                    alunoExistente.setNome(alunoAtualizado.getNome());
                    alunoExistente.setCurso(alunoAtualizado.getCurso());
                    return alunoRepository.save(alunoExistente);
                });
    }

    public Mono<Void> deleteAluno(Long id) {
        return alunoRepository.deleteById(id);
    }
}
