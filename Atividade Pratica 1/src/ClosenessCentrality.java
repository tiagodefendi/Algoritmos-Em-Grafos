/*
 * A Closeness Centrality mede o quão próximo um vértice está de todos os outros em um grafo.
 * Ela é calculada como o inverso da soma das distâncias mínimas até todos os outros vértices.
 * Assim, vértices com valores maiores têm maior acessibilidade média dentro da rede.
 *
 * Fórmula normalizada:
 * C(u) = (N - 1) / Σ d(u, v)
 * onde d(u, v) é a distância mínima entre u e v.
 */
import java.util.*;

public class ClosenessCentrality {

    // Calcula Closeness Centrality de todos os nós do grafo
    public static Map<String, Double> calculate(Graph graph) {
        Map<String, Double> closeness = new HashMap<>();

        for (String node : graph.getNodes()) {
            double sumDistances = 0;
            Map<String, Integer> distances = bfs(graph, node);

            // Soma das distâncias até todos os outros nós
            for (int dist : distances.values()) {
                sumDistances += dist;
            }

            // Evita divisão por zero (isolated nodes)
            if (sumDistances > 0) {
                closeness.put(node, (distances.size() - 1) / sumDistances);
            } else {
                closeness.put(node, 0.0);
            }
        }

        return closeness;
    }

    // BFS para calcular distância mínima de um nó para todos os outros
    private static Map<String, Integer> bfs(Graph graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distances.get(current);

            for (String neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    distances.put(neighbor, currentDist + 1);
                }
            }
        }

        return distances;
    }

    // Imprime a centralidade
    public static void printCentralidade(Graph graph) {
        Map<String, Double> closeness = calculate(graph);
        System.out.println("\nCloseness Centrality:");
        for (String node : closeness.keySet()) {
            System.out.println(node + " -> " + closeness.get(node));
        }
    }

}
