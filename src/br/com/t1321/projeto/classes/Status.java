package br.com.t1321.projeto.classes;

// Enum para status das tarefas
public enum Status {
    PENDENTE, EM_ANDAMENTO, CONCLUIDO;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace("_", " ");
    }
}
