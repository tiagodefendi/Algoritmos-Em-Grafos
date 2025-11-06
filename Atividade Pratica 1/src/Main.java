import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Caminho do arquivo
        String filePath = "res/LesMiserables.gexf";

        // Monta o grafo
        Graph graph = ReadGraphFile.readGEXF(filePath);

        // Imprime o grafo
        graph.printGraph();

        // Eccentricity
        Map<String, Integer> eccentricities = Eccentricity.calculate(graph);
        System.out.println("\nEccentricity:");
        for (String node : eccentricities.keySet()) {
            System.out.println(node + " -> " + eccentricities.get(node));
        }

        // Salva em arquivo .txt
        saveToFile("eccentricity.txt", eccentricities);

        // Closeness Centrality
        Map<String, Double> closeness = ClosenessCentrality.calculate(graph);
        System.out.println("\nCloseness Centrality:");
        for (String node : closeness.keySet()) {
            System.out.println(node + " -> " + closeness.get(node));
        }

        // Salva em arquivo .txt
        saveToFile("closeness_centrality.txt", closeness);
    }

    // salvando os resultados em .txt
    private static <T> void saveToFile(String filename, Map<String, T> values) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String node : values.keySet()) {
                writer.write(node + " -> " + values.get(node) + "\n");
            }
            System.out.println("✅ Arquivo salvo: " + filename);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo " + filename + ": " + e.getMessage());
        }
    }
}
