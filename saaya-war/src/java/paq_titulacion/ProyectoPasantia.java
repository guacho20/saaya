/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
/**
 *
 * @author usuario
 */
public class ProyectoPasantia extends Pantalla {
    
     private Tabla tab_tabla = new Tabla();
     public ProyectoPasantia() {
          
        tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("yavirac_titu_proyecto", "ide_ytipro", 1);
        tab_tabla.setRows(20);
        tab_tabla.setLectura(true);
        tab_tabla.dibujar();

        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setPanelTabla(tab_tabla);
        Division div_division = new Division();
        div_division.dividir1(pat_panel);
        agregarComponente(div_division);
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
