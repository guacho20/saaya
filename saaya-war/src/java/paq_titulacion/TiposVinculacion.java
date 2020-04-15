/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Martha
 */
public class TiposVinculacion extends Pantalla{
    private Tabla tab_tipos_vinculacion = new Tabla();
   
    
    public TiposVinculacion(){
        
            tab_tipos_vinculacion.setId("tab_tipos_vinculacion");
            tab_tipos_vinculacion.setTabla("YAVIRAC_TITU_TIPOS_VINCULACION", "ide_ytitiv ", 1);
            tab_tipos_vinculacion.setHeader("TIPOS VINCULACIÓN");
            tab_tipos_vinculacion.dibujar();
            
            
            PanelTabla pat_tipos_vinculacion = new PanelTabla();
            pat_tipos_vinculacion.setId("pat_tipos_vinculacion");
            pat_tipos_vinculacion.setPanelTabla(tab_tipos_vinculacion);
            
            
             Division div_tipos_vinculacion = new Division();
            div_tipos_vinculacion.setId("div_tipos_vinculacion");
            div_tipos_vinculacion.dividir1(pat_tipos_vinculacion);
            
            agregarComponente(div_tipos_vinculacion);
}
     @Override
    public void insertar() {
      tab_tipos_vinculacion.insertar();
    }

    @Override
    public void guardar() {
       tab_tipos_vinculacion.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipos_vinculacion.eliminar();
    }

    public Tabla getTab_tipos_vinculacion() {
        return tab_tipos_vinculacion;
    }

    public void setTab_tipos_vinculacion(Tabla tab_tipos_vinculacion) {
        this.tab_tipos_vinculacion = tab_tipos_vinculacion;
    }


}