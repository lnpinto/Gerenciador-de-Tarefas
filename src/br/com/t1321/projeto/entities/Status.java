package br.com.t1321.projeto.entities;

// Enum para status das tarefas
public enum Status {
    PENDENTE, EM_ANDAMENTO, CONCLUIDO;

    @Override
    public String toString() {
        return name().chartAt(0) + name().substring(1).toLowerCase().replace("_", " ");
    }
}
