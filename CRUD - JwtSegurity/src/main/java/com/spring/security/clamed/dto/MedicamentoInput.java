package com.spring.security.clamed.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MedicamentoInput {

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
