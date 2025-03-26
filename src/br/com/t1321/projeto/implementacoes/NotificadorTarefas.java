package br.com.t1321.projeto.implementacoes;

import br.com.t1321.projeto.interfaces.Notificador;
import br.com.t1321.projeto.interfaces.TarefaRepository;
import br.com.t1321.projeto.classes.Status;
import br.com.t1321.projeto.classes.Tarefa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class NotificadorTarefas implements Notificador {
    private final TarefaRepository repository;
    private CompletableFuture<Void> future;
    private volatile boolean executando;

    public NotificadorTarefas(TarefaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void iniciarNotificacoes() {
        executando = true;
        future = CompletableFuture.runAsync(this::verificarNotificacoes);
    }

    @Override
    public void pararNotificacoes() {
        executando = false;
        if (future != null) {
            future.cancel(true);
        }
    }

    private void verificarNotificacoes() {
        while (executando) {
            try {
                List<Tarefa> tarefasProximas = repository.listarTodas().stream()
                        .filter(t -> t.getStatus() != Status.CONCLUIDO)
                        .filter(t -> ChronoUnit.DAYS.between(LocalDate.now(), t.getDataLimite()) <= 2)
                        .collect(Collectors.toList());

                if (!tarefasProximas.isEmpty()) {
                    System.out.println("\n=== NOTIFICAÇÕES ===");
                    System.out.println("As seguintes tarefas estão próximas do prazo:");
                    tarefasProximas.forEach(t ->
                            System.out.printf("- %s (ID: %d) - Vence em %d dias%n",
                                    t.getTitulo(), t.getId(),
                                    ChronoUnit.DAYS.between(LocalDate.now(), t.getDataLimite())));
                    System.out.println("===================\n");
                }

                // Verificar a cada hora
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                System.err.println("Erro no notificador: " + e.getMessage());
            }
        }
    }
}
