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

    public static class Main {
        private Double temp;

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }
    }

    public static class Weather {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
