package domain;

import domain.documents.Documento;
import repository.ListaPessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Colaborador extends Pessoa {

    private static List<Documento> documentos = new ArrayList<>();

    public Colaborador(Integer identificador, String nome, String sobrenome, LocalDate dataNascimento, String cpf, String endereco){
        super(identificador, nome, sobrenome, dataNascimento, cpf, endereco);
        ListaPessoa.adicionar(this);

    }
    public void adicionarDocumentos(Documento documento){
        documentos.add(documento);
        System.out.println("Documento Criado");
    }

    public static List<Documento> retornarDocumentos() {
        return documentos;
    }

    public void requererAprovacao(Integer IdAprovador, Integer idDocumento) { return; }

}
