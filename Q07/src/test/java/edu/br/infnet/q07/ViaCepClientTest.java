package edu.br.infnet.q07;

import edu.br.infnet.q07.model.ViaCepResponse;
import edu.br.infnet.q07.service.ViaCepClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
public class ViaCepClientTest {

    @Autowired
    private ViaCepClient viaCepClient;

    @Container
    static GenericContainer<?> wiremock = new GenericContainer<>("rodolpheche/wiremock")
            .withExposedPorts(8087);

    @BeforeAll
    static void setUp() {
        configureFor(wiremock.getHost(), wiremock.getFirstMappedPort());
        stubFor(get(urlEqualTo("/66015060/json/"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"cep\": \"66015-060\", \"logradouro\": \"Praça Barão do Rio Branco\", \"bairro\": \"Campina\", \"localidade\": \"Belém\", \"uf\": \"PA\" }")));
    }

    @Test
    void testGetEnderecoByCep() {
        String cep = "01001000";

        Mono<ViaCepResponse> responseMono = viaCepClient.getEnderecoByCep(cep);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getCep().equals("66015-060")
                        && response.getLogradouro().equals("Praça Barão do Rio Branco")
                        && response.getBairro().equals("Campina")
                        && response.getLocalidade().equals("Belém")
                        && response.getUf().equals("PA"))
                .verifyComplete();
    }
}
