/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficar;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import herramientas.MatrizConfusion;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JScrollPane;
/**
 *
 * @author Belmont
 */
public class graficar extends JFrame{
    
    public double[][] matriz;
     public ArrayList<String> nombre;
    public Object[][] data;
    public double eficaciaGeneral;
    
    public graficar(){
        super("Matriz de Confucion");
    }
    public void mostrarGrafica(){
        String clase[] = new String[this.matriz[0].length];
        
        for(int i = 0 ; i<this.matriz[0].length-1;i++){
//         clase[i]="";
//         clase[i]+=""+i;
           clase[i]=this.nombre.get(i);
        }
        clase[this.matriz[0].length-1]="%Eficacia";
        //String[] clase = {"0","1","2","%Eficacia"};
        
               
        final JTable table = new JTable(data,clase);
        table.setPreferredScrollableViewportSize(new Dimension(500, 80));
        
        //Creamos un scrollpanel y se lo agregamos a la tabla 
        JScrollPane scrollpane = new JScrollPane(table);

        //Agregamos el scrollpanel al contenedor 
        getContentPane().add(scrollpane, BorderLayout.CENTER);

        //manejamos la salida 
        addWindowListener(new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) { 
        System.exit(0); 
        } 
        }); 
        
       }
    public void casteo(){
        this.data = new Object[this.matriz.length+1][this.matriz[0].length];
        for(int x=0 ; x<this.matriz.length ;x++){
             for(int y = 0 ;  y< this.matriz[0].length;y++){
                 data[x][y]=matriz[x][y];
             }
        }
        for(int z=0 ; z<this.matriz[0].length;z++){
            if(this.matriz[0].length==2){
            this.data[this.matriz.length][0]="%Eficacia General";
            this.data[this.matriz.length][this.matriz[0].length-1]=""+this.eficaciaGeneral;
            }
            else if(this.matriz[0].length>=2){
                this.data[this.matriz.length][z]="-";   
            }
        }
                this.data[this.matriz.length][0]="%EficaciaGeneral";
                this.data[this.matriz.length][this.matriz[0].length-1]=""+this.eficaciaGeneral;
        
    }

    public void setEficaciaGeneral(double eficaciaGeneral) {
        this.eficaciaGeneral = eficaciaGeneral;
    }
   public void setDatos(double[][] datos) {
        this.matriz = datos;
    }

    public void setNombre(ArrayList<String> nombre) {
        this.nombre = nombre;
    }
    
}
