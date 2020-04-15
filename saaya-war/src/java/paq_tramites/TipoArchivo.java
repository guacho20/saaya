/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_tramites;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Personal
 */
public class TipoArchivo extends Pantalla{ //Desarrollo de la pantalla archivo
    
    private Tabla tab_tipoarchivo = new Tabla();
    
    public TipoArchivo (){
        tab_tipoarchivo.setId("tab_tipoarchivo");
        tab_tipoarchivo.setTabla("yavirac_tra_tipo_archvio", "ide_ytrtia", 1);
        tab_tipoarchivo.setHeader("Registro Tipo de Archivo");
        tab_tipoarchivo.dibujar();
        
        PanelTabla pat_tipoarchivo = new PanelTabla();
        pat_tipoarchivo.setId("pat_tipoarchivo");
        pat_tipoarchivo.setPanelTabla(tab_tipoarchivo);
        
        Division div_tipoarchivo = new Division();
        div_tipoarchivo.setId("div_tipoentidad");
        div_tipoarchivo.dividir1(pat_tipoarchivo);
        
        
        agregarComponente(div_tipoarchivo);  
}

    @Override
    public void insertar() {
        tab_tipoarchivo.insertar();
    }

    @Override
    public void guardar() {
        tab_tipoarchivo.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipoarchivo.eliminar();
    }

    public Tabla getTab_tipoarchivo() {
        return tab_tipoarchivo;
    }

    public void setTab_tipoarchivo(Tabla tab_tipoarchivo) {
        this.tab_tipoarchivo = tab_tipoarchivo;
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
