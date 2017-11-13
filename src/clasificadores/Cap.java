/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import herramientas.MatrizConfusion;
import java.util.ArrayList;
import modelos.Clasificador;
import modelos.Patron;

/**
 *
 * @author Belmont
 */
public class Cap implements Clasificador{
    
    private double rendimiento;
    private MatrizConfusion matriz;
    private double[][] memoria;
    private ArrayList<String> clases;

    public Cap()
    {
        this.clases = new ArrayList<>();
    }
    
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
    //determinar las clases participantes 
    determinarClasesInvolucradas();
    //calcular la matriz
    this.memoria = new double[this.clases.size()][instancias.get(0).getVectorCa().length];
    //calcular el vector promedio para trasladar el conjunto fundamental
    Patron patronMedio = calcularPatronMedio(instancias);
    //trasladar el conjunto fundamental
    for(Patron aux : instancias)
    {
        aux.trasladar(patronMedio);
    }
    }

    @Override
    public void clasifica(Patron patron) {
    
    }

    @Override
    public void clasificaConjunto(ArrayList<Patron> patrones) {
    
    }
    @Override
    public MatrizConfusion getMatriz() {
        return matriz;
    }
    @Override
    public double getRendimiento() {
        return rendimiento;
    }

    private void determinarClasesInvolucradas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Patron calcularPatronMedio(ArrayList<Patron> instancias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
