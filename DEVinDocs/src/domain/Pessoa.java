package domain;

import java.time.LocalDate;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class Pessoa {

    private Integer identificador;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private LocalDate dataNascimento;
    private String cpf;
    private String endereco;

    public Pessoa() {
    }

    public Pessoa(Integer identificador, String nome, String sobrenome, LocalDate dataNascimento, String cpf, String endereco) {
        this.identificador = identificador;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.idade = calculaIdade();
    }

    public Integer calculaIdade(){
        this.idade = LocalDate.now().getYear() - dataNascimento.getYear();
        return  this.idade;
    }


    public Integer getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "identificador='" + identificador + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", idade=" + idade +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

}
