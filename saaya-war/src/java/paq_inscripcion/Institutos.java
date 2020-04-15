/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;
   
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *    
 * @author CRISTIAN VEGA
 */
public class Institutos extends Pantalla{
    
    private Tabla tab_instituto=new Tabla();
    public Institutos (){
        tab_instituto.setId("tab_instituto");
        tab_instituto.setTabla("yavirac_ins_instituto", "ide_yinsin", 0);
        tab_instituto.getColumna("ide_yinsin").setNombreVisual("CÓDIGO");
        tab_instituto.getColumna("nombre_yinsin").setNombreVisual("NOMBRE INSTITUTO");
        tab_instituto.getColumna("codigo_instit_yinsin").setNombreVisual("CÓDIGO INSTITUTO");
        tab_instituto.getColumna("abreviatura_yinsin").setNombreVisual("ABREVIATURA INSTITUTO");
        tab_instituto.dibujar();
        
        PanelTabla pat_institutos=new PanelTabla();
        pat_institutos.setId("pat_institutos");
        pat_institutos.setPanelTabla(tab_instituto);    
        
        Division div_institutos=new Division();
        div_institutos.setId("div_institutos");
        div_institutos.dividir1(pat_institutos);
        agregarComponente(div_institutos);
        
   }

    @Override
    public void insertar() {
        tab_instituto.insertar();
    }

    @Override
    public void guardar() {
        tab_instituto.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
      tab_instituto.eliminar();
    }
    public Tabla getTab_instituto() {
        return tab_instituto;
    }

    public void setTab_instituto(Tabla tab_instituto) {
        this.tab_instituto = tab_instituto;
    }
    
}
