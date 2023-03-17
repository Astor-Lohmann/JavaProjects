package com.example.PharmacyManagement.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String cep;
    @Column
    private String logradouro;
    @Column
    private Integer numero;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String estado;
    @Column
    private String complemento;
    @Column
    private String latitude;
    @Column
    private String longitude;


}
