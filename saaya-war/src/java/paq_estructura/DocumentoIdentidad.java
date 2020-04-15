/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author PC9
 */
public class DocumentoIdentidad extends Pantalla{
    Tabla tab_documento_identidad = new Tabla();
    public DocumentoIdentidad(){
        tab_documento_identidad.setId ("tab_documento_identidad");
        tab_documento_identidad.setTabla ("yavirac_stror_docu_identidad","ide_ystdoi",1);
        tab_documento_identidad.dibujar();
        
        PanelTabla pa_documento_identidad =new PanelTabla ();
       pa_documento_identidad.setId ("pa_documento_identidad");
        pa_documento_identidad.setPanelTabla (tab_documento_identidad);
        
        Division div_documento_identidad = new Division();
        div_documento_identidad.setId("div_documento_identidad");
        div_documento_identidad.dividir1(pa_documento_identidad);
        
        agregarComponente(pa_documento_identidad);
    }

    @Override
    public void insertar() {
        tab_documento_identidad.insertar();
    }

    @Override
    public void guardar() {
        tab_documento_identidad.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_documento_identidad.eliminar();
    }

    public Tabla getTab_documento_identidad() {
        return tab_documento_identidad;
    }

    public void setTab_documento_identidad(Tabla tab_documento_identidad) {
        this.tab_documento_identidad = tab_documento_identidad;
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
