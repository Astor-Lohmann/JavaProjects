package com.spring.security.clamed.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MedicamentoOutput {

    private Long id;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private String descricaoop;
    private Float preco;
    private String tipo;
}
