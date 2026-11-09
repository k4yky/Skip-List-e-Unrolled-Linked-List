# Árvore Binária de Busca (Java)

Implementação de uma árvore binária de busca com todos os algoritmos iterativos, sem recursividade.

## Arquivos

- `Node.java`: representa um nó, com valor, filho esquerdo e filho direito.
- `Tree.java`: implementa inserção, busca, exclusão e percursos.
- `Main.java`: menu interativo para executar e testar as operações.

## Funcionalidades

- Inserção de valores (duplicatas são rejeitadas).
- Busca de valores.
- Percurso pré-ordem: raiz, esquerda, direita.
- Percurso em-ordem: esquerda, raiz, direita.
- Percurso pós-ordem: esquerda, direita, raiz.
- Exclusão de um nó por operação, considerando nó folha, nó com um filho e nó com dois filhos.

Os percursos utilizam pilhas explícitas (`Deque`). A inserção, a busca e a remoção também são implementadas com laços, sem chamadas recursivas.

## Como executar

Abra o terminal nesta pasta e execute:

```bash
javac Node.java Tree.java Main.java
java Main
```

## Exemplo de teste

Insira, nesta ordem, os valores: `50`, `30`, `70`, `20`, `40`, `60`, `80`.

Resultados esperados:

- Pré-ordem: `[50, 30, 20, 40, 70, 60, 80]`
- Em-ordem: `[20, 30, 40, 50, 60, 70, 80]`
- Pós-ordem: `[20, 40, 30, 60, 80, 70, 50]`
