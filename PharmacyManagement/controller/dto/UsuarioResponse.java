package com.example.PharmacyManagement.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UsuarioResponse {

    private Long id;
    private String email;
    private String senha;
}
