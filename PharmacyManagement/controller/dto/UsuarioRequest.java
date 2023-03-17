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


public class UsuarioRequest {

    @NotNull
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String senha;
}
