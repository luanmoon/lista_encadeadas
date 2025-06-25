// Foi usada a lista circular encadeada pois o problema exigia um loop infinito, sendo essa lista a melhor para essa solução.
// Inserções e remoções são feitas de forma eficiente.




import java.util.Scanner;

public class questao4 {

    static class Jogador {
        String nome;
        int pontuacao;
        Jogador proximo;

        public Jogador(String nome, int pontuacao) {
            this.nome = nome;
            this.pontuacao = pontuacao;
        }

        @Override
        public String toString() {
            return nome + " (Pontuação: " + pontuacao + ")";
        }
    }

    static class ListaRodizio {
        private Jogador atual = null;

        public void adicionarJogador(String nome, int pontuacao) {
            Jogador novo = new Jogador(nome, pontuacao);

            if (atual == null) {
                atual = novo;
                atual.proximo = atual; 
            } else {
                Jogador temp = atual;
                while (temp.proximo != atual) {
                    temp = temp.proximo;
                }
                temp.proximo = novo;
                novo.proximo = atual;
            }
            System.out.println("Jogador " + nome + " adicionado.");
        }

        public void removerJogadorAtual() {
            if (atual == null) {
                System.out.println("Nenhum jogador na lista.");
                return;
            }

            if (atual.proximo == atual) {
                System.out.println("Jogador " + atual.nome + " removido.");
                atual = null;
                return;
            }

            Jogador temp = atual;
            while (temp.proximo != atual) {
                temp = temp.proximo;
            }
            System.out.println("Jogador " + atual.nome + " removido.");
            temp.proximo = atual.proximo;
            atual = atual.proximo;
        }


        public void proximoJogador() {
            if (atual != null) {
                atual = atual.proximo;
                System.out.println("Agora é a vez de: " + atual);
            } else {
                System.out.println("Lista de jogadores vazia.");
            }
        }

        public void listarJogadores() {
            if (atual == null) {
                System.out.println("Lista de jogadores vazia.");
                return;
            }

            Jogador temp = atual;
            do {
                System.out.println(temp);
                temp = temp.proximo;
            } while (temp != atual);
        }

        public Jogador getAtual() {
            return atual;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaRodizio rodizio = new ListaRodizio();

        int opcao;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Adicionar jogador");
            System.out.println("2. Remover jogador atual");
            System.out.println("3. Avançar para o próximo jogador");
            System.out.println("4. Listar jogadores");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Nome do jogador: ");
                    String nome = scanner.nextLine();
                    System.out.print("Pontuação inicial: ");
                    int pontos = scanner.nextInt();
                    rodizio.adicionarJogador(nome, pontos);
                    break;
                case 2:
                    rodizio.removerJogadorAtual();
                    break;
                case 3:
                    rodizio.proximoJogador();
                    break;
                case 4:
                    rodizio.listarJogadores();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
