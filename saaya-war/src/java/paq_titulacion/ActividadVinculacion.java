/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
/**
 *
 * @author ANDRES
 */
public class ActividadVinculacion extends Pantalla {
     private Tabla tab_actividad_vincula = new Tabla();

    public ActividadVinculacion(){
        tab_actividad_vincula.setId("tab_actividad_vincula");
        tab_actividad_vincula.setTabla("yavirac_titu_actividad_vincula","ide_ytiacv",1);
        tab_actividad_vincula.setHeader("actividad vinculación");
        tab_actividad_vincula.dibujar ();
        
        PanelTabla pat_line= new PanelTabla();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_actividad_vincula); 
        
       Division div_actividad_vincula = new Division();
        div_actividad_vincula.setId("div_actividad_vincula");
        div_actividad_vincula.dividir1(pat_line);
        
        agregarComponente(div_actividad_vincula);
        
    
    
}

    @Override
    public void insertar() {
        tab_actividad_vincula.insertar();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        tab_actividad_vincula.guardar();
        guardarPantalla();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        tab_actividad_vincula.eliminar(); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_actividad_vincula() {
        return tab_actividad_vincula;
    }

    public void setTab_actividad_vincula(Tabla tab_actividad_vincula) {
        this.tab_actividad_vincula = tab_actividad_vincula;
    }
    
}

 


    
    
    
    
    
    
    
    

