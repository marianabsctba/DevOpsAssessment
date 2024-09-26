package edu.br.infnet.q07.service;

import edu.br.infnet.q07.model.OpenWeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenWeatherClient {

    private final WebClient webClient;

    public OpenWeatherClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org/data/2.5/").build();
    }

    public Mono<OpenWeatherResponse> getWeatherByCityAndDate(String city, LocalDate date) {
        String apiKey = "7942af791ab5bb5a233016b03c9a9f6d";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("forecast")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(OpenWeatherResponse.class)
                .map(response -> filterByDate(response, formattedDate))
                .onErrorResume(error -> Mono.error(new RuntimeException("Erro ao buscar informações meteorológicas", error)));
    }

    private OpenWeatherResponse filterByDate(OpenWeatherResponse response, String targetDate) {
        List<OpenWeatherResponse.WeatherForecast> filteredForecasts = response.getList().stream()
                .filter(forecast -> forecast.getDt_txt().startsWith(targetDate))
                .collect(Collectors.toList());

        response.setList(filteredForecasts);
        return response;
    }
}
