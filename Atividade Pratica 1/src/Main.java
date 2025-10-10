
void main() {
    ArrayList<DirectedGraph> graphs = ReadGraphFile.getGraphs("res/LesMiserables.gexf");
    DirectedGraph graph = graphs.get(0);
    graph.printGraph();
}
