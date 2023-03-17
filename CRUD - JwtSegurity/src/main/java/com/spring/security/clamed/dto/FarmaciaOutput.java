package com.spring.security.clamed.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmaciaOutput {

    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String nome;
    private String email;
    private Integer telefone;
    private Integer celular;
    private String idEndereco;


    public FarmaciaOutput(Long id, String razaoSocial, String cnpj, String nome, String email, Integer telefone, Integer celular, EnderecoOutput enderecoOutput) {
    }
}
