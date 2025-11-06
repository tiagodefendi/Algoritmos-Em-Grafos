import java.util.*;

public class Graph {
    private Map<String, List<String>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    // Adiciona um nó ao grafo (se ainda não existir)
    public void addNode(String id) {
        adjList.putIfAbsent(id, new ArrayList<>());
    }

    // Adiciona aresta (não direcionada)
    public void addEdge(String source, String target) {
        addNode(source);
        addNode(target);
        adjList.get(source).add(target);
        adjList.get(target).add(source);
    }

    // Retorna vizinhos de um nó
    public List<String> getNeighbors(String id) {
        return adjList.getOrDefault(id, new ArrayList<>());
    }

    // Retorna todos os nós do grafo
    public Set<String> getNodes() {
        return adjList.keySet();
    }

    // Imprime o grafo
    public void printGraph() {
        System.out.println("Lista de adjacência do grafo:\n");
        for (String node : adjList.keySet()) {
            System.out.println(node + " -> " + adjList.get(node));
        }
    }
}
