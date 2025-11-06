import java.util.*;

/*
 * A Betweenness Centrality mede o quanto um vértice atua como "ponte" entre outros vértices.
 * Ela é calculada considerando o número de caminhos mínimos que passam por cada vértice.
 *
 * Fórmula:
 * CB(v) = Σ (σ_st(v) / σ_st), para todos os pares s ≠ v ≠ t
 * onde:
 *  - σ_st é o número total de caminhos mínimos entre s e t
 *  - σ_st(v) é o número desses caminhos que passam por v
 *
 * Implementação baseada no algoritmo de Brandes (2001).
 */
public class Betweenness {

    public static Map<String, Double> calculate(Graph graph) {
        Map<String, Double> betweenness = new HashMap<>();
        for (String v : graph.getNodes()) {
            betweenness.put(v, 0.0);
        }

        for (String s : graph.getNodes()) {
            Stack<String> stack = new Stack<>();
            Map<String, List<String>> predecessors = new HashMap<>();
            Map<String, Integer> distance = new HashMap<>();
            Map<String, Double> sigma = new HashMap<>();
            Queue<String> queue = new LinkedList<>();

            // Inicialização
            for (String v : graph.getNodes()) {
                predecessors.put(v, new ArrayList<>());
                distance.put(v, -1);
                sigma.put(v, 0.0);
            }
            distance.put(s, 0);
            sigma.put(s, 1.0);
            queue.add(s);

            // BFS
            while (!queue.isEmpty()) {
                String v = queue.poll();
                stack.push(v);
                for (String neighbor : graph.getNeighbors(v)) {
                    if (distance.get(neighbor) < 0) {
                        queue.add(neighbor);
                        distance.put(neighbor, distance.get(v) + 1);
                    }
                    if (distance.get(neighbor) == distance.get(v) + 1) {
                        sigma.put(neighbor, sigma.get(neighbor) + sigma.get(v));
                        predecessors.get(neighbor).add(v);
                    }
                }
            }

            // Acumula dependências
            Map<String, Double> delta = new HashMap<>();
            for (String v : graph.getNodes()) {
                delta.put(v, 0.0);
            }

            while (!stack.isEmpty()) {
                String w = stack.pop();
                for (String v : predecessors.get(w)) {
                    double c = (sigma.get(v) / sigma.get(w)) * (1.0 + delta.get(w));
                    delta.put(v, delta.get(v) + c);
                }
                if (!w.equals(s)) {
                    betweenness.put(w, betweenness.get(w) + delta.get(w));
                }
            }
        }

        // Normaliza (opcional para grafos não direcionados)
        for (String v : betweenness.keySet()) {
            betweenness.put(v, betweenness.get(v) / 2.0);
        }

        return betweenness;
    }

    public static void printBetweenness(Graph graph) {
        Map<String, Double> bet = calculate(graph);
        System.out.println("\nBetweenness Centrality:");
        for (String node : bet.keySet()) {
            System.out.println(node + " -> " + bet.get(node));
        }
    }
}
