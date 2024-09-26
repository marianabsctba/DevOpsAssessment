package edu.br.infnet.q06.repository;

import edu.br.infnet.q06.model.Aluno;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AlunoRepository extends ReactiveCrudRepository<Aluno, Long> {
    Flux<Aluno> findByNome(String nome);
}
