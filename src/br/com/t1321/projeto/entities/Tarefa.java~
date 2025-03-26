package br.com.t1321.projeto.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Tarefa {
    private final String titulo;
    private final String descricao;
    private final LocalDate dataLimite;
    private Status status;

    public Tarefa(String titulo, String descricao, LocalDate dataLimite, Status status) {
        this.titulo = Objects.requireNonNull(titulo, "Título não pode ser nulo");
        this.descricao = Objects.requireNonNull(descricao, "Descrição não pode ser nula");
        this.dataLimite = Objects.requireNonNull(dataLimite, "Data limite não pode ser nula");
        this.status = Objects.requireNonNull(status, "Status não pode ser nulo");

        if (titulo.length() < 20) {
            throw new IllegalArgumentException("Título deve ter pelo menos 20 caracteres");
        }
        if (dataLimite.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data limite não pode estar no passado");
        }
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

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Tarefa: %s (Status: %s, Prazo: %s)",
                titulo, status, dataLimite.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}