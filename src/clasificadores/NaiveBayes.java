/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import herramientas.MatrizConfusion;
import herramientas.Tokenizador;
import java.awt.Dimension;
import java.util.ArrayList;
import modelos.Clasificador;
import modelos.Patron;

/**
 *
 * @author Belmont
 */
public class NaiveBayes implements Clasificador{

    //probabilidades a priori
    private double[] aprioris;
    private double[] posteriori;
    private Dimension dim;
    private double medias[][];
    private double varianzas[][];
    private double evidencia;
    private double distribucionNormal[][];
    private double muestra[];
    private MatrizConfusion matriz;
    private double rendimiento;
    private double contadorCorrectos;
    
    public NaiveBayes (){
        this.matriz = new MatrizConfusion(Tokenizador.inst.getClases().size());
        this.contadorCorrectos = 0;
    }
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
        contadorCorrectos = 0;
        int numClases = Tokenizador.inst.getClases().size();
        int contador[] = new int [numClases];
        this.medias = new double[numClases][instancias.get(0).getVectorCa().length];
        this.varianzas = new double[numClases][instancias.get(0).getVectorCa().length];
        this.dim=new Dimension(instancias.get(0).getVectorCa().length,numClases);
        //contar el numero de instancias por clase
        for(int x=0 ; x<instancias.size();x++){
            int pos = Tokenizador.inst.getClases().indexOf(instancias.get(x).getClase());
            contador[pos]++;
        }
        this.aprioris = new double[numClases];
        //calcular a prioris
        for(int j = 0 ;j<this.aprioris.length;j++)
        {
            this.aprioris[j] = (double)contador[j]/(double)instancias.size();
        }
        //calcular medias
         int x=0,y=0;
 	  for(int i=0;i<numClases;i++){
 		  x+=contador[i];
 		  for(int j=y;j<x;j++){
 			  for(int k=0;k<instancias.get(i).getVectorCa().length;k++){
 				  medias[i][k]+=instancias.get(j).getVectorCa()[k];
 			  }
 		  }
 		  y+=contador[i];
                   // dividir entre lo que se conto/entre el numero total
 		  for(int k=0;k<instancias.get(i).getVectorCa().length;k++){
 			 medias[i][k]/=contador[i];
 		}
 	  }
            
         // calcular varianza 
         x=0;
         y=0;
 	  for(int i=0;i<numClases;i++){
 		  x+=contador[i];
 		  for(int j=y;j<x;j++){
 			  for(int k=0;k<instancias.get(i).getVectorCa().length;k++){
 			      double cuadrado = Math.pow((instancias.get(j).getVectorCa()[k]
                                       -medias[i][k]), 2);
                               varianzas[i][k]+=cuadrado;
 			  }
 		  }
 		  y+=contador[i];
                   // dividir entre lo que se conto/entre el numero total
 		  for(int k=0;k<instancias.get(i).getVectorCa().length;k++){
 			 varianzas[i][k]/=contador[i]-1;
 		}
 	  }
    }

    @Override
    public void clasifica(Patron patron) {
        int numClases =  Tokenizador.inst.getClases().size();
         // calcuar matriz de ditribucion  
        this.distribucionNormal= new double[(int)dim.getHeight()][(int)dim.getWidth()];
        this.muestra = patron.getVectorCa();
        this.evidencia=0;
        this.posteriori = new double[numClases];
        double acumulado[] = new double[numClases];
        for(int c=0;c<numClases;c++){
            double producto = aprioris[c];
             acumulado[c]=1;
            for(int ca=0; ca<dim.getWidth();ca++){
               double varianza = this.varianzas[c][ca]; 
               double media = this.medias[c][ca]; 
               double vdn = (1/(Math.sqrt(2*Math.PI*varianza)))*
                       (Math.exp(-1*((Math.pow(muestra[ca]-media,2))/(varianza*2))));
               distribucionNormal[c][ca]=vdn;
               producto*=distribucionNormal[c][ca];
               acumulado[c]*=distribucionNormal[c][ca];
            }
            evidencia+=producto;           
        }
        double mA = -1;
        int pos=0;
        for(int pri = 0 ; pri<numClases;pri++){
                this.posteriori[pri]=(this.aprioris[pri]*acumulado[pri])/evidencia;
                
                if(posteriori[pri]>mA)
                {
                    mA=posteriori[pri];
                    pos = pri;
                }
                
            }
           //System.out.println(""+Tokenizador.inst.getClases().get(pos));
           String clase = Tokenizador.inst.getClases().get(pos);
           patron.setClase_resultado(clase);
        int pertenece = Tokenizador.inst.getClases().indexOf(patron.getClase());
        int resultado = Tokenizador.inst.getClases().indexOf(patron.getClase_resultado());
        this.getMatriz().acumula(pertenece, resultado);
        if(patron.getClase().equals(patron.getClase_resultado()))
              {
                  contadorCorrectos++;
              }
}
    @Override
    public void clasificaConjunto(ArrayList<Patron> patrones)
    {this.contadorCorrectos = 0;
     this.rendimiento = 0;
        for(Patron aux: patrones){
            clasifica(aux);
        }
        this.matriz.calculaEficacias();
        this.rendimiento = this.matriz.getEficaciaGeneral();
        
       matriz.setNombre(Tokenizador.inst.getClases());
       matriz.graficar(getMatriz().matriz);
    }

    public MatrizConfusion getMatriz() {
        return matriz;
    }

    public double getRendimiento() {
        return rendimiento;
    }
    
}