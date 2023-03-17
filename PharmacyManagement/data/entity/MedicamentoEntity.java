package com.example.PharmacyManagement.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MedicamentoEntity {

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
}
