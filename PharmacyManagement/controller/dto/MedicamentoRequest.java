package com.example.PharmacyManagement.controller.dto;


import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MedicamentoRequest {

    @NotNull
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String laboratorio;
    @NotNull
    private String dosagem;
    @NotNull
    private String descricao;
    private String descricaoop;
    @NotNull
    private Float preco;
    @NotNull
    private String tipo;
}
