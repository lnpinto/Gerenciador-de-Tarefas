package br.com.t1321.projeto;

import br.com.t1321.projeto.interfaces.Notificador;
import br.com.t1321.projeto.interfaces.TarefaRepository;
import br.com.t1321.projeto.classes.Status;
import br.com.t1321.projeto.implementacoes.NotificadorTarefas;
import br.com.t1321.projeto.implementacoes.TarefaRepositoryMemoria;
import br.com.t1321.projeto.classes.Tarefa;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

class GerenciadorTarefasApp {
    private final TarefaRepository repository = new TarefaRepositoryMemoria();
    private final Notificador notificador = new NotificadorTarefas(repository);
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        System.out.println("=== Gerenciador de Tarefas Inteligente ===");

        // Inicia o notificador em background
        notificador.iniciarNotificacoes();

        while (true) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> cadastrarTarefa();
                case 2 -> listarTodasTarefas();
                case 3 -> filtrarTarefasPorStatus();
                case 4 -> listarTarefasOrdenadasPorData();
                case 5 -> {
                    System.out.println("Saindo do sistema...");
                    notificador.pararNotificacoes();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Cadastrar nova tarefa");
        System.out.println("2. Listar todas as tarefas");
        System.out.println("3. Filtrar tarefas por status");
        System.out.println("4. Listar tarefas ordenadas por data limite");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void cadastrarTarefa() {
        System.out.println("\n=== Cadastro de Tarefa ===");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        LocalDate dataLimite = lerDataLimite();
        Status status = lerStatus();

        try {
            Tarefa novaTarefa = new Tarefa(titulo, descricao, dataLimite, status);
            repository.salvar(novaTarefa);
            System.out.println("Tarefa cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar tarefa: " + e.getMessage());
        }
    }

    private LocalDate lerDataLimite() {
        while (true) {
            try {
                System.out.print("Data limite (yyyy-MM-dd): ");
                LocalDate data = LocalDate.parse(scanner.nextLine());

                if (data.isBefore(LocalDate.now())) {
                    System.out.println("A data limite não pode ser no passado!");
                    continue;
                }

                return data;
            } catch (DateTimeException e) {
                System.out.println("Formato de data inválido. Use yyyy-MM-dd.");
            }
        }
    }

    private Status lerStatus() {
        while (true) {
            System.out.println("Status disponíveis:");
            for (Status status : Status.values()) {
                System.out.println(status.ordinal() + 1 + ". " + status);
            }
            System.out.print("Escolha o status: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                return Status.values()[opcao - 1];
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        }
    }

    private void listarTodasTarefas() {
        System.out.println("\n=== Lista de Todas as Tarefas ===");
        List<Tarefa> tarefas = repository.listarTodas();

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada");
        } else {
            tarefas.forEach(this::imprimirTarefa);
        }
    }

    private void filtrarTarefasPorStatus() {
        System.out.println("\n=== Filtrar Tarefas por Status ===");
        Status status = lerStatus();

        System.out.println("\nTarefas com status '" + status + "':");
        repository.filtrarPorStatus(status).forEach(this::imprimirTarefa);
    }

    private void listarTarefasOrdenadasPorData() {
        System.out.println("\n=== Tarefas Ordenadas por Data Limite ===");
        repository.listarOrdenadoPorData().forEach(this::imprimirTarefa);
    }

    private void imprimirTarefa(Tarefa tarefa) {
        System.out.println("\n---------------------------------");
        System.out.println("\nTarefa #" + tarefa.getId());
        System.out.println("Título: " + tarefa.getTitulo());
        System.out.println("Descrição: " + tarefa.getDescricao());
        System.out.println("Data Limite: " + tarefa.getDataLimite());
        System.out.println("Status: " + tarefa.getStatus());
        //System.out.println("Dias restantes: " + ChronoUnit.DAYS.between(LocalDate.now(), tarefa.getDataLimite()));

        long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), tarefa.getDataLimite());
        String situacaoPrazo;
        if (diasRestantes < 0) {
            situacaoPrazo = "ATRASADA (" + (-diasRestantes) + "dias)";
        } else if (diasRestantes == 0) {
            situacaoPrazo = "ÚLTIMO DIA!";
        } else {
            situacaoPrazo = "Faltam " + diasRestantes + " dias";
        }
        System.out.println("Situação do prazo: " + situacaoPrazo);
        System.out.println("---------------------------------");
    }
}
