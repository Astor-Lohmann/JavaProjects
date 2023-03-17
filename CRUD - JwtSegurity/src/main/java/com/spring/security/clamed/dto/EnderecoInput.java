package com.spring.security.clamed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EnderecoInput {

    @NotNull
    private Long id;
    @NotNull
    private String cep;
    @NotNull
    private String logradouro;
    @NotNull
    private Integer numero;
    @NotNull
    private String bairro;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
    private String complemento;
    @NotNull
    private String latitude;
    @NotNull
    private String longitude;

}
