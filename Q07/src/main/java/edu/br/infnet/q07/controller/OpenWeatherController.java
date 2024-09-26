package edu.br.infnet.q07.controller;

import edu.br.infnet.q07.model.OpenWeatherResponse;
import edu.br.infnet.q07.service.OpenWeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/weather")
public class OpenWeatherController {

    @Autowired
    private OpenWeatherClient openWeatherClient;

    @GetMapping("/{city}")
    public Mono<OpenWeatherResponse> getWeatherByCityAndDate(@PathVariable String city, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return openWeatherClient.getWeatherByCityAndDate(city, localDate);
    }
}
