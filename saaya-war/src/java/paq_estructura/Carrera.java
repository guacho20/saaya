/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.VisualizarPDF;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author USER
 */
public class Carrera extends Pantalla {

    Tabla tab_carrera = new Tabla();
    Tabla tab_mension = new Tabla();
    Tabla tab_malla = new Tabla();
    private SeleccionTabla sel_mesion = new SeleccionTabla();
    private VisualizarPDF vipdf_malla = new VisualizarPDF();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public Carrera() {
        tab_carrera.setId("tab_carrera");
        tab_carrera.setTabla("yavirac_stror_carrera", "ide_ystcrr", 1);
        tab_carrera.setHeader("REGISTRO DE CARRERAS");
        tab_carrera.agregarRelacion(tab_mension);
        tab_carrera.getColumna("ide_ystcrr").setNombreVisual("CÓDIGO");
        tab_carrera.getColumna("fechaaprob_ystcrr").setNombreVisual("FECHA APROBACIÓN");
        tab_carrera.getColumna("descripcion_ystcrr").setNombreVisual("DESCRICPIÓN");
        tab_carrera.getColumna("resolucion_ystcrr").setNombreVisual("RESOLUCIÓN");
        tab_carrera.setRows(3);
        tab_carrera.dibujar();

        PanelTabla pat_carrera = new PanelTabla();
        pat_carrera.setId("pat_carrera");
        pat_carrera.setPanelTabla(tab_carrera);

        tab_mension.setId("tab_mension");
        tab_mension.setTabla("yavirac_stror_mension", "ide_ystmen", 2);
        tab_mension.getColumna("ide_ysttfe").setCombo(ser_estructura.getTipoFormacionEducativa("true"));
        tab_mension.setHeader("REGISTRO DE MENSIONES");
        tab_mension.agregarRelacion(tab_malla);
        tab_mension.getColumna("ide_ystmen").setNombreVisual("CÓDIGO");
        tab_mension.getColumna("ide_ysttfe").setNombreVisual("TIPO FORMACIÓN EDUCATIVA");
        tab_mension.getColumna("fechaapro_ystmen").setNombreVisual("FECHA APROBACIÓN");
        tab_mension.getColumna("descripcion_ystmen").setNombreVisual("DESCRIPCIÓN");
        tab_mension.getColumna("resolucion_ystmen").setNombreVisual("RESOLUCIÓN");
        tab_mension.getColumna("total_credito_ystmen").setVisible(false);
        tab_mension.getColumna("codigo_mension_ystmen").setVisible(false);
        tab_mension.getColumna("yavirac_stror_periodo_academic").setVisible(false);
        tab_mension.getColumna("yavirac_stror_periodo_mension").setVisible(false);
        tab_mension.setRows(2);
        tab_mension.dibujar();

        PanelTabla pat_mension = new PanelTabla();
        pat_mension.setId("pat_mension");
        pat_mension.setPanelTabla(tab_mension);

        tab_malla.setId("tab_malla");
        tab_malla.setTabla("yavirac_stror_malla", "ide_ystmal", 3);
        tab_malla.getColumna("ide_ystnie").setCombo(ser_estructura.getNivelEducacion());
        tab_malla.getColumna("ide_ystnie").setFiltro(true);
        tab_malla.getColumna("ide_ystmat").setCombo(ser_estructura.getMaterias());
        tab_malla.getColumna("ide_ysttif").setCombo(ser_estructura.getTipoFormacion());
        tab_malla.setHeader("MALLA ACADEMICA");
        tab_malla.getColumna("ide_ystmal").setNombreVisual("CÓDIGO");
        tab_malla.getColumna("ide_ystnie").setNombreVisual("NIVEL EDUCACIÓN");
        tab_malla.getColumna("ide_ystnie").setAncho(-1);
        tab_malla.getColumna("ide_ystnie").setLongitud(-1);
        tab_malla.getColumna("ide_ystmat").setNombreVisual("MATERIAS");
        tab_malla.getColumna("ide_ystmat").setAutoCompletar();
        tab_malla.getColumna("ide_ysttif").setNombreVisual("TIPO FORMACIÓN");
        //tab_malla.getColumna("ide_ysttif").setEstilo("width: 100px;");
        tab_malla.getColumna("codigo_ystmal").setNombreVisual("CÓDIGO MALLA");
        tab_malla.getColumna("aplica_requisitos_ystmal").setNombreVisual("APLICA REQUISITOS");
        tab_malla.getColumna("prerrequisito_ystmal").setNombreVisual("PRERREQUISITO");
        tab_malla.getColumna("correquisito_ystmal").setNombreVisual("CORREQUISITO");
        tab_malla.getColumna("numero_horas_ystmal").setNombreVisual("NÚMERO HORAS");
        tab_malla.getColumna("maximo_horas_ystmal").setNombreVisual("MAXIMO HORAS");
        tab_malla.getColumna("minimo_horas_ystmal").setNombreVisual("MINIMO HORAS");

        tab_malla.getColumna("codigo_ystmal").setVisible(false);
        tab_malla.getColumna("orden_ystmal").setVisible(false);
        tab_malla.getColumna("prioridad_materia_ystmal").setVisible(false);
        tab_malla.getColumna("aplica_requisitos_ystmal").setVisible(false);
        tab_malla.getColumna("numero_credito_ystmal").setVisible(false);

        tab_malla.getColumna("ide_ystmal").setOrden(0);
        tab_malla.getColumna("ide_ystnie").setOrden(1);
        tab_malla.getColumna("ide_ystmat").setOrden(2);
        tab_malla.getColumna("ide_ysttif").setOrden(3);
        tab_malla.getColumna("numero_horas_ystmal").setOrden(4);
        tab_malla.getColumna("maximo_horas_ystmal").setOrden(5);
        tab_malla.getColumna("minimo_horas_ystmal").setOrden(6);
        tab_malla.getColumna("prerrequisito_ystmal").setOrden(7);
        tab_malla.getColumna("correquisito_ystmal").setOrden(8);
        tab_malla.getColumna("aplica_calculo_ystmal").setOrden(9);
        tab_malla.setRows(6);
        tab_malla.dibujar();

        PanelTabla pat_malla = new PanelTabla();
        pat_malla.setPanelTabla(tab_malla);

        Division div_carrera = new Division();
        div_carrera.setId("div_carrera");
        div_carrera.dividir3(pat_carrera, pat_mension, pat_malla, "20%", "60%", "H");

        agregarComponente(div_carrera);

        Boton bot_malla = new Boton();
        bot_malla.setValue("Imprimir Malla");
        bot_malla.setIcon("ui-icon-print");
        bot_malla.setMetodo("abrirReporte");
        bar_botones.agregarBoton(bot_malla);

        sel_mesion.setId("sel_mesion");
        sel_mesion.setTitle("SELECION DE MALLAS");
        sel_mesion.setSeleccionTabla(ser_estructura.getMensionFormacion(), "ide_ystmen");
        sel_mesion.getTab_seleccion().getColumna("descripcion_ystmen").setFiltro(true);
        tab_mension.getColumna("anexo_ystmen").setVisible(false);
        sel_mesion.getTab_seleccion().setRows(12);
        sel_mesion.getBot_aceptar().setMetodo("aceptarReporte");
        agregarComponente(sel_mesion);

        vipdf_malla.setId("vipdf_malla");
        vipdf_malla.setTitle("MALLA ACADEMICA");
        agregarComponente(vipdf_malla);

    }

