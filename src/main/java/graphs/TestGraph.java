package graphs;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestGraph {
    public static void main(String[] args) {             
        System.out.println("-----------------undirectedGraph--------------------");
        UndirectedGraph undirectedGraph = new UndirectedGraph();        
        System.out.println(undirectedGraph.toString());
//        System.out.println("-----------------directedGraph--------------------");
//        DirectedGraph directedGraph = new DirectedGraph();
//        System.out.println(directedGraph.toString());
//        
//        System.out.println("-----------------directedWithNegativeWeights--------------------");
//        DirectedWithNegativeWeights directedWithNegativeWeights = new DirectedWithNegativeWeights();        
//        System.out.println(directedWithNegativeWeights.graph.toString());
    }
    
    private static class UndirectedGraph{//1.grafo no dirigido
        final List<Vertex<Integer>> verticies = new ArrayList<>();
        final Vertex<Integer> v1 = new Vertex<>(1);
        final Vertex<Integer> v2 = new Vertex<>(2);
        final Vertex<Integer> v3 = new Vertex<>(3);
        final Vertex<Integer> v4 = new Vertex<>(4);
        final Vertex<Integer> v5 = new Vertex<>(5);
        final Vertex<Integer> v6 = new Vertex<>(6);
        final Vertex<Integer> v7 = new Vertex<>(7);
        final Vertex<Integer> v8 = new Vertex<>(8);
        {
        verticies.add(v1);
        verticies.add(v2);
        verticies.add(v3);
        verticies.add(v4);
        verticies.add(v5);
        verticies.add(v6);
        verticies.add(v7);
        verticies.add(v8);}
        
        final List<Edge<Integer>> edges = new ArrayList<>();
        final Edge<Integer> e1_2 = new Edge<>(7, v1, v2);
        final Edge<Integer> e1_3 = new Edge<>(9, v1, v3);
        final Edge<Integer> e1_6 = new Edge<>(14, v1, v6);
        final Edge<Integer> e2_3 = new Edge<>(10, v2, v3);
        final Edge<Integer> e2_4 = new Edge<>(15, v2, v4);
        final Edge<Integer> e3_4 = new Edge<>(11, v3, v4);
        final Edge<Integer> e3_6 = new Edge<>(2, v3, v6);
        final Edge<Integer> e5_6 = new Edge<>(9, v5, v6);
        final Edge<Integer> e4_5 = new Edge<>(6, v4, v5);
        final Edge<Integer> e1_7 = new Edge<>(1, v1, v7);
        final Edge<Integer> e1_8 = new Edge<>(1, v1, v8);
        
        {edges.add(e1_2);
        edges.add(e1_3);
        edges.add(e1_6);
        edges.add(e2_3);
        edges.add(e2_4);
        edges.add(e3_4);
        edges.add(e3_6);
        edges.add(e5_6);
        edges.add(e4_5);
        edges.add(e1_7);
        edges.add(e1_8);}
        
        final Graph<Integer> graph = new Graph<>(verticies, edges);
        @Override
        public String toString() {
            return graph.toString();
        }        
    }
    private static class DirectedGraph{//2.grafo dirigido
        final List<Vertex<Integer>> verticies = new ArrayList<>();
        final Vertex<Integer> v1 = new Vertex<>(1);
        final Vertex<Integer> v2 = new Vertex<>(2);
        final Vertex<Integer> v3 = new Vertex<>(3);
        final Vertex<Integer> v4 = new Vertex<>(4);
        final Vertex<Integer> v5 = new Vertex<>(5);
        final Vertex<Integer> v6 = new Vertex<>(6);
        final Vertex<Integer> v7 = new Vertex<>(7);
        final Vertex<Integer> v8 = new Vertex<>(8);
        
        {verticies.add(v1);
        verticies.add(v2);
        verticies.add(v3);
        verticies.add(v4);
        verticies.add(v5);
        verticies.add(v6);
        verticies.add(v7);
        verticies.add(v8);}
        
        final List<Edge<Integer>> edges = new ArrayList<>();
        final Edge<Integer> e1_2 = new Edge<>(7, v1, v2);
        final Edge<Integer> e1_3 = new Edge<>(9, v1, v3);
        final Edge<Integer> e1_6 = new Edge<>(14, v1, v6);
        final Edge<Integer> e2_3 = new Edge<>(10, v2, v3);
        final Edge<Integer> e2_4 = new Edge<>(15, v2, v4);
        final Edge<Integer> e3_4 = new Edge<>(11, v3, v4);
        final Edge<Integer> e3_6 = new Edge<>(2, v3, v6);
        final Edge<Integer> e6_5 = new Edge<>(9, v6, v5);
        final Edge<Integer> e6_8 = new Edge<>(14, v6, v8);
        final Edge<Integer> e4_5 = new Edge<>(6, v4, v5);
        final Edge<Integer> e4_7 = new Edge<>(16, v4, v7);
        final Edge<Integer> e1_8 = new Edge<>(30, v1, v8);
        
        {edges.add(e1_2);
        edges.add(e1_3);
        edges.add(e1_6);
        edges.add(e2_3);
        edges.add(e2_4);
        edges.add(e3_4);
        edges.add(e3_6);
        edges.add(e6_5);
        edges.add(e6_8);
        edges.add(e4_5);
        edges.add(e4_7);
        edges.add(e1_8);}
        
        final Graph<Integer> graph = new Graph<>(Graph.TYPE.DIRECTED, verticies, edges);        
        
        @Override
        public String toString() {
            return graph.toString();
        }
    }
    public static class DirectedWithNegativeWeights{//3.Grafos dirigidos con pesos negativos
        final List<Vertex<Integer>> verticies = new ArrayList<>();
        final Vertex<Integer> v1 = new Vertex<>(1);
        final Vertex<Integer> v2 = new Vertex<>(2);
        final Vertex<Integer> v3 = new Vertex<>(3);
        final Vertex<Integer> v4 = new Vertex<>(4);
        
        {verticies.add(v1);
        verticies.add(v2);
        verticies.add(v3);
        verticies.add(v4);}
        
        final List<Edge<Integer>> edges = new ArrayList<>();
        final Edge<Integer> e1_4 = new Edge<>(2, v1, v4);  // w->z
        final Edge<Integer> e2_1 = new Edge<>(6, v2, v1);  // x->w
        final Edge<Integer> e2_3 = new Edge<>(3, v2, v3);  // x->y
        final Edge<Integer> e3_1 = new Edge<>(4, v3, v1);  // y->w
        final Edge<Integer> e3_4 = new Edge<>(5, v3, v4);  // y->z
        final Edge<Integer> e4_2 = new Edge<>(-7, v4, v2); // z->x
        final Edge<Integer> e4_3 = new Edge<>(-3, v4, v3); // z->y
        
        {edges.add(e1_4);
        edges.add(e2_1);
        edges.add(e2_3);
        edges.add(e3_1);
        edges.add(e3_4);
        edges.add(e4_2);
        edges.add(e4_3);}
        
        final Graph<Integer> graph = new Graph<>(Graph.TYPE.DIRECTED, verticies, edges);
        
        @Override
        public String toString() {
            return graph.toString();
        } 
    }
}