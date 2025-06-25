// Foi usada lista duplamente encadeada pois ela permite que cada nó tenha referência para o próximo e anterior.




import java.util.Scanner;

public class questao5 {

    static class Pagina {
        String url;
        String titulo;
        Pagina anterior, proxima;

        public Pagina(String url, String titulo) {
            this.url = url;
            this.titulo = titulo;
            this.anterior = null;
            this.proxima = null;
        }

        public String toString() {
            return "[" + titulo + " - " + url + "]";
        }
    }

    static class Navegador {
        Pagina atual;

        public void visitarNovaPagina(String url, String titulo) {
            Pagina nova = new Pagina(url, titulo);
            if (atual != null) {
                atual.proxima = nova;
                nova.anterior = atual;
            }
            atual = nova;
            System.out.println("Visitando nova página: " + atual);
        }

        public void voltar() {
            if (atual != null && atual.anterior != null) {
                atual = atual.anterior;
                System.out.println("Voltando para: " + atual);
            } else {
                System.out.println("Não é possível voltar.");
            }
        }

        public void avancar() {
            if (atual != null && atual.proxima != null) {
                atual = atual.proxima;
                System.out.println("Avançando para: " + atual);
            } else {
                System.out.println("Não é possível avançar.");
            }
        }

        public void paginaAtual() {
            if (atual != null) {
                System.out.println("Página atual: " + atual);
            } else {
                System.out.println("Nenhuma página visitada.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Navegador navegador = new Navegador();
        int opcao;

        do {
            System.out.println("\n1. Visitar nova página");
            System.out.println("2. Voltar");
            System.out.println("3. Avançar");
            System.out.println("4. Página atual");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite a URL: ");
                    String url = scanner.nextLine();
                    System.out.print("Digite o título: ");
                    String titulo = scanner.nextLine();
                    navegador.visitarNovaPagina(url, titulo);
                    break;
                case 2:
                    navegador.voltar();
                    break;
                case 3:
                    navegador.avancar();
                    break;
                case 4:
                    navegador.paginaAtual();
                    break;
                case 0:
                    System.out.println("Encerrando navegador.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
