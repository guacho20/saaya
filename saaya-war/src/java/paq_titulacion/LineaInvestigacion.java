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
public class LineaInvestigacion extends Pantalla{
    private Tabla tab_linea_investigacion = new Tabla();
    public LineaInvestigacion (){
        tab_linea_investigacion.setId("tab_linea_investigacion");
        tab_linea_investigacion.setTabla("yavirac_titu_linea_investigacio","ide_ytilii",1);
        tab_linea_investigacion.setHeader("linea investigacion");
        tab_linea_investigacion.getColumna("ide_ytilii").setNombreVisual("CODIGO");
        tab_linea_investigacion.getColumna("detalle_ytilii").setNombreVisual("DETALLE");
        tab_linea_investigacion.dibujar ();
        
        PanelTabla pat_line= new PanelTabla();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_linea_investigacion);
        
        Division div_linea= new Division();
        div_linea.setId("div_linea");
        div_linea.dividir1(pat_line);
               
         agregarComponente(div_linea);
     }

    @Override
    public void insertar() {
        tab_linea_investigacion.insertar();
    }
    
 @Override
    public void guardar() {
        tab_linea_investigacion.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_linea_investigacion.eliminar();
    }

    public Tabla getTab_linea_investigacion() {
        return tab_linea_investigacion;
    }

    public void setTab_linea_investigacion(Tabla tab_linea_investigacion) {
        this.tab_linea_investigacion = tab_linea_investigacion;  
                
                }

 

}

  
    
    
    
    
    
    

