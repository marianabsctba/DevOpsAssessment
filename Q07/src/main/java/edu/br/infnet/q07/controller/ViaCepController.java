package edu.br.infnet.q07.controller;

import edu.br.infnet.q07.model.ViaCepResponse;
import edu.br.infnet.q07.service.ViaCepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cep")
public class ViaCepController {

    @Autowired
    private ViaCepClient viaCepClient;

    @GetMapping("/{cep}")
    public Mono<ViaCepResponse> getEnderecoByCep(@PathVariable String cep) {
        return viaCepClient.getEnderecoByCep(cep);
    }
}
