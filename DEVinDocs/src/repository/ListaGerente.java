package repository;

import domain.Gerente;

import java.util.ArrayList;
import java.util.List;

public class ListaGerente {

    public static List<Gerente> gerentes = new ArrayList<>();

    public static void adicionar(Gerente gerente){ gerentes.add(gerente); }

    public static Gerente retornar(Integer id){
        return gerentes.get(id);
    }

    public static List<Gerente> retornarTodos(){
        return gerentes;
    }
}
