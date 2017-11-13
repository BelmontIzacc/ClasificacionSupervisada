/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import herramientas.GeneradorInstancias;
import herramientas.MatrizConfusion;
import herramientas.Tokenizador;
import java.util.ArrayList;
import modelos.Clasificador;
import modelos.Patron;

/**
 *
 * @author Belmont
 */
public class memoria implements Clasificador{
    public double[][] lernmatrix;
    public double[][] columna;
    public double[][] end;
    public int dimensiones;
    public int numclases;
    public ArrayList<Patron> instancias;
    public ArrayList<String> nombre;
    private double rendimiento;
    private MatrizConfusion matriz;
    private int up=0;
    

    public memoria()
    {
        this.dimensiones=Tokenizador.inst.getPatrones().get(0).getVectorCa().length;
        this.numclases=Tokenizador.inst.getClases().size();
        this.lernmatrix= new double[numclases][dimensiones];
        this.columna= new double[numclases][numclases];
        
         for(int x = 0 ; x<numclases;x++)
        {
            for(int y = 0 ; y<numclases; y++ )
            {
                if(x==y)
                {
                    this.columna[y][x]=1;
                }
            }
        }
    }
    
    @Override
    public void entrenar(ArrayList<Patron> instancias) {
        this.instancias=instancias;
        double patron[] = new double[dimensiones];
        int numClasePatron;
        if(up==1)
        {
            numclases=1;
        }
        for(int x = 0 ; x<numclases;x++)
        {
            patron= instancias.get(x).getVectorCa();
            int clas = Integer.valueOf(this.nombre.get(x)).intValue();
        for(int i =0 ; i<numclases;i++)
        {                        
            for(int j = 0 ; j<dimensiones;j++)
            {           
               if(columna[clas-1][i]==patron[j] && columna[clas-1][i]==1)
               {
                   this.lernmatrix[i][j]+=1;
               }
               if(columna[clas-1][i]==1 && patron[j]==0 && columna[clas-1][i]==1)
               {
                   this.lernmatrix[i][j]+=-1;
               }
            }
        }
        up=1;
        }
        System.out.println();
    }

    @Override
    public void clasifica(Patron patron) 
    {
        double mayor=0;
        double aux=-1;
        double[][] resultado = new double[this.lernmatrix.length][1];
        double[] vector = patron.getVectorCa();
        for(int x = 0 ; x<resultado.length;x++)
        {
            for(int y = 0 ; y<resultado[x].length;y++)
            {
                for(int z = 0 ; z<this.lernmatrix[0].length;z++)
                {
                    resultado[x][y]+=this.lernmatrix[x][z]*vector[z];
                    aux=resultado[x][y];
                }
            }
            if(aux>mayor){
                mayor=aux;
            }
        }
        
        for(int i = 0 ; i<this.lernmatrix.length;i++)
        {
            if(resultado[i][0]==mayor)
            {
                resultado[i][0]=1;
            }
            else
            {
                resultado[i][0]=0;
            }
        }
        
        System.out.println();
        
    }
    @Override
    public void clasificaConjunto (ArrayList<Patron> patrones)
    {
       for (Patron aux: patrones){
           clasifica(aux);
       }       
    }

    public void setNombre(ArrayList<String> clases) {
        this.nombre = clases;
    }
    @Override
   public MatrizConfusion getMatriz() {
        return matriz;
    }
   @Override
    public double getRendimiento() {
        return rendimiento;
    }


}
