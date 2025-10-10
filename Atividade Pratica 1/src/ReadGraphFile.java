import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class ReadGraphFile {
    public static ArrayList<DirectedGraph> getGraphs(String filename) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        ArrayList<DirectedGraph> graphs = new ArrayList<>();

        try {
            File file = new File(filename); // Abre o arquivo XML
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            // Pega todos os grafos dentro do arquivo
            NodeList graphList = doc.getElementsByTagName("graph");

            for (int g = 0; g < graphList.getLength(); g++) {
                Element graph = (Element) graphList.item(g);

                DirectedGraph graphObj = new DirectedGraph();

                // Pega os nós dentro deste grafo
                NodeList nodeList = graph.getElementsByTagName("node");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element node = (Element) nodeList.item(i);
                    String idStr = node.getAttribute("id");
                    float id = Float.parseFloat(idStr);
                    String label = node.getAttribute("label");
                    Node nodeObj = new Node(id, label);
                    graphObj.addNode(nodeObj);
                }

                // Pega as arestas dentro deste grafo
                NodeList edgeList = graph.getElementsByTagName("edge");
                for (int i = 0; i < edgeList.getLength(); i++) {
                    Element edge = (Element) edgeList.item(i);
                    String idStr = edge.getAttribute("id");
                    int id = Integer.parseInt(idStr);
                    String sourceStr = edge.getAttribute("source");
                    float source = Float.parseFloat(sourceStr);
                    String targetStr = edge.getAttribute("target");
                    float target = Float.parseFloat(targetStr);
                    Node nodeSource = graphObj.getNode(source);
                    Node nodeTarget = graphObj.getNode(target);
                    Edge edgeObj = new Edge(id, nodeSource, nodeTarget);
                    graphObj.addEdge(edgeObj);
                }

                graphs.add(graphObj);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return graphs;
    }
}
