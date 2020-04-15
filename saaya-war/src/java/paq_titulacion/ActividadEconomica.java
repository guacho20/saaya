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
public class ActividadEconomica extends Pantalla{
     private Tabla tab_actividad_economica = new Tabla();
     
     public ActividadEconomica(){
         
         tab_actividad_economica.setId("tab_actividad_economica");
         tab_actividad_economica.setTabla("yavirac_titu_actividad_economic", "ide_ytiace", 1);
         tab_actividad_economica.setHeader("ACTIVIDAD ECONOMICA");
         tab_actividad_economica.getColumna("ide_ytiace").setNombreVisual("CÓDIGO");
         tab_actividad_economica.getColumna("descripcion_ytiace").setNombreVisual("DESCRIPCIÓN");
         tab_actividad_economica.dibujar();
         
         
         PanelTabla pat_actividad_economica = new PanelTabla();
            pat_actividad_economica.setId("pat_tipo_entidad");
            pat_actividad_economica.setPanelTabla(tab_actividad_economica);
            
            
            Division div_actividad_economica = new Division();
            div_actividad_economica.setId("div_actividad_economica");
            div_actividad_economica.dividir1(pat_actividad_economica);
            
            agregarComponente(div_actividad_economica);
     }
    @Override
    public void insertar() {
      tab_actividad_economica.insertar();
    }

    @Override
    public void guardar() {
       tab_actividad_economica.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_actividad_economica.eliminar();
    }

    public Tabla getTab_actividad_economica() {
        return tab_actividad_economica;
    }

        public void setTab_actividad_economica(Tabla tab_actividad_economica) {
        this.tab_actividad_economica = tab_actividad_economica;
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
