import domain.Funcionario;
import domain.Gerente;
import domain.Supervisor;
import domain.documents.Documento;
import domain.documents.ListaDocumentos;
import repository.ListaFuncionario;
import repository.ListaGerente;
import repository.ListaSupervisor;
import service.Servico;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Funcionario funcionario = new Funcionario(
                "Fulano 1",
                "De tal",
                LocalDate.of(1950,1,1),
                "08099484200",
                "rua dos ciclanos 00"

        );

        Supervisor supervisor = new Supervisor(
                "Fulano 2",
                "De tal",
                LocalDate.of(1950,1,1),
                "08099484200",
                "rua dos ciclanos 00"

        );

        Gerente gerente = new Gerente(
                "Fulano 3",
                "De tal",
                LocalDate.of(1950,1,1),
                "08099484200",
                "rua dos ciclanos 00"

        );

        Scanner scannerNavegacao = new Scanner(System.in);
        Servico servico = new Servico();

        System.out.println("Sistema DEVinDocs --- Bem-Vindo! -----");
        System.out.println("Escolha o seu cargo: " +
                "\n 1 - Funcionário" +
                "\n 2 - Supervisor" +
                "\n 3 - Gerente" +
                "\n Entre a opção escolhida:"
        );

        Integer cargo = scannerNavegacao.nextInt();

        boolean executa = true;
        while(executa){


            switch (cargo){
                case 1:
                    System.out.print("---- Perfil Funcionário ----" +
                            "\n Digite o seu identificador: ");
                    Integer idfunc = scannerNavegacao.nextInt();
                    Funcionario func = ListaFuncionario.retornar(idfunc);

                    boolean loginfunc = true;

                    while(loginfunc){
                        System.out.println("---- Perfil Funcionário ----" +
                                "\n Escolha a operação: " +
                                "\n 1 - Criar Novo Documento" +
                                "\n 2 - Listar Documentos" +
                                "\n 3 - Listar Funcionarios" +
                                "\n 4 - Requerer Aprovação de Documento" +
                                "\n 5 - Voltar ao Login" +
                                "\n Entre a opção escolhida:"
                        );

                        Integer operacao = scannerNavegacao.nextInt();

                        switch (operacao){
                            case 1:
                                servico.criarNovoDocumento(func);
                                break;
                            case 2:
                                System.out.println(Funcionario.retornarDocumentos().toString());
                                break;
                            case 3:
                                System.out.println(ListaFuncionario.retornarTodos().toString());
                                break;
                            case 4:
                                servico.enviarAprovacao(func);
                                break;
                            case 5:
                                loginfunc = false;
                                break;
                        }
                    }
                case 2:
                    System.out.print("---- Perfil Supervisor ----" +
                            "\n Digite o seu identificador: ");
                    Integer idsuperv = scannerNavegacao.nextInt();
                    Supervisor superv = ListaSupervisor.retornar(idsuperv);

                    boolean loginsuperv = true;

                    while(loginsuperv){
                        System.out.println("---- Perfil Supervisor ----" +
                                "\n Escolha a operação: " +
                                "\n 1 - Listar Documentos Cadastrados" +
                                "\n 2 - Aprovar Documento" +
                                "\n 3 - Requerer Aprovação Gerente" +
                                "\n 4 - Recusar Documento" +
                                "\n 5 - Listar Supervisores" +
                                "\n 6 - Voltar" +
                                "\n Entre a opção escolhida:"
                        );

                        Integer operacao = scannerNavegacao.nextInt();

                        switch (operacao){
                            case 1:
                                System.out.println(Supervisor.retornarDocumentos().toString());
                                break;
                            case 2:
                                servico.aprovarDocumento(superv);
                                break;
                            case 3:
                                servico.enviarAprovacao(superv);
                                break;
                            case 4:
                                servico.rejeitarDocumento(superv);
                                break;
                            case 5:
                                System.out.println(ListaSupervisor.retornarTodos().toString());
                                break;
                            case 6:
                                loginsuperv = false;
                                break;
                        }
                    }

                case 3:

                    System.out.print("---- Perfil Gerente ----" +
                            "\n Digite o seu identificador: ");
                    Integer id = scannerNavegacao.nextInt();
                    Gerente ger = ListaGerente.retornar(id);

                    boolean loginger = true;

                    while(loginger){
                        System.out.println("---- Perfil Gerente ----" +
                                "\n Escolha a operação: " +
                                "\n 1 - Listar Documentos" +
                                "\n 2 - Arquivar Documento" +
                                "\n 3 - Desarquivar Documento" +
                                "\n 4 - Rejeitar Documento" +
                                "\n 4 - Voltar" +
                                "\n Entre a opção escolhida:"
                        );

                        Integer operacao = scannerNavegacao.nextInt();

                        switch (operacao){
                            case 1:
                                System.out.println(Gerente.retornarDocumentos().toString());
                                break;
                            case 2:
                                servico.arquivarDocumento(ger);
                                break;
                            case 3:
                                servico.dearquivarDocumento(ger);
                                break;
                            case 4:
                                servico.rejeitarDocumento(ger);
                                break;
                            case 5:
                                System.out.println(ListaGerente.retornarTodos().toString());
                                break;
                            case 6:
                                loginger = false;
                                break;
                        }
                    }

                    System.out.print("Deseja Encerrar? " +
                            "\n 1 - Sim" +
                            "\n 2 - Não" +
                            "\n Entre a opção escolhida:");

                    Integer encerrar = scannerNavegacao.nextInt();

                    switch (encerrar){
                        case 1:
                            executa = false;
                            break;
                        case 2:
                            executa = true;
                            break;
                    }
            }
        }

    }
}