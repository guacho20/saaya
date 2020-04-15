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
public class ActividadResulta extends Pantalla{
    private Tabla tab_actividad_resulta = new Tabla ();
    public ActividadResulta (){
        tab_actividad_resulta.setId("tab_actividad_resulta");
        tab_actividad_resulta.setTabla("yavirac_titu_actividad_resulta", "ide_ytiacr", 1);
        tab_actividad_resulta.setHeader("Actividad Resultado");
        tab_actividad_resulta.dibujar();
        
        
        PanelTabla pat_line=new PanelTabla ();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_actividad_resulta);
        
        Division div_actividad_resulta = new Division();
        div_actividad_resulta.setId("div_actividad_resulta");
        div_actividad_resulta.dividir1(pat_line);
         
        
        agregarComponente(div_actividad_resulta);
        
        
        
        
    }
    
   
    
    
    @Override
    public void insertar() {
    tab_actividad_resulta.insertar();
    
    }

    @Override
    public void guardar() {
    
    tab_actividad_resulta.guardar();
    guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_actividad_resulta.eliminar();
    
    
    }

    public Tabla getTab_actividad_resulta() {
        return tab_actividad_resulta;
    }

    public void setTab_actividad_resulta(Tabla tab_actividad_resulta) {
        this.tab_actividad_resulta = tab_actividad_resulta;
    }
    
    
    
    
    
    
    
}
