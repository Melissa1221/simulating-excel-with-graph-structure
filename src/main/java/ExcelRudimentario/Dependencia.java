/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExcelRudimentario;

import ExcelRudimentario.Celda;
import graphs.Edge;
import graphs.Vertex;

public class Dependencia extends Edge<Celda>{
    private String formula;

    public Dependencia(Vertex<Celda> from, Vertex<Celda> to, String formula){
        super(0, from, to);
        this.formula = formula;    
    }
    
    public String getFormula(){
        return formula;
    }
}
