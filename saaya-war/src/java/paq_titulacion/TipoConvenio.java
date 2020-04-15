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
public class TipoConvenio extends Pantalla {

    private Tabla tab_tipo_convenio = new Tabla();

    public TipoConvenio() {

        tab_tipo_convenio.setId("tab_tipo_convenio");
        tab_tipo_convenio.setTabla("yavirac_titu_tipo_ciclo_conven", "ide_ytitcc", 9);
        tab_tipo_convenio.setHeader("TIPO CONVENIO");
        tab_tipo_convenio.dibujar();

        PanelTabla pat_tipo_convenio = new PanelTabla();
        pat_tipo_convenio.setId("pat_tipo_convenio");
        pat_tipo_convenio.setPanelTabla(tab_tipo_convenio);

        Division div_tipo_convenio = new Division();
        div_tipo_convenio.setId("div_tipo_convenio");
        div_tipo_convenio.dividir1(pat_tipo_convenio);

        agregarComponente(div_tipo_convenio);
    }

    @Override
    public void insertar() {
        tab_tipo_convenio.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_convenio.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_convenio.eliminar();
    }

    public Tabla getTab_tipo_convenio() {
        return tab_tipo_convenio;
    }

    public void setTab_tipo_convenio(Tabla tab_tipo_convenio) {
        this.tab_tipo_convenio = tab_tipo_convenio;
    }

    
}
