package com.spring.security.clamed.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="farmacia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Farmacia {

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
    @JoinColumn(name = "idEndereço", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_endereco"))
    private Endereco endereco;


    public Farmacia(String razaoSocial, String cnpj, String nome, String email, Integer telefone, Integer celular, String idEndereco, Endereco endereco) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.idEndereco = idEndereco;
        this.endereco = endereco;
    }

    public Farmacia(String razaoSocial, String cnpj, String nome, String email, String telefone, String celular, Endereco endereco) {
    }
}