    public void aceptarReporte() {
        if (sel_mesion.getSeleccionados() != "") {
            String mension = sel_mesion.getSeleccionados();
            Map map_parametros = new HashMap();
            map_parametros.put("ide_ystmen", mension);
            vipdf_malla.setVisualizarPDF("rep_estructura/rep_malla_academica.jasper", map_parametros);
            vipdf_malla.dibujar();
            utilitario.addUpdate("vipdf_malla");
            sel_mesion.cerrar();
        } else {
            utilitario.agregarMensajeInfo("ADEVRTENCIA,", "Seleccione al menos una opción");
        }
    }

    public void abrirReporte() {
        sel_mesion.dibujar();
    }

    @Override
    public void insertar() {
        if (tab_carrera.isFocus()) {
            tab_carrera.insertar();
        } else if (tab_mension.isFocus()) {
            tab_mension.insertar();
        } else if (tab_malla.isFocus()) {
            tab_malla.insertar();
        }
    }

    @Override
    public void guardar() {

        if (tab_carrera.isFocus()) {
            tab_carrera.guardar();
        } else if (tab_mension.isFocus()) {
            tab_mension.guardar();
        } else if (tab_malla.isFocus()) {
            tab_malla.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if (tab_carrera.isFocus()) {
            tab_carrera.eliminar();
        } else if (tab_mension.isFocus()) {
            tab_mension.eliminar();
        } else if (tab_malla.isFocus()) {
            tab_malla.eliminar();
        }
    }

    public Tabla getTab_carrera() {
        return tab_carrera;
    }

    public void setTab_carrera(Tabla tab_carrera) {
        this.tab_carrera = tab_carrera;
    }

    public Tabla getTab_mension() {
        return tab_mension;
    }

    public void setTab_mension(Tabla tab_mension) {
        this.tab_mension = tab_mension;
    }

    public Tabla getTab_malla() {
        return tab_malla;
    }

    public void setTab_malla(Tabla tab_malla) {
        this.tab_malla = tab_malla;
    }

    public SeleccionTabla getSel_mesion() {
        return sel_mesion;
    }

    public void setSel_mesion(SeleccionTabla sel_mesion) {
        this.sel_mesion = sel_mesion;
    }

    public VisualizarPDF getVipdf_malla() {
        return vipdf_malla;
    }

    public void setVipdf_malla(VisualizarPDF vipdf_malla) {
        this.vipdf_malla = vipdf_malla;
    }

}
