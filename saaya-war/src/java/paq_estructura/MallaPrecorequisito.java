/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHON
 */
public class MallaPrecorequisito extends Pantalla {

    Tabla tab_tabla1 = new Tabla();
    Tabla tab_tabla2 = new Tabla();
    private Combo com_carrera = new Combo();
    private Combo com_mension = new Combo();
    private Combo com_nivel = new Combo();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public MallaPrecorequisito() {

        com_carrera.setId("com_carrera");
        com_carrera.setCombo(ser_estructura.getCarrera());
        com_carrera.setMetodo("filtrarCarrera");
        bar_botones.agregarComponente(new Etiqueta("CARRERA:"));
        bar_botones.agregarComponente(com_carrera);

        com_mension.setId("com_mension");
        com_mension.setCombo(ser_estructura.getMensionxCarrera("-1"));
        bar_botones.agregarComponente(new Etiqueta("MESIÓN:"));
        bar_botones.agregarComponente(com_mension);

        com_nivel.setId("com_nivel");
        com_nivel.setCombo(ser_estructura.getNivelEducacion());
        bar_botones.agregarComponente(new Etiqueta("NIVEL:"));
        bar_botones.agregarComponente(com_nivel);

        Boton bot_clean = new Boton();
        bot_clean.setIcon("ui-icon-cancel");
        bot_clean.setTitle("Limpiar");
        bot_clean.setMetodo("limpiar");
        bar_botones.agregarComponente(bot_clean);

        Boton bot_buscar = new Boton();
        bot_buscar.setIcon("ui-icon-search");
        bot_buscar.setValue("Consultar");
        bot_buscar.setMetodo("consultarMalla");
        bar_botones.agregarComponente(bot_buscar);

        tab_tabla1.setId("tab_tabla1");
        tab_tabla1.setTabla("yavirac_stror_malla", "ide_ystmal", 1);
        tab_tabla1.setCondicion("ide_ystmal=-1");
        tab_tabla1.setHeader("MALLA ACADEMICA");
        tab_tabla1.setCampoOrden("orden_ystmal asc");
        tab_tabla1.getColumna("ide_ystnie").setCombo(ser_estructura.getNivelEducacion());
        tab_tabla1.getColumna("ide_ystmat").setCombo(ser_estructura.getMaterias());
        tab_tabla1.getColumna("ide_ysttif").setCombo(ser_estructura.getTipoFormacion());
        tab_tabla1.getColumna("ide_ystmal").setNombreVisual("CÓDIGO");
        tab_tabla1.getColumna("ide_ystnie").setNombreVisual("NIVEL EDUCACIÓN");
        tab_tabla1.getColumna("ide_ystnie").setAncho(-1);
        tab_tabla1.getColumna("ide_ystnie").setLongitud(-1);
        tab_tabla1.getColumna("ide_ystmat").setNombreVisual("MATERIAS");
        // tab_tabla1.getColumna("ide_ystmat").setAutoCompletar();
        tab_tabla1.getColumna("ide_ysttif").setNombreVisual("TIPO FORMACIÓN");
        tab_tabla1.getColumna("ide_ystmen").setVisible(false);

        tab_tabla1.getColumna("numero_credito_ystmal").setNombreVisual("NÚMERO HORAS");
        tab_tabla1.getColumna("orden_ystmal").setVisible(false);
        tab_tabla1.getColumna("maximo_horas_ystmal").setNombreVisual("MAXIMO HORAS");
        tab_tabla1.getColumna("minimo_horas_ystmal").setNombreVisual("MINIMO HORAS");

        tab_tabla1.getColumna("prerrequisito_ystmal").setVisible(false);
        tab_tabla1.getColumna("correquisito_ystmal").setVisible(false);
        tab_tabla1.getColumna("aplica_calculo_ystmal").setVisible(false);
        tab_tabla1.getColumna("codigo_ystmal").setVisible(false);
        tab_tabla1.getColumna("prioridad_materia_ystmal").setVisible(false);
        tab_tabla1.getColumna("aplica_requisitos_ystmal").setVisible(false);
        tab_tabla1.getColumna("numero_horas_ystmal").setVisible(false);

        tab_tabla1.getColumna("ide_ystmal").setOrden(0);
        tab_tabla1.getColumna("ide_ystnie").setOrden(1);
        tab_tabla1.getColumna("ide_ystmat").setOrden(2);
        tab_tabla1.getColumna("ide_ysttif").setOrden(3);
        tab_tabla1.getColumna("numero_credito_ystmal").setOrden(4);
        tab_tabla1.getColumna("orden_ystmal").setOrden(5);
        tab_tabla1.getColumna("maximo_horas_ystmal").setOrden(6);
        tab_tabla1.getColumna("minimo_horas_ystmal").setOrden(7);
        tab_tabla1.getColumna("prerrequisito_ystmal").setOrden(8);
        tab_tabla1.getColumna("correquisito_ystmal").setOrden(9);
        tab_tabla1.getColumna("aplica_calculo_ystmal").setOrden(10);
        tab_tabla1.setRecuperarLectura(true);
        tab_tabla1.agregarRelacion(tab_tabla2);
        tab_tabla1.dibujar();

        PanelTabla pat_malla = new PanelTabla();
        pat_malla.getMenuTabla().getItem_eliminar().setRendered(false);
        pat_malla.getMenuTabla().getItem_insertar().setRendered(false);
        pat_malla.getMenuTabla().getItem_guardar().setRendered(false);
        pat_malla.setPanelTabla(tab_tabla1);

        tab_tabla2.setId("tab_tabla2");
        tab_tabla2.setTabla("yavirac_stror_malla_precorequi", "ide_ystmap", 2);
        tab_tabla2.setHeader("PRECOREQUISITO");
        tab_tabla2.getColumna("ide_ystmap").setNombreVisual("CÓDIGO");
        tab_tabla2.getColumna("yav_ide_ystmal").setNombreVisual("MATERIA");
        tab_tabla2.getColumna("yav_ide_ystmal").setCombo(ser_estructura.getMalla());
        tab_tabla2.getColumna("yav_ide_ystmal").setRequerida(true);
        tab_tabla2.getColumna("yav_ide_ystmal").setAutoCompletar(); 
        tab_tabla2.getColumna("detalle_ystmap").setNombreVisual("DETALLE");
        tab_tabla2.getColumna("abreviatura_ystmap").setNombreVisual("ABREVIATURA");
        tab_tabla2.dibujar();

        PanelTabla pat_corequisito = new PanelTabla();
        pat_corequisito.setPanelTabla(tab_tabla2);

        Division div_division = new Division();
        div_division.setId("div_division");
        div_division.dividir2(pat_malla, pat_corequisito, "40%", "H");

        agregarComponente(div_division);
    }

