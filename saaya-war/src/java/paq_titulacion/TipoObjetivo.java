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
public class TipoObjetivo  extends Pantalla {
    
    private Tabla tab_tipo_objetivo = new Tabla ();
    public TipoObjetivo () {
        tab_tipo_objetivo. setId("tab_tipo_objetivo");
        tab_tipo_objetivo.setTabla("yavirac_titu_tipo_objetivo","ide_ytitio", 1);
        tab_tipo_objetivo.setHeader("Tipo Objetivo");
        tab_tipo_objetivo.dibujar();
        
        PanelTabla pat_line=new PanelTabla ();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_tipo_objetivo);
        
        Division div_tipo_objetivo = new Division();
        div_tipo_objetivo.setId("div_tipo_objetivo");
        div_tipo_objetivo.dividir1(pat_line);
         
        
        agregarComponente(div_tipo_objetivo);
        
        
    
}

    @Override
    public void insertar() {
        tab_tipo_objetivo.insertar();
    }

    @Override
    public void guardar() {
    tab_tipo_objetivo.guardar();
    guardarPantalla();
    
    }

    @Override
    public void eliminar() {
        tab_tipo_objetivo.eliminar();
    }

    public Tabla getTab_tipo_objetivo() {
        return tab_tipo_objetivo;
    }

    public void setTab_tipo_objetivo(Tabla tab_tipo_objetivo) {
        this.tab_tipo_objetivo = tab_tipo_objetivo;
    }
    
    
    
}
