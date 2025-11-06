import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadGraphFile {

    // Lê o arquivo e retorna um grafo não direcionado
    public static Graph readGEXF(String filePath) {
        Graph graph = new Graph();

        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            // Lê todos os nós
            NodeList nodeList = doc.getElementsByTagName("node");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element node = (Element) nodeList.item(i);
                String id = node.getAttribute("id");
                graph.addNode(id);
            }

            // Lê todas as arestas
            NodeList edgeList = doc.getElementsByTagName("edge");
            for (int i = 0; i < edgeList.getLength(); i++) {
                Element edge = (Element) edgeList.item(i);
                String source = edge.getAttribute("source");
                String target = edge.getAttribute("target");
                graph.addEdge(source, target);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return graph;
    }
}