    public void consultarMalla() {
        if (com_carrera.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione una carrera");
            return;
        }
        if (com_mension.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione una mensíon");
            return;
        }
        if (com_nivel.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione un nivel");
            return;
        }
        tab_tabla1.setCondicion("ide_ystmen=" + com_mension.getValue().toString() + " and ide_ystnie=" + com_nivel.getValue().toString());
        tab_tabla1.ejecutarSql();
    }

    public void filtrarCarrera() {
        com_mension.setCombo(ser_estructura.getMensionxCarrera(com_carrera.getValue().toString()));
        utilitario.addUpdate("com_mension");
    }

    public void limpiar() {
        com_mension.setCombo(ser_estructura.getMensionxCarrera("-1"));
        tab_tabla1.limpiar();
        tab_tabla2.limpiar();
        com_carrera.setValue("null");
        com_mension.setValue("null");
        com_nivel.setValue("null");
        utilitario.addUpdate("tab_tabla1,tab_tabla2,com_carrera,com_mension,com_nivel");
    }

    @Override
    public void insertar() {
        if (tab_tabla2.isFocus()) {
            if (tab_tabla1.getTotalFilas() > 0) {
                tab_tabla2.getColumna("yav_ide_ystmal").setCombo(ser_estructura.getMallaMension(com_mension.getValue().toString()));
                tab_tabla2.ejecutarSql();
                tab_tabla2.insertar();
            } else {
                utilitario.agregarMensajeInfo("No tiene registros de materias,", "Realice una nueva consulta");
                return;
            }
        }
    }

    @Override
    public void guardar() {
        tab_tabla2.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if (tab_tabla2.isFocus()) {
            tab_tabla2.eliminar();
        }
    }

    public Tabla getTab_tabla1() {
        return tab_tabla1;
    }

    public void setTab_tabla1(Tabla tab_tabla1) {
        this.tab_tabla1 = tab_tabla1;
    }

    public Tabla getTab_tabla2() {
        return tab_tabla2;
    }

    public void setTab_tabla2(Tabla tab_tabla2) {
        this.tab_tabla2 = tab_tabla2;
    }

}
