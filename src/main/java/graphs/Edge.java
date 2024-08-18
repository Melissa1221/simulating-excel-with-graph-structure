package graphs;
/*
* Clase Edge o arista de un grafo
*/
public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {

    protected Vertex<T> from = null;//nodo o vertice origen
    protected Vertex<T> to = null;  //nodo o vertice destino
    protected int cost = 0;
    
    //constructor con tres parametros
    public Edge(int cost, Vertex<T> from, Vertex<T> to) {
        if (from == null || to == null)
            throw (new NullPointerException("Both 'to' and 'from' vertices need to be non-NULL."));

        this.cost = cost;
        this.from = from;
        this.to = to;
    }
    //constructor con un parametro del mismo tipo(arista). El objeto actual se inicializa con la arista pasada como parametro
    public Edge(Edge<T> e) {
        this(e.cost, e.from, e.to);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    //retorna el vertice origen
    public Vertex<T> getFromVertex() {
        return from;
    }
    //retorna el vertice destino
    public Vertex<T> getToVertex() {
        return to;
    }

    /**
     * retorna el hashcode determinado de la multiplicacion del costo de la arista por los hashcode de
     * su vertice origen y el hashcode de su vertice destino
     */
    @Override
    public int hashCode() {
        final int cost = (this.cost * (this.getFromVertex().hashCode() * this.getToVertex().hashCode())); 
        return 31 * cost;
    }

    /**
     * compara dos objetos de tipo arista en funcion de sus costos, nodos origen y nodos destino
     */
    @Override
    public boolean equals(Object e1) {
        if (!(e1 instanceof Edge))
            return false;

        final Edge<T> e = (Edge<T>) e1;
        //compara costos de la arista actual y la arista pasada como parametro
        final boolean costs = this.cost == e.cost;
        if (!costs)
            return false;//si costos difieren retorna falso

        final boolean from = this.from.equals(e.from);//compara nodos origen de ambos objetos arista
        if (!from)
            return false;//si nodos origen difieren retorna falso

        final boolean to = this.to.equals(e.to);//compara nodos destino de ambos objetos arista
        if (!to)
            return false;//si nodos destino difieren retorna falso

        return true;
    }

    /**
     * compara dos objetos de tipo arista en funcion de sus costos, nodos origen y nodos destino
     */
    @Override
    public int compareTo(Edge<T> e) {
        if (this.cost < e.cost)
            return -1;
        if (this.cost > e.cost)
            return 1;

        final int from = this.from.compareTo(e.from);
        if (from != 0)
            return from;

        final int to = this.to.compareTo(e.to);
        if (to != 0)
            return to;

        return 0;
    }

    /**
     * Retorna un string con los datos detallados de las aristas del grafo
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ")
                .append(from.value)//origin value
                .append("(")
                .append(from.weight)
                .append(") ")
                .append("]")//cierra origin value
                .append(" -> ")
               .append("[ ")
                .append(to.value)//destino value
                .append("(")
                .append(to.weight)
                .append(") ")
                .append("]")//cierra destino value
                .append(" = ")
                .append(cost)//costo
                .append("\n");
        return builder.toString();
    }
    
}