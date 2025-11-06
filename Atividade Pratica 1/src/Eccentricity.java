/*
 * A excentricidade de um vértice é a maior distância entre ele e qualquer outro vértice do grafo.
 * Ela mostra o "pior caso" de quão distante um vértice está dos outros,
 * podendo ser usada para medir o quão central ou periférico um vértice é.
 */
import java.util.*;


public class Eccentricity {

    // Calcula a excentricidade de todos os vértices
    public static Map<String, Integer> calculate(Graph graph) {
        Map<String, Integer> eccentricities = new HashMap<>();

        for (String node : graph.getNodes()) {
            Map<String, Integer> distances = bfs(graph, node);
            int maxDist = 0;

            for (int d : distances.values()) {
                if (d > maxDist) {
                    maxDist = d;
                }
            }

            eccentricities.put(node, maxDist);
        }

        return eccentricities;
    }

    // BFS para calcular distâncias mínimas
    private static Map<String, Integer> bfs(Graph graph, String start) {
        Map<String, Integer> dist = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        dist.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = dist.get(current);

            for (String neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    dist.put(neighbor, currentDist + 1);
                }
            }
        }

        return dist;
    }

    // Imprime as excentricidades
    public static void printEccentricities(Graph graph) {
        Map<String, Integer> ecc = calculate(graph);
        System.out.println("\nEccentricity:");
        for (String node : ecc.keySet()) {
            System.out.println(node + " -> " + ecc.get(node));
        }
    }
}
