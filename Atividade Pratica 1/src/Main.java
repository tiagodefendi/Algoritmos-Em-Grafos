
void main() {
    ArrayList<DirectedGraph> graphs = ReadGraphFile.getGraphs("res/LesMiserables.gexf");
    DirectedGraph graph = graphs.get(0);

    System.out.println(graph);
    System.out.println("Node:");
    for (int i = 1; i < graph.getNodes().size(); i++) {
        float id = graph.getNodes().get(i).getId();
        String label = graph.getNodes().get(i).getLabel();
        System.out.println("    id=" + id + ", label=" + label);
    }
    System.out.println("Edges:");
    for (int i = 1; i < graph.getEdges().size(); i++) {
        int id = graph.getEdges().get(i).getId();
        float source = graph.getEdges().get(i).getSource().getId();
        float target = graph.getEdges().get(i).getTarget().getId();
        System.out.println("    id=" + id + ", source=" + source + ", target=" + target);
    }
}
