package com.example.PharmacyManagement.controller.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MedicamentoResponse {

    private Long id;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private String descricaoop;
    private Float preco;
    private String tipo;
}
