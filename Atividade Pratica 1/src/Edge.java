public class Edge {
    private final int id;
    private final Node source;
    private final Node target;

    public Edge(int id, Node source, Node target) {
        this.id = id;
        this.source = source;
        this.target = target;
    }

    public int getId() {
        return this.id;
    }
    public Node getSource() {
        return this.source;
    }
    public Node getTarget() {
        return this.target;
    }

}
