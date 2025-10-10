import java.util.ArrayList;

public class DirectedGraph {
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public DirectedGraph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public ArrayList<Node> getNodes() {
        return this.nodes;
    }
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }
    public Node getNode(float id) {
        for (Node node : this.nodes) {
            if(node.getId() == id) {
                return node;
            }
        }
        return null;
    }
    public Edge getEdge(float id) {
        for (Edge edge : this.edges) {
            if(edge.getId() == id) {
                return edge;
            }
        }
        return null;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}
