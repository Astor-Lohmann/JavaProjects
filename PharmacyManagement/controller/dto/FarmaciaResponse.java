package com.example.PharmacyManagement.controller.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmaciaResponse {

    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String nome;
    private String email;
    private Integer telefone;
    private Integer celular;
    private String idEndereco;


    public FarmaciaResponse(Long id, String razaoSocial, String cnpj, String nome, String email, Integer telefone, Integer celular, EnderecoResponse enderecoResponse) {
    }
}
