package domain.documents;

import domain.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class ListaDocumentos {

    public static List<Documento> documentos = new ArrayList<>();

    public static void adicionar(Documento documento){ documentos.add(documento); }

    public static List<Documento> retornarTodos() {return documentos; }
}
