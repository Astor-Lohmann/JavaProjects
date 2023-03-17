package com.example.PharmacyManagement.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class FarmaciaRequest {
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
