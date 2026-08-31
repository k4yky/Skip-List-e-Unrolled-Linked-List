/**
 * Implementacao de uma Unrolled Linked List em Java.
 *
 * Diferente de uma lista encadeada comum (um valor por no), cada no de uma
 * Unrolled Linked List guarda um pequeno array (bloco) de varios elementos.
 * Isso melhora a localidade de cache e reduz o overhead de memoria dos
 * ponteiros "next"/"previous" por elemento, sendo um meio-termo entre uma
 * lista encadeada e um array.
 */
public class UnrolledLinkedList {

    private static final int NODE_CAPACITY = 4; // capacidade maxima de elementos por no

    /**
     * No da lista. Guarda um bloco (array) de elementos, quantos deles
     * estao efetivamente em uso (numElements) e a referencia para o proximo no.
     */
    private static class Node {
        int[] elements;
        int numElements;
        Node next;

        Node() {
            this.elements = new int[NODE_CAPACITY];
            this.numElements = 0;
            this.next = null;
        }
    }

    private Node head;
    private int size; // quantidade total de elementos na lista (somando todos os nos)

    public UnrolledLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Insere um valor no final da lista.
     * Se o ultimo no estiver cheio, cria um novo no.
     */
    public void insertValue(int value) {
        if (head == null) {
            head = new Node();
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        // se o no atual esta cheio, cria um novo no e encadeia
        if (current.numElements == NODE_CAPACITY) {
            Node newNode = new Node();
            current.next = newNode;
            current = newNode;
        }

        current.elements[current.numElements] = value;
        current.numElements++;
        size++;
    }

    /**
     * Remove a primeira ocorrencia do valor na lista.
     * Lida com blocos ficando vazios (removendo o no da cadeia)
     * e com lista vazia.
     *
     * @return true se o valor foi encontrado e removido.
     */
    public boolean deleteValue(int value) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            for (int i = 0; i < current.numElements; i++) {
                if (current.elements[i] == value) {

                    // desloca os elementos seguintes uma posicao para a esquerda,
                    // preenchendo o "buraco" deixado pelo valor removido
                    for (int j = i; j < current.numElements - 1; j++) {
                        current.elements[j] = current.elements[j + 1];
                    }
                    current.numElements--;
                    size--;

                    // se o no ficou vazio, remove ele da cadeia
                    if (current.numElements == 0) {
                        if (previous == null) {
                            head = current.next; // era o primeiro no
                        } else {
                            previous.next = current.next;
                        }
                    }

                    return true;
                }
            }
            previous = current;
            current = current.next;
        }

        return false; // valor nao encontrado
    }

    /**
     * Busca um valor na lista.
     */
    public boolean search(int value) {
        Node current = head;
        while (current != null) {
            for (int i = 0; i < current.numElements; i++) {
                if (current.elements[i] == value) {
                    return true;
                }
            }
            current = current.next;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    /**
     * Imprime todos os elementos da lista em sequencia, ignorando
     * a divisao interna em blocos (visao "logica" da lista).
     */
    public void printList() {
        Node current = head;
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        while (current != null) {
            for (int i = 0; i < current.numElements; i++) {
                if (!first) {
                    sb.append(" <~> ");
                }
                sb.append(current.elements[i]);
                first = false;
            }
            current = current.next;
        }

        System.out.println(sb.length() == 0 ? "Lista vazia" : sb.toString());
    }

    /**
     * Imprime a lista mostrando a divisao em blocos (nos),
     * util para visualizar a estrutura interna.
     */
    public void printBlocks() {
        Node current = head;
        int blockIndex = 0;

        while (current != null) {
            StringBuilder sb = new StringBuilder("Bloco " + blockIndex + ": [");
            for (int i = 0; i < current.numElements; i++) {
                sb.append(current.elements[i]);
                if (i < current.numElements - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            System.out.println(sb.toString());
            current = current.next;
            blockIndex++;
        }
    }

    public static void main(String[] args) {
        UnrolledLinkedList list = new UnrolledLinkedList();

        int[] values = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for (int v : values) {
            list.insertValue(v);
        }

        System.out.println("Lista completa:");
        list.printList();

        System.out.println("\nDivisao em blocos (capacidade = " + NODE_CAPACITY + "):");
        list.printBlocks();

        System.out.println("\nTamanho: " + list.getSize());

        System.out.println("\nRemovendo 30...");
        list.deleteValue(30);
        list.printList();
        list.printBlocks();

        System.out.println("\nBuscando 70: " + list.search(70));
        System.out.println("Buscando 100: " + list.search(100));
    }
}
