package edu.br.infnet.q07.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OpenWeatherResponse {

    private List<WeatherForecast> list;

    @Setter
    @Getter
    public static class WeatherForecast {
        private String dt_txt;
        private Main main;
        private List<Weather> weather;
    }

    @Setter
    @Getter
    public static class Main {
        private Double temp;
    }

    @Setter
    @Getter
    public static class Weather {
        private String description;
    }
}
