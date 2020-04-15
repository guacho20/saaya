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
 * @author Martha
 */
public class CicloConvenio extends Pantalla {

    private Tabla tab_ciclo_convenio = new Tabla();

    public CicloConvenio() {

        tab_ciclo_convenio.setId("tab_ciclo_convenio");
        tab_ciclo_convenio.setTabla("yavirac_titu_ciclo_convenio", "ide_yticic", 1);
        tab_ciclo_convenio.setHeader("CICLO DE CONVENIO");
        tab_ciclo_convenio.getColumna("ide_yticic").setNombreVisual("CÓDIGO CONVENIO");
        tab_ciclo_convenio.getColumna("ide_ytitcc").setNombreVisual("CÓDIGO CICLO CONVENIO");
        tab_ciclo_convenio.getColumna("ide__ytifod ").setNombreVisual("CÓDIGO DE FORMACÓN DUAL");
        tab_ciclo_convenio.getColumna("detalle_yticic ").setNombreVisual("DETALLE");
        
        tab_ciclo_convenio.dibujar();

        PanelTabla pa_ciclo_convenio = new PanelTabla();
        pa_ciclo_convenio.setId("pa_ciclo_convenio");
        pa_ciclo_convenio.setPanelTabla(tab_ciclo_convenio);

      
        Division div_ciclo_convenio = new Division();
        div_ciclo_convenio.setId("div_ciclo_convenio");
        div_ciclo_convenio.dividir1(tab_ciclo_convenio);

        agregarComponente(div_ciclo_convenio);
    }

    @Override
    public void insertar() {
        tab_ciclo_convenio.insertar();
    }

    @Override
    public void guardar() {
        tab_ciclo_convenio.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_ciclo_convenio.eliminar();
    }

    public Tabla getTab_ciclo_convenio() {
        return tab_ciclo_convenio;
    }

    public void setTab_ciclo_convenio(Tabla tab_ciclo_convenio) {
        this.tab_ciclo_convenio = tab_ciclo_convenio;
    }

    
}
