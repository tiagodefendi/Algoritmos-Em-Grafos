import java.util.ArrayList;

public class Graph {
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public ArrayList<Node> getNodes() {
        return this.nodes;
    }
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}
