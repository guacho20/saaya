/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHONPRODUCER
 */
public class EstadoNota extends Pantalla {

    private Tabla tab_estado = new Tabla();

    public EstadoNota() {

        tab_estado.setId("tab_estado");
        tab_estado.setTabla("yavirac_nota_estado_nota", "ide_ynoest", 1);
        tab_estado.getColumna("ide_ynoest").setNombreVisual("CODIGO");
        tab_estado.getColumna("detalle_ynoest").setNombreVisual("DETALLE");
        tab_estado.dibujar();
        
        PanelTabla pa_estado = new PanelTabla();
        pa_estado.setId("pa_estado");
        pa_estado.setPanelTabla(tab_estado);
        
        Division div_estado = new Division();
        div_estado.setId("div_estado");
        div_estado.dividir1(pa_estado);
        
        agregarComponente(div_estado);
    }

    @Override
    public void insertar() {
        tab_estado.insertar();
    }

    @Override
    public void guardar() {
        tab_estado.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_estado.eliminar();
    }

    public Tabla getTab_estado() {
        return tab_estado;
    }

    public void setTab_estado(Tabla tab_estado) {
        this.tab_estado = tab_estado;
    }

}
