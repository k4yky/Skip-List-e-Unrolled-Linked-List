import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree {
    private Node raiz;

    public Tree() {
        raiz = null;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    // Insere um valor sem permitir duplicatas.
    public boolean inserir(int valor) {
        Node novo = new Node(valor);

        if (raiz == null) {
            raiz = novo;
            return true;
        }

        Node atual = raiz;
        Node pai = null;

        while (atual != null) {
            pai = atual;

            if (valor < atual.getValor()) {
                atual = atual.getEsquerda();
            } else if (valor > atual.getValor()) {
                atual = atual.getDireita();
            } else {
                return false;
            }
        }

        if (valor < pai.getValor()) {
            pai.setEsquerda(novo);
        } else {
            pai.setDireita(novo);
        }

        return true;
    }

    // Busca iterativamente um valor.
    public boolean buscar(int valor) {
        Node atual = raiz;

        while (atual != null) {
            if (valor == atual.getValor()) {
                return true;
            }

            if (valor < atual.getValor()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        return false;
    }

    // Pré-ordem: raiz, esquerda, direita.
    public List<Integer> preOrdem() {
        List<Integer> resultado = new ArrayList<>();

        if (raiz == null) {
            return resultado;
        }

        Deque<Node> pilha = new ArrayDeque<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            Node atual = pilha.pop();
            resultado.add(atual.getValor());

            if (atual.getDireita() != null) {
                pilha.push(atual.getDireita());
            }
            if (atual.getEsquerda() != null) {
                pilha.push(atual.getEsquerda());
            }
        }

        return resultado;
    }

    // Em-ordem: esquerda, raiz, direita.
    public List<Integer> emOrdem() {
        List<Integer> resultado = new ArrayList<>();
        Deque<Node> pilha = new ArrayDeque<>();
        Node atual = raiz;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.getEsquerda();
            }

            atual = pilha.pop();
            resultado.add(atual.getValor());
            atual = atual.getDireita();
        }

        return resultado;
    }

    // Pós-ordem: esquerda, direita, raiz.
    public List<Integer> posOrdem() {
        List<Integer> resultado = new ArrayList<>();
        Deque<Node> pilha = new ArrayDeque<>();
        Node atual = raiz;
        Node ultimoVisitado = null;

        while (atual != null || !pilha.isEmpty()) {
            if (atual != null) {
                pilha.push(atual);
                atual = atual.getEsquerda();
            } else {
                Node topo = pilha.peek();

                if (topo.getDireita() != null
                        && ultimoVisitado != topo.getDireita()) {
                    atual = topo.getDireita();
                } else {
                    resultado.add(topo.getValor());
                    ultimoVisitado = pilha.pop();
                }
            }
        }

        return resultado;
    }

    // Exclui um nó por chamada, sem recursividade.
    public boolean excluir(int valor) {
        Node atual = raiz;
        Node pai = null;

        // Localiza o nó e seu pai.
        while (atual != null && atual.getValor() != valor) {
            pai = atual;

            if (valor < atual.getValor()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        if (atual == null) {
            return false;
        }

        // Caso 1 ou 2: nó folha ou com apenas um filho.
        if (atual.getEsquerda() == null || atual.getDireita() == null) {
            Node filho;

            if (atual.getEsquerda() != null) {
                filho = atual.getEsquerda();
            } else {
                filho = atual.getDireita();
            }

            if (pai == null) {
                raiz = filho;
            } else if (pai.getEsquerda() == atual) {
                pai.setEsquerda(filho);
            } else {
                pai.setDireita(filho);
            }

            return true;
        }

        // Caso 3: dois filhos. Encontra o sucessor em-ordem.
        Node paiSucessor = atual;
        Node sucessor = atual.getDireita();

        while (sucessor.getEsquerda() != null) {
            paiSucessor = sucessor;
            sucessor = sucessor.getEsquerda();
        }

        // Substitui o valor e remove o sucessor da posição original.
        atual.setValor(sucessor.getValor());

        if (paiSucessor == atual) {
            paiSucessor.setDireita(sucessor.getDireita());
        } else {
            paiSucessor.setEsquerda(sucessor.getDireita());
        }

        return true;
    }
}
