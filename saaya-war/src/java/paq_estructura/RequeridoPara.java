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
 * @author User
 */
public class RequeridoPara extends Pantalla{
private Tabla tab_tabla = new Tabla();
public RequeridoPara(){
    tab_tabla.setId("tab_tabla");
    tab_tabla.setTabla("yavirac_stror_requerido_para", "ide_ystrep", 1);
    tab_tabla.getColumna("ide_ystrep").setNombreVisual("CODIGO");
    tab_tabla.getColumna("abreviatura_ystrep").setNombreVisual("ABREVIATURA");
    tab_tabla.getColumna("descripcion_ystrep").setNombreVisual("DESCRIPCION");
    tab_tabla.dibujar();
    
    PanelTabla pat_panel = new PanelTabla();
    pat_panel.setPanelTabla(tab_tabla);
    
    Division div_dividir = new Division();
    div_dividir.dividir1(pat_panel);
    
    agregarComponente(div_dividir);
}

    @Override
    public void insertar() {
        
        tab_tabla.insertar();
    }

    @Override
    public void guardar() {
        tab_tabla.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tabla.eliminar();
    }

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }
    
}
