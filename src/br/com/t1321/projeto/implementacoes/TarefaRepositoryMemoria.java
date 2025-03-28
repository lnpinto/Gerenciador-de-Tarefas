package br.com.t1321.projeto.implementacoes;


import br.com.t1321.projeto.interfaces.TarefaRepository;
import br.com.t1321.projeto.classes.Status;
import br.com.t1321.projeto.classes.Tarefa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TarefaRepositoryMemoria implements TarefaRepository {
    private final List<Tarefa> tarefas = new ArrayList<>();

    @Override
    public void salvar(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    @Override
    public List<Tarefa> listarTodas() {
        return new ArrayList<>(tarefas);
    }

    @Override
    public List<Tarefa> filtrarPorStatus(Status status) {
        List<Tarefa> collect = tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Tarefa> listarOrdenadoPorData() {
        return tarefas.stream()
                .sorted(Comparator.comparing(Tarefa::getDataLimite))
                .collect(Collectors.toList());
    }
}

