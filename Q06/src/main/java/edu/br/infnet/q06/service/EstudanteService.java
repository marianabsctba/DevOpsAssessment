package edu.br.infnet.q06.service;

import edu.br.infnet.hogwarts.model.Estudante;
import edu.br.infnet.q06.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    public Flux<Estudante> getAllEstudantes() {
        return estudanteRepository.findAll();
    }

    public Mono<Estudante> createEstudante(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    public Mono<Estudante> getEstudanteById(Long id) {
        return estudanteRepository.findById(id);
    }

    public Flux<Estudante> getEstudantesByNome(String nome) {
        return estudanteRepository.findByNome(nome);
    }

    public Mono<Estudante> updateEstudante(Long id, Estudante estudanteAtualizado) {
        return estudanteRepository.findById(id)
                .flatMap(estudanteExistente -> {
                    estudanteExistente.setNome(estudanteAtualizado.getNome());
                    estudanteExistente.setCasa(estudanteAtualizado.getCasa());
                    return estudanteRepository.save(estudanteExistente);
                });
    }

    public Mono<Void> deleteEstudante(Long id) {
        return estudanteRepository.deleteById(id);
    }
}
