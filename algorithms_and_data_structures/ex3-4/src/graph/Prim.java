package graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import priorityqueue.*;

/**
 * 
 * @author rautakoski
 */
public class Prim{

    public static class EdgeComparator implements Comparator<AbstractEdge<?, ?>> {
        /**
         * Compares two abstract edges based on their labels.
         * @return a negative value if e1 is smaller than e2, a positive value if e1 is greater than e2, 0 if e1 and e2 are equal.
         */
        @Override
        public int compare(AbstractEdge<?, ?> e1, AbstractEdge<?, ?> e2) {
            return Double.compare(((Double) e1.getLabel()).doubleValue(), ((Double) e2.getLabel()).doubleValue());
        }
    } // inner class

    /**
     * Create a minimum spanning tree of the graph. 
     * @param <V> the type of nodes
     * @param <L> the type of edges
     * @param graph the graph 
     * @return ordered edges
     */
    public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(Graph<V,L> graph) {
        Comparator comparator = new EdgeComparator();
        PriorityQueue<AbstractEdge<V,L>> queue = new PriorityQueue<>(comparator);
        Set<V> visited = new HashSet<>();
        List<AbstractEdge<V, L>> edges = new ArrayList<>();

        // Make priority queue of edges
        for(V node : graph.getNodes()){
            if (!visited.contains(node)) { // node is not visited yet
                visited.add(node); // we are visiting node
                for (V neighbor : graph.getNeighbours(node)) { // add all the neighbours of node to queue
                    if (!visited.contains(neighbor)) {
                        queue.push(new Graph.Edge<>(node, neighbor, graph.getLabel(node, neighbor)));
                    }
                }
                while (!queue.empty()) {
                    AbstractEdge<V, L> edge = queue.top(); // get the first element of priority queue
                    queue.pop(); // remove the first element after getting it
                    V end = edge.getEnd();
                    if (!visited.contains(end)) {
                        visited.add(end);
                        edges.add(edge);
                        for (V neighbor : graph.getNeighbours(end)) { // add all the neighbours of the end to queue
                            if (!visited.contains(neighbor)) {
                                queue.push(new Graph.Edge<>(end, neighbor, graph.getLabel(end, neighbor)));
                            }
                        }
                    }
                }
            }
        }
        return edges;
    } // minimumSpanningForest

    public static void main(String[] args) {
        String path = args[0];
        Graph<String, Double> graph = new Graph<>(false, true);
        // graph.Graph(false, true); // not directed, labelled graph

        try{
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                String[] row = line.split(",");
                if (row.length != 3) continue; // skip invalid rows

                String start = row[0];
                String end = row[1];
                Double label = Double.parseDouble(row[2]);

                graph.addNode(start);
                graph.addNode(end);
                graph.addEdge(start, end, label);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Collection<? extends AbstractEdge<String, Double>> mst = minimumSpanningForest(graph);
        double totalWeight = 0.0;

        for (AbstractEdge<String, Double> edge : mst) {
            System.out.println(edge.getStart()+ "  "+ edge.getEnd());
            totalWeight += edge.getLabel();
        }

        System.out.println("Number of nodes in MST: " + graph.numNodes());
        System.out.println("Number of edges in MST: " + mst.size());
        System.out.println("Total weight of MST: " + totalWeight);

    } // main
}// class