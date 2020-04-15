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
 * @author User
 */
public class TipoEntidad extends Pantalla{
    
    private Tabla tab_tipoentidad = new Tabla();
    
    public TipoEntidad (){ //Desarrollo de la pantalla tipo entidad
        tab_tipoentidad.setId("tab_tipoentidad");
        tab_tipoentidad.setTabla("yavirac_tra_tipo_entidad", "ide_ytrtie", 1);
        tab_tipoentidad.setHeader("Registro Tipo de Entidad");
        tab_tipoentidad.getColumna("ide_ytrtie").setNombreVisual("CODIGO");
        tab_tipoentidad.getColumna("nombre_ytrtie").setNombreVisual("NOMBRE");
        tab_tipoentidad.getColumna("codigo_abreviatura_ytrtie").setNombreVisual("CODIGO ABREVIATURA");
        tab_tipoentidad.dibujar();
        
        PanelTabla pat_tipoentidad = new PanelTabla();
        pat_tipoentidad.setId("pat_tipoentidad");
        pat_tipoentidad.setPanelTabla(tab_tipoentidad);
        
        Division div_tipoentidad = new Division();
        div_tipoentidad.setId("div_tipoentidad");
        div_tipoentidad.dividir1(pat_tipoentidad);
        
        agregarComponente(div_tipoentidad);    
}

    @Override
    public void insertar() {
        tab_tipoentidad.insertar();
    }

    @Override
    public void guardar() {
        tab_tipoentidad.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipoentidad.eliminar();
    }

    public Tabla getTab_tipoentidad() {
        return tab_tipoentidad;
    }

    public void setTab_tipoentidad(Tabla tab_tipoentidad) {
        this.tab_tipoentidad = tab_tipoentidad;
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
