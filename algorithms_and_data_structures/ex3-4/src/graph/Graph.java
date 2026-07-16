package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author rautakoski
 */

public class Graph<V,L> implements AbstractGraph<V,L> {
    private boolean directed;
    private boolean labelled;
    private Map<V, Map<V, L>> AdjacencyList= null;
    private int edgeCount;

    /**
     * Create an empty graph and initialize the edgecount.
     * @param directed a boolean value if graph is directed or not
     * @param labelled a boolean value if graph is labelled or not
     */
    public Graph(boolean directed, boolean labelled){
        this.directed = directed;
        this.labelled = labelled;
        this.AdjacencyList = new HashMap<>();
        this.edgeCount = 0;
    } // graph

    /**
     * Add node to the graph if the graph does not contain the node already.
     * @return true if the node added, false otherwise.
     */
    @Override
    public boolean addNode(V a){
        if(this.AdjacencyList.containsKey((a))){ // node already in the graph
            return false;
        } else {
            this.AdjacencyList.put(a, new HashMap<>());
            return true;
        }
        
    } // addNode

    /**
     * Add edge to the graph if the graph contains the nodes and does not contain the edge already.
     * If the graph is labelled, add the label to the edge. If it is not labelled the label is null.
     * If the graph is not directed, add the edge also to the other direction.
     * @return true if edge is added, false otherwise.
     */
    @Override
    public boolean addEdge(V a, V b, L l){
        if(!this.AdjacencyList.containsKey(a) || !this.AdjacencyList.containsKey(b)) // one of the node missing from graph
            return false;
        if(!isLabelled())  // if graph not labelled, label is null
            l = null;
        this.AdjacencyList.get(a).put(b, l);
        if(!isDirected()) // if graph is not directed, add the edge to both directions
            this.AdjacencyList.get(b).put(a, l);
        this.edgeCount++; // one edge added
        return true;
    } // addEdge

    /**
     * @return true if the graph is directed, false otherwise.
     */
    @Override
    public boolean isDirected(){
        return this.directed;
    } // isDirected

    /**
     * @return true if the graph is labelled, false otherwise.
     */
    @Override
    public boolean isLabelled(){
        return this.labelled;
    } // isLabelled

    /**
     * @return true if the graph contains the node, false otherwise.
     */
    @Override
    public boolean containsNode(V a){
        if(this.AdjacencyList==null)
            return false;
        return this.AdjacencyList.containsKey(a);
    } // containsNode

    /**
     * @return true if the graph contains the edge, false otherwise.
     */
    @Override
    public boolean containsEdge(V a, V b){
        if(this.AdjacencyList==null || !this.AdjacencyList.containsKey(a)) // if the graph doesn't contain a, an edge from a cannot exist
            return false;
        return this.AdjacencyList.get(a).containsKey(b);
    } // containsEdge

    /**
     * Remove the node from the graph if it contains it.
     * @return true if the graph contains the node and it is removed, false otherwise.
     */
    @Override
    public boolean removeNode(V a) {
        if(!containsNode(a)){ // if the graph doesn't contain a, it cannot be removed either
            return false;
        }
        // all the edges to a has to be removed
        for(V node : this.AdjacencyList.keySet()){
            if(this.AdjacencyList.get(node).remove(a) != null)
                edgeCount--;
        }
        this.AdjacencyList.remove(a);
        return true;
    } // removeNode

    /**
     * Remove the edge from the graph if it contains it.
     * @return true if the graph contains the edge and it is removed, false otherwise.
     */
    @Override
    public boolean removeEdge(V a, V b){
        if(!containsEdge(a, b)) // if the edge doesn't exist, it cannot be removed
            return false;
        this.AdjacencyList.get(a).remove(b);
        if(!isDirected()) // if graph is not directed, remove edge also from b to a
            this.AdjacencyList.get(b).remove(a);
        edgeCount--;
        return true;
    } // removeEdge

    /**
     * @return the number of nodes in the graph
     */
    @Override
    public int numNodes(){
        return this.AdjacencyList.size();
    } // numNodes

    /**
     * @return the numer of edges in the graph
     */
    @Override
    public int numEdges(){
        return edgeCount;
    }// numEdges

    /**
     * @return the nodes of the graph
     */
    @Override
    public Collection<V> getNodes(){
        if(this.AdjacencyList==null){
            return null;
        }
        return this.AdjacencyList.keySet();
    } // getNodes

    /**
     * @return the edges of the graph
     */
    @Override
    public Collection<? extends AbstractEdge<V,L>> getEdges(){
        List<Edge<V, L>> edges = new ArrayList<>();
        for (V start : this.AdjacencyList.keySet()) {
            for (Map.Entry<V, L> end : this.AdjacencyList.get(start).entrySet()) {
                edges.add(new Edge<>(start, end.getKey(), end.getValue()));
            }
        }
        return edges;
    } // getEdges

    /**
     * @return the neighbours of the given node
     */
    @Override
    public Collection<V> getNeighbours(V a){
        if(this.AdjacencyList==null || !containsNode(a)) // if the graph doesn't contain a, there is no neighbours of a either
            return null;
        return this.AdjacencyList.get(a).keySet();
    } // getNeighbours

    /**
     * @return label of the given edge
     */
    @Override
    public L getLabel(V a, V b){
        if(this.AdjacencyList==null || !isLabelled() || !containsEdge(a, b)) // if the graph is not labelled or doesn't contain the edge from a to b, there is no label
            return null;
        return this.AdjacencyList.get(a).get(b);
    } // getLabel

    /*
     * Edge implements abstract edge.
     */
    public static class Edge<V, L> implements AbstractEdge<V, L> {
        private final V start;
        private final V end;
        private final L label;

        /**
         * Create an edge with given values.
         * @param start source node of the edge
         * @param end destination node of the edge
         * @param label label of the edge
         */
        public Edge(V start, V end, L label) {
            this.start = start;
            this.end = end;
            this.label = label;
        } // Edge

        /**
         * @return the source node of the edge
         */
        @Override
        public V getStart() {
            return start;
        } // getStart

        /**
         * @return the destination node of the edge
         */
        @Override
        public V getEnd() {
            return end;
        } //getEnd

        /**
         * @return the label of the edge
         */
        @Override
        public L getLabel() {
            return label;
        } // getLabel
    } // inner class Edge
}// class
  