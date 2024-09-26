package edu.br.infnet.q07;

import edu.br.infnet.q07.service.OpenWeatherClient;
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
import edu.br.infnet.q07.model.OpenWeatherResponse;

import java.time.LocalDate;


@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
public class OpenWeatherClientTest {

    @Autowired
    private OpenWeatherClient openWeatherClient;

    @Container
    static GenericContainer<?> wiremock = new GenericContainer<>("rodolpheche/wiremock")
            .withExposedPorts(8087);

    @BeforeAll
    static void setUp() {
        configureFor(wiremock.getHost(), wiremock.getFirstMappedPort());
        stubFor(get(urlPathEqualTo("/data/2.5/forecast"))
                .withQueryParam("q", equalTo("London"))
                .withQueryParam("appid", equalTo("YOUR_OPENWEATHER_API_KEY"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"list\": [{\"dt_txt\": \"2024-09-26 12:00:00\", \"main\": {\"temp\": 20.5}, \"weather\": [{\"description\": \"clear sky\"}]}]}")));
    }

    @Test
    void testGetWeatherByCityAndDate() {
        String city = "London";
        String date = "2024-09-26";

        Mono<OpenWeatherResponse> responseMono = openWeatherClient.getWeatherByCityAndDate(city, LocalDate.parse(date));

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getList().get(0).getMain().getTemp() == 20.5
                        && response.getList().get(0).getWeather().get(0).getDescription().equals("clear sky"))
                .verifyComplete();
    }
}
