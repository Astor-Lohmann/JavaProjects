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


public class FarmaciaInput {
    @NotNull
    private Long id;
    @NotNull
    private String razaoSocial;
    @NotNull
    private String cnpj;
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String telefone;
    @NotNull
    private String celular;
    @NotNull
    private Long idEndereço;
}
