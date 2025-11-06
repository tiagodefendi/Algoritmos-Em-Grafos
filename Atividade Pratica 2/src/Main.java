import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Caminho do arquivo GEXF
        String filePath = "res/LesMiserables.gexf";

        // Monta o grafo
        Graph graph = ReadGraphFile.readGEXF(filePath);
        graph.printGraph();

        // Calcula e imprime a Betweenness Centrality
        Map<String, Double> betweenness = Betweenness.calculate(graph);
        System.out.println("\nBetweenness Centrality:");
        for (String node : betweenness.keySet()) {
            System.out.println(node + " -> " + betweenness.get(node));
        }

        // Salva em arquivo de saída
        saveToFile("saida.txt", betweenness);
    }

    private static <T> void saveToFile(String filename, Map<String, T> values) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String node : values.keySet()) {
                writer.write(node + " -> " + values.get(node) + "\n");
            }
            System.out.println("Arquivo salvo: " + filename);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo " + filename + ": " + e.getMessage());
        }
    }
}
