package com.example.PharmacyManagement.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="farmacia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmaciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String razaoSocial;
    @Column
    private String cnpj;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private Integer telefone;
    @Column
    private Integer celular;
    @Column
    private String idEndereco;

    @OneToOne
    @JoinColumn
    private EnderecoEntity enderecoEntity;


    public FarmaciaEntity(String razaoSocial, String cnpj, String nome, String email, Integer telefone, Integer celular, String idEndereco, EnderecoEntity enderecoEntity) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.idEndereco = idEndereco;
        this.enderecoEntity = enderecoEntity;
    }

    public FarmaciaEntity(String razaoSocial, String cnpj, String nome, String email, String telefone, String celular, EnderecoEntity enderecoEntity) {
    }
}
