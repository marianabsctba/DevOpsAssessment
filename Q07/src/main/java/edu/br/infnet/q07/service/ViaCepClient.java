package edu.br.infnet.q07.service;

import edu.br.infnet.q07.model.ViaCepResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepClient {

    private final WebClient webClient;

    public ViaCepClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://viacep.com.br/ws/").build();
    }

    public Mono<ViaCepResponse> getEnderecoByCep(String cep) {
        return this.webClient.get()
                .uri("{cep}/json/", cep)
                .retrieve()
                .bodyToMono(ViaCepResponse.class)
                .onErrorResume(error -> Mono.error(new RuntimeException("Erro ao buscar o CEP", error)));
    }
}

