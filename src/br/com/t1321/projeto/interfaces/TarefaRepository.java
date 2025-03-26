package br.com.t1321.projeto.interfaces;

import br.com.t1321.projeto.classes.Status;
import br.com.t1321.projeto.classes.Tarefa;

import java.util.List;

public interface TarefaRepository {
    void salvar(Tarefa tarefa);
    List<Tarefa> listarTodas();
    List<Tarefa> filtrarPorStatus(Status status);
    List<Tarefa> listarOrdenadoPorData();
}

