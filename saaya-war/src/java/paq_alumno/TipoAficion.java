/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Kelly Sanaylan
 */
public class TipoAficion extends Pantalla{
    
    Tabla tab_tipo_aficio = new Tabla();
    
    
    public TipoAficion(){
    
        tab_tipo_aficio.setId("tab_tipo_aficio");
        tab_tipo_aficio.setTabla("yavirac_alum_tipo_aficion", "ide_yaltia", 1);
        tab_tipo_aficio.dibujar();
        
        PanelTabla pat_tipo_aficion = new PanelTabla();
        pat_tipo_aficion.setId("pat_tipo_aficion");
        pat_tipo_aficion.setPanelTabla(tab_tipo_aficio);
        
        Division div_tipo_aficion = new Division();
        div_tipo_aficion.setId("div_tipo_aficion");
        div_tipo_aficion.dividir1(pat_tipo_aficion);
        
        agregarComponente(div_tipo_aficion);
    }

    @Override
    public void insertar() {
        tab_tipo_aficio.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_aficio.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_aficio.eliminar();
    }

    public Tabla getTab_tipo_aficio() {
        return tab_tipo_aficio;
    }

    public void setTab_tipo_aficio(Tabla tab_tipo_aficio) {
        this.tab_tipo_aficio = tab_tipo_aficio;
    }
    
}
