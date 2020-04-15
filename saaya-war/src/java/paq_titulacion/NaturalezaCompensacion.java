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
public class NaturalezaCompensacion extends Pantalla{
   private Tabla tab_naturaleza_compensacion = new Tabla();
    
    public NaturalezaCompensacion(){
        
           tab_naturaleza_compensacion.setId("tab_naturaleza_compensacion");
           tab_naturaleza_compensacion.setTabla("yavirac_titu_carrera_afines", "ide_yticaa",11);
           tab_naturaleza_compensacion.setHeader("NATURALEZA DE COMPENSACIÓN");
           tab_naturaleza_compensacion.getColumna("ide_yticaa").setNombreVisual(" CÓDIGO DE CARRERA");
           tab_naturaleza_compensacion.getColumna("ide_ytiemp").setNombreVisual(" CÓDIGO DE EMPRESA");
           tab_naturaleza_compensacion.getColumna("observacion_yticaa").setNombreVisual(" OBSERVACIÓN ");
           tab_naturaleza_compensacion.getColumna("activo_yticaa").setNombreVisual(" ACTIVO ");
        
           tab_naturaleza_compensacion.dibujar(); 
            
            
            PanelTabla pat_naturaleza_compensacion = new PanelTabla();
            pat_naturaleza_compensacion.setId("pat_naturaleza_compensacion");
            pat_naturaleza_compensacion.setPanelTabla(tab_naturaleza_compensacion);
            
            
             Division div_naturaleza_compensacion = new Division();
            div_naturaleza_compensacion.setId("div_naturaleza_compensacion");
            div_naturaleza_compensacion.dividir1(pat_naturaleza_compensacion);
            
            agregarComponente(div_naturaleza_compensacion);
    }
    @Override
    public void insertar() {
      tab_naturaleza_compensacion.insertar();
    }

    @Override
    public void guardar() {
       tab_naturaleza_compensacion.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_naturaleza_compensacion.eliminar();
    }

    public Tabla getTab_naturaleza_compensacion() {
        return tab_naturaleza_compensacion;
    }

    public void setTab_naturaleza_compensacion(Tabla tab_naturaleza_compensacion) {
        this.tab_naturaleza_compensacion = tab_naturaleza_compensacion;
    }

    public Utilitario getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(Utilitario utilitario) {
        this.utilitario = utilitario;
    }

    public Barra getBar_botones() {
        return bar_botones;
    }

    public void setBar_botones(Barra bar_botones) {
        this.bar_botones = bar_botones;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
    }
}