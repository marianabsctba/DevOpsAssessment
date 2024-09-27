package edu.br.infnet.q07.model;

import lombok.Data;

@Data
public class CepResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

}
