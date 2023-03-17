package service;

import domain.Funcionario;
import domain.Gerente;
import domain.Supervisor;
import domain.documents.Documento;

import java.util.Scanner;

public class Servico {

    public void criarNovoDocumento(Funcionario usuario){
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        System.out.print("Digite o Id do Criador:");
        Integer idCriador = scanner1.nextInt();
        System.out.print("Digite o id do Responsável:");
        Integer idResponsavel = scanner1.nextInt();
        System.out.print("Insira o Link do Documento");
        String link = scanner2.nextLine();
        Documento documento = new Documento(idCriador, idResponsavel, link);
        usuario.adicionarDocumentos(documento);
    }

    public void enviarAprovacao(Funcionario usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o identificador do Aprovador(Supervisor):");
        Integer idAprovador = scanner.nextInt();
        System.out.print("Informe o identificador do Documento:");
        Integer idDocumento = scanner.nextInt();

        usuario.requererAprovacao(idAprovador, idDocumento);
    }

    public void enviarAprovacao(Supervisor usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o identificador do Aprovador(Gerente):");
        Integer idAprovador = scanner.nextInt();
        System.out.print("Informe o identificador do Documento:");
        Integer idDocumento = scanner.nextInt();

        usuario.requererAprovacao(idAprovador, idDocumento);
    }

    public void aprovarDocumento(Supervisor usuario){
        Scanner scanner = new Scanner(System.in);

        Long idDocumento = scanner.nextLong();

        usuario.aprovarDocumento( idDocumento);

    }

    public void rejeitarDocumento(Supervisor usuario){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o identificador do Documento:");
        Long idDocumento = scanner.nextLong();

        System.out.print("Informe o identificador do Funcionário:");
        Integer idFuncionario = scanner.nextInt();



        usuario.rejeitarDocumento(idDocumento, idFuncionario);
    }

    public void rejeitarDocumento(Gerente usuario){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o identificador do Documento:");
        Long idDocumento = scanner.nextLong();

        System.out.print("Informe o identificador do Supervisor:");
        Integer idSupervisor = scanner.nextInt();


        usuario.rejeitarDocumento(idDocumento, idSupervisor);
    }

    public void arquivarDocumento(Gerente usuario){
        Scanner scanner = new Scanner(System.in);

        Long idDocumento = scanner.nextLong();

        usuario.arquivarDocumento( idDocumento);

    }

    public void dearquivarDocumento(Gerente usuario){
        Scanner scanner = new Scanner(System.in);

        Long idDocumento = scanner.nextLong();

        usuario.desarquivarDocumento( idDocumento);
    }


}
