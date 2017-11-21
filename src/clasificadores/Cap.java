/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import herramientas.MatrizConfusion;
import herramientas.Tokenizador;
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
    private Patron patronMedio;
    private double contadorCorrectos;

    public Cap()
    {
        this.clases = new ArrayList<>();
    }
    
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
       // determinar las clases participantes
       
       determinarClasesInvolucradas(instancias);
       // crear la matriz 
       this.memoria = new double[this.clases.size()][instancias.get(0).getVectorCa().length];
       // calcular el vector promedio para trasladar el conjunto fundamental
       calcularPatronMedio(instancias);
       // trasladar todo el junto fundamental
       for(Patron aux: instancias)
           aux.trasladar(patronMedio);
       // construir la memoria
       for(Patron aux: instancias){
       int indiceClase = this.clases.indexOf(aux.getClase());
       // acumulamos en la clase que le corresponde 
       for(int x=0; x<this.memoria[indiceClase].length;x++){
         this.memoria[indiceClase][x]+=aux.getVectorCa()[x];
       }
       }   
        
       
    }

      @Override
    public void clasifica(Patron patron) {
       // trasladar el patron 
       // patron.trasladar(this.patronMedio);
       // ejecutamos la clasificación 
       // recorremos la memoria 
       
       double aso[] = new double[this.memoria.length];
       int iM = 0;
       for (int x=0; x<this.memoria.length;x++){
            double acu = 0;
            for (int y=0;y<this.memoria[x].length;y++){
              acu+=patron.getVectorCa()[y]*
                     this.memoria[x][y];
            }
            // asignamos la parte de la asociacion que 
            // corresponde
            aso[x]=acu;
            if (aso[x]>aso[iM]){
             iM = x;
            }
            patron.setClase_resultado(this.clases.get(iM));
       } 
       
        int pertenece = Tokenizador.inst.getClases().indexOf(patron.getClase());
        int resultado = Tokenizador.inst.getClases().indexOf(patron.getClase_resultado());
        this.getMatriz().acumula(pertenece, resultado);
         if(patron.getClase().equals(patron.getClase_resultado()))
              {
                  contadorCorrectos++;
              }
       
        System.out.println();
    }

    @Override
    public void clasificaConjunto(ArrayList<Patron> patrones) 
    {
        this.contadorCorrectos = 0;
     this.rendimiento = 0;
        for(Patron aux: patrones){
            clasifica(aux);
        }
        this.matriz.calculaEficacias();
        this.rendimiento = this.matriz.getEficaciaGeneral();
        
       matriz.setNombre(Tokenizador.inst.getClases());
       matriz.graficar(getMatriz().matriz);
    }
    @Override
    public MatrizConfusion getMatriz() {
        return matriz;
    }
    @Override
    public double getRendimiento() {
        return rendimiento;
    }

        private void determinarClasesInvolucradas(ArrayList<Patron> instancias) {
      // agregamos la primer clase
       this.clases.add(instancias.get(0).getClase());
      // recorremos el conjunto para encontrar clases
      for(int x=1;x<instancias.size();x++){
        if(!this.clases.contains(instancias.get(x).getClase())){
            // si no la contiene la agrega
            this.clases.add(instancias.get(x).getClase());
        }
      }
    }

    private void calcularPatronMedio(ArrayList<Patron> instancias) {
        // acumulamos en un patron existente
        this.patronMedio = new Patron(instancias.get(0).getVectorCa().length);
        // recorrer las instancias
        for (Patron aux: instancias){
           for(int x=0;x<aux.getVectorCa().length;x++){
               patronMedio.getVectorCa()[x]+=aux.getVectorCa()[x];
           }
        }
        // dividimos 
        for(int x=0;x<patronMedio.getVectorCa().length;x++){
               patronMedio.getVectorCa()[x]/=instancias.size();
           }      
    }
}
