📌 Gerenciador de Tarefas Inteligente

📍 Contexto

O objetivo é criar uma aplicação capaz de cadastrar, listar e filtrar tarefas, garantindo uma experiência simples e eficiente.

Além disso, o sistema deve oferecer suporte para notificações automáticas sobre prazos próximos, ajudando os usuários a manterem suas atividades sob controle.

O desafio é desenvolver essa solução utilizando Java, aplicando conceitos modernos de programação funcional, manipulação de datas e concorrência para tornar o sistema rápido e eficiente.

🎯 Requisitos do Projeto
A aplicação deve permitir que os usuários realizem as seguintes operações:

✅ Cadastro de Tarefas
Cada tarefa deve conter: título, descrição, data limite (deadline) e status (Pendente, Em andamento, Concluído).
O sistema deve garantir que nenhuma tarefa seja cadastrada com data limite no passado.

✅ Gerenciamento e Filtragem
Exibir todas as tarefas cadastradas.
Permitir filtrar tarefas por status.
Listar as tarefas ordenadas por data limite.

✅ Validação e Qualidade dos Dados
Evitar que tarefas com títulos muito curtos sejam cadastradas.
Garantir que nenhuma tarefa seja salva sem um status válido.

✅ Notificações Inteligentes
O sistema deve alertar automaticamente o usuário quando uma tarefa estiver próxima da data limite.


🛠 Tecnologias e Conceitos

📌 Manipulação de Datas: Uso da API java.time para lidar com datas e prazos.
📌 Programação Funcional: Uso de Streams, Optional e Predicate para processar e validar as tarefas.
📌 Execução Assíncrona: Uso de CompletableFuture para implementar notificações automáticas.
