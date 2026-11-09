import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tree arvore = new Tree();
        int opcao;

        do {
            System.out.println("\n===== ARVORE DE BUSCA BINARIA =====");
            System.out.println("1 - Inserir valor");
            System.out.println("2 - Exibir percurso pre-ordem");
            System.out.println("3 - Exibir percurso em-ordem");
            System.out.println("4 - Exibir percurso pos-ordem");
            System.out.println("5 - Excluir valor");
            System.out.println("6 - Buscar valor");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Digite um numero valido: ");
                scanner.next();
            }
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Informe o valor para inserir: ");
                    int valorInserir = lerInteiro(scanner);
                    if (arvore.inserir(valorInserir)) {
                        System.out.println("Valor inserido com sucesso.");
                    } else {
                        System.out.println("Esse valor ja existe na arvore.");
                    }
                    break;

                case 2:
                    System.out.println("Pre-ordem: " + arvore.preOrdem());
                    break;

                case 3:
                    System.out.println("Em-ordem: " + arvore.emOrdem());
                    break;

                case 4:
                    System.out.println("Pos-ordem: " + arvore.posOrdem());
                    break;

                case 5:
                    System.out.print("Informe o valor para excluir: ");
                    int valorExcluir = lerInteiro(scanner);
                    if (arvore.excluir(valorExcluir)) {
                        System.out.println("Valor excluido com sucesso.");
                    } else {
                        System.out.println("Valor nao encontrado na arvore.");
                    }
                    break;

                case 6:
                    System.out.print("Informe o valor para buscar: ");
                    int valorBuscar = lerInteiro(scanner);
                    if (arvore.buscar(valorBuscar)) {
                        System.out.println("Valor encontrado na arvore.");
                    } else {
                        System.out.println("Valor nao encontrado na arvore.");
                    }
                    break;

                case 0:
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um numero inteiro valido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
