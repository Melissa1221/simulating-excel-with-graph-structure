package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
* Clase Vertex, vertice o nodo de un grafo
*/
public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>>  {

    protected T value = null;
    protected int weight = 0;
    protected List<Edge<T>> edges = new ArrayList<>();//lista de aristas del vertice o nodo

    //constructor con 1 parametro
    public Vertex(T value) {
        this.value = value;
    }
    //constructor con 2 parametros
    public Vertex(T value, int weight) {
        this(value);
        this.weight = weight;
    }
    //constructor con 1 parametro de tipo vertice, traslada la data del parametro(valor,peso y aristas) al objeto actual
    public Vertex(Vertex<T> vertex) {
        this(vertex.value, vertex.weight);
        this.edges.addAll(vertex.edges);
    }

    public T getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    //metodo añade una arista a la lista de aristas
    public void addEdge(Edge<T> e) {
        edges.add(e);
    }
    //retorna la lista de aristas
    public List<Edge<T>> getEdges() {
        return edges;
    }
    //retorna la arista que tiene como destino el vertice o nodo pasado como parametro
    public Edge<T> getEdge(Vertex<T> v) {
        for (Edge<T> e : edges) {
            if (e.to.equals(v))//recorre la lista de aristas comparando el vertice o nodo destino con el vertice parametro
                return e;
        }
        return null;
    }
    //retorna T si se ubico una arista que tiene como destino el vertice o nodo pasado como parametro
    public boolean pathTo(Vertex<T> v) {
        for (Edge<T> e : edges) {
            if (e.to.equals(v))//recorre la lista de aristas comparando el vertice o nodo destino con el vertice parametro
                return true;
        }
        return false;
    }

    /**
     * retorna un hashcode de la suma del hashcode del valor del vertice o nodo actual sumado con su peso y numero de aristas
     */
    @Override
    public int hashCode() {
        final int code = this.value.hashCode() + this.weight + this.edges.size();
        return 31 * code;
    }

    /**
     * Retorna T si dos objetos en terminos de su peso,# de aristas, valor y costo de cada arista son iguales
     */
    @Override
    public boolean equals(Object v1) {
        if (!(v1 instanceof Vertex))//valida si el parametro es una instancia de vertice
            return false;

        final Vertex<T> v = (Vertex<T>) v1;

        final boolean weightEquals = this.weight == v.weight;
        if (!weightEquals)
            return false;

        final boolean edgesSizeEquals = this.edges.size() == v.edges.size();
        if (!edgesSizeEquals)
            return false;

        final boolean valuesEquals = this.value.equals(v.value);
        if (!valuesEquals)
            return false;

        final Iterator<Edge<T>> iter1 = this.edges.iterator();
        final Iterator<Edge<T>> iter2 = v.edges.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {            
            final Edge<T> e1 = iter1.next();
            final Edge<T> e2 = iter2.next();
            if (e1.cost != e2.cost)             // solo verifica el costo
                return false;
        }

        return true;
    }

    /**
     * retorna,1,0,-1 añ comparar dos objetos de tipo vertice o nodo
     * considerando su valor,peso,# aristas, costos de cada arista de la lista de aristas de los vertices comparados
     */
    @Override
    public int compareTo(Vertex<T> v) {        
        final int valueComp = this.value.compareTo(v.value); //compara valores
        if (valueComp != 0)
            return valueComp;

        if (this.weight < v.weight)                             //compara pesos
            return -1;
        if (this.weight > v.weight)
            return 1;

        if (this.edges.size() < v.edges.size())                 //compara #aristas de lista de aristas
            return -1;
        if (this.edges.size() > v.edges.size())
            return 1;

        final Iterator<Edge<T>> iter1 = this.edges.iterator();
        final Iterator<Edge<T>> iter2 = v.edges.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            // Only checking the cost
            final Edge<T> e1 = iter1.next();
            final Edge<T> e2 = iter2.next();
            if (e1.cost < e2.cost)                              //compara costos de las aristas de la lista de aristas
                return -1;
            if (e1.cost > e2.cost)
                return 1;
        }

        return 0;
    }

    /**
     * Retorna un string con los datos del vertice o nodo: valor,peso y lista de aristas
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Value=")
                .append(value)
                .append(" weight=")
                .append(weight)
                .append("\n");
        for (Edge<T> e : edges)
            builder.append("\t").append(e.toString());
        return builder.toString();
    }
    
}