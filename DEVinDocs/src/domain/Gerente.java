package domain;

import domain.documents.Documento;
import repository.ListaFuncionario;
import repository.ListaGerente;
import repository.ListaSupervisor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gerente extends Colaborador{

    private static Integer contador = 0;

    private List<Documento> documentosArquivados = new ArrayList<>();
    private List<Documento> documentosPendentes = new ArrayList<>();

    public Gerente(String nome, String sobrenome, LocalDate dataNascimento, String cpf, String endereco){
        super(contador++, nome, sobrenome, dataNascimento, cpf, endereco);
        ListaGerente.adicionar(this);
    }

    @Override
    public void adicionarDocumentos(Documento documento) {
        documentosPendentes.add(documento);
        System.out.println("Documento encaminhado ao gerente!");
    }





    public void rejeitarDocumento(Long idDocumento, Integer idSupervisor) {
        Supervisor supervisor = ListaSupervisor.retornar(idSupervisor);

        for (Documento documento :
                documentosPendentes) {
            if (Objects.equals(documento.getIdentificador(), idDocumento)) {
                supervisor.adicionarDocumentos(documento);
                documentosPendentes.remove(documento);
            }
        }
    }

    public void arquivarDocumento(Long idDocumentoPendente) {
        System.out.println("Documento Arquivado pelo Gerente, nome: " + this.getNome());

        for (Documento documento : documentosPendentes) {
            if (Objects.equals(documento.getIdentificador(), idDocumentoPendente)) {
                documentosPendentes.remove(documento);
                documentosArquivados.add(documento);
            }
        }

    }

    public void desarquivarDocumento(Long idMedicamentoArquivado) {
        System.out.println("Documento Desarquivado pelo Gerente, nome: " + this.getNome());

        for (Documento documento : documentosPendentes) {
            if (Objects.equals(documento.getIdentificador(), idMedicamentoArquivado)) {
                documentosPendentes.add(documento);
                documentosArquivados.remove(documento);
            }
        }

    }

}
