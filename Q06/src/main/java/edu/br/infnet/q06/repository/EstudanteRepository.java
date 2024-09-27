package edu.br.infnet.q06.repository;

import edu.br.infnet.q06.model.Estudante;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EstudanteRepository extends ReactiveCrudRepository<Estudante, Long> {
    Flux<Estudante> findByNome(String nome);  // Reactive method to find Estudantes by name
}
