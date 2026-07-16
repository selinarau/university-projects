package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import graph.Graph.Edge;

/**
 * Unit tests to graph functions
 * @author rautakoski
 */

public class GraphTests {
    
    private Integer i1, i2, i3;
    private Graph<Integer,Integer> graph;

    @Before
    public void createGraph(){
        i1 = 0;
        i2 = 2;
        i3 = 7;
    } // createGraph

    @Test
    public void testDirectedLabelled(){
        graph = new Graph<>(true,true);
        assertTrue(graph.isDirected() && graph.isLabelled());
    }

    @Test
    public void testNotDirectedNotLabelled(){
        graph = new Graph<>(false,false);
        assertTrue(!graph.isDirected() && !graph.isLabelled());
    }

    @Test
    public void testContainsNode_zeroEl(){
        graph = new Graph<>(true,true);
        assertFalse(graph.containsNode(i1));
    }

    @Test
    public void testAddContainsNode_oneEl(){
        graph = new Graph<>(true,true);
        boolean added = graph.addNode(i1);
        assertTrue(added && graph.containsNode(i1));
    }

    @Test
    public void testContainsEdge_zeroEl(){
        graph = new Graph<>(true,true);
        assertFalse(graph.containsEdge(i1, i2));
    }

    @Test
    public void testAddContainsEdge_DirectedLabeled(){
        graph = new Graph<>(true,true);
        boolean added_node1 = graph.addNode(i1);
        boolean added_node2 = graph.addNode(i2);
        boolean added_edge = graph.addEdge(i1, i2, i3);
        assertTrue(added_node1 && added_node2 && added_edge //nodes and edge added
                    && graph.containsEdge(i1,i2) // edge in graph
                    && graph.getLabel(i1, i2) == i3); // label as expected
    }

    @Test
    public void testAddContainsEdge_NotDirectedNotLabeled(){
        graph = new Graph<>(false,false);
        boolean added_node1 = graph.addNode(i1);
        boolean added_node2 = graph.addNode(i2);
        boolean added_edge = graph.addEdge(i1, i2, i3);
        assertTrue(added_node1 && added_node2 && added_edge // nodes and edge added
                    && graph.containsEdge(i1,i2) && graph.containsEdge(i2, i1) // edge in graph to both directions
                    && graph.getLabel(i1, i2) == null); // label null
    }

    @Test
    public void testAddRemoveNode_zeroEl(){
        graph = new Graph<>(true,true);
        assertFalse(graph.removeNode(i1));
    }

    @Test
    public void testAddRemoveNode_OneEl(){
        graph = new Graph<>(true,true);
        boolean added = graph.addNode(i1);
        assertTrue(added && graph.removeNode(i1));
    }

    @Test
    public void testAddRemoveEdge_zeroEl(){
        graph = new Graph<>(true,true);
        assertFalse(graph.removeEdge(i1, i2));
    }

    @Test
    public void testAddRemoveEdge_OneEl(){
        graph = new Graph<>(true,true);
        boolean added_node1 = graph.addNode(i1);
        boolean added_node2 = graph.addNode(i2);
        boolean added_edge = graph.addEdge(i1, i2, i3);
        assertTrue(added_node1 && added_node2 && added_edge // nodes and edge added
                    && graph.removeEdge(i1, i2) && !graph.containsEdge(i1,i2)); // edge removed
    }

    @Test
    public void testNumberOfNodes_zeroEl(){
        graph = new Graph<>(true,true);
        assertEquals(0, graph.numNodes());
    }

    @Test
    public void testNumberOfNodes_oneEl(){
        graph = new Graph<>(true,true);
        boolean added_node = graph.addNode(i1);
        assertEquals(1, graph.numNodes());
    }

    @Test
    public void testNumberOfEdges_zeroEl(){
        graph = new Graph<>(true,true);
        assertEquals(0, graph.numEdges());
    }

    @Test
    public void testNumberOfEdges_oneEl(){
        graph = new Graph<>(true,true);
        boolean added_node1 = graph.addNode(i1);
        boolean added_node2 = graph.addNode(i2);
        boolean added_edge = graph.addEdge(i1, i2, i3);
        assertEquals(1, graph.numEdges());
    }

    @Test
    public void testGetNodes_zeroEl(){
        graph = new Graph<>(true,true);
        ArrayList<Integer> expected = new ArrayList<>();
        ArrayList<Integer> actual = new ArrayList<>();
        for(Integer i : graph.getNodes()){
            actual.add(i);
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNodes_twoEl(){
        graph = new Graph<>(true,true);
        boolean added_node1 = graph.addNode(i1);
        boolean added_node2 = graph.addNode(i2);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(i1);
        expected.add(i2);
        ArrayList<Integer> actual = new ArrayList<>();
        for(Integer i : graph.getNodes()){
            actual.add(i);
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testGetEdges_zeroEl(){
        graph = new Graph<>(true,true);
        List<Edge<Integer,Integer>> expected = new ArrayList<>();
        assertEquals(expected, graph.getEdges());
    }

    @Test
    public void testGetEdges_Directed_oneEl(){
        graph = new Graph<>(true,true);
        boolean added_node1 = graph.addNode(i1);
        boolean added_node2 = graph.addNode(i2);
        boolean added_edge = graph.addEdge(i1, i2, i3);

        Set<String> expected = new HashSet<>();
        expected.add(i1+"->"+i2+"["+i3+"]");
        Set<String> actual = new HashSet<>();

        for (AbstractEdge<Integer, Integer> e : graph.getEdges()) {
            actual.add(e.getStart()+"->"+e.getEnd()+"["+e.getLabel()+"]");
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testGetEdges_NonDirected_oneEl(){
        graph = new Graph<>(false,true);
        boolean added_node1 = graph.addNode(i1);
        boolean added_node2 = graph.addNode(i2);
        boolean added_edge = graph.addEdge(i1, i2, i3);

        Set<String> expected = new HashSet<>();
        expected.add(i1+"->"+i2+"["+i3+"]");
        expected.add(i2+"->"+i1+"["+i3+"]");
        Set<String> actual = new HashSet<>();

        for (AbstractEdge<Integer, Integer> e : graph.getEdges()) {
            actual.add(e.getStart()+"->"+e.getEnd()+"["+e.getLabel()+"]");
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testGetNeighbours_zeroEl(){
        graph = new Graph<>(true,true);
        ArrayList<Integer> expected = null;
        assertEquals(expected, graph.getNeighbours(i1));
    }

    @Test
    public void testGetNeighbours_oneEl(){
        graph = new Graph<>(true,true);
        boolean added_node1 = graph.addNode(i1);
        boolean added_node2 = graph.addNode(i2);
        boolean added_edge = graph.addEdge(i1, i2, i3);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(i2);
        ArrayList<Integer> actual = new ArrayList<>();
        for(Integer i : graph.getNeighbours(i1)){
            actual.add(i);
        }
        assertEquals(expected, actual);
    }


} //class