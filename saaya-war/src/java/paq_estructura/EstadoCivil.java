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
public class EstadoCivil extends Pantalla{
    Tabla tab_estado_civil= new Tabla(); 
    public EstadoCivil(){
        tab_estado_civil.setId("tab_estado_civil");
        
        tab_estado_civil.setTabla("yavirac_stror_estado_civil","ide_ystesc",1);
        tab_estado_civil.getColumna("ide_ystesc").setNombreVisual("CÓDIGO");
        tab_estado_civil.getColumna("descripcion_ystesc").setNombreVisual("DESCRIPCIÓN");
        tab_estado_civil.getColumna("activo_ystesc").setNombreVisual("ACTIVO");
        tab_estado_civil.dibujar();
        
        PanelTabla pa_estado_civil = new PanelTabla();
        pa_estado_civil.setId("pa_estado_civil");
        pa_estado_civil.setPanelTabla(tab_estado_civil);
        
        Division div_estado_civil = new Division();
        div_estado_civil.setId("div_estado_civil");
        div_estado_civil.dividir1(pa_estado_civil);
        agregarComponente(div_estado_civil);
        
        
        
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

    public Tabla getTab_estado_civil() {
        return tab_estado_civil;
    }

    public void setTab_estado_civil(Tabla tab_estado_civil) {
        this.tab_estado_civil = tab_estado_civil;
    }
    
}
