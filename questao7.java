
import java.util.*;

public class questao7 {

    interface Operacao {
        void desfazer(StringBuilder texto);
        void refazer(StringBuilder texto);
    }

    static class AdicionarTexto implements Operacao {
        String textoAdicionado;
        int posicao;

        public AdicionarTexto(String textoAdicionado, int posicao) {
            this.textoAdicionado = textoAdicionado;
            this.posicao = posicao;
        }

        public void desfazer(StringBuilder texto) {
            texto.delete(posicao, posicao + textoAdicionado.length());
        }

        public void refazer(StringBuilder texto) {
            texto.insert(posicao, textoAdicionado);
        }
    }

    static class RemoverTexto implements Operacao {
        String textoRemovido;
        int posicao;

        public RemoverTexto(String textoRemovido, int posicao) {
            this.textoRemovido = textoRemovido;
            this.posicao = posicao;
        }

        public void desfazer(StringBuilder texto) {
            texto.insert(posicao, textoRemovido);
        }

        public void refazer(StringBuilder texto) {
            texto.delete(posicao, posicao + textoRemovido.length());
        }
    }

    static class SubstituirTexto implements Operacao {
        String antigo;
        String novo;
        int posicao;

        public SubstituirTexto(String antigo, String novo, int posicao) {
            this.antigo = antigo;
            this.novo = novo;
            this.posicao = posicao;
        }

        public void desfazer(StringBuilder texto) {
            texto.replace(posicao, posicao + novo.length(), antigo);
        }

        public void refazer(StringBuilder texto) {
            texto.replace(posicao, posicao + antigo.length(), novo);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder texto = new StringBuilder();
        Stack<Operacao> desfazer = new Stack<>();
        Stack<Operacao> refazer = new Stack<>();

        while (true) {
            System.out.println("\nTexto atual: \"" + texto + "\"");
            System.out.println("1. Adicionar texto");
            System.out.println("2. Remover texto");
            System.out.println("3. Substituir texto");
            System.out.println("4. Desfazer");
            System.out.println("5. Refazer");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o texto a adicionar: ");
                    String adicionado = scanner.nextLine();
                    int posAdd = texto.length(); // adiciona no fim
                    texto.append(adicionado);
                    desfazer.push(new AdicionarTexto(adicionado, posAdd));
                    refazer.clear();
                    break;

                case 2:
                    System.out.print("Digite a posição inicial para remover: ");
                    int inicio = scanner.nextInt();
                    System.out.print("Digite o número de caracteres a remover: ");
                    int tam = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer

                    if (inicio >= 0 && inicio + tam <= texto.length()) {
                        String removido = texto.substring(inicio, inicio + tam);
                        texto.delete(inicio, inicio + tam);
                        desfazer.push(new RemoverTexto(removido, inicio));
                        refazer.clear();
                    } else {
                        System.out.println("Posição inválida.");
                    }
                    break;

                case 3:
                    System.out.print("Digite a posição do texto a substituir: ");
                    int pos = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o texto antigo: ");
                    String antigo = scanner.nextLine();
                    System.out.print("Digite o texto novo: ");
                    String novo = scanner.nextLine();

                    if (pos >= 0 && pos + antigo.length() <= texto.length()
                            && texto.substring(pos, pos + antigo.length()).equals(antigo)) {
                        texto.replace(pos, pos + antigo.length(), novo);
                        desfazer.push(new SubstituirTexto(antigo, novo, pos));
                        refazer.clear();
                    } else {
                        System.out.println("Texto na posição não confere com o antigo.");
                    }
                    break;

                case 4:
                    if (!desfazer.isEmpty()) {
                        Operacao op = desfazer.pop();
                        op.desfazer(texto);
                        refazer.push(op);
                    } else {
                        System.out.println("Nada para desfazer.");
                    }
                    break;

                case 5:
                    if (!refazer.isEmpty()) {
                        Operacao op = refazer.pop();
                        op.refazer(texto);
                        desfazer.push(op);
                    } else {
                        System.out.println("Nada para refazer.");
                    }
                    break;

                case 6:
                    System.out.println("Encerrando.");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
