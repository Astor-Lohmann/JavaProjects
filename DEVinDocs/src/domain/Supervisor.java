package domain;

import domain.documents.Documento;
import repository.ListaFuncionario;
import repository.ListaGerente;
import repository.ListaSupervisor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Supervisor extends Colaborador{

    private static Integer contador = 0;

    private final List<Documento> documentosAoGerente = new ArrayList<>();

    private static final List<Documento> documentosPendentes = new ArrayList<>();


    public Supervisor( String nome, String sobrenome, LocalDate dataNascimento, String cpf, String endereco){
        super(contador++, nome, sobrenome, dataNascimento, cpf, endereco);
        ListaSupervisor.adicionar(this);
    }

    @Override
    public void adicionarDocumentos(Documento documento) {
        documentosPendentes.add(documento);
        System.out.println("Documento encaminhado ao gerente!");
    }

    public void imprimirPendentes(){
        System.out.println(documentosPendentes);
    }
    @Override
    public void requererAprovacao(Integer IdAprovador, Integer idDocumento) {
        try{
            Gerente aprovador = ListaGerente.retornar(IdAprovador);
            aprovador.adicionarDocumentos(documentosAoGerente.get(idDocumento));
        }catch (Exception e){
            throw e;
        }
    }

    public void aprovarDocumento(Long idDocumentoPendente) {
        System.out.println("Documento Aprovado por Supervisor, nome: " + this.getNome());

        for (Documento documento : documentosPendentes) {
            if (Objects.equals(documento.getIdentificador(), idDocumentoPendente)) {
                documentosPendentes.remove(documento);
                documentosAoGerente.add(documento);
            }
        }

    }



    public void rejeitarDocumento(Long idDocumento, Integer idFuncionario) {
        Funcionario funcionario = ListaFuncionario.retornar(idFuncionario);

        for (Documento documento :
                documentosPendentes) {
            if (Objects.equals(documento.getIdentificador(), idDocumento)) {
                funcionario.adicionarDocumentos(documento);
                documentosPendentes.remove(documento);
            }
        }
    }

    public static List<Documento> retornarDocumentos() {
        return documentosPendentes;
    }
}
