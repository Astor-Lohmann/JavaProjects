package repository;

import domain.Funcionario;
import domain.Supervisor;

import java.util.ArrayList;
import java.util.List;

public class ListaFuncionario {

    public static List<Funcionario> funcionarios = new ArrayList<>();

    public static void adicionar(Funcionario funcionario){ funcionarios.add(funcionario); }

    public static Funcionario retornar(Integer id){
        return funcionarios.get(id);
    }

    public static List<Funcionario> retornarTodos(){
        return funcionarios;
    }
}
