package edu.br.infnet.q06.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EstudanteRepository extends ReactiveCrudRepository<edu.br.infnet.hogwarts.model.Estudante, Long> {
    Flux<edu.br.infnet.hogwarts.model.Estudante> findByNome(String nome);
}
