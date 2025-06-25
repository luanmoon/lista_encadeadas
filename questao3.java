 // foi usada a lista duplamente encadeada pois ela permite que cada nó tenha referência para o próximo e anterior.
 
 
 
 import java.util.Scanner;

public class questao3 {

    static class Item {
        String nome;
        int quantidade;
        double precoUnitario;

        public Item(String nome, int quantidade, double precoUnitario) {
            this.nome = nome;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }

        @Override
        public String toString() {
            return nome + " - Quantidade: " + quantidade + ", Preço Unitário: R$" + precoUnitario;
        }
    }

    static class No {
        Item item;
        No anterior, proximo;

        public No(Item item) {
            this.item = item;
        }
    }

    static class Carrinho {
        No inicio = null;
        No atual = null;

        public void adicionarItem(String nome, int quantidade, double precoUnitario) {
            Item novoItem = new Item(nome, quantidade, precoUnitario);
            No novoNo = new No(novoItem);

            if (inicio == null) {
                inicio = novoNo;
                atual = novoNo;
            } else {
                No temp = inicio;
                while (temp.proximo != null) {
                    temp = temp.proximo;
                }
                temp.proximo = novoNo;
                novoNo.anterior = temp;
            }

            System.out.println("Item adicionado com sucesso!");
        }

        public void removerItem(String nome) {
            No temp = inicio;

            while (temp != null) {
                if (temp.item.nome.equalsIgnoreCase(nome)) {
                    if (temp.anterior != null) {
                        temp.anterior.proximo = temp.proximo;
                    } else {
                        inicio = temp.proximo;
                    }

                    if (temp.proximo != null) {
                        temp.proximo.anterior = temp.anterior;
                    }

                    if (atual == temp) {
                        atual = temp.proximo != null ? temp.proximo : temp.anterior;
                    }

                    System.out.println("Item removido com sucesso!");
                    return;
                }
                temp = temp.proximo;
            }
            System.out.println("Item não encontrado.");
        }

        public void proximoItem() {
            if (atual != null && atual.proximo != null) {
                atual = atual.proximo;
                System.out.println("Item atual: " + atual.item);
            } else {
                System.out.println("Fim do carrinho.");
            }
        }

        public void itemAnterior() {
            if (atual != null && atual.anterior != null) {
                atual = atual.anterior;
                System.out.println("Item atual: " + atual.item);
            } else {
                System.out.println("Início do carrinho.");
            }
        }

        public void mostrarCarrinho() {
            No temp = inicio;
            System.out.println("\n--- Itens no Carrinho ---");
            while (temp != null) {
                System.out.println(temp.item);
                temp = temp.proximo;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();
        int opcao;

        do {
            System.out.println("\n1. Adicionar item");
            System.out.println("2. Remover item");
            System.out.println("3. Próximo item");
            System.out.println("4. Item anterior");
            System.out.println("5. Mostrar carrinho");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do item: ");
                    String nome = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int qtd = scanner.nextInt();
                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();
                    carrinho.adicionarItem(nome, qtd, preco);
                    break;
                case 2:
                    System.out.print("Nome do item para remover: ");
                    String nomeRemover = scanner.nextLine();
                    carrinho.removerItem(nomeRemover);
                    break;
                case 3:
                    carrinho.proximoItem();
                    break;
                case 4:
                    carrinho.itemAnterior();
                    break;
                case 5:
                    carrinho.mostrarCarrinho();
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 6);

        scanner.close();
    }
}



