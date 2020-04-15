/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_titulacion.ejb.ServicioTitulacion;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ANDRES
 */
public class TituCampo extends Pantalla {

    private Tabla tab_titu_campo = new Tabla();
    private Tabla tab_tabla = new Tabla();
    @EJB

    private final ServicioTitulacion ser_titulacion = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);

    public TituCampo() {
        tab_titu_campo.setId("tab_titu_campo");
        tab_titu_campo.setTabla("YAVIRAC_TITU_CAMPO", "IDE_YTICAM", 1);
        tab_titu_campo.setHeader("titu campo");
        tab_titu_campo.getColumna("IDE_YTICAM").setNombreVisual("CODIGO");
        tab_titu_campo.getColumna("detalle_yticam").setNombreVisual("DETALLE");
        tab_titu_campo.agregarRelacion(tab_tabla);
        tab_titu_campo.dibujar();

        PanelTabla pat_titu_campo = new PanelTabla();
        pat_titu_campo.setId("pat_titu_campo");
        pat_titu_campo.setPanelTabla(tab_titu_campo);

        tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("yavirac_titu_campo_linea", "ide_yticali", 2);
        tab_tabla.getColumna("ide_ytilii").setCombo(ser_titulacion.getLineaSupervision());
        tab_tabla.dibujar();

        PanelTabla pat_titu = new PanelTabla();
        pat_titu.setId("pat_titu");
        pat_titu.setPanelTabla(tab_tabla);

        Division div_titu = new Division();
        div_titu.setId("div_titu");
        div_titu.dividir2(pat_titu_campo, pat_titu, "50%", "H");

        agregarComponente(div_titu);

    }

    @Override
    public void insertar() {
        if (tab_titu_campo.isFocus()) {
            tab_titu_campo.insertar();
        } else if (tab_tabla.isFocus()) {
            tab_tabla.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_titu_campo.guardar()) {
            if (tab_tabla.guardar()) {
                guardarPantalla();
            }
        }
    }

    @Override
    public void eliminar() {
        if (tab_titu_campo.isFocus()) {
            tab_titu_campo.eliminar();
        } else if (tab_tabla.isFocus()) {
            tab_tabla.eliminar();
        }

    }

    public Tabla getTab_titu_campo() {
        return tab_titu_campo;
    }

    public void setTab_titu_campo(Tabla tab_titu_campo) {
        this.tab_titu_campo = tab_titu_campo;
    }

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }

}
