// foi usada a lista duplamente ligada pois era necessária apenas a remoção dos items, sem necessitar de mover os elementos dentro da lista



import java.util.Scanner;

public class questao6 {

    static class Pedido {
        int numero;
        String cliente;
        double valor;

        Pedido proximo;
        Pedido anterior;

        public Pedido(int numero, String cliente, double valor) {
            this.numero = numero;
            this.cliente = cliente;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return "Pedido #" + numero + " - Cliente: " + cliente + " - R$" + valor;
        }
    }

    static class ListaPedidos {
        Pedido inicio;
        Pedido fim;

        public void adicionarPedido(int numero, String cliente, double valor) {
            Pedido novo = new Pedido(numero, cliente, valor);
            if (inicio == null) {
                inicio = fim = novo;
            } else {
                fim.proximo = novo;
                novo.anterior = fim;
                fim = novo;
            }
            System.out.println("Pedido adicionado com sucesso.");
        }

        public boolean cancelarPedido(int numero) {
            Pedido atual = inicio;

            while (atual != null) {
                if (atual.numero == numero) {
                    if (atual == inicio) {
                        inicio = atual.proximo;
                        if (inicio != null) {
                            inicio.anterior = null;
                        }
                    } else if (atual == fim) {
                        fim = atual.anterior;
                        fim.proximo = null;
                    } else {
                        atual.anterior.proximo = atual.proximo;
                        atual.proximo.anterior = atual.anterior;
                    }
                    System.out.println("Pedido #" + numero + " cancelado.");
                    return true;
                }
                atual = atual.proximo;
            }

            System.out.println("Pedido não encontrado.");
            return false;
        }

        public void listarPedidos() {
            if (inicio == null) {
                System.out.println("Nenhum pedido em andamento.");
                return;
            }

            Pedido atual = inicio;
            System.out.println("Histórico de pedidos:");
            while (atual != null) {
                System.out.println(atual);
                atual = atual.proximo;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaPedidos lista = new ListaPedidos();

        int opcao;
        do {
            System.out.println("\n=== Sistema de Pedidos ===");
            System.out.println("1. Fazer novo pedido");
            System.out.println("2. Cancelar pedido");
            System.out.println("3. Listar pedidos em andamento");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Número do pedido: ");
                    int numero = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome do cliente: ");
                    String cliente = sc.nextLine();
                    System.out.print("Valor do pedido: R$");
                    double valor = sc.nextDouble();
                    lista.adicionarPedido(numero, cliente, valor);
                    break;

                case 2:
                    System.out.print("Digite o número do pedido a cancelar: ");
                    int cancelar = sc.nextInt();
                    lista.cancelarPedido(cancelar);
                    break;

                case 3:
                    lista.listarPedidos();
                    break;

                case 4:
                    System.out.println("Encerrando o sistema.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 4);

        sc.close();
    }
}
