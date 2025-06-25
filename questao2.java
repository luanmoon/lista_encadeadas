// foi usada a lista duplamente ligada circular pois ela permite que cada nó tenha referência para o próximo e anterior.
// a circular entra devido ao looping infinito da playlist





import java.util.Scanner;

public class questao2 {
    static class Musica {
        String nome;
        int duracao; 

        public Musica(String nome, int duracao) {
            this.nome = nome;
            this.duracao = duracao;
        }

        public String toString() {
            return nome + " (" + duracao + "s)";
        }
    }

    static class No {
        Musica musica;
        No anterior, proximo;

        public No(Musica musica) {
            this.musica = musica;
        }
    }

    static class Playlist {
        private No atual;
        private int tamanho = 0;

        public void adicionarMusica(Musica musica, int posicao) {
            No novo = new No(musica);

            if (tamanho == 0) {
                atual = novo;
                atual.proximo = atual;
                atual.anterior = atual;
            } else {
                if (posicao <= 0) posicao = 0;
                if (posicao >= tamanho) posicao = tamanho;

                No aux = atual;
                for (int i = 0; i < posicao; i++) {
                    aux = aux.proximo;
                }

                novo.anterior = aux.anterior;
                novo.proximo = aux;
                aux.anterior.proximo = novo;
                aux.anterior = novo;

                if (posicao == 0) atual = novo;
            }

            tamanho++;
        }

        public void removerMusica(int posicao) {
            if (tamanho == 0 || posicao < 0 || posicao >= tamanho) return;

            No aux = atual;
            for (int i = 0; i < posicao; i++) {
                aux = aux.proximo;
            }

            aux.anterior.proximo = aux.proximo;
            aux.proximo.anterior = aux.anterior;

            if (aux == atual) atual = aux.proximo;

            tamanho--;
            if (tamanho == 0) atual = null;
        }

        public void proximaMusica() {
            if (atual != null) atual = atual.proximo;
        }

        public void musicaAnterior() {
            if (atual != null) atual = atual.anterior;
        }

        public void tocarMusicaAtual() {
            if (atual != null) {
                System.out.println("🎵 Tocando: " + atual.musica);
            } else {
                System.out.println("⚠️ Playlist vazia.");
            }
        }

        public void listarMusicas() {
            if (tamanho == 0) {
                System.out.println("⚠️ Playlist vazia.");
                return;
            }

            No aux = atual;
            for (int i = 0; i < tamanho; i++) {
                System.out.println((i == 0 ? ">> " : "   ") + aux.musica);
                aux = aux.proximo;
            }
        }

        public int getTamanho() {
            return tamanho;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist playlist = new Playlist();

        while (true) {
            System.out.println("\n🎶 MENU DA PLAYLIST");
            System.out.println("1. Tocar música atual");
            System.out.println("2. Avançar para próxima música");
            System.out.println("3. Voltar para música anterior");
            System.out.println("4. Adicionar música");
            System.out.println("5. Remover música");
            System.out.println("6. Listar músicas");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    playlist.tocarMusicaAtual();
                    break;
                case 2:
                    playlist.proximaMusica();
                    playlist.tocarMusicaAtual();
                    break;
                case 3:
                    playlist.musicaAnterior();
                    playlist.tocarMusicaAtual();
                    break;
                case 4:
                    System.out.print("Nome da música: ");
                    String nome = scanner.nextLine();
                    System.out.print("Duração (em segundos): ");
                    int duracao = scanner.nextInt();
                    System.out.print("Posição para inserir (0 até " + playlist.getTamanho() + "): ");
                    int posicao = scanner.nextInt();
                    scanner.nextLine();
                    playlist.adicionarMusica(new Musica(nome, duracao), posicao);
                    break;
                case 5:
                    System.out.print("Posição da música a remover (0 até " + (playlist.getTamanho() - 1) + "): ");
                    int posRemover = scanner.nextInt();
                    scanner.nextLine();
                    playlist.removerMusica(posRemover);
                    break;
                case 6:
                    playlist.listarMusicas();
                    break;
                case 7:
                    System.out.println("🎧 Encerrando...");
                    scanner.close();
                    return;
                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        }
    }
}
