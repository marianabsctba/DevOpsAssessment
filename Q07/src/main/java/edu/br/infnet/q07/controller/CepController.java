package edu.br.infnet.q07.controller;

import edu.br.infnet.q07.model.CepResponse;
import edu.br.infnet.q07.service.CepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    @Autowired
    private CepClient cepClient;

    @GetMapping("/{cep}")
    public Mono<CepResponse> getEnderecoByCep(@PathVariable String cep) {
        return cepClient.getEnderecoByCep(cep);
    }
}