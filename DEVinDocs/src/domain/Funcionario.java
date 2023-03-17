package domain;

import domain.documents.Documento;
import domain.documents.ListaDocumentos;
import repository.ListaFuncionario;
import repository.ListaSupervisor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Funcionario extends Colaborador{

    private static Integer contador = 0;
    private static List<Documento> documentos = new ArrayList<>();

    public Funcionario( String nome, String sobrenome, LocalDate dataNascimento, String cpf, String endereco){
        super( contador++, nome, sobrenome, dataNascimento, cpf, endereco);
        ListaFuncionario.adicionar(this);
    }

    @Override
    public void adicionarDocumentos(Documento documento) {
        documentos.add(documento);
        System.out.println("Documento foi Criado!");
    }

    @Override
    public void requererAprovacao(Integer idAprovador, Integer idDocumento) {
        try{
            Supervisor aprovador = ListaSupervisor.retornar(idAprovador);
            aprovador.adicionarDocumentos(documentos.get(idDocumento));
        }catch (Exception e){
            throw e;
        }
    }

    public static List<Documento> retornarDocumentos() {
        return documentos;
    }
}
