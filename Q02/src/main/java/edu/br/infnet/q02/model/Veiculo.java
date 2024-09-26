package edu.br.infnet.q02.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 7, max = 7, message = "A placa deve ter 7 caracteres.")
    @Column(name = "placa")
    private String placa;

    @NotNull(message = "Marca não pode ser nula.")
    @Column(name = "marca")
    private String marca;

    @NotNull(message = "Modelo não pode ser nulo.")
    @Column(name = "modelo")
    private String modelo;

    @NotNull(message = "Ano não pode ser nulo.")
    @Column(name = "ano")
    private int ano;
}
