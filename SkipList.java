import java.util.Random;

public class SkipList {

    private static final int MAX_LEVEL = 16;
    private static final double PROBABILITY = 0.5;

    private int currentLevel;
    private Node head;
    private Random random;

    private static class Node {
        int value;
        Node[] next;

        Node(int value, int level) {
            this.value = value;
            this.next = new Node[level + 1];
        }
    }

    public SkipList() {
        this.head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
        this.currentLevel = 0;
        this.random = new Random();
    }

    private int randomLevel() {
        int level = 0;
        while (random.nextDouble() < PROBABILITY && level < MAX_LEVEL - 1) {
            level++;
        }
        return level;
    }

    public void insert(int value) {
        Node[] update = new Node[MAX_LEVEL];
        Node current = head;

        for (int i = currentLevel; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
            update[i] = current;
        }

        current = current.next[0];

        if (current != null && current.value == value) {
            System.out.println("Value " + value + " already exists, skipping insert.");
            return;
        }

        int newLevel = randomLevel();

        if (newLevel > currentLevel) {
            for (int i = currentLevel + 1; i <= newLevel; i++) {
                update[i] = head;
            }
            currentLevel = newLevel;
        }

        Node newNode = new Node(value, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    public boolean search(int value) {
        Node current = head;

        for (int i = currentLevel; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
        }

        current = current.next[0];

        return current != null && current.value == value;
    }

    public boolean remove(int value) {
        Node[] update = new Node[MAX_LEVEL];
        Node current = head;

        for (int i = currentLevel; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
            update[i] = current;
        }

        current = current.next[0];

        if (current == null || current.value != value) {
            return false;
        }

        for (int i = 0; i <= currentLevel; i++) {
            if (update[i].next[i] != current) {
                break;
            }
            update[i].next[i] = current.next[i];
        }

        while (currentLevel > 0 && head.next[currentLevel] == null) {
            currentLevel--;
        }

        return true;
    }

    public void print() {
        System.out.println("---- Skip List Structure ----");
        for (int i = currentLevel; i >= 0; i--) {
            Node node = head.next[i];
            StringBuilder sb = new StringBuilder("Level " + i + ": ");
            while (node != null) {
                sb.append(node.value).append(" -> ");
                node = node.next[i];
            }
            sb.append("null");
            System.out.println(sb);
        }
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        SkipList list = new SkipList();

        int[] values = {3, 6, 7, 9, 12, 19, 17, 26, 21, 25};
        for (int v : values) {
            list.insert(v);
        }

        list.print();

        System.out.println("Search 19: " + list.search(19));
        System.out.println("Search 100: " + list.search(100));

        System.out.println("Removing 19...");
        list.remove(19);
        System.out.println("Search 19 after removal: " + list.search(19));

        list.print();
    }
}
