/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import graficar.graficar;
import java.util.ArrayList;

/**
 *
 * @author Roberto Cruz Leija
 */
public class MatrizConfusion {
    
   public double[][] matriz;
   public ArrayList<String> nombre;
   private double eficaciaGeneral;
   private double clasCorrectos;
   private double contadorClasificados;

    public MatrizConfusion(int numClases) {
        this.matriz = new double[numClases][numClases+1];
        this.eficaciaGeneral = 0;
        this.clasCorrectos = 0;
        this.contadorClasificados = 0;
    }
   
    public void acumula(int pertenece, int resultado){
        // vamos contando los clasificados
        this.contadorClasificados++;
        
        if (pertenece==resultado){
         // fue una clasificacion correcta 
         clasCorrectos++;
        }
        this.matriz[pertenece][resultado]++;
        //contando los clasificados por clase
        this.matriz[pertenece][this.matriz[pertenece].length-1]++;
    }
    
    public void calculaEficacias(){
          // total de los elementos por clase
          this.eficaciaGeneral = (this.clasCorrectos/this.contadorClasificados)*100;
          // calculamos los % por clase
          for (int c=0; c<matriz.length;c++){
            double total = matriz[c][matriz[0].length-1];
            matriz[c][matriz[0].length-1] = (matriz[c][c]/total)*100;
          }
          
    }

    /**
     * @return the eficaciaGeneral
     */
    public double getEficaciaGeneral() {
        return eficaciaGeneral;
    }
   
   public void graficar(double[][] arreglo){
       calculaEficacias();
       graficar g = new graficar();
       g.setDatos(arreglo);
       g.setNombre(nombre);
       g.setEficaciaGeneral(this.eficaciaGeneral);
       g.casteo();
       g.mostrarGrafica();
       g.pack();
       g.setVisible(true);
   }

    public void setNombre(ArrayList<String> nombre) {
        this.nombre = nombre;
    }

    
}
