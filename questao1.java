// a melhor é a lista simples pois nesse contexto há a remoção dos pacientes na lista por ordem de chamada, ou seja, remoção da cabeça da lista
// As outras listas funcionam melhor em loops infinitos, o que não é o caso dessa questão.


import java.util.Scanner;

public class questao1 {

    static class Paciente {
        String nome;
        int idade;
        int senha;
        Paciente proximo;

        public Paciente(String nome, int idade, int senha) {
            this.nome = nome;
            this.idade = idade;
            this.senha = senha;
            this.proximo = null;
        }
    }

    static class FilaAtendimento {
        private Paciente inicio;
        private int senhaAtual = 1;

        public void inserirPaciente(String nome, int idade) {
            Paciente novo = new Paciente(nome, idade, senhaAtual++);
            if (inicio == null) {
                inicio = novo;
                return;
            }

            if (idade > 60) {
                if (inicio.idade <= 60) {
                    novo.proximo = inicio;
                    inicio = novo;
                } else {
                    Paciente atual = inicio;
                    while (atual.proximo != null && atual.proximo.idade > 60) {
                        atual = atual.proximo;
                    }
                    novo.proximo = atual.proximo;
                    atual.proximo = novo;
                }
            } else {
                Paciente atual = inicio;
                while (atual.proximo != null) {
                    atual = atual.proximo;
                }
                atual.proximo = novo;
            }
        }

        public void chamarProximoPaciente() {
            if (inicio == null) {
                System.out.println("Nenhum paciente na fila.");
            } else {
                System.out.println("Chamando paciente: " + inicio.nome + " (Idade: " + inicio.idade + ", Senha: " + inicio.senha + ")");
                inicio = inicio.proximo;
            }
        }

        public void listarPacientes() {
            if (inicio == null) {
                System.out.println("Nenhum paciente na fila.");
                return;
            }
            System.out.println("Pacientes na fila:");
            Paciente atual = inicio;
            while (atual != null) {
                System.out.println("Nome: " + atual.nome + ", Idade: " + atual.idade + ", Senha: " + atual.senha);
                atual = atual.proximo;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaAtendimento fila = new FilaAtendimento();

        while (true) {
            System.out.println("\n1. Inserir paciente");
            System.out.println("2. Chamar próximo paciente");
            System.out.println("3. Listar pacientes");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    fila.inserirPaciente(nome, idade);
                    break;
                case 2:
                    fila.chamarProximoPaciente();
                    break;
                case 3:
                    fila.listarPacientes();
                    break;
                case 4:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}






     



