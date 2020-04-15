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
public class TipoEntidad extends Pantalla {

    private Tabla tab_tipo_entidad = new Tabla();

    public TipoEntidad() {

        tab_tipo_entidad.setId("tab_tipo_entidad");
        tab_tipo_entidad.setTabla("yavirac_titu_tipo_entidad", "ide_ytiten", 1);
        tab_tipo_entidad.setHeader("TIPO ENTIDAD");
        tab_tipo_entidad.dibujar();

        PanelTabla pat_tipo_entidad = new PanelTabla();
        pat_tipo_entidad.setId("pat_tipo_entidad");
        pat_tipo_entidad.setPanelTabla(tab_tipo_entidad);

        Division div_tipo_entidad = new Division();
        div_tipo_entidad.setId("div_tipo_entidad");
        div_tipo_entidad.dividir1(pat_tipo_entidad);

        agregarComponente(div_tipo_entidad);

    }

    @Override
    public void insertar() {
        tab_tipo_entidad.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_entidad.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_entidad.eliminar();
    }

    public Tabla getTab_tipo_entidad() {
        return tab_tipo_entidad;
    }

    public void setTab_tipo_entidad(Tabla tab_tipo_entidad) {
        this.tab_tipo_entidad = tab_tipo_entidad;
    }

 

}
