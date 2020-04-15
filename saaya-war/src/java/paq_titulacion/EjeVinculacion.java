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
public class EjeVinculacion extends Pantalla{
    private Tabla tab_eje_vinculacion = new Tabla ();
    public EjeVinculacion (){
    
        tab_eje_vinculacion.setId("tab_eje_vinculacion");
        tab_eje_vinculacion.setTabla("yavirac_titu_eje_vinculacion","ide_ytiejv",1);
        tab_eje_vinculacion.setHeader("Sectores de intervención");
        tab_eje_vinculacion.dibujar();
        
        
        PanelTabla pat_line= new PanelTabla();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_eje_vinculacion); 
        
        Division div_eje_vinculacion = new Division();
        div_eje_vinculacion.setId("div_eje_vinculacion");
        div_eje_vinculacion.dividir1(pat_line);
        
        agregarComponente(div_eje_vinculacion);
        
        
        
        
        
} 

    @Override
    public void insertar() {
        tab_eje_vinculacion.insertar(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        tab_eje_vinculacion.guardar();
        guardarPantalla();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        tab_eje_vinculacion.eliminar(); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_eje_vinculacion() {
        return tab_eje_vinculacion;
    }

    public void setTab_eje_vinculacion(Tabla tab_eje_vinculacion) {
        this.tab_eje_vinculacion = tab_eje_vinculacion;
    }
    
    

    
}
