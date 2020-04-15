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
public class IntervaloTiempo extends Pantalla{
   private Tabla tab_intervalo_tiempo = new Tabla();
    
    public IntervaloTiempo(){
        
           tab_intervalo_tiempo.setId("tab_intervalo_tiempo");
           tab_intervalo_tiempo.setTabla("yavirac_titu_carrera_afines", "ide_yticaa",10);
           tab_intervalo_tiempo.setHeader("INTERVALO DE TIEMPO");
           tab_intervalo_tiempo.getColumna(" ide_yticaa "). setNombreVisual("CÓDIGO DE CARRERA");
           tab_intervalo_tiempo.getColumna(" ide_ytiemp "). setNombreVisual("CÓDIGO DE EMPRESA");
           tab_intervalo_tiempo.getColumna(" observacion_yticaa "). setNombreVisual("OBSERVACIÓN");
           tab_intervalo_tiempo.getColumna(" activo_yticaa "). setNombreVisual("ACTIVO");
           
           
        
            tab_intervalo_tiempo.dibujar(); 
            
            
            PanelTabla pat_intervalo_tiempo = new PanelTabla();
            pat_intervalo_tiempo.setId("pat_intervalo_tiempo");
            pat_intervalo_tiempo.setPanelTabla(tab_intervalo_tiempo);
            
            
             Division div_intervalo_tiempo = new Division();
            div_intervalo_tiempo.setId("div_intervalo_tiempo");
            div_intervalo_tiempo.dividir1(pat_intervalo_tiempo);
            
            agregarComponente(div_intervalo_tiempo);
}
    @Override
    public void insertar() {
      tab_intervalo_tiempo.insertar();
    }

    @Override
    public void guardar() {
       tab_intervalo_tiempo.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_intervalo_tiempo.eliminar();
    }

    public Tabla getTab_intervalo_tiempo() {
        return tab_intervalo_tiempo;
    }

    public void setTab_intervalo_tiempo(Tabla tab_intervalo_tiempo) {
        this.tab_intervalo_tiempo = tab_intervalo_tiempo;
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
