package br.com.t1321.projeto.classes;

import java.time.LocalDate;
import java.util.Objects;

public class Tarefa {

    private static int contador = 1;

    private final int id;
    private final String titulo;
    private final String descricao;
    private final LocalDate dataLimite;
    private final Status status;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public Status getStatus() {
        return status;
    }

    public Tarefa(String titulo, String descricao, LocalDate dataLimite, Status status) {
        this.id = contador++;
        this.titulo = titulo;
        this.descricao = Objects.requireNonNull(descricao, "Preencha o campo da descrição");
        this.dataLimite = dataLimite;
        this.status = Objects.requireNonNull(status, "Inclua o status da tarefa");

        if (titulo == null || titulo.length() < 20) {
            throw new IllegalArgumentException("O título deve ter pelo menos 20 caracteres");
        }
        if (dataLimite == null || dataLimite.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data limite não pode ser no passado");
        }

    }


}