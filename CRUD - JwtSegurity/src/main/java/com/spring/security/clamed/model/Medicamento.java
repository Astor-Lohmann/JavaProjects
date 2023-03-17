package com.spring.security.clamed.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String nome;
    @Column
    private String laboratorio;
    @Column
    private String dosagem;
    @Column
    private String descricao;
    @Column
    private String descricaoop;
    @Column
    private Float preco;
    @Column
    private String tipo;

    public Medicamento(String nome, String laboratorio, String dosagem, String descricao, String descricaoop, Float preco, String tipo) {
    }
}
