package ExcelRudimentario;



import ExcelRudimentario.Celda;
import graphs.*;
import static graphs.Graph.TYPE.DIRECTED;

public class Hoja_Calculo {
    private Graph<Celda> hoja;

    public Hoja_Calculo() {
        this.hoja = new Graph<>(DIRECTED);
        String title;

        // Se inicializan vertices desde A1 hasta H20
        for (char i = 'A'; i <= 'H'; i++) {  
            for (int j = 1; j <= 20; j++) {
                title = i + Integer.toString(j);
                hoja.getVertices().add(new Vertex<>(new Celda(title)));
            }
        }
    }

    public Vertex<Celda> getCelda(String name) {
        for (Vertex<Celda> it : hoja.getVertices()) {
            if (it.getValue().getId().equals(name)) {
                return it;
            }
        }
        return null;
    }

    public Graph<Celda> getHoja() {
        return hoja;
    }

    public void addDependencia(Vertex<Celda> from, Vertex<Celda> to, String formula) {
        hoja.getEdges().add(new Edge<>(0, from, to));
        if (hoja.getType() == Graph.TYPE.UNDIRECTED) {
            hoja.getEdges().add(new Edge<>(0, to, from));
        }
    }

    public void reiniciarCelda(Vertex<Celda> ver) {
        hoja.getEdges().removeIf(d -> d.getToVertex().equals(ver));
        ver.getValue().setValue(0.00);
    }

    @Override
    public String toString() {
        return hoja.toString();
    }
}
