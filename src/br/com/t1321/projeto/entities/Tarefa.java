package br.com.t1321.projeto.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataLimite;
    private Enum status;

    public Tarefa(Enum status, LocalDateTime dataLimite, LocalDateTime dataCriacao, String descricao, String titulo) {
        this.status = status;
        this.dataLimite = dataLimite;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.titulo = titulo;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public LocalDateTime getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDateTime dataLimite) {
        this.dataLimite = dataLimite;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
// Classe que representa uma tarefa
public static class Tarefa {
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

    // Getters
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataLimite() { return dataLimite; }
    public Status getStatus() { return status; }

    // Setters (apenas para status que pode mudar)
    public void setStatus(Status status) {
        this.status = Objects.requireNonNull(status);
    }

    @Override
    public String toString() {
        return String.format("Tarefa: %s (Status: %s, Prazo: %s)",
                titulo, status, dataLimite.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}