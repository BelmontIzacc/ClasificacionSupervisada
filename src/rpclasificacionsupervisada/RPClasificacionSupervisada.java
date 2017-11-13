/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpclasificacionsupervisada;

import clasificadores.Knn;
import clasificadores.MinimaDistancia;
import clasificadores.NaiveBayes;
import clasificadores.memoria;
import herramientas.FactorSeleccion;
import herramientas.GeneradorInstancias;
import herramientas.Grafica;
import herramientas.MatrizConfusion;
import herramientas.Tokenizador;
import java.util.ArrayList;
import modelos.Instancias;
import modelos.Patron;
import org.jfree.data.xy.XYDataItem;

/**
 *
 * @author Roberto Cruz Leija
 */
public class RPClasificacionSupervisada {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Tokenizador.leerInstancias();
        GeneradorInstancias ins = new GeneradorInstancias(Tokenizador.inst);
        memoria m = new memoria();
        System.out.println();
        ArrayList<Patron> ce = ins.generaInstancia(new int[]{0,1,2,3,4}, 100, FactorSeleccion.PRIMEROS);
        m.setNombre(Tokenizador.inst.getClases());
        m.entrenar(ce);
        m.clasificaConjunto(ce);
        Patron aux = new Patron(new double[]{0,1,0,1,1},"1");
        ArrayList<Patron> nuevo = new ArrayList<>();
        nuevo.add(aux);
        System.out.println();
        m.entrenar(nuevo);
        m.clasifica(aux);
        //Tokenizador.leerInstancias();
        //GeneradorInstancias ins = new GeneradorInstancias(Tokenizador.inst);
        //ArrayList<Patron> ce = ins.generaInstancia(new int[]{0,1,2}, 100, FactorSeleccion.PRIMEROS);
        //NaiveBayes n = new NaiveBayes();
        //n.entrenar(Tokenizador.inst.getPatrones());
        //Patron aux = new Patron(new double[]{6,130,8},"desconocido");
        //n.clasifica(aux);
        //n.entrenar(Tokenizador.inst.getPatrones());
        //n.clasificaConjunto(Tokenizador.inst.getPatrones());
//        
//         MatrizConfusion m = new MatrizConfusion(Tokenizador.inst.getClases().size());
//         m.setNombre(Tokenizador.inst.getClases());
//         m.graficar(n.getMatriz().matriz);

           
//         Tokenizador.leerInstancias();
//         GeneradorInstancias ins = new GeneradorInstancias(Tokenizador.inst);
//         MinimaDistancia md = new MinimaDistancia();
//         md.entrenar(Tokenizador.inst.getPatrones());
//         md.clasificaConjunto(Tokenizador.inst.getPatrones());
//         
//         MatrizConfusion m = new MatrizConfusion(Tokenizador.inst.getClases().size());
//         m.setNombre(Tokenizador.inst.getClases());
//         m.graficar(md.getMatriz().matriz);
      
         System.out.println();

//            knn.entrenar(ce);
//            md.entrenar(ce);


//        Grafica grafica = new Grafica("Comparación","%","Comparación Rendimientos");
//        grafica.agrearSerie("md");
//        grafica.agrearSerie("knn");
//        
//        MinimaDistancia md = new MinimaDistancia();
//        Knn knn = new Knn(4);
//        
//        // crear las comparaciones 
//        for (int x=0; x<50;x++){
//            
//            //  entrenar
//            GeneradorInstancias ge = new GeneradorInstancias(Tokenizador.inst);
//            
//            ArrayList<Patron> ce = ge.generaInstancia(new int[]{2,3}, 6*x, FactorSeleccion.PRIMEROS);
//            knn.entrenar(ce);
//            md.entrenar(ce);
//          
//            
//            knn.clasificaConjunto(ce);
//            double renKnn = knn.getRendimiento();
//            grafica.agregarDatoASerie("knn", new XYDataItem(x, renKnn));
//            //  clasificar 
//            md.clasificaConjunto(ce);
//            double renMd = md.getRendimiento();
//            grafica.agregarDatoASerie("md",new XYDataItem(x, renMd));
//           // System.out.println();
//        }
//        
//        grafica.crearYmostrarGrafica();
//       
//      //  System.out.println();
    }
    
}
