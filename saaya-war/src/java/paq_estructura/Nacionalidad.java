/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ITSY
 */
public class Nacionalidad extends Pantalla { 

    Tabla tab_nacionalidad = new Tabla (); // importar tabla 
    
    public Nacionalidad(){ // constructor
        
        tab_nacionalidad.setId("tab_nacionalidad");  // todo objeto instanciado poner id 
        
        tab_nacionalidad.setTabla("yavirac_stror_nacionalidad","ide_ystnac",1);    // nom bdd
        tab_nacionalidad.getColumna("ide_ystnac").setNombreVisual("CÓDIGO");
        tab_nacionalidad.getColumna("descripcion_ystnac").setNombreVisual("DESCRIPCIÓN");
        tab_nacionalidad.getColumna("activo_ystnac").setNombreVisual("ACTIVO");
        tab_nacionalidad.dibujar();
        
        PanelTabla pa_nacionalidad = new PanelTabla();
        pa_nacionalidad.setId("pa_nacionalidad"); // nombre de i
        pa_nacionalidad.setPanelTabla(tab_nacionalidad);
        
        Division div_nacionalidad = new Division();
        div_nacionalidad.setId("div_nacionalidad");
        div_nacionalidad.dividir1(tab_nacionalidad);
        
        agregarComponente(div_nacionalidad);
            
     
        
    }
    
    
    
    
    
    
    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_nacionalidad() {
        return tab_nacionalidad;
    }

    public void setTab_nacionalidad(Tabla tab_nacionalidad) {
        this.tab_nacionalidad = tab_nacionalidad;
    }
    
}
