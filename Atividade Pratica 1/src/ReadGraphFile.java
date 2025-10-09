import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadGraphFile {
    public static void getGraph(String filename) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            File file = new File(filename); // Abre o arquivo XML
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            // Pega todos os grafos dentro do arquivo
            NodeList graphs = doc.getElementsByTagName("graph");

            for (int g = 0; g < graphs.getLength(); g++) {
                Element graph = (Element) graphs.item(g);
                System.out.println("Graph " + (g + 1) + ":");

                // Pega os nós dentro deste grafo
                NodeList nodeList = graph.getElementsByTagName("node");
                System.out.println("  Nodes:");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element node = (Element) nodeList.item(i);
                    String id = node.getAttribute("id");
                    String label = node.getAttribute("label");
                    System.out.println("    id=" + id + ", label=" + label);
                }

                // Pega as arestas dentro deste grafo
                NodeList edgeList = graph.getElementsByTagName("edge");
                System.out.println("  Edges:");
                for (int i = 0; i < edgeList.getLength(); i++) {
                    Element edge = (Element) edgeList.item(i);
                    String id = edge.getAttribute("id");
                    String source = edge.getAttribute("source");
                    String target = edge.getAttribute("target");
                    System.out.println("    id=" + id + ", source=" + source + ", target=" + target);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
