package UnrolledLinkedList;
public class UnrolledLinkedList {

    private static final int NODE_CAPACITY = 4;

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
    private int size;
    public UnrolledLinkedList() {
        this.head = null;
        this.size = 0;
    }


    public void insertValue(int value) {
        if (head == null) {
            head = new Node();
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        if (current.numElements == NODE_CAPACITY) {
            Node newNode = new Node();
            current.next = newNode;
            current = newNode;
        }

        current.elements[current.numElements] = value;
        current.numElements++;
        size++;
    }

    public boolean deleteValue(int value) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            for (int i = 0; i < current.numElements; i++) {
                if (current.elements[i] == value) {

                    for (int j = i; j < current.numElements - 1; j++) {
                        current.elements[j] = current.elements[j + 1];
                    }
                    current.numElements--;
                    size--;


                    if (current.numElements == 0) {
                        if (previous == null) {
                            head = current.next;
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

        return false;
    }


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


    public void printList() {
        if (head == null) {
            System.out.println("Lista vazia");
            return;
        }

        Node current = head;

        while (current != null) {
            for (int i = 0; i < current.numElements; i++) {
                System.out.print(current.elements[i]);

                boolean temProximoNoMesmoBloco = i < current.numElements - 1;
                boolean temProximoBloco = current.next != null;

                if (temProximoNoMesmoBloco || temProximoBloco) {
                    System.out.print(" <~> ");
                }
            }
            current = current.next;
        }
    }


    public void printBlocks() {
        Node current = head;
        int blockIndex = 0;

        while (current != null) {
            System.out.print("Bloco " + blockIndex + ": [");

            for (int i = 0; i < current.numElements; i++) {
                System.out.print(current.elements[i]);

                if (i < current.numElements - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println("]");

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
