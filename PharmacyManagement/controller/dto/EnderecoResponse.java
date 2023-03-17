package com.example.PharmacyManagement.controller.dto;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {

    private Long id;
    private String cep;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private String latitude;
    private String longitude;

    public EnderecoResponse( String cep, String logradouro, Integer numero, String bairro, String cidade, String estado, String complemento, String latitude, String longitude) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
